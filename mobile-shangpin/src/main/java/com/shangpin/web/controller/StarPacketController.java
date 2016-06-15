package com.shangpin.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.CouponInfo;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.service.SPBizStarPacketService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.core.entity.AccountWeixinBind;
import com.shangpin.core.service.IWeixinBindService;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.PropertyUtil;
import com.shangpin.wechat.bo.base.AccessToken;
import com.shangpin.wechat.service.WeChatPublicService;


/**
 * @author qinyingchun
 * 明星发红包
 *
 */
@Controller
@RequestMapping("/star")
public class StarPacketController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(StarPacketController.class);
	
	@SuppressWarnings("unused")
    private static final String INDEX = "/star/index";//现金券领取页面
	private static final String RECEIVED = "/star/received";//现金券领完页面
	private static final String RECEIVE = "/star/receive";//已领取页面
	private static final String SHARE = "/star/friend";//分享页面
	private static final String TITLE = "/star/title";//分享页面
	@SuppressWarnings("unused")
	private static final String ERROR = "/star/titleee";//分享页面
	//为红包CEO发红包配置
	@SuppressWarnings("unused")
	private static final String CEO_INDEX = "/star/black_five/index";//现金券领取页面
    @SuppressWarnings("unused")
	private static final String CEO_RECEIVED = "/star/20151020/activity_end";//现金券领完页面
	
	@Autowired
	private SPBizStarPacketService starPacketService;
	@Autowired
    private SPBizUserService userService;
	@Autowired
	private SPBizSendInfoService bizSendInfoService;
	@Autowired
	private WeChatPublicService weChatPublicService;
	@Autowired
    private IWeixinBindService weixinBindService;
	
	@RequestMapping(value = "/packet", method = RequestMethod.GET)
	public String redirectPage(String star, String batchNo, HttpServletRequest  request){
		if(!StringUtils.isEmpty(star)){
			batchNo = PropertyUtil.getString(star);
		}
		logger.debug("batchNo============={}", batchNo);
		String code = request.getParameter("code");
		logger.debug("weixin code==========={}", code);
		AccessToken accessToken = weChatPublicService.getAccessTokenObj(code);// 网页授权获得的token和公共账号获取的token不同
		String openId = accessToken.getOpenid();
		logger.debug("weixin openId==========={}", openId);
		//检查用户是否与微信绑定
//		AccountWeixinBind account = weixinBindService.findByWXId(openId);
//		logger.info("weixin account info=============");
//		if(account != null){
//			String userId = account.getUserId();
//			User user = userService.findUserByUserId(userId);
//			logger.info("goddes index user info===================");
//			request.getSession().setAttribute(Constants.SESSION_USER, user);
//		}
		request.getSession().setAttribute("star_open_id", openId);
		return "redirect:/star/weixin/index?batchNo=" + batchNo;
	}
	
	/**
	 * 微信跳转到领取红包页面
	 * @param batchCode 红包批次号
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/weixin/index", method = RequestMethod.GET)
	public String winxinindex(String batchNo, String source, String phoneNum, String amount, Model model, HttpServletRequest request){
		//查询红包领取的历史记录
		model.addAttribute("batchNo", batchNo);
		model.addAttribute("index", true);
		model.addAttribute("source", source);
		return TITLE;
	}
	
	@RequestMapping(value = "/title", method = RequestMethod.GET)
	public String title(String star, String batchNo, String source, String phoneNum, String amount, Model model, HttpServletRequest request){
		if(!StringUtils.isEmpty(star)){
			batchNo = PropertyUtil.getString(star);
		}
		String ua = request.getHeader("User-Agent");
		if(ClientUtil.CheckMircro(ua)){
			return "redirect:/star/packet?batchNo=" + batchNo;
		}
		String referer = request.getHeader("Referer");
		if(!StringUtils.isEmpty(referer) && referer.indexOf("share/msuccess") > -1){
			return "redirect:/star/had?batchNo=" + batchNo + "&phoneNum=" + phoneNum + "&source=1&amount=" + amount;
		}
		model.addAttribute("batchNo", batchNo);
		model.addAttribute("source", source);
		return TITLE;
	}
	
	/**
	 * 跳转到领取红包页面
	 * @param batchCode 红包批次号
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(String star, String batchNo, String source, String share, String phoneNum, String amount, Model model, HttpServletRequest request){
		if(!StringUtils.isEmpty(star)){
			batchNo = PropertyUtil.getString(star);
		}
		String referer = request.getHeader("Referer");
		if(!StringUtils.isEmpty(referer) && referer.indexOf("share/msuccess") > -1){
			return "redirect:/star/had?batchNo=" + batchNo + "&phoneNum=" + phoneNum + "&source=1&amount=" + amount;
		}
		model.addAttribute("batchNo", batchNo);
		model.addAttribute("index", true);
		model.addAttribute("source", source);
		return INDEX;
	}
	*/
	
	/**
     * 跳转到领取红包页面
     * @param batchCode 红包批次号
     * @param model
     * @return
     *//*
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String star,Model model, HttpServletRequest request){
        if (star==null || !isUrl(star)) {
            return ERROR;
        }  
        String ua = request.getHeader("User-Agent");
        boolean isWeixin = ClientUtil.CheckMircro(ua);
        if(isWeixin){
            String code = request.getParameter("code");
            logger.debug("weixin code==========={}", code);
            AccessToken accessToken = weChatPublicService.getAccessTokenObj(code);// 网页授权获得的token和公共账号获取的token不同
            String openId = accessToken.getOpenid();
            logger.debug("weixin openId==========={}", openId);
            request.getSession().setAttribute("star_open_id", openId);
        }
        //判断红包是否结束
        if (!PropertyUtil.isActEndTime()) {
            return CEO_RECEIVED;
        }
        model.addAttribute("star", star);
        return CEO_INDEX;
       
    }*/
	/**
	 * 现金券领完页面
	 * @return
	 */
	@RequestMapping(value = "/out", method = RequestMethod.GET)
	public String couponOut(String batchNo, String phoneNum, String source, String amount, Model model, HttpServletRequest request){
		model.addAttribute("batchNo", batchNo);
		model.addAttribute("phoneNum", phoneNum);
		model.addAttribute("source", source);
		model.addAttribute("amount", amount);
		return RECEIVED;
	}
	
	/**
	 * 已经领取页面
	 * @return
	 */
	@RequestMapping(value = "/had", method = RequestMethod.GET)
	public String hadCoupon(String batchNo, String phoneNum, String source, Model model, HttpServletRequest request){
		List<CouponInfo> coupons = starPacketService.findByKey(batchNo, phoneNum);
		model.addAttribute("batchNo", batchNo);
		model.addAttribute("phoneNum", phoneNum);
		model.addAttribute("source", source);
		model.addAttribute("amount", coupons.get(0).getAmount().split("\\.")[0]);
		return RECEIVE;
	}
	
	/**
	 * 分享页面
	 * @return
	 */
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public String Share(String batchNo, String phoneNum, String amount, Model model, HttpServletRequest request){
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = request.getHeader(name);
			logger.debug("request header =================={}", name + ":" + value);
		}
		model.addAttribute("batchNo", batchNo);
		model.addAttribute("phoneNum", phoneNum);
		model.addAttribute("amount", amount);
		return SHARE;
	}
	
	/**
	 * 判断手机号是否领取过优惠券
	 * @param batchCode
	 * @param phoneNum
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/check/coupon/phone", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> verifyPhoneCoupon(String batchNo, String phoneNum, String verifyCode, String source, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		//万能验证码
		//if(!"666666".equals(verifyCode)){
		//1.验证短信验证码是否正确
		ResultBase result = userService.beVerifyPhoneCode(phoneNum, phoneNum, verifyCode);
		if(Constants.FAILE.equals(result.getCode())){
			map.put("code", "4");
			map.put("msg", "短信验证码错误");
			return map;
		}
		//}
		
		//判断券是否已被领取光
		String key = "TotalWeixinWalletCoupon" + batchNo;
		boolean flag = starPacketService.isCouponOut(key);
		if(flag){
			map.put("code", "3");
			map.put("msg", "券已经被领光");
			return map;
		}
		//调用biz层方法判断手机号是否领取过
		long count = starPacketService.phoneCouponCount(batchNo, phoneNum);
		if(count >= 2){//表示手机号已经领取过，并且该手机号已经不能再领取了
			List<CouponInfo> coupons = starPacketService.findByKey(batchNo, phoneNum);
			map.put("code", "1");
			map.put("msg", "该手机号已经领取过");
			map.put("couponInfo", coupons);
			User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
			if(null == user){//表示session失效
				QuickUser quickUser = userService.checkPhoneUser(phoneNum, Constants.STAR_PACKET_SOURCE);
				String userId = quickUser.getUserId();
				User user1 = userService.findUserByUserId(userId);
				request.getSession().setAttribute(Constants.SESSION_USER, user1);
			}
			return map;
		}else if(count == 1){//表示已经领取过一次，需要跳转到分享页面
			List<CouponInfo> coupons = starPacketService.findByKey(batchNo, phoneNum);
			map.put("code", "2");
			map.put("msg", "你已经领取过一次了");
			map.put("couponInfo", coupons);
			User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
			if(null == user){//表示session失效
				QuickUser quickUser = userService.checkPhoneUser(phoneNum, Constants.STAR_PACKET_SOURCE);
				String userId = quickUser.getUserId();
				User user1 = userService.findUserByUserId(userId);
				request.getSession().setAttribute(Constants.SESSION_USER, user1);
			}
			return map;
		}else {//跳转到领取页面
			map.put("code", "0");
			map.put("msg", "你还没领取过优惠券");
			map.put("couponInfo", "");
			return map;
		}
	}
	
	@RequestMapping(value = "/check/have", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> checkHave(String batchNo, String phoneNum, String verifyCode, HttpServletRequest request){
		String ua = request.getHeader("User-Agent");
		boolean isWeixin = ClientUtil.CheckMircro(ua);
		Map<String, Object> map = new HashMap<String, Object>();
		
		//2.判断手机号是否为新老用户
		QuickUser quickUser = userService.checkPhoneUser(phoneNum, Constants.STAR_PACKET_SOURCE);
		String userId = quickUser.getUserId();
		User user = userService.findUserByUserId(userId);
		String name = user.getName();
		request.getSession().setAttribute(Constants.SESSION_USER, user);
		//判断是否为新用户，如果是新用户下发注册信息通知
		if(Constants.IS_NEW_USER.equals(quickUser.getIsNewUser())){
			String msgTemplateRe = "太棒啦！你抢到了杨幂生日红包，已充值到尚品网账户" + phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, 11) +"中，登录密码：手机号后六位。点击链接立刻查看t.cn/RvweXV1";
			bizSendInfoService.sendInfo(phoneNum, phoneNum, msgTemplateRe);
		}else {
			String msgTemplateRe = "太棒啦！你抢到了杨幂生日红包，已充值到尚品网账户" + phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, 11) +"中，若您忘记密码，可打开尚品网APP进入登录页面，点击‘手机找回密码’找回。点击链接立刻查看t.cn/RvweXV1";
			bizSendInfoService.sendInfo(phoneNum, phoneNum, msgTemplateRe);
		}
		String openId = (String)request.getSession().getAttribute("star_open_id");
		if(!StringUtils.isEmpty(openId) && isWeixin){
			AccountWeixinBind account = weixinBindService.findByWXId(openId);
			if(null == account){//未绑定,需要绑定微信账号
				bind(openId, name, user);
			}
		}
		//3.获取优惠券信息，从redis中获取
		String coupon = starPacketService.randomObtainCoupon(batchNo);
		if(StringUtils.isEmpty(coupon)){
			map.put("code", "2");
			map.put("msg", "优惠券已经领光");
			return map;
		}
		//4.将手机号与券的信息关联放到redis中
		String[] coupons = coupon.split("\\|");
		String amount = coupons[0];
		String type = coupons[1];
		String activeCode = coupons[2];
		String couponCode = coupons[3];
		CouponInfo couponInfo = new CouponInfo();
		couponInfo.setAmount(amount.split("\\.")[0]);
		couponInfo.setType(type);
		couponInfo.setActiveCode(activeCode);
		couponInfo.setCouponCode(couponCode);
		starPacketService.addPhoneToCache(batchNo, phoneNum, amount, type);
		//5.绑定优惠券信息到缓存
		starPacketService.bindPhoneUser(batchNo, userId, activeCode, couponCode);
		//6.redis 券的总数减一
		String key = "TotalWeixinWalletCoupon" + batchNo;
		starPacketService.decr(key);
		//7.已领取的券的数量加一
		String incrkey = "TotalWeixinWalletHasTaked" + batchNo;
		starPacketService.incr(incrkey);
		map.put("code", "0");
		map.put("msg", "领取成功");
		map.put("couponInfo", couponInfo);
		logger.debug("return data==============={}", map.toString());
		return map;
	}
	
	/**
	 * 判断手机号是否可以领取第二个红包
	 * @param batchNo
	 * @param phoneNum
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/hava/other", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> haveOther(String batchNo, String phoneNum, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		long count = starPacketService.phoneCouponCount(batchNo, phoneNum);
		logger.debug("coupon count==============={}", count);
		//判断手机号领取的次数
		if(count == 1){//表示可以领取第二个
			//判断券是否被领完
			String key = "TotalWeixinWalletCoupon" + batchNo;
			boolean flag = starPacketService.isCouponOut(key);
			if(flag){
				map.put("code", "3");
				map.put("msg", "券已经被领光");
				return map;
			}
			User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
			if(null == user){
				map.put("code", "4");
				map.put("msg", "session 中用户失效");
				return map;
			}
			//随机获取一条券的信息
			String coupon = starPacketService.randomObtainCoupon(batchNo);
			if(StringUtils.isEmpty(coupon)){
				map.put("code", "3");
				map.put("msg", "优惠券已经领光");
				return map;
			}
			String userId = user.getUserid();
			CouponInfo couponInfo = this.getRandomCoupon(coupon, userId, batchNo, phoneNum);
			map.put("code", "0");
			map.put("msg", "第二个领取成功");
			map.put("couponInfo", couponInfo);
			return map;
		}else if (count >= 2) {//表示该手机号已经领取完全部的优惠券了
			List<CouponInfo> coupons = starPacketService.findByKey(batchNo, phoneNum);
			map.put("code", "2");
			map.put("msg", "已经领取完了");
			map.put("couponInfo", coupons);
			return map;
		}else{
			map.put("code", "5");
			map.put("msg", "其它原因");
			return map;
		}
	}
	
	/**
	 * 红包下发短信通知
	 * @param phoneNum
	 * @param msgTemplate
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/send/phone/code", method = {RequestMethod.GET, RequestMethod.POST})
    public  ResultBase sendPhoneCode(String phoneNum, String msgTemplate, HttpServletRequest request) {
		return starPacketService.sendPhoneNotic(phoneNum, phoneNum, msgTemplate);
	}
	
	/**
	 * redis获取优惠券信息之后对优惠券的操作包括往
	 * redis中添加手机号领取的记录，绑定手机号与券的信息
	 * @param coupon
	 * @param userId
	 * @param batchNo
	 * @param phoneNum
	 * @return
	 */
	private CouponInfo getRandomCoupon(String coupon, String userId, String batchNo, String phoneNum){
		//4.将手机号与券的信息关联放到redis中
		String[] coupons = coupon.split("\\|");
		String amount = coupons[0];
		String type = coupons[1];
		String activeCode = coupons[2];
		String couponCode = coupons[3];
		CouponInfo couponInfo = new CouponInfo();
		couponInfo.setAmount(amount.split("\\.")[0]);
		couponInfo.setType(type);
		couponInfo.setActiveCode(activeCode);
		couponInfo.setCouponCode(couponCode);
		starPacketService.addPhoneToCache(batchNo, phoneNum, amount, type);
		//5.绑定优惠券信息到缓存
		starPacketService.bindPhoneUser(batchNo, userId, activeCode, couponCode);
		//6.redis 券的总数减一
		String key = "TotalWeixinWalletCoupon" + batchNo;
		starPacketService.decr(key);
		//7.已领取的券的数量加一
		String incrkey = "TotalWeixinWalletHasTaked" + batchNo;
		starPacketService.incr(incrkey);
		return couponInfo;
	}
	
	/**
     * 用户与微信进行绑定
     * @param openId 微信用户openId
     * @param 
     * @param User 用户信息
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
    /***
     * 判断url是否正确
     * @param str
     * @return
     */
    @SuppressWarnings("unused")
	private Boolean isUrl(String str) {
        String regex = "^([1-9]|10)$";  
        return str.matches(regex);
    }
}
