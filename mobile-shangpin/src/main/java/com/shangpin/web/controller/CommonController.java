package com.shangpin.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.CaptchaService;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.PropertyUtil;


/**常用的公共请求
 * @author qinyingchun
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private SPBizSendInfoService bizSendInfoService;
	@Autowired
    private SPBizUserService userService;
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private SPBizCouponService bizCouponService;
	
	/**
	 * 下发短信通知
	 * @param phoneNum
	 * @param temple
	 * @return
	 */
	@RequestMapping(value = "/send/notic", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> sendPhoneNotic(String phoneNum, String temple){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean registerflag = bizSendInfoService.sendInfo(phoneNum, phoneNum, temple);
		if(registerflag){
			map.put("code", "0");
			map.put("msg", "短信通知下发成功");
		}else {
			map.put("code", "1");
			map.put("msg", "短信通知下发失败");
		}
		return map;
	}
	
	/**
	 * 下发手机验证码
	 * @param phoneNum
	 * @param request
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value = "/send/phone/code", method = { RequestMethod.GET, RequestMethod.POST })
    public Map<String, Object> sendPhoneCode(String phoneNum, HttpServletRequest request) {
    	String msgTemplate = "验证码:{$verifyCode$}，手机号需要验证。【请勿告知他人】";
        Map<String, Object> sendMap = bizSendInfoService.sendPhoneCode(phoneNum, phoneNum, msgTemplate);
        if (sendMap.get("code").equals(Constants.SUCCESS)) {
            logger.info("send msg sucess,{}", phoneNum);
        } else {
            logger.info("send msg fail,{}", phoneNum);
        }
        return sendMap;

    }
	
	/**
	 * 判断手机验证码是否正确
	 * @param phoneNum
	 * @param verifyCode
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/check/verify/code", method = {RequestMethod.GET, RequestMethod.POST})
	public ResultBase checkVerifycode(String phoneNum, String verifyCode, HttpServletRequest request){
		ResultBase result = userService.beVerifyPhoneCode(phoneNum, phoneNum, verifyCode);
		return result;
	}
	
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/captcha", method = {RequestMethod.GET, RequestMethod.POST})
    public void writeImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            captchaService.writeImage(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("writeImage :" + e);
        }
    }
	
	/**
	 * 验证验证码是否正确
	 * @param captcha
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/check/captcha", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, String> checkCaptcha(String captcha, HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		boolean result = captchaService.verifyCaptcha(request.getSession(), captcha);
		if(!result){
			map.put("code", "1");
			map.put("msg", "验证码输入错误，请从新输入");
		}
		return map;
	}
	/**
	 * 激活优惠券
	 * @param type 来源
	 * @param no 优惠券标识
	 * @param rule 激活规则
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/coupon/active", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> activeObj(String type, String no, String rule, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		String userId = user.getUserid();
		String activeCode = PropertyUtil.getString("coupon_code" + no);
    	Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, type, "coupon:" + activeCode, rule);
    	return resultBase;
    }
	
}
