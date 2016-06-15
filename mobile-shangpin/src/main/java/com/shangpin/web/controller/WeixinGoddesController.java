package com.shangpin.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.ActivityLottery;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizMeetPlaceService;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.core.entity.AccountWeixinBind;
import com.shangpin.core.service.IWeixinBindService;
import com.shangpin.utils.StringUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.PropertyUtil;
import com.shangpin.wechat.bo.base.AccessToken;
import com.shangpin.wechat.service.WeChatPublicService;

@Controller
@RequestMapping("/goddes")
public class WeixinGoddesController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(WeixinGoddesController.class);
	
	private static String COUPON_CODE = "";
	
	@Autowired
	private WeChatPublicService weChatPublicService;
	@Autowired
    private SPBizUserService userService;
	@Autowired
    private IWeixinBindService weixinBindService;
	@Autowired
	private SPBizCouponService bizCouponService;
	@Autowired
	private SPBizSendInfoService bizSendInfoService;
	@Autowired
    SPBizMeetPlaceService meetPlaceService;
	
	/**
	 * 跳转到女神活动的h5页面
	 * @author qinyingchun
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		String code = request.getParameter("code");
		logger.debug("authroized code==============={}", code);
		AccessToken accessToken = weChatPublicService.getAccessTokenObj(code);// 网页授权获得的token和公共账号获取的token不同
		logger.debug("access token==============={}", accessToken.toString());
		String openId = accessToken.getOpenid();
		logger.debug("user openId================={}", openId);
		request.getSession().setAttribute("weixin_user_openId", openId);
		String couponCode = request.getParameter("activateCode");
		logger.debug("coupon code==================={}", couponCode);
		session.setAttribute("activateCode", couponCode);//activateCode=1,表示用户还没有领取优惠券
		//检查用户是否与微信绑定
		AccountWeixinBind account = weixinBindService.findByWXId(openId);
		logger.debug("weixin account info=============");
		if(account != null){
			String userId = account.getUserId();
			User user = userService.findUserByUserId(userId);
			logger.debug("goddes index user info==================={}", user.toString());
			request.getSession().setAttribute(Constants.SESSION_USER, user);
		}
		int nper = Integer.parseInt(request.getParameter("nper"));
		switch (nper) {
		case 1:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_286");
			return "redirect:/meet/index?id=286";
		case 2:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_301");
			return "redirect:/meet/index?id=301";
		case 3:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_311");
			return "redirect:/meet/index?id=311";
		case 4:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_315");
			return "redirect:/meet/index?id=315";
		case 5:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_324");
			return "redirect:/meet/index?id=324";
		case 6:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_341");
			return "redirect:/meet/index?id=341";
		case 7:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_362");
			return "redirect:/meet/index?id=362";
		case 8:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_366");
			return "redirect:/meet/index?id=366";
		case 9:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_370");
			return "redirect:/meet/index?id=370";
		case 10:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_395");
			return "redirect:/meet/index?id=395";
		case 11:
			COUPON_CODE = PropertyUtil.getString("goddes_meet_404");
			return "redirect:/meet/index?id=404";
		default:
			return null;
		}
	}
	
	/**
	 * 下发手机验证码
	 * @param phoneNum
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/sendPhoneCode", method = RequestMethod.POST)
    public  Map<String, Object> sendPhoneCode(String phoneNum, HttpServletRequest request) {
		 String msgTemplate = "短信验证码：{$verifyCode$}，您正在激活尚品网优惠券，需要进行验证，请勿向任何人提供您收到的短信验证码。";
		 Map<String, Object> sendMap = bizSendInfoService.sendPhoneCode(phoneNum, phoneNum, msgTemplate);
		if (sendMap.get("code").equals(Constants.SUCCESS)) {
			logger.info("goddes send msg sucess");
		} else {
			logger.info("goddes run send msg fail");
		}
		return sendMap;
     
    }
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> check(String phoneNum, String verifycode,String couponType,
	        String couponCode, HttpServletRequest request){
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
        //登录或注册用户，如果是新用户，用户是否与微信绑定
        String userId = makWeixinUInf(phoneNum,request,session);
		//2.激活优惠券
		logger.debug("优惠券激活码====================={}", couponCode);
		if(StringUtil.isEmpty(couponCode)){
		    couponCode=COUPON_CODE;
		}
		Map<String, Object> resultBase = this.activeObj(userId, couponCode,couponType);
		logger.debug("coupon active code flag=============={}",resultBase);
		String code = (String)resultBase.get("code");
		if(StringUtil.isBlank(code)){
			map.put("code", "2");
			map.put("msg", "您的礼券领取失败！");
			logger.debug("return result code is null==============={}",map);
			return map;
		}else if("1".equals(code)){
            map.put("code", "1");
            map.put("msg", resultBase.get("msg"));
            return map;
        }else {
			session.removeAttribute("activateCode");
			map.put("code", "0");
			map.put("msg", "您的礼券包激活成功！购买商品提交订单时记得选择优惠券哦！");
			logger.debug("return result==============={}", map);
			return map;
		}
		
	}
	
	/***微信手机号  登录
	 * 1.验证手机号码是否注册，如果没有注册默认给用户注册一个账号
	 * 2.检查用户是否为新用户
	 * 3.检查用户是否与微信绑定
	 * @param phoneNum 手机号
	 * @param request request
	 * @param session session
	 * @return 用户id
	 */
	private String  makWeixinUInf(String phoneNum, HttpServletRequest request, HttpSession session) {
	   // 1.验证手机号码是否注册，如果没有注册默认给用户注册一个账号
        QuickUser quickUser = userService.checkUser(phoneNum, Constants.CREATE_NEW_USER, Constants.GODDES_REGISTE_SOURCE);
        logger.debug("quick user info=============={}", quickUser.getUserId() + "===" + quickUser.getIsNewUser());
        String userId = quickUser.getUserId();
        String isNewUser = quickUser.getIsNewUser();
        //String openId = "oFHXijs8DAnA2OfSwOUH7rZtuv4U";
        String openId = (String)request.getSession().getAttribute("weixin_user_openId");
        logger.debug("session open id============={}", openId);
        User user = userService.findUserByUserId(userId);
        logger.debug("user info============{}", user.getUserid() + "=========" + user.getName());
        String userName = user.getName();
        logger.debug("session user start======================");
        logger.debug("session user is================");
        User sessionUser = (User)request.getSession().getAttribute(Constants.SESSION_USER);
        logger.debug("session user info==============={}");
        if(sessionUser == null){
            session.setAttribute(Constants.SESSION_USER, user);
        }
        logger.debug("session user info end======================");
        //2.检查用户是否为新用户
        if(Constants.IS_NEW_USER.equals(isNewUser)){//表示是新用户，给用户下发短信通知
            String msgTemplateRe = "您好，感谢参与尚品网活动，您同时尊享尚品网会员权益。使用您的手机号即可登录，密码为手机号后6位。";
            logger.debug("自动生成的新用户下发短信通知=====================");
            boolean registerflag = bizSendInfoService.sendInfo(phoneNum, phoneNum, msgTemplateRe);
            logger.debug("短信下发成功标识=================={}", registerflag);
        }
        //3.检查用户是否与微信绑定
        logger.debug("weinxin user is bind start=======");
        //boolean bindFlag = weixinBindService.isBind(openId, userId);
        AccountWeixinBind account = weixinBindService.findByWXId(openId);
        logger.debug("weixin bind user==============={}", account);
        logger.debug("weixin user is bind===========");
        if(account == null){//表示未绑定，进行绑定
            this.bind(openId, userName, user);
        }
        return userId;
    }
	
	/**
	 * 微信没登录  抽奖 
	 * @param activityId
	 * @param verifycode
	 * @param phoneNum
	 * @param request
	 * @return map
	 */
    @RequestMapping(value = "/checkActivity")
    @ResponseBody
    public Map<String, Object> checkActivity(String verifycode, String phoneNum, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        // 1.验证手机验证码是否正确
        boolean verifyCodeFlag = this.checkPhoneCode(phoneNum, verifycode);
        logger.debug("verify cod flag============={}", verifyCodeFlag);
        if (!verifyCodeFlag) {
            map.put("code", "2");
            map.put("msg", "手机验证码输入错误！");
            return map;
        }
        // 2.登录或注册用户，如果是新用户，用户是否与微信绑定
        String userId = makWeixinUInf(phoneNum, request, session);        
        // 3.开始抽奖
        logger.debug("抽奖活动编码====================={}", "910");
        ResultObjOne<ActivityLottery> obj = meetPlaceService.beFollowerLottery(userId, "910");
        if (obj != null && obj.isSuccess()) {
            ActivityLottery activityLottery = obj.getContent();
            if (activityLottery != null) {
                map.put("status", activityLottery.getIsDraw());
                map.put("remainCount", activityLottery.getRemainCount());
                map.put("message", activityLottery.getPrizeLevel());
                map.put("times", activityLottery.getRemainCount());
                return map;
            }
        } else {
            map.put("code", "1");
            return map;
        }
        return map;
    }
    
    
    /**
     * 抽奖
     * @param request
     * @param activityId
     * @return
     * @author wh
     */
    @RequestMapping(value = "/activityFollower")
    @ResponseBody
    public Object activityLottery(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        User user = getSessionUser(request);
        if (user == null || StringUtils.isEmpty(user.getUserid())) {
            map.put("status", "2");
            return map;
        }
        logger.debug("抽奖活动编码====================={}", "910");
        ResultObjOne<ActivityLottery> obj = meetPlaceService.beFollowerLottery(user.getUserid(),"910");
        if (obj != null && obj.isSuccess()) {
            ActivityLottery activityLottery = obj.getContent();
            if (activityLottery != null) {
                map.put("status", activityLottery.getIsDraw());
                map.put("remainCount", activityLottery.getRemainCount());
                map.put("message", activityLottery.getPrizeLevel());
                map.put("times", activityLottery.getRemainCount());
                return map;
            }
        } else {
            map.put("code", "1");
            return map;
        }
        return map;
    }

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> isReceive(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String isReceive = (String)session.getAttribute("activateCode");
		if(!StringUtils.isEmpty(isReceive) && "1".equals(isReceive)){
			map.put("code", "0");
			map.put("msg", "");
		}else {
			map.put("code", "1");
			map.put("msg", "您已经领取过了！");
		}
		return map;
	}
	
	@RequestMapping(value = "/direct/receive", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> receive(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constants.SESSION_USER);
		logger.debug("direct receive coupon user info==============={}", user);
		String userId = user.getUserid();
		//boolean couponFlag = this.active(userId, COUPON_CODE);
		Map<String, Object> resultBase = this.activeObj(userId, COUPON_CODE,null);
		String code = (String)resultBase.get("code");
		if("1".equals(code)){
			map.put("code", "1");
			map.put("msg", resultBase.get("msg"));
			return map;
		}else {
			session.removeAttribute("activateCode");
			map.put("code", "0");
			map.put("msg", " 您的礼券包激活成功！购买商品提交订单时记得选择优惠券哦！");
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
     * 验证手机号是否已注册
     * @param phoneNum
     * @return
     */
    @SuppressWarnings("unused")
    private boolean isRegister(String phoneNum){
    	QuickUser quickUser = userService.checkUser(phoneNum, Constants.CREATE_NO_USER);
    	return Constants.IS_NEW_USER.equals(quickUser.getIsNewUser()) ? true : false;
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
    
    /**
     * 微信绑定用户后自动登录
     * @param account
     * @return
     */
    @SuppressWarnings("unused")
    private User weixinAutoLogin(AccountWeixinBind account){
    	User user = userService.weixinAutoLogin(account);
    	return user;
    }
    
    /**
     * 优惠券激活
     * @param userId
     * @param activeCode
     * @return
     */
    @SuppressWarnings("unused")
    private boolean active(String userId, String activeCode){
    	Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:" + activeCode, "9");
    	String code = (String)resultBase.get("code");
    	return Constants.SUCCESS.equals(code) ? true : false;
    }
    
    private Map<String, Object> activeObj(String userId, String activeCode,String couponType){
        if (StringUtil.isBlank(couponType)) {
            couponType="9";
        }
    	Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:" + activeCode, couponType);
    	return resultBase;
    } 
}
