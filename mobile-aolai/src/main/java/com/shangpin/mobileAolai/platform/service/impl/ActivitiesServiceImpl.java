package com.shangpin.mobileAolai.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Service;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.DateTimeUtil;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.ActivitiesService;
import com.shangpin.mobileAolai.platform.vo.ActivitiesVO;
import com.shangpin.mobileAolai.platform.vo.TopicVO;

/**
 * 活动、专题业务逻辑接口实现类，用于获取活动、专题列表
 * 
 * @author yumeng
 * @date:2012-10-29
 */
@Service("activitiesService")
public class ActivitiesServiceImpl implements ActivitiesService {

	/**	今日活动未满6个时，需要补充6个活动 */
	private int MAX_Activities = 6;

	@Resource
	private AoLaiService aoLaiService;
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivitiesVO> get24ActivitiesList(String gender) {
		List<ActivitiesVO> list = null;
		String url = Constants.BASE_URL + "endingSubjectList/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", gender);
		map.put("pich", Constants.ACTIVITIES_PICTURE_HEIGHT);
		map.put("picw", Constants.ACTIVITIES_PICTURE_WIDTH);
		String json = null;
		try {
			// 获取专题轮播广告json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}

		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj
					&& Constants.SUCCESS.equals(jsonObj.get("code"))
					&& !"[]".equals(jsonObj.get("content").toString())) {
				JSONArray array = (JSONArray) jsonObj.get("content");
				// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
				list = JSONArray.toList(array, new ActivitiesVO(),
						new JsonConfig());
			}
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public List<ActivitiesVO> getActivitiesList(String gender,
			String startTime, String endTime) {
		List<ActivitiesVO> list = null;

		String json = null;
		try {
			// 获取活动json格式字符串
			json = aoLaiService.findSubjectListByPeriod(gender, startTime, endTime, Constants.ACTIVITIES_PICTURE_WIDTH,  Constants.ACTIVITIES_PICTURE_HEIGHT);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}

		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj
					&& Constants.SUCCESS.equals(jsonObj.get("code"))
					&& !"[]".equals(jsonObj.get("content").toString())) {
				JSONArray array = (JSONArray) jsonObj.get("content");
				JSONArray js = (JSONArray) array.get(0);
				// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
				list = js.toList(js, new ActivitiesVO(), new JsonConfig());
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopicVO> getTopicCarouselListt(String gender) {
		List<TopicVO> list = null;
		String json = null;
		try {
			// 获取专题轮播广告json格式字符串
			ListOfGoods listOfGoodsVO = new ListOfGoods();
			listOfGoodsVO.setGender(gender);
			listOfGoodsVO.setPicw(Constants.TOPICCAROUSEL_PICTURE_WIDTH);
			listOfGoodsVO.setPich(Constants.TOPICCAROUSEL_PICTURE_HEIGHT);
			json = aoLaiService.findMobileTopicList(listOfGoodsVO );
			if (null != json && !"".equals(json)) {
				JSONObject jsonObj = JSONObject.fromObject(json);
				JSONArray contentArray=jsonObj.getJSONArray("content");
				if(contentArray==null||"".equals(contentArray.toString())||"[]".equals(contentArray.toString())){
                    String url=Constants.BASE_URL + "newtopiclist/";
            		// 组装参数
            		Map<String, String> map = new HashMap<String, String>();
            		map.put("gender", gender);
            		map.put("pich", Constants.TOPICCAROUSEL_PICTURE_HEIGHT);
            		map.put("picw", Constants.TOPICCAROUSEL_PICTURE_WIDTH);
                    map.put("showtype", "1");
                    json = WebUtil.readContentFromGet(url, map);        
                    jsonObj = JSONObject.fromObject(json);
                }
				if (null != jsonObj
						&& Constants.SUCCESS.equals(jsonObj.get("code"))
						&& !"[]".equals(jsonObj.get("content").toString())) {
					JSONArray array = (JSONArray) jsonObj.get("content");
					// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
					list = JSONArray.toList(array, new TopicVO(), new JsonConfig());
					if(list.size()>3){
					    List<TopicVO> lastList=new ArrayList<TopicVO>();
					    for(int i=0;i<list.size();i++){
					        lastList.add(list.get(i));
					        if(lastList.size()==3){
					            break;
					        }
					    }
					    list=lastList;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public List<ActivitiesVO> getAllActivitiesList(String gender) {
		List<ActivitiesVO> list = null;
		String url = Constants.BASE_URL + "SelectAllSubjectListAndToday/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", gender);
		map.put("pich", Constants.ACTIVITIES_PICTURE_HEIGHT);
		map.put("picw", Constants.ACTIVITIES_PICTURE_WIDTH);

		String json = null;
		try {
			// 获取活动json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}

		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj
					&& Constants.SUCCESS.equals(jsonObj.get("code"))
					&& !"[ ]".equals(jsonObj.get("content").toString())) {
				JSONObject contentObj = (JSONObject) jsonObj.get("content");
				if (null != contentObj.get("list")
						&& !"[ ]".equals(contentObj.get("list").toString())) {
					JSONArray array = (JSONArray) contentObj.get("list");
					// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
					list = array.toList(array, new ActivitiesVO(),
							new JsonConfig());
				}
			}
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public List<ActivitiesVO> getAllEndingActivitiesList(String gender) {
		List<ActivitiesVO> list = null;
		String json = null;
		try {
			// 获取活动json格式字符串
			json = aoLaiService.findEndingSubjectList(gender, Constants.ACTIVITIES_PICTURE_WIDTH, Constants.ACTIVITIES_PICTURE_HEIGHT);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}

		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj
					&& Constants.SUCCESS.equals(jsonObj.get("code"))
					&& !"[ ]".equals(jsonObj.get("content").toString())) {
				JSONObject contentObj = (JSONObject) jsonObj.get("content");
				if (null != contentObj.get("list")
						&& !"[ ]".equals(contentObj.get("list").toString())) {
					JSONArray array = (JSONArray) contentObj.get("list");
					// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
					list = array.toList(array, new ActivitiesVO(),
							new JsonConfig());
				}
			}
		}
		return list;
	}

	@Override
	public List<ActivitiesVO> getNewActivitiesList(String gender,
			String startTime, String endTime) {
		// 获取今日活动列表
		List<ActivitiesVO> list = this.getActivitiesList(gender, startTime,
				endTime);
		if (null == list || list.size() <= 0) {
			list = new ArrayList<ActivitiesVO>();
		}
		if (list.size() < MAX_Activities) {
			// 获取前2日活动列表
			List<ActivitiesVO> allList = this.getActivitiesList(gender,DateTimeUtil.shortFmt(DateTimeUtil
					.getAppointDayFromToday(DateTimeUtil.getShortDate(startTime), -2)),startTime);

			if (null != allList && allList.size() > 0) {
				for (int i = 0; i < allList.size(); i++) {
					if (list.size() < MAX_Activities) {
						list.add(allList.get(i));
					}
				}
			}
		}
		return list;
	}
}
