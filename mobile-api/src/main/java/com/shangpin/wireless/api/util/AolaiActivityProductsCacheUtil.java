package com.shangpin.wireless.api.util;

import java.util.HashMap;
import java.util.Hashtable;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;

/**
 * 奥莱活动商品列表数据缓存
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-09-30
 */
@Deprecated
public class AolaiActivityProductsCacheUtil {
	protected final static Log log = LogFactory.getLog(AolaiActivityProductsCacheUtil.class);
	private static final int CACHE_KEEP_TIME	= 12 * 60 * 60 * 1000;	//	缓存驻留时间12小时，过后清空缓存，减小内存占用
	private static long CacheTime;
	
	// 将奥莱活动商品列表放入内存中
	private static Hashtable<String, String> Products = new Hashtable<String, String>();

//	key = subjectNo_picw_pich
	public static String getProducts(String key) {
		return getProducts(key, true);
	}
	public static String getProducts (String key, boolean fromCache) {
		String data = Products.get(key);
		if (data == null || !fromCache) {
			try {
				String[] params = key.split("_");
				data = getProductsInterface (params[0], params[1], params[2]);
				Products.put(key, data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("AolaiActivityProductsCacheUtil：" + e);
			}
		}
		return data;
	}
	public static String getProductsInterface (String subjectNo, String picw, String pich) {
		String data = null;
		try {
			String url = Constants.BASE_URL + "subjectproductlist/";
			HashMap<String, String> map = new HashMap<String, String>();
			//主战接口默认不传分页参数，会返回第一页的100条记录。
			map.put("pageindex", "1");
			map.put("pagesize", "500");
			map.put("subjectNo", subjectNo);
			map.put("picurlw", picw);
			map.put("picurlh", pich);
			String content = WebUtil.readContentFromGet(url, map);

			JSONObject obj = JSONObject.fromObject(content);
			
			data = obj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("AolaiActivityProductsCacheUtil：" + e);
		}
		return data;
	}

	// 更新内存中的数据
	public static void update() {
		log.debug("update Aolai Activity Products cache  start" + Products.size());
		Products.clear();
//		long now = System.currentTimeMillis();
//		if (now > CacheTime + CACHE_KEEP_TIME) {
//			Products.clear();
//			CacheTime = now;
//		}
//		Hashtable<String, String> products = new Hashtable<String, String>(Products);
//		for (String key : products.keySet()) {
//			String[] params = key.split("_");
//			String data = getProductsInterface (params[0], params[1], params[2]);
//			products.put(key, data);
//		}
//		Products = products;
		log.debug("update Aolai Activity Products cache finished " + Products.size());
	}
}
