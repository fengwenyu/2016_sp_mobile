package com.shangpin.wireless.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.base.service.impl.AoLaiServiceImpl;
import com.shangpin.wireless.api.api2server.domain.TopicAndActivityServerResponse;
import com.shangpin.wireless.api.domain.Constants;

/**
 * 主页数据缓存
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-09-17
 */
public class AolaiIndexCacheUtil {
	protected final static Log log = LogFactory.getLog(AolaiIndexCacheUtil.class);
	// public final static String INDEX_WOMAN_TOPIC_HIGH =
	// "indexWomanTopicHigh";
	// public final static String INDEX_MAN_TOPIC_HIGH = "indexManTopicHigh";
	// public final static String INDEX_WOMAN_ACTIVITY_HIGH =
	// "indexWomanActivityHigh";
	// public final static String INDEX_MAN_ACTIVITY_HIGH =
	// "indexManActivityHigh";
	//
	// public final static String INDEX_WOMAN_TOPIC_LOW = "indexWomanTopicLow";
	// public final static String INDEX_MAN_TOPIC_LOW = "indexManTopicLow";
	// public final static String INDEX_WOMAN_ACTIVITY_LOW =
	// "indexWomanActivityLow";
	// public final static String INDEX_MAN_ACTIVITY_LOW =
	// "indexManActivityLow";

	// // 将首页数据放入内存中
	// private static Map<String, String> aolaiMap = new HashMap<String,
	// String>();
	//
	// public static void add(String key, String data) {
	// aolaiMap.put(key, data);
	// }
	//
	// public static String get(String key) {
	// return aolaiMap.get(key);
	// }

	// 将首页数据放入内存中
	private static Hashtable<String, JSONArray> activity = new Hashtable<String, JSONArray>();

	private static Hashtable<String, JSONArray> topic = new Hashtable<String, JSONArray>();
	// private static Hashtable<String, String> channel = new Hashtable<String,
	// String>();
	private static final int CHANNEL_KEEP_TIME = 120 * 60 * 1000; // 两个小时
	// private static long ChannelTime;

	private static HashMap<String, String> DetailCache = new HashMap<String, String>();
	private static final int DETAIL_KEEP_TIME = 30 * 60 * 1000; // 30分钟
	private static long DetailTime;

	// key = gender_picw_pich
	public static JSONArray getActivity(String key) {
		return getActivity(key, true);
	}

	private static AoLaiService getAolaiService() {
		return ApplicationContextUtil.get("ac").getBean(AoLaiService.class);
	}

