package com.shangpin.wireless.api.util;

import java.util.HashMap;
import java.util.Hashtable;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;

/**
 * 奥莱专题商品列表缓存
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-09-30
 */
@Deprecated
public class AolaiTopicProductsCacheUtil {
	protected final static Log log = LogFactory.getLog(AolaiTopicProductsCacheUtil.class);
	private static final int CACHE_KEEP_TIME	= 12 * 60 * 60 * 1000;	//	缓存驻留时间12小时，过后清空缓存，减小内存占用
	private static long CacheTime;

	// 将奥莱活动商品列表放入内存中
	private static Hashtable<String, String> Products = new Hashtable<String, String>();

//	key = topicNo_picw_pich
	public static String getProducts(String key) {
		return getProducts(key, true);
	}
	public static String getProducts(String key, boolean fromCache) {
		String data = Products.get(key);
		if (data == null || !fromCache) {
			try {
				String[] params = key.split("_");
//				String url = Constants.BASE_URL + "GetTopic/";
//				HashMap<String, String> map = new HashMap<String, String>();
//				map.put("topicNo", params[0]);
				String url = Constants.BASE_URL + "subjectproductlist/";
				HashMap<String, String> map = new HashMap<String, String>();
				//主战接口默认不传分页参数，会返回第一页的100条记录。
				map.put("pageindex", "1");
				map.put("pagesize", "500");
				map.put("subjectNo", params[0]);
				map.put("picurlw", params[1]);
				map.put("picurlh", params[2]);
				String content = WebUtil.readContentFromGet(url, map);

				JSONObject obj = JSONObject.fromObject(content);
				
				data = obj.toString();
				Products.put(key, data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("AolaiTopicProductsCacheUtil：" + e);
			}
		}
		return data;
	}

	// 更新内存中的数据
	public static void update() {
		log.debug("update Aolai Topic Products cache " + Products.size());
		long now = System.currentTimeMillis();
		//if (now > CacheTime + CACHE_KEEP_TIME) {
			Products.clear();
			//CacheTime = now;
		//}
		/*for (String key : Products.keySet()) {
			getProducts(key, false);
		}*/
		//System.out.println("update Aolai Topic Products cache finished " + Products.size());
	}
}
