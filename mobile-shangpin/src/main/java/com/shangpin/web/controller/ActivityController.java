package com.shangpin.web.controller;

import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shangpin.biz.bo.Province;

import com.shangpin.biz.bo.ActivityLottery;
import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizActivityService;
import com.shangpin.biz.service.SPBizAddressService;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizMeetPlaceService;
import com.shangpin.biz.service.SPBizReceiveService;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.utils.FreeBie520Util;
import com.shangpin.utils.StringUtil;
import com.shangpin.web.utils.ActivifyUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.PropertyUtil;

/**
 * 活动页面ajax请求的公共方法
 * @author qinyingchun
 *
 */
@Controller
@RequestMapping("/acivity")
public class ActivityController extends BaseController{
    private static final Logger logger  = LoggerFactory.getLogger(ProductController.class);
    //活动提醒
	private static final String ACTIVITY_TIP="activity/beepTimer";
	@Autowired
	private SPBizSendInfoService bizSendInfoService;
	@Autowired
    private SPBizUserService userService;
	@Autowired
	private SPBizCouponService bizCouponService;
    @Autowired
    private SPBizMeetPlaceService meetPlaceService;
    @Autowired
    private ASPBizActivityService aSPBizActivityService;	
    @Autowired
    private SPBizAddressService bizAddressService;
    @Autowired
    private SPBizReceiveService sPBizReceiveService;
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
	public Map<String, Object> check(String phoneNum, String verifyCode, String mettId, String couponType,String code, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//1.验证手机验证码
		ResultBase base = userService.beVerifyPhoneCode(phoneNum, phoneNum, verifyCode);
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
		if(couponCode==null||"".equals(couponCode)){
			couponCode = code;
		}
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
	public Map<String, Object> isUserLogin(String mettId, String couponType,String code, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = getSessionUser(request);
		//判断用户是否登录， 如果用户已经登录直接领券
		if(null == user){
			map.put("code", "2");
			map.put("msg", "用户未登录");
			return map;
		}
		String couponCode = PropertyUtil.getString(mettId);
		if(couponCode==null||"".equals(couponCode)){
			couponCode = code;
		}
		String userId = user.getUserid();
		Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:" + couponCode, couponType);
		return resultBase;
	}
	/**
	 * 验证手机号和验证码
	 * @param phoneNum
	 * @param verifyCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkPhoneNum", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> checkPhoneNum(String phoneNum, String verifyCode, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//1.验证手机验证码
		ResultBase base = userService.beVerifyPhoneCode(phoneNum, phoneNum, verifyCode);
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
		map.put("code", "0");
		map.put("msg", "手机号验证通过");
		return map;
		
	}
	/**
     * 活动提醒
     * @param batchCode 
     * @param model
     * @return
     */
    @RequestMapping(value = "/tip")
    public String indexPage(@RequestParam String actityId,Model model, HttpServletRequest request){ 
        model.addAttribute("code", actityId);
        return ACTIVITY_TIP;
    }
    /**
     * 活动提醒
     * @param batchCode 
     * @param model
     * @return
     */
    @RequestMapping(value = "/follow/remind")
    @ResponseBody
    public  Map<String, Object> followRemind(@RequestParam String phoneNum,String verifyCode,@RequestParam String code,@RequestParam String time,
            Model model, HttpServletRequest request){ 
        Map<String, Object> map = new HashMap<String, Object>();
        //1.验证手机验证码
        ResultBase base = userService.beVerifyPhoneCode(phoneNum, phoneNum, verifyCode);
        if(!Constants.SUCCESS.equals(base.getCode())){
            map.put("code", "3");
            map.put("msg", "手机验证码输入错误！");
            return map;
        }
        return aSPBizActivityService.activityStartRemind(phoneNum, code, time);
    }
    
    /**
     * 下发手机验证码
     * @param phoneNum
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendTipPhoneCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> sendTipPhoneCode(String phoneNum, HttpServletRequest request){
        String msgTemplate = "短信验证码：{$verifyCode$}，请勿向任何人提供您收到的短信验证码。";
        Map<String, Object> sendMap = bizSendInfoService.sendPhoneCode(phoneNum, phoneNum, msgTemplate);
        return sendMap;
    }
	/**
     * 520抽奖
     * 
     * @param request
     * @return
     * @author zghw
     */
    @RequestMapping(value = "/lottery")
    @ResponseBody
    public Object activityLottery520(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        User user = getSessionUser(request);
        if (user == null || StringUtils.isEmpty(user.getUserid())) {
            map.put("status", "2");
            return map;
        }
        ResultObjOne<ActivityLottery> obj = meetPlaceService.beActivityLottery("16426", user.getUserid());
        if (obj != null && obj.isSuccess()) {
            ActivityLottery activityLottery = obj.getContent();
            if (activityLottery != null) {
                map.put("status", activityLottery.getIsDraw());
               /* if ("0".equals(activityLottery.getIsDraw())) {
                    return map;
                }*/
                map.put("message", activityLottery.getPrizeLevel());
                map.put("times", activityLottery.getRemainCount());
            }
        } else {
            map.put("status", "1");
            return map;
        }
        return map;
    }
    
    /**
     * 51抽奖转盘
     * @param phoneNum
     * @param request
     * @return
     */
    @RequestMapping(value = "/turntable")
    public String turntable(Model model,HttpServletRequest request){
        User user = getSessionUser(request);
        if (user != null) {
            model.addAttribute("loginStatus", "1");
        }
        return "activity/51/index";
    }
    
    /**
     * 520买赠获取地址
     * 
     * @param request
     * @return
     * @author wh
     */
    @RequestMapping(value = "/ajlist")
    @ResponseBody
    public Map<String, Object> listAddresses(HttpServletRequest request, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = getSessionUser(request);
        List<Address> addresses = null;
        if (user!=null && !StringUtils.isEmpty(user.getUserid())) {
            addresses = bizAddressService.findAddressObj(user.getUserid());
        }
        map.put("addresses", addresses);
        // 解密身份证号码
        return map;
    }
    
    /**
     * 520买赠获province
     * @Title: province
     * @Description:获取provinces
     * @param
     * @return List<Area>
     * @throws
     * @Create By wh
     * @Create Date 2016年05月21日
     */
    @RequestMapping(value = "/province")
    @ResponseBody
    public List<Province> Province() {
        List<Province> provinces = bizAddressService.findProvinceListObj();
        return provinces;
    }
    
    /**
     * 520买赠获取地址
     * 
     * @param request
     * @return
     * @author wh
     */
    @RequestMapping(value = "/addAddresses")
    @ResponseBody
    public Map<String, Object> addAddresses(Address address, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = getSessionUser(request);
        if (user!=null && !StringUtils.isEmpty(user.getUserid())) {
            address.setPostcode("111111");
            address.setTel(user.getMobile());
            List<Address> addresses = bizAddressService.addAddr(address, user.getUserid());
            if (null != addresses) {
                map.put("code", Constants.SUCCESS);
                map.put("id", addresses.get(0).getId());
            } else {
                map.put("code", Constants.FAILE);
            }
        }else {
            map.put("code", Constants.FAILE);
        }
        return map;
    }
    /**
     * 520买赠 检查验证码
     * @param phoneNum
     * @param verifyCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/isCode", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> receiveCoupons(String phoneNum,String verifyCode, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        ResultBase result = userService.beVerifyPhoneCode(phoneNum, phoneNum, verifyCode);
        if (result == null || Constants.FAILE.equals(result.getCode())) {
            map.put("code", "4");
            map.put("msg", "短信验证码错误");
            return map;
        }
        map.put("code", "0");
        return map;
    }
    /***
     * 520买赠 老虎机算几率
     * @return
     */
    @RequestMapping(value = "/slot")
    @ResponseBody
    public Map<String, Object> slot(String p,HttpServletResponse response,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!ActivifyUtil.isfreeActivify()){
			map.put("state", "10");
			return map;
		}
		Map<String,String> parameter= FreeBie520Util.decodeFreebieParam(p);
		String spuS =parameter.get("spu");
		String sortN =parameter.get("sortNo");
		String orderIds =parameter.get("orderId");
		String sp=spuS+sortN+orderIds;

        Cookie cookies[] = ((HttpServletRequest) request).getCookies();
        for (Cookie uaasCookie : cookies) {
            if (uaasCookie.getName().equals(sp)) {
                map.put("state", "3");
               return map;
            }
        }
        Cookie cookieToken = new Cookie(sp, "111");
        cookieToken.setPath("/");
        cookieToken.setMaxAge(7200);
        response.addCookie(cookieToken);
        //是否领取过
        String re= sPBizReceiveService.isPicked(parameter.get("userId"), parameter.get("orderId"), parameter.get("sku"), parameter.get("sortNo"));
        if (!StringUtils.isEmpty(re) &&"0".equals(re)) {
        	if(laohuji()){
        		//抽奖方法
        		map.put("stopNum1", 0);
        		map.put("stopNum2", 0);
        		map.put("stopNum3", 0);
        		map.put("state", "1");
        	}else{
        		map.put("stopNum1", 1);
        		map.put("stopNum2", 2);
        		map.put("stopNum3", 0);
        		map.put("state", "1");
        	}
        }else {
            map.put("stopNum1", 1);
            map.put("stopNum2", 3);
            map.put("stopNum3", 0);
            map.put("state", "1");
        }
        return map;
    }
    
    
    

    private boolean laohuji() {
		if (new Random().nextInt(19) == 11) {
			//中奖
			 return true;
		}else{
			//非中奖
			return false;
		}
	}

    
	/**
	 * 
	 * @Title: getCoupon
	 * @Description: 领取优惠券
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月9日
	 */
	@RequestMapping(value = "/receive", method = { RequestMethod.GET, RequestMethod.POST })
	public String getCoupon(HttpServletRequest request,String topicId,Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = getSessionUser(request);
		map = bizCouponService.sendCoupon(user.getUserid(), "1", "coupon:2644709794", "30");
		if(!StringUtil.isEmpty(topicId)){
			String back="/subject/product/list?topicId="+topicId;
			return "redirect:" + back;
		}
		if (Constants.SUCCESS.equals(map.get("code"))) {
			model.addAttribute("code","0");
			return "coupon/getCoupon_success";
		}
		// 发送失败时没有业务逻辑
		model.addAttribute("code","1");
		return "coupon/getCoupon_success";
	}
}
