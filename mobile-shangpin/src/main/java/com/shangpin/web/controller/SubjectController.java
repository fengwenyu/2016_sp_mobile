package com.shangpin.web.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.shangpin.biz.bo.ActivityIndex;
import com.shangpin.biz.bo.BrandActivityHead;
import com.shangpin.biz.bo.SearchConditions;
import com.shangpin.biz.bo.SearchResult;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.common.page.Page;
import com.shangpin.biz.service.ASPBizActivityService;
import com.shangpin.biz.service.SPBizSubjectService;
import com.shangpin.pay.utils.common.StringUtil;
import com.shangpin.utils.JedisUtilFactory;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.MD5Util;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.SearchCache;
import com.shangpin.web.utils.SearchUtil;

@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {
	public static Logger logger = LoggerFactory.getLogger(SubjectController.class);
	private static final String NEWLIST = "subject/newList";
	private static final String LIST_FLOOR = "subject/newList_Floor";
	@Autowired
	private SPBizSubjectService subjectBizService;
	@Autowired
	private ASPBizActivityService aspBizActivityService;



	/**
	 * 
	 * @Title: getMore
	 * @Description:活动下商品列表获取更多
	 * @param
	 * @return Map<String, Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	@ResponseBody
	@RequestMapping(value = "/getMore", method = RequestMethod.POST)
	public Map<String, Object> getMore(HttpServletRequest request, @RequestParam("topicId") String topicId, String gender, @RequestParam String pageIndex) {
		String userId = null;
		User user = getSessionUser(request);
		if (!StringUtils.isEmpty(user)) {
			userId = user.userid;
		}
		Map<String, Object> map = subjectBizService.getNewTopicProducts(topicId, gender, userId, pageIndex, String.valueOf(Page.DEFAULT_PAGE_SIZE));
		return map;
	}


	@RequestMapping(value = "/product/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchProductList(SearchConditions searchConditions, HttpServletRequest request, String gender, Model model,String OPT) {
		
		//SearchUtil.SystemConditions(searchConditions);
		try {
			User user = getSessionUser(request);
			String userId = getUserId(request);
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			String start = (StringUtils.isEmpty(searchConditions.getStart())) ? "1" : searchConditions.getStart();
			String topicId=searchConditions.getTopicId();
			String postArea=(StringUtils.isEmpty(searchConditions.getPostArea())) ?"0" : searchConditions.getPostArea();
			searchConditions.setPostArea(postArea);
			if(org.apache.commons.lang3.StringUtils.isBlank(topicId)){
				return NEWLIST;
			}
			topicId=topicId.trim();
			/**
			 * 定义缓存策略
			 */
			String redisKey = "subject_key_floor:"+ topicId;
			String redisTime ="900";
			Jedis jedis = JedisUtilFactory.getDefaultJedisUtil().getJedis();
			if(OPT!=null){
				if("miss".equals(OPT)||"refresh".equals(OPT)){
					jedis.del(redisKey);
				}
			}
			model.addAttribute("userLv", userLv);
			Map<String, Object> checkMap = null;
			if(jedis.get(redisKey)!=null){
				String redisVal = jedis.get(redisKey);
				checkMap = JsonUtil.fromJson(redisVal, Map.class);
			}
			BrandActivityHead activityHead = aspBizActivityService.getBrandActivityHead(userId, topicId);
			//判断是否开启楼层展示
			if(checkMap==null ||checkMap.isEmpty()){
				checkMap = aspBizActivityService.getSubjectFloorInfo(topicId,activityHead.getActivity());
				checkMap.put("stor", "1");
			}
			String isFloor = (String) checkMap.get("isFloor");
			Map<String, String> headPic = (Map<String, String>) checkMap.get("headPic");
			model.addAttribute("headPic", headPic);//头图部分放入页面
			
			if(isFloor!=null&&"1".equals(isFloor)){
				//获取headinfo
				ActivityIndex activityIndex = aspBizActivityService.getSearchActivityIndexWithoutSearch(userId,topicId);
				List<Map<String, Object>> floorList = (List<Map<String, Object>>) checkMap.get("floorList");
				if(checkMap.get("stor")!=null &&"1".equals(checkMap.get("stor"))){
					checkMap.remove("stor");
					jedis.set(redisKey, JsonUtil.toJson(checkMap));
					jedis.expire(redisKey, Integer.parseInt(redisTime));
				}
				model.addAttribute("floorList", floorList);
				model.addAttribute("activityIndex", activityIndex);
				model.addAttribute("searchConditions", searchConditions);
				return LIST_FLOOR;
			}else{
				ActivityIndex activityIndex=null;
				SearchResult searchResult=null;
				
				//redis   之前   520微信分享广告推广  
				
				String conditions=JsonUtil.toJson(searchConditions);
				
				String key="actity_key"+topicId+MD5Util.MD5(conditions);
				String jedisVal = JedisUtilFactory.getDefaultJedisUtil().get(key);
				if(jedisVal == null){
				// 筛选项
				activityIndex = aspBizActivityService.searchActivityIndex(userId, topicId, null, null, null, null, null, null, null, null, null, null, null, postArea,"");
				// 商品
				 searchResult = aspBizActivityService.searchActivityProduct(topicId, start, Constants.PRODUCT_LIST_END, searchConditions.getTagId(), searchConditions.getBrandNo(), searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(), searchConditions.getOrder(), searchConditions.getGender(), userLv, postArea,"",activityIndex.getOperat().getActivity());
				
					if("60512985".equals(topicId)){
						SearchCache search=new SearchCache();
						search.cache(searchResult,key);
						search.cache(activityIndex,key+"activity");
					}
				}else{
					logger.info("--------get subject from 缓存"+key);
					String json=JedisUtilFactory.getDefaultJedisUtil().get(key+"activity");
					if(json==null){
						activityIndex = aspBizActivityService.searchActivityIndex(userId, topicId, null, null, null, null, null, null, null, null, null, null, null, postArea,"");
						if("60512985".equals(topicId)){
							SearchCache search=new SearchCache();
							search.cache(activityIndex,key+"activity");
						}
					}else{
						activityIndex=JsonUtil.fromJson(json, ActivityIndex.class);
					}
					searchResult=JsonUtil.fromJson(jedisVal, SearchResult.class);
				}
				int hasPageNum = SearchUtil.hasPageNum(Integer.parseInt(searchResult.getCount()), Integer.parseInt(Constants.PRODUCT_LIST_END));			
				model.addAttribute("activityIndex", activityIndex);
				model.addAttribute("searchConditions", searchConditions);
				model.addAttribute("searchResult", searchResult);
				
				model.addAttribute("hasPageNum", hasPageNum);
				searchConditions.setPostArea(postArea);
				if (StringUtils.isEmpty(searchConditions.getOrder())) {
					model.addAttribute("isHot", "1");
				} else {
					if (searchConditions.getOrder().equals("5")) {
						model.addAttribute("isHot", "0");
					} else {
						model.addAttribute("isHot", "1");
					}
				}
				return NEWLIST;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NEWLIST;
	}

	/**
	 * 
	 * @Title: getNewMore
	 * @Description:活动商品列表加载更多
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月6日
	 */
	@ResponseBody
	@RequestMapping(value = "/new/more", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getNewMore(SearchConditions searchConditions, HttpServletRequest request, String gender, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			User user = getSessionUser(request);
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			String start = String.valueOf(Integer.valueOf(searchConditions.getStart()) + 1);
			String topicId=searchConditions.getTopicId();
			if(!StringUtil.isEmpty(topicId)){
				topicId=topicId.trim();
			}
			String userId=StringUtils.isEmpty(user) ? "" : user.getUserid();
			BrandActivityHead brandActivityHead = aspBizActivityService.getBrandActivityHead(userId, topicId);
			SearchResult searchResult = aspBizActivityService.searchActivityProduct(topicId, start, Constants.PRODUCT_LIST_END, searchConditions.getTagId(), searchConditions.getBrandNo(), searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(), searchConditions.getOrder(), searchConditions.getGender(), userLv, searchConditions.getPostArea(),"",brandActivityHead.getActivity());
			// int hasMore =
			// SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()),Integer.parseInt(start),
			// Integer.parseInt(Constants.PRODUCT_LIST_END));
			// int hasPageNum =
			// SearchUtil.hasPageNum(Integer.parseInt(searchResult.getCount()),Integer.parseInt(Constants.PRODUCT_LIST_END));
			map.put("start", start);
			map.put("searchResult", searchResult);
			map.put("userLv", userLv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 
	 * @Title: getSearchConditions
	 * @Description:活动商品列表筛选条件
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月6日
	 */

	@ResponseBody
	@RequestMapping(value = "/search/conditions", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getSearchConditions(SearchConditions searchConditions, HttpServletRequest request, String gender, Model model) {
		//SearchUtil.SystemConditions(searchConditions);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = getSessionUser(request);
			String topicId=searchConditions.getTopicId();
			if(!StringUtil.isEmpty(topicId)){
				topicId=topicId.trim();
			}
			String userId=StringUtils.isEmpty(user) ? "" : user.getUserid();
			BrandActivityHead brandActivityHead = aspBizActivityService.getBrandActivityHead(userId, topicId);
			SearchResult searchResult = aspBizActivityService.searchActivityProduct(searchConditions.getTopicId(), null, null, searchConditions.getTagId(), null, searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(), searchConditions.getOrder(), searchConditions.getGender(), null, searchConditions.getPostArea(),"",brandActivityHead.getActivity());
			// int hasMore =
			// SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()),Integer.parseInt(start),
			// Integer.parseInt(Constants.PRODUCT_LIST_END));
			// int hasPageNum =
			// SearchUtil.hasPageNum(Integer.parseInt(searchResult.getCount()),Integer.parseInt(Constants.PRODUCT_LIST_END));
			map.put("searchResult", searchResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
