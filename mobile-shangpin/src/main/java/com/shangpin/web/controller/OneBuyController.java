package com.shangpin.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.ProductCount;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizProductService;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.web.utils.Constants;
import com.shangpin.wechat.service.WeChatPublicService;

@Controller
@RequestMapping("/one")
public class OneBuyController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(OneBuyController.class);
	
	@Autowired
	private WeChatPublicService weChatPublicService;
	@Autowired
	private SPBizUserService userService;
	@Autowired
	private SPBizSendInfoService bizSendInfoService;
	@Autowired
	private SPBizCouponService bizCouponService;
	@Autowired
	private SPBizProductService bizProductService;
	
	private static final String INDEX = "one/index";
	private static final String INDEX2 = "one/index2";
	private static final String INDEX3 = "one/index3";
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(String nper, Model model, HttpServletRequest request){
		if(StringUtils.isEmpty(nper) || "1".equals(nper)){
			ProductCount productCount = bizProductService.productCount("30176752");
			model.addAttribute("productCount", productCount);
			return INDEX;
		}
		int num = Integer.parseInt(nper);
		switch (num) {
		case 2:
			ProductCount productCount = bizProductService.productCount("30224465");
			model.addAttribute("productCount", productCount);
			return INDEX2;
		case 3:
			ProductCount productCount1 = bizProductService.productCount("30224465");
			model.addAttribute("productCount", productCount1);
			return INDEX3;
		default:
			break;
		}
		return null;
	}
	
	@RequestMapping(value = "/check/phone", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBase checkPhone(String phoneNum, HttpServletRequest request){
		return bizCouponService.checkActiveCode(phoneNum, "4972995089");
	}
	
	@RequestMapping(value = "/check/phone/activity", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBase checkPhoneActivity(String phoneNum, HttpServletRequest request){
		return bizCouponService.checkPhoneActivity(phoneNum, "42");
	}
	
	@RequestMapping(value = "/is/check/phone/activity", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBase isCheckPhoneActivity(String phoneNum, HttpServletRequest request){
		return bizCouponService.isCheckPhoneActivity(phoneNum, "42");
	}
	
	@RequestMapping(value = "/check", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> check(String phoneNum, HttpServletRequest request){
		QuickUser quickUser = userService.checkPhoneUser(phoneNum, Constants.GODDES_REGISTE_SOURCE);
		String userId = quickUser.getUserId();
		String isNewUser = quickUser.getIsNewUser();
		User user = userService.findUserByUserId(userId);
		request.getSession().setAttribute(Constants.SESSION_USER, user);
		if(Constants.IS_NEW_USER.equals(isNewUser)){//表示是新用户，给用户下发短信通知
            String msgTemplateRe = "您好，感谢参与尚品网活动，您同时尊享尚品网会员权益。使用您的手机号即可登录，密码为手机号后6位。";
            boolean registerflag = bizSendInfoService.sendInfo(phoneNum, phoneNum, msgTemplateRe);
            logger.debug("send info==========={}", registerflag);
	    }
		//激活优惠券
		//Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:4532526975", "30");
		//Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:4814201887", "30");
		Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:4972995089", "30");
		return resultBase;
	}
	
	@RequestMapping(value = "/check/activity", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> checkActivity(String phoneNum, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		QuickUser quickUser = userService.checkPhoneUser(phoneNum, Constants.GODDES_REGISTE_SOURCE);
		String userId = quickUser.getUserId();
		String isNewUser = quickUser.getIsNewUser();
		User user = userService.findUserByUserId(userId);
		request.getSession().setAttribute(Constants.SESSION_USER, user);
		String msgTemplateRe = "您成功参与999情侣基金抽奖活动。新用户使用手机号登录尚品APP即送新人礼包，密码手机号后六位。戳t.cn/RvweXV1";
		if(Constants.IS_NEW_USER.equals(isNewUser)){
			boolean registerflag = bizSendInfoService.sendInfo(phoneNum, phoneNum, msgTemplateRe);
            logger.debug("send info==========={}", registerflag);
		}
		map.put("code", "0");
		return map;
	}
	
}
