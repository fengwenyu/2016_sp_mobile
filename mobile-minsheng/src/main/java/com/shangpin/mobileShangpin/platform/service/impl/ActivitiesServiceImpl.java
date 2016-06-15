package com.shangpin.mobileShangpin.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Service;

import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.DataContainerPool;
import com.shangpin.mobileShangpin.common.util.DateTimeUtil;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.ActivitiesService;
import com.shangpin.mobileShangpin.platform.vo.ActivitiesVO;
import com.shangpin.mobileShangpin.platform.vo.BrandVo;
import com.shangpin.mobileShangpin.platform.vo.CategoryVo;
import com.shangpin.mobileShangpin.platform.vo.TopicVO;

/**
 * 活动、专题业务逻辑接口实现类，用于获取活动、专题列表
 * 
 * @author yumeng
 * @date:2012-10-29
 */
@Service("activitiesService")
@SuppressWarnings({ "unchecked", "static-access" })
public class ActivitiesServiceImpl implements ActivitiesService {

	/**	今日活动未满6个时，需要补充6个活动 */
	private int MAX_Activities = 6;

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

	@Override
	public List<ActivitiesVO> getActivitiesList(String gender,
			String startTime, String endTime) {
		List<ActivitiesVO> list = null;
		String url = Constants.BASE_SP_URL + "SubjectList/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", gender);
		map.put("pich", Constants.ACTIVITIES_PICTURE_HEIGHT);
		map.put("picw", Constants.ACTIVITIES_PICTURE_WIDTH);
		map.put("starttime", startTime);
		map.put("endtime", endTime);

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
					&& !"[]".equals(jsonObj.get("content").toString())) {
				JSONArray array = (JSONArray) jsonObj.get("content");
				JSONArray js = (JSONArray) array.get(0);
				// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
				list = js.toList(js, new ActivitiesVO(), new JsonConfig());
			}
		}
		return list;
	}

	@Override
	public List<TopicVO> getTopicCarouselListt(String gender) {
		List<TopicVO> list = null;
		String url = Constants.BASE_SP_URL + "SpMobileTopicList/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", gender);
		map.put("pich", Constants.TOPICCAROUSEL_PICTURE_HEIGHT);
		map.put("picw", Constants.TOPICCAROUSEL_PICTURE_WIDTH);
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
					&& !"{}".equals(jsonObj.get("content").toString())) {
				JSONObject contentObj = (JSONObject) jsonObj.get("content");
				if(!"[]".equals(contentObj.get("list").toString()))
				{
					JSONArray array = (JSONArray) contentObj.get("list");
					// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
					list = JSONArray.toList(array, new TopicVO(), new JsonConfig());
				}
				
			}
		}
		return list;
	}

	@Override
	public List<ActivitiesVO> getAllActivitiesList(String gender) {
		List<ActivitiesVO> list = null;
		String url = Constants.BASE_SP_URL + "SelectAllSubjectListAndToday/";
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

	@Override
	public List<ActivitiesVO> getAllEndingActivitiesList(String gender) {
		List<ActivitiesVO> list = null;
		String url = Constants.BASE_SP_URL + "SelectEndingSubjectList/";
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

	@Override
	public List<ActivitiesVO> getAllActivitiesList(String activityId,
			String gender) {
		Object obj=DataContainerPool.activityConfigContainer.get(activityId);
		JSONArray array=(JSONArray)obj;
		List<ActivitiesVO> list=null;
		if (null != obj && !"".equals(obj)) {
			list=new ArrayList<ActivitiesVO>();
			for (int i = 0; i < array.size(); i++) {
				ActivitiesVO activitiesVO = new ActivitiesVO();
				JSONObject activitiesVoJsonObj = array.getJSONObject(i);
				activitiesVO.setActivityid(activitiesVoJsonObj.getString("activityid"));
				activitiesVO.setPic(activitiesVoJsonObj.getString("imgurl"));	
				activitiesVO.setActivityname(activitiesVoJsonObj.getString("activityname"));
				list.add(activitiesVO);
			}


		}
		return list;
	}

	@Override
	public List<BrandVo> getHotBrandList(String brandId) {
		Object obj=DataContainerPool.brandConfigContainer.get(brandId);
		JSONArray array=(JSONArray)obj;
		List<BrandVo> list=null;
		if (null != obj && !"".equals(obj)) {
			list=new ArrayList<BrandVo>();
			list = array.toList(array, new BrandVo(),
					new JsonConfig());


		}
		return list;
	}
}
