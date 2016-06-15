package com.shangpin.web.controller;


import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.*;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.core.entity.AccountWeixinBind;
import com.shangpin.core.entity.WeiXinPacketAccount;
import com.shangpin.core.service.IWeixinBindService;
import com.shangpin.utils.*;
import com.shangpin.web.utils.ActivifyUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.PropertyUtil;
import com.shangpin.web.utils.ThreeDES;
import com.shangpin.wechat.bo.base.AccessToken;
import com.shangpin.wechat.bo.base.QRcodeCreateResult;
import com.shangpin.wechat.bo.base.UserInfo;
import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wechat.utils.openplatform.TenpayHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: WeixinController
 * @Description:微信相关
 * @author liling
 * @date 2014年12月18日
 * @version 1.0
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController extends BaseController {
	public static final Logger logger = LoggerFactory.getLogger(WeixinController.class);
	private static final String WXLOGIN = "/wxLogin";
	private static final String BINDIDOSUCESS = "/weixin/bind/success";
	private static final String WXINFO = "weixin/info";
	private static final String UNBINDSUCCESS = "weixin/unBind";
	private static final String RETURN_URL = "back";
	private static final String WEIXININDEX =  "weixin/weixinindex";
	private static final String BINDSUCCESS = "weixin/bindSuccess";
	private static final String LOGIN = "user/login";
	private static final String GIFT_BAG = "/weixin/gift_bag";
	/** 个人中心 */
	private static final String USERINFO = "user/home";

	private static final String CONPON_WEIXIN_520 = "2101910139";
	private static final String WEIXIN_INCR_520_FIX = "weixin_phone_incr_";
	private static final String WEIXIN_520_FIX = "weixin_phone_";

	@Autowired
	private IWeixinBindService weixinBindService;
	@Autowired
	private WeChatPublicService weiXinService;
	@Autowired
	private SPBizUserService userService;
	@Autowired
	private SPBizWeixinPacketService bizWeixinPacketService;
	
	@Autowired
	private SPBizSendInfoService bizSendInfoService;
	@Autowired
	private SPBizCouponService bizCouponService;

	@Autowired
	private SPBizStarPacketService starPacketService;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return WEIXININDEX;
		
	}
	@RequestMapping(value = "bind/success", method = RequestMethod.GET)
	public String bindSuccess() {
		
		return BINDSUCCESS;
		
	}
	/**
	 * 
	 * @Title: ok
	 * @Description: 判断是否已经绑定
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */

	@RequestMapping(value = "/ok", method = RequestMethod.GET)
	public String ok(HttpServletRequest request, @RequestParam("wx") String wx) {
		wx = request.getParameter("wx");
		// 先解密
		wx = ThreeDES.decryptToString(wx);
		/** 微信id */
		String weixinid = wx.split("[|]")[0];
		/** 微信用户想要访问的链接 */
		String backUrl = wx.split("[|]")[1];
		HttpSession session = request.getSession();
		AccountWeixinBind accountWeixinBind = weixinBindService.findByWXId(weixinid);
		User user = getSessionUser(request);
		if (accountWeixinBind != null) {
			if(!StringUtils.isEmpty(user)){
				 removeSessionUser(request);
			}
			user = userService.weixinAutoLogin(accountWeixinBind);
			if (!StringUtils.isEmpty(user)) {
				  // 将user放入session中
                session.setAttribute(Constants.SESSION_USER, user);
                // 将userId存入session，便于日志查看
                session.setAttribute(Constants.SESSION_USERID, user.getUserid());
				return bindOkURL(backUrl);
			}
			
		}
		session.setAttribute(Constants.WX_ID_NAME, weixinid);
		// 判断是否已经绑定
		return bindNotOkURL(backUrl);
	}

	/**
	 * 
	 * @Title: modifybind
	 * @Description: 解除绑定,更改绑定
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	@RequestMapping(value = "/bind/modify", method = RequestMethod.GET)
	public String modifybind(HttpServletRequest request, String way) {
		HttpSession session = request.getSession();
		String useragent = request.getHeader("User-Agent");
		String wxId = (String) session.getAttribute(Constants.WX_ID_NAME);
		// 判断登陆操作是否来自微信，如果是需进行绑定账号操作
		if (!ClientUtil.CheckMircro(useragent) || StringUtils.isEmpty(wxId)) {
			User user = getSessionUser(request);
			if (!StringUtils.isEmpty(user)) {
				return USERINFO ;
			}
			return LOGIN;
		}
		/** 微信id */
		// weixinid="oFHXijvkFXv7ypscJ-jl3rP3O4lY";
		AccountWeixinBind accountWeixinBind = null;
		if (Constants.WX_MODIFY_BIND_NAME.equals(way)) {
			return "redirect:" + WXLOGIN + "?way=modify";

		} else if ("remove".equals(way)) {
			try {
				accountWeixinBind = weixinBindService.findByWXId(wxId);
				if (accountWeixinBind != null) {
					accountWeixinBind.setUnbindTime(new Date());
					weixinBindService.addOrModifyAccountWeixinBind(accountWeixinBind);
				}
			} catch (Exception e) {
				logger.error("shangpin weixinmodifybind Exception:loginName={}" , accountWeixinBind.getLoginName() );
				e.printStackTrace();
			}
			removeSessionUser(request);
			return UNBINDSUCCESS;
		}
		return BINDSUCCESS;
	}

	/**
	 * 
	 * @Title: bindInfo
	 * @Description: 绑定账号信息
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	@RequestMapping(value = "/bind/info", method = RequestMethod.GET)
	public String bindInfo(HttpServletRequest request,String back) {
		HttpSession session = request.getSession();
		String useragent = request.getHeader("User-Agent");
		String wxId = (String) session.getAttribute(Constants.WX_ID_NAME);
		User user = getSessionUser(request);
		// 判断登陆操作是否来自微信，如果是需进行绑定账号操作
		if (!ClientUtil.CheckMircro(useragent) || StringUtils.isEmpty(wxId)) {
			if (!StringUtils.isEmpty(user)) {
				return USERINFO ;
			}
			return LOGIN;
		}
		if (null == user || StringUtils.isEmpty(user.getName())) {
			return bindInfoURL(back);
		}
		return WXINFO;
	}

	private String bindInfoURL(String backUrl) {
		StringBuilder url = new StringBuilder("redirect:");
		url.append(WXLOGIN);
		if (!StringUtils.isEmpty(backUrl)) {
			url.append("?");
			url.append(RETURN_URL).append("=");
			String encoderReturnURL;
			try {
				encoderReturnURL = URLEncoder.encode(backUrl, Constants.DEFAULT_ENCODE);
				url.append(encoderReturnURL);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}else{
			url.append("?");
			url.append(RETURN_URL).append("=");
			String encoderReturnURL;
			try {
				encoderReturnURL = URLEncoder.encode(BINDIDOSUCESS, Constants.DEFAULT_ENCODE);
				url.append(encoderReturnURL);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return url.toString();
	}
	/**
	 * 
	 * @Title: bindOkURL
	 * @Description: 微信绑定登陆请求并拼接back url
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	private String bindOkURL(String backUrl) {
		StringBuilder url = new StringBuilder("redirect:/");
	
		if (!StringUtils.isEmpty(backUrl)) {
				url.append(backUrl);
		}
		return url.toString();
	}
	/**
	 * 
	 * @Title: bindOkURL
	 * @Description: 微信绑定登陆请求并拼接back url
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	private String bindNotOkURL(String backUrl) {
		StringBuilder url = new StringBuilder("redirect:");

		url.append(WXLOGIN);
		if (!StringUtils.isEmpty(backUrl)) {
			url.append("?");
			url.append(RETURN_URL).append("=");
			String encoderReturnURL;
			try {
				encoderReturnURL = URLEncoder.encode(backUrl, Constants.DEFAULT_ENCODE);
				url.append(encoderReturnURL);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		return url.toString();
	}
	
	/**
	 * 微信分享
	 * @param shareUrl
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/shareUrl", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> getShareParam(String shareUrl){
		String jsapi_ticket=weiXinService.getTicket();
		Map<String,String> shareConfig=WXShareSign.sign(jsapi_ticket, shareUrl);
		return shareConfig;
	}
	
	//@RequestMapping(value = "/send/text", method = RequestMethod.GET)
	public void sendText(HttpServletRequest request, HttpServletResponse response){
		logger.debug("weixin send msg start.....................");
		//1.获取access_token的票据code
		String code = request.getParameter("code");
		logger.debug("authorized code:" + code);
		if(!StringUtils.isEmpty(code)){
			//2.通过code获取网页授权的access_token
			AccessToken token = weiXinService.getAccessTokenObj(code);
			//3.根据网页授权的access_token和openid获取用户的基本信息
			String accessToken = token.getAccess_token();
			String openId = token.getOpenid();
			logger.debug("access_token:" + accessToken + "====openId:" + openId);;
			UserInfo user = weiXinService.userInfoObj(accessToken, openId);
			bizWeixinPacketService.save(convert(user));
			//String text = "<xml><ToUserName><![CDATA["+ user.getOpenid() +"]]></ToUserName><FromUserName><![CDATA[wx4e93df954af2f52c]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好,我是第一次发送消息]]></Content></xml>";
			String text = "{\"touser\":\""+ openId + "\",\"msgtype\":\"text\",\"text\":{\"content\":\"你好,我是第一次发送消息。\"}}";
			logger.debug("send message:" + text);
			String sendToken = weiXinService.getToken();
			String data = weiXinService.sendMsg(sendToken, text);
			logger.debug("return data:" + data);
			try {
				response.getWriter().write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/send/text", method = RequestMethod.GET)
	public void sendImg(HttpServletRequest request, HttpServletResponse response){
		logger.debug("weixin send img start.....................");
		String code = request.getParameter("code");
		logger.debug("authorized code:" + code);
		if(!StringUtils.isEmpty(code)){
			AccessToken token = weiXinService.getAccessTokenObj(code);
			String accessToken = token.getAccess_token();
			String openId = token.getOpenid();
			logger.debug("access_token:" + accessToken + "====openId:" + openId);
			String baseToken = weiXinService.getToken();
			logger.debug("base token:" + baseToken);
			UserInfo user = weiXinService.baseUserObj(baseToken, openId);
			bizWeixinPacketService.save(convert(user));
			String text = "{\"touser\":\""+ openId + "\",\"msgtype\":\"text\",\"text\":{\"content\":\"你好,我是第三次发送消息。\"}}";
			logger.debug("send message:" + text);
			String data = weiXinService.sendMsg(baseToken, text);
			logger.debug("return data:" + data);
		}
	}
	
	@RequestMapping(value = "/send/image", method = RequestMethod.GET)
	public void code(HttpServletRequest request, HttpServletResponse response){
		logger.debug("qrcode create start...........................");
		String token = weiXinService.getToken();
		logger.debug("base token=========={}", token);
		String content = "{\"expire_seconds\":1800,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":100000}}}";
		String json = weiXinService.qrcodeCreate(token, content);
		QRcodeCreateResult result = JsonUtil.fromJson(json, QRcodeCreateResult.class);
		String ticket = result.getTicket();
		String qcode = weiXinService.showQRCode(ticket);
		String msg = "{\"touser\":\"oFHXijs8DAnA2OfSwOUH7rZtuv4U\",\"msgtype\":\"image\",\"agentid\":\"1\",\"image\":{\"media_id\":\""+ qcode +"\"},\"safe\":\"0\"}";
		logger.debug("send msg============={}", msg);
		String data = weiXinService.sendMsg(token, msg);
		logger.debug("return data==={}", data);
	}
	
	public WeiXinPacketAccount convert(UserInfo user){
		WeiXinPacketAccount account = new WeiXinPacketAccount();
		account.setOpenId(user.getOpenid());
		account.setNickName(user.getNickname());
		account.setSex(user.getSex());
		account.setCountry(user.getCountry());
		account.setProvince(user.getProvince());
		account.setCity(user.getCity());
		account.setHeadImgUrl(user.getHeadimgurl());
		account.setUnionid(user.getUnionid());
		account.setLanguage(user.getLanguage());
		account.setCreateTime(new Timestamp(new Date().getTime()));
		return account;
	}
	
	@RequestMapping(value = "/gift/bag", method = RequestMethod.GET)
	public String giftBag(Model model, HttpServletRequest request){
		String code = request.getParameter("code");
		AccessToken accessToken = weiXinService.getAccessTokenObj(code);
		String openId = accessToken.getOpenid();
		//String openId = "oFHXijs8DAnA2OfSwOUH7rZtuv4U";
		request.getSession().setAttribute("weixin_user_openId", openId);
		model.addAttribute("openId", openId);
		model.addAttribute("index", true);
		return GIFT_BAG;
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> check(String phoneNum, String verifycode, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		//1.验证手机验证码是否正确
		boolean verifyCodeFlag = this.checkPhoneCode(phoneNum, verifycode);
		logger.debug("verify cod flag============={}", verifyCodeFlag);
		if(!verifyCodeFlag){
			map.put("code", "1");
			map.put("msg", "手机验证码输入错误！");
			return map;
		}
		//2.验证手机号码是否注册，如果没有注册默认给用户注册一个账号
		QuickUser quickUser = userService.checkPhoneUser(phoneNum, Constants.STAR_PACKET_SOURCE);
		logger.debug("quick user info=============={}", quickUser.getUserId() + "===" + quickUser.getIsNewUser());
		String userId = quickUser.getUserId();
		String isNewUser = quickUser.getIsNewUser();
		//String openId = "oFHXijs8DAnA2OfSwOUH7rZtuv4U";
		String openId = (String)request.getSession().getAttribute("weixin_user_openId");
		logger.debug("session open id============={}", openId);
		User user = userService.findUserByUserId(userId);
		logger.debug("user info============{}", user.getUserid() + "=========" + user.getName());
		String userName = user.getName();
		User sessionUser = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		if(sessionUser == null){
			session.setAttribute(Constants.SESSION_USER, user);
		}
		//3.检查用户是否为新用户
		if(Constants.IS_NEW_USER.equals(isNewUser)){//表示是新用户，给用户下发短信通知
			String msgTemplateRe = "你已领到TOPSHOP千元礼包，已放入你的尚品网账户" + phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, 11) +"中，登录密码：手机号后六位。点击链接立刻查看";
			logger.debug("自动生成的新用户下发短信通知=====================");
			boolean registerflag = bizSendInfoService.sendInfo(phoneNum, phoneNum, msgTemplateRe);
			logger.debug("短信下发成功标识=================={}", registerflag);
		}
		//4.检查用户是否与微信绑定
		logger.debug("weinxin user is bind start=======");
		AccountWeixinBind account = weixinBindService.findByWXId(openId);
		logger.debug("weixin bind user==============={}", account);
		logger.debug("weixin user is bind===========");
		if(account == null){//表示未绑定，进行绑定
			this.bind(openId, userName, user);
		}
		String COUPON_CODE = PropertyUtil.getString("gift_bag_code");
		Map<String, Object> resultBase = this.activeObj(userId, COUPON_CODE);
		logger.debug("coupon active code flag=============={}",resultBase);
		String code = (String)resultBase.get("code");
		if("1".equals(code)){
			map.put("code", "2");
			map.put("msg", resultBase.get("msg"));
			return map;
		}else {
			map.put("code", "0");
			map.put("msg", "您的礼券包激活成功！购买商品提交订单时记得选择优惠券哦！");
			logger.debug("return result==============={}", map);
			return map;
		}
		
	}
	
	/**
	 * 验证短信发送的验证码
	 * @param mobi
	 * @param verifycode
	 * @return
	 */
	private boolean checkPhoneCode(String mobi, String verifycode) {
    	ResultBase result = userService.beVerifyPhoneCode(mobi, mobi, verifycode);
        if(!result.isSuccess()){
        	return false; 
        }
        return true;
    }
	
	/**
     * 用户与微信进行绑定
     * @param openId 微信用户openId
     * @param 
     * @param user 用户信息
     */
    private void bind(String openId, String userName, User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 绑定
        AccountWeixinBind accountWeixinBind = new AccountWeixinBind();
        accountWeixinBind.setWeixinId(openId);
        accountWeixinBind.setCreateTime(new Date());
        accountWeixinBind.setLoginName(userName);
        accountWeixinBind.setUserId(user.getUserid());
        accountWeixinBind.setNickname(user.getName());
        accountWeixinBind.setGender(user.getGender());
        try {
            accountWeixinBind.setRegTime(sdf.parse(user.getRegTime()));
            accountWeixinBind.setRegOrigin(user.getRegOrigin());
            accountWeixinBind.setBindTime(new Date());
            accountWeixinBind.setMarkup("hand");
            weixinBindService.addOrModifyAccountWeixinBind(accountWeixinBind);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    
    private Map<String, Object> activeObj(String userId, String activeCode){
    	Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:" + activeCode, "8");
    	return resultBase;
    }

	////////////////////////////////////////////微信 2016年5月16日新加/////////////////////////
	////////////////////////////////////////////微信 2016年5月16日新加/////////////////////////
	////////////////////////////////////////////微信 2016年5月16日新加/////////////////////////
	////////////////////////////////////////////微信 2016年5月16日新加/////////////////////////


	@RequestMapping(value = "/toActivify")
	public String toActivify(HttpServletRequest  request){

		if(ActivifyUtil.isRunActivify()){
			return "/weixin/520/index";
		}

		//活动结束进首页
		return "redirect:/index";
	}


//？
	@RequestMapping(value = "/toSuccess")
	public String toSuccess(HttpServletRequest  request){

		return "/weixin/520/success";
	}

	/***
	 * 根据条件判断是否发送验证码
	 *
	 * @param phoneNum
	 *            手机号
	 * @param model
	 * @param request
	 * @return map
	 */
	@ResponseBody
	@RequestMapping(value = "/isSendInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> sendInfoLogic(String phoneNum, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<>();

		Pattern p = Pattern.compile("^1\\d{10}$");
		Matcher m = p.matcher(phoneNum);

		if( !m.matches()){
			map.put("code", "1");
			map.put("msg", "手机号码格式错误");
			return map;
		}

		//记录次数
		long num= JedisUtil.getInstance().incr(WEIXIN_INCR_520_FIX + phoneNum );
		JedisUtil.getInstance().expire(WEIXIN_INCR_520_FIX + phoneNum, 3600 );


		int channel = 81;
		// 一、点击获取验证码时判断该手机号是否已经注册尚品网
		QuickUser quickUser = userService.checkUser(phoneNum, Constants.CREATE_NEW_USER, String.valueOf(channel));

		String phone = phoneNum.substring(0, 3) + "XXXX" + phoneNum.substring(7, 11);

		// 新用户下发验证码和注册信息
		if (Constants.IS_NEW_USER.equals(quickUser.getIsNewUser())) {
			String msgTemplate = "验证码:{$verifyCode$}，输入抢100元无门槛现金券！您已注册为尚品网会员，密码是手机号后6位，抢到的现金券将直接放入该账户";
			return bizSendInfoService.sendPhoneCode(phoneNum, phoneNum, msgTemplate);
		} else {
			// 二、已注册的用户点击获取验证码时需判断该手机号是否已经抢过红包
			// 获得是否抢过优惠劵。
			ResultBase rBase = bizCouponService.checkActiveCode(phoneNum, CONPON_WEIXIN_520);
			if (rBase.isSuccess()) {

				//多余5次可以获得网页验证码
				if(num > 5){
					map.put("code", "5");
					map.put("msg", "您请求验证码过于频繁!");
//					String random = getRandom(6)+"";
//					map.put("msg", "输入验证码\""+random+"\"即可");
//					JedisUtil.getInstance().set(WEIXIN_520_FIX + phoneNum, random);
//					JedisUtil.getInstance().expire(WEIXIN_520_FIX + phoneNum, 600); //10分钟过期
					return map;
				}

				String msgTemplate = "验证码:{$verifyCode$}，输入抢100元无门槛现金券！将会放入" + phone +"的账户中，登录即可查看使用";
				return bizSendInfoService.sendPhoneCode(phoneNum, phoneNum, msgTemplate);
			}else {
				map.put("code", "3");
				map.put("phone", phoneNum);
			}

		}
		return map;
	}


	/**
	 * 抽奖方法
	 *
	 * @param phoneNum
	 * @param verifyCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toCheck", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> receiveCoupons(String phoneNum, String verifyCode, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();

		if(true){
			map.put("code", "1");
			map.put("msg", "您来晚了，优惠券已被领光！");
			return map;
		}


//		String rundom = JedisUtil.getInstance().get(WEIXIN_520_FIX + phoneNum);
//
//		if (StringUtils.isEmpty(rundom)){
			ResultBase result = userService.beVerifyPhoneCode(phoneNum, phoneNum, verifyCode);
			if (result == null || Constants.FAILE.equals(result.getCode())) {
				map.put("code", "4");
				map.put("msg", "短信验证码错误");
				return map;
			}

//		} else if (!rundom.equals(verifyCode)) {
//			map.put("code", "4");
//			map.put("msg", "短信验证码错误");
//			return map;
//		}



		// 这个是判断用户是否登录
		String userId = null;
		String userName = null;
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
		if (user == null) {
			int channel = 81;
			QuickUser quickUser = userService.checkUser(phoneNum, Constants.CREATE_NEW_USER, String.valueOf(channel));
			userId = quickUser.getUserId();
			userName = quickUser.getName();
			User users = userService.findUserByUserId(userId);
			request.getSession().setAttribute(Constants.SESSION_USER, users);
		} else {
			userId = user.getUserid();
			userName = user.getName();
		}

//		//4.检查用户是否与微信绑定
//		String openId = (String)request.getSession().getAttribute("weixin_user_openId");
//		if(!StringUtil.isBlank(openId)){
//			AccountWeixinBind account = weixinBindService.findByWXId(openId);
//			if(account == null){//表示未绑定，进行绑定
//				this.bind(openId, userName, user);
//			}
//		}

		// 4用户优惠劵接口(addPacket)
		map = bizCouponService.sendCoupon(userId, "1", "coupon:" + CONPON_WEIXIN_520, "30");
		logger.info("撞衫页面.用户:" + userId + ",领取[" + CONPON_WEIXIN_520 + "],结果:" + map);
		return map;

	}



	private int getRandom(int lenth){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < lenth; i++)
			result = result * 10 + array[i];
		return result;
	}


	public static void main(String[] args) {
		String a="{\"touser\":\"oFHXijs8DAnA2OfSwOUH7rZtuv4U\",\"msgtype\":\"text\",\"text\":{\"content\":\"ssssssssss。\"}}";
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=Hfr4YneiTecZBUOMgKlrU9ChLc1sX4W5p9J0Xipf0hko0fB0UCWLbHxXGtgbJnlkqglu6MCy8gKZM0O9NrwGsb747cvPRoqXj71lXD2bXbg";
		//String text = "<xml><ToUserName><![CDATA[oFHXijs8DAnA2OfSwOUH7rZtuv4U]]></ToUserName><FromUserName><![CDATA[wx4e93df954af2f52c]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>";
	//	Map<String, String> params = new HashMap<String, String>();
//		params.put("grant_type", "client_credential");
//		params.put("appid", "wx4e93df954af2f52c");
//		params.put("secret", "2a55eede9fbd467e25e6a0eb7b17ce60");
//		params.put("text", text);
//		HttpClientUtil.doGet(url, params);
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(url);
		boolean flag = httpClient.callHttpPost(url, a);
		System.out.println("flag:" + flag);
	}
}
