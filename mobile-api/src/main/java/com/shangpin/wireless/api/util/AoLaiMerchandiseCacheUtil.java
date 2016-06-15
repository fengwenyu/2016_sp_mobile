package com.shangpin.wireless.api.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.MerchandiseCache;

/**
 * 商品数据缓存
 * 
 * @Author: zhouyu
 * @CreatDate: 2013-05-06
 */
@Deprecated
public class AoLaiMerchandiseCacheUtil {
	protected final static Log log = LogFactory.getLog(AoLaiMerchandiseCacheUtil.class);

	// public final static String ACTIVITY_WOMAN_HIGH = "aolaiActivityWomanHigh";
	// public final static String ACTIVITY_MAN_HIGH = "aolaiActivityManHigh";
	// public final static String TOPIC_WOMAN_HIGH = "aolaiTopicWomanHigh";
	// public final static String TOPIC_MAN_HIGH = "aolaiTopicManHigh";
	//
	// public final static String ACTIVITY_WOMAN_LOW = "aolaiActivityWomanLow";
	// public final static String ACTIVITY_MAN_LOW = "aolaiActivityManLow";
	// public final static String TOPIC_WOMAN_LOW = "aolaiTopicWomanLow";
	// public final static String TOPIC_MAN_LOW = "aolaiTopicManLow";

	public static Map<String, MerchandiseCache> activityMap = new HashMap<String, MerchandiseCache>();
	public static LinkedList<String> activityList = new LinkedList<String>();

	public static Map<String, MerchandiseCache> topicMap = new HashMap<String, MerchandiseCache>();
	public static LinkedList<String> topicList = new LinkedList<String>();

	// 添加活动缓存
	public static void addActivity(String key, MerchandiseCache merchandiseCache) {
		activityMap.put(key, merchandiseCache);
		if (activityList.contains(merchandiseCache.getKey())) {
			activityList.remove(merchandiseCache.getKey());
		}
		activityList.add(merchandiseCache.getKey());
	}

	// 获取活动商品列表
	public static String getActivityMerchandiseList(String key, String type) {
		long currentTimeMillis = System.currentTimeMillis();
		MerchandiseCache merchandiseCache = activityMap.get(key);
		if (merchandiseCache == null) {
			updateActivityMerchandiseList(key);
		} else {
			long timestamp = merchandiseCache.getTimestamp();
			if ((currentTimeMillis - timestamp) > (1000 * 60 * 1)) {
				updateActivityMerchandiseList(key);
			}
		}
		String firstKey = activityList.getFirst();
		MerchandiseCache first = activityMap.get(firstKey);
		if (first != null) {
			long firstTimestamp = first.getTimestamp();
			currentTimeMillis = System.currentTimeMillis();
			if ((currentTimeMillis - firstTimestamp) > (1000 * 60 * 30)) {
				activityList.removeFirst();
				activityMap.remove(firstKey);
			}
		}
		MerchandiseCache cache = activityMap.get(key);
		if ("low".equals(type)) {
			return cache.getLowData();
		} else {
			return cache.getHighData();
		}
	}

	// 更新活动商品列表
	public static void updateActivityMerchandiseList(String key) {
		try {
			String url = Constants.BASE_URL + "subjectproductlist/";
			Map<String, String> activityMerchandiseListMap = new HashMap<String, String>();
			//主战接口默认不传分页参数，会返回第一页的100条记录。
			activityMerchandiseListMap.put("pageindex", "1");
			activityMerchandiseListMap.put("pagesize", "500");
			activityMerchandiseListMap.put("subjectNo", key);
			activityMerchandiseListMap.put("picurlw", "282");
			activityMerchandiseListMap.put("picurlh", "376");
			String activityWomanHighData = WebUtil.readContentFromGet(url, activityMerchandiseListMap);
			activityMerchandiseListMap.put("picurlw", "141");
			activityMerchandiseListMap.put("picurlh", "188");
			String activityWomanLowData = WebUtil.readContentFromGet(url, activityMerchandiseListMap);
			MerchandiseCache cache = new MerchandiseCache();
			cache.setKey(key);
			cache.setHighData(activityWomanHighData);
			cache.setLowData(activityWomanLowData);
			cache.setTimestamp(System.currentTimeMillis());
			AoLaiMerchandiseCacheUtil.addActivity(key, cache);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("MerchandiseCacheUtil-updateActivityMerchandiseList:" + e);
		}
	}

	// 添加专题缓存
	public static void addTopic(String key, MerchandiseCache merchandiseCache) {
		topicMap.put(key, merchandiseCache);
		if (topicList.contains(merchandiseCache.getKey())) {
			topicList.remove(merchandiseCache.getKey());
		}
		topicList.add(merchandiseCache.getKey());
	}

	// 获取专题商品列表
	public static String getTopicMerchandiseList(String key, String type) {
		long currentTimeMillis = System.currentTimeMillis();
		MerchandiseCache merchandiseCache = topicMap.get(key);
		if (merchandiseCache == null) {
			updateTopicMerchandiseList(key);
		} else {
			long timestamp = merchandiseCache.getTimestamp();
			if ((currentTimeMillis - timestamp) > (1000 * 60 * 1)) {
				updateTopicMerchandiseList(key);
			}
		}
		String firstKey = topicList.getFirst();
		MerchandiseCache first = topicMap.get(firstKey);
		if (first != null) {
			currentTimeMillis = System.currentTimeMillis();
			long firstTimestamp = first.getTimestamp();
			if ((currentTimeMillis - firstTimestamp) > (1000 * 60 * 30)) {
				topicList.removeFirst();
				topicMap.remove(firstKey);
			}
		}
		MerchandiseCache cache = topicMap.get(key);
		if ("low".equals(type)) {
			return cache.getLowData();
		} else {
			return cache.getHighData();
		}
	}

	// 更新专题商品列表
	public static void updateTopicMerchandiseList(String key) {
		try {
			String url = Constants.BASE_URL + "GetTopic/";
			Map<String, String> topicMerchandiseListMap = new HashMap<String, String>();
			topicMerchandiseListMap.put("topicNo", key);
			topicMerchandiseListMap.put("picurlw", "282");
			topicMerchandiseListMap.put("picurlh", "376");
			String topicWomanHighData = WebUtil.readContentFromGet(url, topicMerchandiseListMap);
			topicMerchandiseListMap.put("picurlw", "141");
			topicMerchandiseListMap.put("picurlh", "188");
			String activityWomanLowData = WebUtil.readContentFromGet(url, topicMerchandiseListMap);
			MerchandiseCache cache = new MerchandiseCache();
			cache.setKey(key);
			cache.setHighData(topicWomanHighData);
			cache.setLowData(activityWomanLowData);
			cache.setTimestamp(System.currentTimeMillis());
			AoLaiMerchandiseCacheUtil.addTopic(key, cache);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("MerchandiseCacheUtil-updateTopicMerchandiseList:" + e);
		}
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "111111111");
		map.put("2", "11222222");
//		System.out.println(map.size());
	}
}
