package com.shangpin.web.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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

import com.shangpin.biz.bo.Gallery;
import com.shangpin.biz.bo.SiteType;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.dailyCoupons.CouponStatus;
import com.shangpin.biz.bo.dailyCoupons.DailyCouponsList;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizDailyCouponsService;
import com.shangpin.biz.service.SPBizGalleryService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.utils.StringUtil;
import com.shangpin.web.utils.Constants;

/**
 * 天天抢券频道控制器
 * @author zkj
 *
 */
@Controller
@RequestMapping("/dailyCoupon")
public class DailyCouponsController extends BaseController{
	
	public static final Logger logger = LoggerFactory.getLogger(DailyCouponsController.class);
	private static final String DC_GALLERY = "dailyCoupon/dc_gallery";
	private static final String DC_LIST = "dailyCoupon/dc_list";
	private static final String DC_MODIFY = "dailyCoupon/dc_modify";
	private static final String DC_INDEX = "dailyCoupon/dc_index";
	@Autowired
	private SPBizDailyCouponsService spBizDailyCouponsService;
	@Autowired
	private SPBizCouponService bizCouponService;
	@Autowired
	private SPBizGalleryService bizGalleryService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("index", true);
		return DC_INDEX;
	}
	
	/**
	 * 天天抢券页面轮播图
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String getGallery(Model model,HttpServletRequest request) {
		List<Gallery> galleries = bizGalleryService.galleries(Constants.GALLERY_DAILY_COUPON);
		if(galleries.size() == 0){
			return DC_GALLERY;
		}
		SiteType siteType =ClientUtil.getSiteType(request);
        model.addAttribute("isTag", "1");
        if(siteType.equals(SiteType.ANDRIOD_SHANGPIN)){
            String ver = request.getHeader("ver");
            logger.info(ver);
            if(StringUtil.compareVer(ver, "2.9.3")==-1){
                model.addAttribute("isTag", "0");
            }
        }
		model.addAttribute("galleries", galleries);
		return DC_GALLERY;
	}
	
	/**
	 * 从首页进入天天抢券频道
	 * @param model
	 * @param request
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public String getList(Model model,HttpServletRequest request, String pageIndex, String pageSize, String order, String type){
		String userId = "";
		String useragent = request.getHeader("User-Agent").toLowerCase();
		if(ClientUtil.CheckIOS(useragent)){//IOS
			userId = request.getHeader(Constants.APP_COOKIE_NAME_UID);
		}else{
			userId = getUserId(request);
		}
		DailyCouponsList dailyCouponsList=spBizDailyCouponsService.getCouponsList(userId, order, type, pageIndex,pageSize);
		model.addAttribute("dailyCouponsList",dailyCouponsList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("order", order);
		model.addAttribute("type", type);
		DailyCouponsList dailyCouponsList1 = spBizDailyCouponsService.getCouponsList(userId, order, type, String.valueOf(Integer.parseInt(pageIndex) + 1),pageSize);
		if(null == dailyCouponsList1 || dailyCouponsList1.getList().size() == 0){
			model.addAttribute("hasMore", "0");
		}else {
			model.addAttribute("hasMore", "1");
		}
		return DC_LIST;
	}
	
	@RequestMapping(value = "/ajax/list", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> list(String pageIndex, String pageSize, String order, String type, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = "";
		String useragent = request.getHeader("User-Agent").toLowerCase();
		if(ClientUtil.CheckIOS(useragent)){//IOS
			userId = request.getHeader(Constants.APP_COOKIE_NAME_UID);
		}else{
			userId = getUserId(request);
		}
		DailyCouponsList dailyCouponsList=spBizDailyCouponsService.getCouponsList(userId, order, type, pageIndex,pageSize);
		map.put("dailyCouponsList", dailyCouponsList);
		map.put("pageIndex", pageIndex);
		DailyCouponsList dailyCouponsList1=spBizDailyCouponsService.getCouponsList(userId, order, type, String.valueOf(Integer.parseInt(pageIndex) + 1),pageSize);
		if(null == dailyCouponsList1 || dailyCouponsList1.getList().size() == 0){
			map.put("hasMore", "0");
		}else {
			map.put("hasMore", "1");
		}
		return map;
	}
	
	
	/**
	 * 执行操作刷新列表
	 * @param model
	 * @param request
	 * @param order
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/refresh",method=RequestMethod.GET)
	public String refresh(Model model,HttpServletRequest request,String order,String type,String pageIndex,String pageSize){
		String userId = getUserId(request);
		
		DailyCouponsList dailyCouponsList=spBizDailyCouponsService.getCouponsList(userId, order, type, pageIndex,pageSize);
		model.addAttribute("dailyCouponsList", dailyCouponsList);
		return DC_MODIFY;
		
	}
	
	/**
	 * 领券
	 * @param activeCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get/coupon", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCoupon(String activeCode, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = getUserId(request);
		System.out.println("userId=========" + userId);
		if(StringUtils.isEmpty(userId)){
			map.put("code", "5");
			map.put("msg", "未登录");
			return map;
		}
		//1.券的领取状态
		List<CouponStatus> couponStatus = spBizDailyCouponsService.getCouponStatus(activeCode, userId);
		if(null == couponStatus){
			map.put("code", "4");
			map.put("msg", "领取异常");
			return map;
		}
		CouponStatus status = couponStatus.get(0);
		String code = status.getIsReceive();
		if("0".equals(code)){
			map.put("code", code);
			map.put("msg", "已领取");
			return map;
		}else if("2".equals(code)){
			map.put("code", code);
			map.put("msg", "已领光");
			return map;
		}else if("1".equals(code)){
			//3.激活券
			Map<String, Object> resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:" + activeCode, null);
			if("0".equals(resultBase.get("code"))){
				map.put("code", "1");
				map.put("msg", "领取成功");
			}else{
				map.put("code", "4");
				map.put("msg", "领取异常");
			}
			return map;
		}
		
		return map;
	}
	
	/**
	 * 天天抢券获取券当前的状态
	 * @param model
	 * @param request
	 * @param activeCode
	 * @return
	 */
	@RequestMapping(value="/isGet",method=RequestMethod.GET)
	@ResponseBody
	public String isGet(Model model,HttpServletRequest request,String activeCode){
		String userId = getUserId(request);
		List<CouponStatus> isGet=spBizDailyCouponsService.getCouponStatus(activeCode, userId);
		CouponStatus couponStatus=isGet.get(0);
		String status=couponStatus.getIsReceive();
		model.addAttribute("status", status);
		return status;
	}
	
	/**
	 * 激活券
	 * @param model
	 * @param request
	 * @param activeCode
	 * @return
	 */
	@RequestMapping(value="/activate",method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> active(Model model,HttpServletRequest request,String activeCode){
		User user=getSessionUser(request);
		String userId=user.getUserid();
		Map<String, Object> resultBase = new HashMap<String, Object>();
		String code="";
		if(user == null || StringUtil.isEmpty(userId)){
			code=Constants.UNLOGIN;
			resultBase.put("code", code);
			return resultBase;
		}else{
		resultBase=bizCouponService.sendCoupon(userId, "1", "coupon:" + activeCode, null);
		return resultBase;
		}
	}

	@RequestMapping("/coupon/list")
	@ResponseBody
	public DailyCouponsList list(String pageIndex, String pageSize){
		return spBizDailyCouponsService.getCouponsList("", "", "", pageIndex, pageSize);
	}
}