	public static JSONArray getActivity(String key, boolean fromCache) {
		JSONArray data = activity.get(key);
		if (data == null || data.size() == 0 || !fromCache) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date d = new Date();
				String starttime = sdf.format(new Date(d.getTime() - 4 * 24 * 60 * 60 * 1000));
				String endtime = sdf.format(new Date(d.getTime() + 5 * 24 * 60 * 60 * 1000));
				String[] params = key.split("_");
				String content = getAolaiService().findSubjectListByPeriod(params[0], starttime, endtime, params[1],  params[2]);

				TopicAndActivityServerResponse topicAndActivityServerResponse = new TopicAndActivityServerResponse();
				topicAndActivityServerResponse.activeJsonToObj(content);
				data = topicAndActivityServerResponse.getContent();

				activity.put(key, data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("AolaiIndexCacheUtil：" + e);
			}
		}
		return data;
	}

	// key = gender_picw_pich
	public static JSONArray getTopic(String key) {
		return getTopic(key, true);
	}

	public static JSONArray getTopic(String key, boolean fromCache) {
		JSONArray data = topic.get(key);
		if (data == null || data.size() == 0 || !fromCache) {
			try {
				String[] params = key.split("_");
				String url = Constants.BASE_URL + "TopicList/";
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("gender", params[0]);
				map.put("picw", params[1]);
				map.put("pich", params[2]);
				String content = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(content);
				JSONArray contentArray = JSONArray.fromObject(obj.getJSONArray("content"));
				if (contentArray == null || "".equals(contentArray.toString()) || "[]".equals(contentArray.toString())) {
					url = Constants.BASE_URL + "newTopicList/";
					map.put("showtype", "0");
					content = WebUtil.readContentFromGet(url, map);
				}
				TopicAndActivityServerResponse topicAndActivityServerResponse = new TopicAndActivityServerResponse();
				topicAndActivityServerResponse.json2Obj(content);
				data = topicAndActivityServerResponse.getContent();
				topic.put(key, data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("AolaiIndexCacheUtil：" + e);
			}
		}
		return data;
	}

	public static JSONArray getNewTopic(String key, boolean fromCache) {
		JSONArray data = topic.get(key);
		if (data == null || data.size() == 0 || !fromCache) {
			try {
				String[] params = key.split("_");
				ListOfGoods listOfGoodsVO = new ListOfGoods();
				listOfGoodsVO.setGender(params[0]);
				listOfGoodsVO.setPicw(params[1]);
				listOfGoodsVO.setPich(params[2]);
				String content = getAolaiService().findMobileTopicList(listOfGoodsVO);
				// JSONObject obj = JSONObject.fromObject(content);
				// JSONArray contentArray =
				// JSONArray.fromObject(obj.getJSONArray("content"));
				// if(contentArray==null||"".equals(contentArray.toString())||"[]".equals(contentArray.toString())){
				// url=Constants.BASE_URL + "newTopicList/";
				// map.put("showtype", "0");
				// content = WebUtil.readContentFromGet(url, map);
				// }
				TopicAndActivityServerResponse topicAndActivityServerResponse = new TopicAndActivityServerResponse();
				topicAndActivityServerResponse.json2Obj(content);
				data = topicAndActivityServerResponse.getContent();
				topic.put(key, data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("AolaiIndexCacheUtil：" + e);
			}
		}
		return data;
	}

	public static JSONArray getActivityIndex(String type, String picw, String pich, String pageIndex, String pageSize) {
		JSONArray data = null;
		try {
			String content = getAolaiService().findAolaiIndex(type, picw, pich, pageIndex, pageSize);
			JSONObject obj = JSONObject.fromObject(content);
			JSONArray contentArray = JSONArray.fromObject(obj.getJSONArray("content"));
			data = contentArray;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("AolaiIndexCacheUtil：" + e);
		}
		return data;
	}

	// 更新内存中的数据
	public static void update() {
		log.debug("update aolai index cache");
		for (String key : activity.keySet()) {
			getActivity(key, false);
		}
		for (String key : topic.keySet()) {
			getTopic(key, false);
		}
		// getActivity("0_620_460", false);
		// getTopic("0_640_640", false);
		// getActivity("1_620_460", false);
		// getTopic("1_640_640", false);
		// getActivity("0_310_230", false);
		// getTopic("0_320_320", false);
		// getActivity("1_310_230", false);
		// getTopic("1_320_320", false);
		// try {
		// // 0 女 1 男
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Date d = new Date();

		// String starttime = sdf.format(new Date(d.getTime() - 4 * 24 * 60 * 60
		// * 1000));
		// String endtime = sdf.format(new Date(d.getTime() + 6 * 24 * 60 * 60 *
		// 1000));
		// String topicUrl = Constants.BASE_URL + "TopicList/";
		// String activityUrl = Constants.BASE_URL + "SubjectList/";
		// //---------------------HIGH---------------------
		// Map<String, String> highTopicMap = new HashMap<String, String>();
		// highTopicMap.put("gender", "0");
		// highTopicMap.put("picw", "640");
		// highTopicMap.put("pich", "640");
		// String highTopicWomanData = WebUtil.readContentFromGet(topicUrl,
		// highTopicMap);
		// AolaiIndexCacheUtil.add(INDEX_WOMAN_TOPIC_HIGH, highTopicWomanData);
		// Map<String, String> highActivityMap = new HashMap<String, String>();
		// highActivityMap.put("starttime", starttime);
		// highActivityMap.put("endtime", endtime);
		// highActivityMap.put("picw", "620");
		// highActivityMap.put("pich", "460");
		// highActivityMap.put("gender", "0");
		// String highActivityWomanData =
		// WebUtil.readContentFromGet(activityUrl, highActivityMap);
		// AolaiIndexCacheUtil.add(INDEX_WOMAN_ACTIVITY_HIGH,
		// highActivityWomanData);
		// highTopicMap.put("gender", "1");
		// String highTopicManData = WebUtil.readContentFromGet(topicUrl,
		// highTopicMap);
		// AolaiIndexCacheUtil.add(INDEX_MAN_TOPIC_HIGH, highTopicManData);
		// highActivityMap.put("gender", "1");
		// String highActivityManData = WebUtil.readContentFromGet(activityUrl,
		// highActivityMap);
		// AolaiIndexCacheUtil.add(INDEX_MAN_ACTIVITY_HIGH,
		// highActivityManData);
		// //---------------------LOW---------------------
		// Map<String, String> lowTopicMap = new HashMap<String, String>();
		// lowTopicMap.put("gender", "0");
		// lowTopicMap.put("picw", "320");
		// lowTopicMap.put("pich", "320");
		// String lowTopicWomanData = WebUtil.readContentFromGet(topicUrl,
		// lowTopicMap);
		// AolaiIndexCacheUtil.add(INDEX_WOMAN_TOPIC_LOW, lowTopicWomanData);
		// Map<String, String> lowActivityMap = new HashMap<String, String>();
		// lowActivityMap.put("starttime", starttime);
		// lowActivityMap.put("endtime", endtime);
		// lowActivityMap.put("picw", "310");
		// lowActivityMap.put("pich", "230");
		// lowActivityMap.put("gender", "0");
		// String lowActivityWomanData = WebUtil.readContentFromGet(activityUrl,
		// lowActivityMap);
		// AolaiIndexCacheUtil.add(INDEX_WOMAN_ACTIVITY_LOW,
		// lowActivityWomanData);
		// lowTopicMap.put("gender", "1");
		// String lowTopicManData = WebUtil.readContentFromGet(topicUrl,
		// lowTopicMap);
		// AolaiIndexCacheUtil.add(INDEX_MAN_TOPIC_LOW, lowTopicManData);
		// lowActivityMap.put("gender", "1");
		// String lowActivityManData = WebUtil.readContentFromGet(activityUrl,
		// lowActivityMap);
		// AolaiIndexCacheUtil.add(INDEX_MAN_ACTIVITY_LOW, lowActivityManData);
		// } catch (Exception e) {
		// e.printStackTrace();
		// log.error("AolaiIndexCacheUtil-update:" + e);
		// }
		log.debug("update aolai index cache finished");
	}

	public static JSONArray queryGroupTime(String picw, String pich) {
		JSONArray data = null;
		try {
			String content = getAolaiService().findGroupTime(picw, pich);
			JSONObject obj = JSONObject.fromObject(content);
			JSONArray contentArray = JSONArray.fromObject(obj.getJSONArray("content"));
			data = contentArray;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("AolaiIndexCacheUtil：" + e);
		}
		return data;
	}

	public static JSONArray queryAdActivity(String channelNo, String picw, String pich) {
		JSONArray data = null;
		try {
			String content = getAolaiService().findAoLaiADList(channelNo, picw, pich);
			JSONObject obj = JSONObject.fromObject(content);
			JSONArray contentArray = JSONArray.fromObject(obj.getJSONArray("content"));
			data = contentArray;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("AolaiIndexCacheUtil：" + e);
		}
		return data;
	}

	public static JSONArray queryCollection(String userId, String starttime, String endtime, String type, String picw, String pich, String pageindex, String pagesize) {
		JSONArray data = null;
		try {
			String url = Constants.BASE_URL + "queryCollection/";
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("startTime", starttime);
			map.put("endTime", endtime);
			map.put("picw", picw);
			map.put("pich", pich);
			map.put("type", type);
			map.put("pageIndex", pageindex);
			map.put("pageSize", pagesize);
			map.put("userId", userId);
			String content = WebUtil.readContentFromGet(url, map);
			JSONObject obj = JSONObject.fromObject(content);
			if (obj != null && !obj.equals("{}")) {
				String contentObject = obj.getString("content");
				if (contentObject != null && !contentObject.equals("{}")) {
					JSONArray contentArray = JSONArray.fromObject(contentObject);
					data = contentArray;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("AolaiIndexCacheUtil：" + e);
		}
		return data;
	}

	public static String getChannel(String id) {
		String data = getAolaiService().findChannel();
		if (data != null) {
			try {
				JSONObject obj = JSONObject.fromObject(data);
				JSONArray contentArray = JSONArray.fromObject(obj.getJSONObject("content").getJSONArray("list"));
				for (int i = 0; i < contentArray.size(); i++) {
					JSONObject object = (JSONObject) contentArray.get(i);
					String tmpId = object.getString("id");
					if (tmpId != null && tmpId.equals(id)) {
						return object.getString("name");
					}
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	public static String getDetail(String name) {
		long now = System.currentTimeMillis();
		if (now > DetailTime + DETAIL_KEEP_TIME) {
			Properties props = PropertiesUtil.getInstance("/aolaiindex/aolaiindex.properties");
			DetailCache.clear();
			for (Enumeration e = props.keys(); e.hasMoreElements();) {
				String propkey = (String) e.nextElement();
				String propvalue = null;
				if (StringUtil.isNotEmpty(props.getProperty(propkey))) {
					propvalue = new String(props.getProperty(propkey));
				}

				DetailCache.put(propkey, propvalue);
			}
			DetailTime = now;
		}
		return DetailCache.get(name);
	}
}
