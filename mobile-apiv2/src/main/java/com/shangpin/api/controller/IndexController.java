package com.shangpin.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.service.ASPBizBrandService;
import com.shangpin.biz.service.ASPBizIndexInfoService;
import com.shangpin.biz.service.ASPBizRecommendService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.utils.StringUtil;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ASPBizBrandService aspBizBrandService;

	@Autowired
	private ASPBizRecommendService aspBizRecommendService;

	@Autowired
	private ASPBizIndexInfoService aspBizIndexInfoService;

	@ResponseBody
	@RequestMapping(value = "/firstIndex", method = { RequestMethod.POST, RequestMethod.GET })
	public String firstIndex() {
		final String imei = request.getHeader("imei");
		if (!StringUtil.isNotEmpty(imei)) {
			return returnParamError();
		}

		try {
			return aspBizIndexInfoService.getAppFirstIndex(request);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}

	/**
	 * 用于显示用户自己关注的以及品牌池的品牌
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customBrand", method = { RequestMethod.POST, RequestMethod.GET })
	public String customBrand() {
		final String imei = request.getHeader("imei");
		final String userId = request.getHeader("userid");
		final String num = "8";
		if (!StringUtil.isNotEmpty(imei)) {
			return returnParamError();
		}

		try {
			return aspBizBrandService.doCustomBrand(userId, imei, num);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}

	/**
	 * 用于保存用户自己定制的品牌接口
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/conserveBrand", method = { RequestMethod.POST, RequestMethod.GET })
	public String conserveBrand(@RequestParam String brandId) {
		final String imei = request.getHeader("imei");
		final String userId = request.getHeader("userid");
		if (!StringUtil.isNotEmpty(imei, brandId)) {
			return returnParamError();
		}

		try {
			return aspBizBrandService.doConserveBrand(brandId, userId, imei);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}

	/**
	 * 更多新品到货
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/newGoods", method = { RequestMethod.POST, RequestMethod.GET })
	public String newGoods() {
		final String imei = request.getHeader("imei");
		final String userId = request.getHeader("userid");
		if (!StringUtil.isNotEmpty(imei)) {
			return returnParamError();
		}

		try {
			return aspBizBrandService.doNewGoods(userId);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}

	/**
	 * 展示首页标签
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/revealLabel", method = { RequestMethod.POST, RequestMethod.GET })
	public String revealLabel(@RequestParam String pageIndex, @RequestParam String pageSize) {
		final String imei = request.getHeader("imei");
		if (!StringUtil.isNotEmpty(imei, pageIndex, pageSize)) {
			return returnParamError();
		}

		try {
			return aspBizIndexInfoService.getAppRevealLabel(request);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}
	
	/**
	 * 弹窗
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shellWindow", method = { RequestMethod.POST, RequestMethod.GET })
	public String shellWindow(@RequestParam String pageIndex, @RequestParam String pageSize) {
		final String imei = request.getHeader("imei");
		if (!StringUtil.isNotEmpty(imei, pageIndex, pageSize)) {
			return returnParamError();
		}

		try {
			return aspBizIndexInfoService.getAppShellWindow(pageIndex, pageSize);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}

	/**
	 * 每日超值购接口
	 * 
	 * @author zghw
	 */
	@ResponseBody
	@RequestMapping(value = "/worthBuy")
	public Object electronicRecharge(@RequestParam(value = "type", defaultValue = "2", required = false) String type, String pageIndex, String pageSize) {
		if (!StringUtil.isNotEmpty(pageIndex) && !StringUtil.isNotEmpty(pageSize)) {
			return returnParamError();
		}
		try {
			String json = aspBizRecommendService.fromFindPopularityAndWorth(type, "", pageIndex, pageSize);
			return toResult(json);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}
	}
	/**
	 * 获取首页导航图标
	 * @author fengwenyu
	 */
	@ResponseBody
	@RequestMapping(value="/bottomNavigate",method={RequestMethod.POST, RequestMethod.GET })
	public String getBottomNavigatePic(){
		String useragent = request.getHeader("User-Agent");
		//ios=1  android=0
    	String plateForm = ClientUtil.isIOS(useragent) ? "1" : "0";
		String date = aspBizIndexInfoService.getBottomNavigatePic(plateForm);
		return date;
	}
}
