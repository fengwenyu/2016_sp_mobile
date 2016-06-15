package com.aolai.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aolai.web.utils.Constants;
import com.aolai.web.utils.PropertyUtil;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.service.SPBizUserService;

/**
 * 活动页面ajax请求的公共方法
 * @author qinyingchun
 *
 */
@Controller
@RequestMapping("/acivity")
public class ActivityController extends BaseController{
	
	@Autowired
	private SPBizSendInfoService bizSendInfoService;
	@Autowired
    private SPBizUserService userService;
	@Autowired
	private SPBizCouponService bizCouponService;
	
	/**
	 * 下发手机验证码
	 * @param phoneNum
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sendPhoneCode", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> sendPhoneCode(String phoneNum, HttpServletRequest request){
		String msgTemplate = "【尚品网】短信验证码：{$verifyCode$}，您正在激活尚品网优惠券，需要进行验证，请勿向任何人提供您收到的短信验证码。";
		Map<String, Object> sendMap = bizSendInfoService.sendPhoneCode(phoneNum, phoneNum, msgTemplate);
		System.out.println("sendPhoneCode: "+sendMap);
		return sendMap;
	}
	
	/**
	 * 验证手机号并领取优惠券
	 * @param phoneNum 手机号
	 * @param verifyCode 手机验证码
	 * @param mettId 会场Id
	 * @param couponType 优惠券类型；8一天一次，9多次，30一次
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/check", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> check(String phoneNum, String verifyCode, String mettId, String couponType, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//1.验证手机验证码
		ResultBase base = userService.beVerifyPhoneCode(phoneNum, phoneNum, verifyCode);
		System.out.println("Code: "+base.getCode());
		if(!Constants.SUCCESS.equals(base.getCode())){
			map.put("code", "3");
			map.put("msg", "手机验证码输入错误！");
			return map;
		}
		//2.验证手机号是否是尚品用户,如果没有创建尚品用户
		QuickUser quickUser = userService.checkUser(phoneNum, Constants.IS_NEW_USER, null);
		String userId = quickUser.getUserId();
		User user = userService.findUserByUserId(userId);
		request.getSession().setAttribute(Constants.SESSION_USER, user);
		//3.检查用户是否为新用户
		if(Constants.IS_NEW_USER.equals(quickUser.getIsNewUser())){//表示是新用户，给用户下发短信通知
			String msgTemplateRe = "您好，感谢参与尚品网活动，您同时尊享尚品网会员权益。使用您的手机号即可登录，密码为手机号后6位。";
			bizSendInfoService.sendInfo(phoneNum, phoneNum, msgTemplateRe);
		}
		//4.领券
		String couponCode = PropertyUtil.getString(mettId);
		Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:" + couponCode, couponType);
		return resultBase;
		
	}
	
	/**
	 * 检查用户是否登录，如果登录则直接领取优惠券
	 * @param mettId
	 * @param couponType
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/check/user", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> isUserLogin(String mettId, String couponType, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		//判断用户是否登录， 如果用户已经登录直接领券
		if(null == user){
			map.put("code", "2");
			map.put("msg", "用户未登录");
			return map;
		}
		String couponCode = PropertyUtil.getString(mettId);
		String userId = user.getUserid();
		System.out.println("couponCode: "+couponCode);
		Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:" + couponCode, couponType);
		return resultBase;
	}

}
