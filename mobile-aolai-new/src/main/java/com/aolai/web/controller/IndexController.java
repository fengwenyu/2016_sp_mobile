/**
 * <pre>
 * Copyright:		Copyright(C) 2010-2014, shangpin.com
 * Filename:		com.shangpin.controller.UserController.java
 * Class:			UserController
 * Date:			2014-07-11
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.0
 * Description:		
 *
 * </pre>
 **/

package com.aolai.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aolai.web.utils.BizUtil;
import com.aolai.web.utils.Constants;
import com.aolai.web.utils.DateTimeUtil;
import com.shangpin.biz.bo.Activity;
import com.shangpin.biz.bo.PreSell;
import com.shangpin.biz.service.ALBizIndexService;
import com.shangpin.biz.utils.StringUtil;

/**
 * 首页controller
 * 
 * @author sunweiwei
 */
@Controller
public class IndexController extends BaseController {

	public static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	// 女士首页
	private static final String INDEX = "index/index";
	// 最新热卖列表
	private static final String TODAYNEW = "index/new_hot_list";
	// 限时特卖列表
	private static final String LIMITEDNEW = "index/time_limit_list";
	// 全部最新热卖列表
	private static final String ACTIVITY = "product/activity_all_list";
	// 预售日历首页
	private static final String PREINDEX = "pre/pre_index";
	// 预售日期列表页
	private static final String PREDATE = "pre/pre_date_list";
	// 预售产品列表页
	private static final String PREPRODUCT = "pre/pre_product_list";

	@Autowired
	private ALBizIndexService indexService;

	/**
	 * 加载首页时执行,直接跳转到首页面
	 * 
	 * @author sunweiwei
	 * @return
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Map<String, Object> map) {
		map.put("gender", Constants.GENDER_WOMEN);
		return INDEX;
	}

	@RequestMapping(value = "/men", method = RequestMethod.GET)
	public String men(Map<String, Object> map) {
		map.put("gender", Constants.GENDER_MEN);
		return INDEX;
	}

	/**
	 * 预售日历首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/presell", method = RequestMethod.GET)
	public String preSell(Map<String, Object> map) {
		map.put("gender", Constants.GENDER_PRE);
		return PREINDEX;
	}

	/**
	 * 最新热卖列表
	 * 
	 * @param map
	 * @param gender
	 * @return
	 */
	@RequestMapping(value = "/newhot", method = RequestMethod.GET)
	public String newHotProduct(Map<String, Object> map, String gender) {
		Date date = new Date();
		String startTime = DateTimeUtil.shortFmt(date);
		String endTime = DateTimeUtil.shortFmt(DateTimeUtil.getAppointDayFromToday(date, 1));
		String picw = Constants.ACTIVITIES_PICTURE_WIDTH;
		String pich = Constants.ACTIVITIES_PICTURE_HEIGHT;
		// 获取最新热卖列表，即当日最新活动列表
		List<Activity> newHotProductList = indexService.getNewHotProduct(gender, startTime, endTime, picw, pich);

		if (newHotProductList == null || newHotProductList.size() <= 0) {
			logger.debug("There is no newHotProduct!");
		}
		map.put("newHotProductList", newHotProductList);
		return TODAYNEW;
	}

	/**
	 * 限时特卖列表
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/timelimit", method = RequestMethod.GET)
	public String timeLimitProduct(Map<String, Object> map, String gender) {
		String picw = Constants.ACTIVITIES_PICTURE_WIDTH;
		String pich = Constants.ACTIVITIES_PICTURE_HEIGHT;
		List<Activity> limitedProductList = indexService.getLimitedProduct(gender, picw, pich);
		Date now = new Date();
		if (limitedProductList != null && limitedProductList.size() > 0) {
			Date startDate = new Date(Long.parseLong(limitedProductList.get(0).getStarttime()));
			Date endDate = new Date(Long.parseLong(limitedProductList.get(0).getEndtime()));
			map.put("endTime", DateTimeUtil.getTime(endDate));
			if (now.after(startDate) && now.before(endDate)) {
				map.put("flag", true);
			} else {
				map.put("flag", false);
			}
		}
		map.put("nowTime", DateTimeUtil.getTime(now));
		map.put("limitedProductList", limitedProductList);
		return LIMITEDNEW;
	}

	/**
	 * 全部最新热卖列表
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/allnew", method = RequestMethod.GET)
	public String getAllNewHot(Map<String, Object> map, String gender, String pageType) {
		String picw = Constants.ACTIVITIES_PICTURE_WIDTH;
		String pich = Constants.ACTIVITIES_PICTURE_HEIGHT;
		List<Activity> allNewHotProductList = indexService.getAllNewHotProduct(gender, picw, pich);

		// 获取当前活动主题
		String activityName = BizUtil.getActivityName(pageType);
		map.put("allNewHotProductList", allNewHotProductList);
		map.put("activityName", activityName);
		return ACTIVITY;
	}

	/**
	 * 预售日期列表
	 * 
	 * @param map
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "/predate", method = RequestMethod.GET)
	public String getPreDateList(Map<String, Object> map, String id, String startTime, String endTime) {
		Date date = new Date();
		// 预售日期列表
		List<PreSell> preSellDateList = indexService.getPreDateList(date, id, startTime, endTime);
		map.put("preSellDateList", preSellDateList);
		return PREDATE;
	}

	/**
	 * 预售产品列表
	 * 
	 * @param map
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "/preproduct", method = RequestMethod.GET)
	public String getPreProductList(Map<String, Object> map, String id, String startTime, String endTime) {
		Date date = new Date();
		String picw = Constants.ACTIVITIES_PICTURE_WIDTH;
		String pich = Constants.ACTIVITIES_PICTURE_HEIGHT;
		// 预售产品列表
		List<Activity> preSellProductList = new ArrayList<Activity>();

		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String showHead = "";
		if (StringUtil.isNotEmpty(id, startTime, endTime)) {// 非首次进入预售页
			showHead = sdf.format(DateTimeUtil.getAfterAmountDay(date, Integer.parseInt(id))) + "&nbsp"
					+ DateTimeUtil.dateToWeek(DateTimeUtil.getAfterAmountDay(date, Integer.parseInt(id))) + "&nbsp" + "预售商品";
			preSellProductList = indexService.getPreSellProduct(startTime, endTime, picw, pich);
		} else {// 首次进入预售页
			showHead = sdf.format(DateTimeUtil.getAfterAmountDay(date, 1)) + "&nbsp" + DateTimeUtil.dateToWeek(DateTimeUtil.getAfterAmountDay(date, 1)) + "&nbsp" + "预售商品";
			preSellProductList = indexService.getPreSellProduct(sdf1.format(DateTimeUtil.getAfterAmountDay(date, 1)), sdf1.format(DateTimeUtil.getAfterAmountDay(date, 2)), picw,
					pich);
		}
		map.put("preSellProductList", preSellProductList);
		map.put("showHead", showHead);
		return PREPRODUCT;
	}

}
