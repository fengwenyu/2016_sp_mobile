package com.shangpin.wireless.api.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2server.domain.SPGoodsDetailServerResponse;
import com.shangpin.wireless.api.domain.Constants;

/**
 * 功能：读取属性文件config.properties
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-08-9
 */
public final class ProReader extends Properties {
	private static final long serialVersionUID = -143027453907113416L;
	private static ProReader instance;
	private static long lastLoadTime;
	protected static final Log log = LogFactory.getLog(ProReader.class.getSimpleName());

	// 获取 instance
	public static ProReader getInstance() {
		if (instance != null) {
			instance.load();
			return instance;
		} else {
			makeInstance();// 调用创建方法
			return instance;
		}
	}

	// 创建 instance 值
	public static synchronized void makeInstance() {
		if (instance == null) {
			instance = new ProReader();
		}
	}

	// 类的构造函数，读取属性文件
	private ProReader() {
		load();
	}

	private void load() {
		Long now = System.currentTimeMillis();
		if (now - lastLoadTime > 1800000) { // 半小时更新一次
			InputStream is = null;
			try {
				// 将config.properties 文件读取到 InputStream 流中
				is = new FileInputStream(ProReader.class.getResource("/config.properties").getPath());
				clear();
				load(is);
				lastLoadTime = now;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("read properties file fail"+e);
				return;
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (Exception e) {
						e.printStackTrace();
						log.error(e);
					}
				}
			}
		}
	}

	/**
	 * 读取品类首页中的配置文件并写入缓存
	 * 
	 * @param categoryID
	 *            品类ID
	 * @throws Exception
	 */
//	private static void load(String categoryID) throws Exception {
//		// 获取配置文件路径
//		String configFile = ProReader.class.getResource("/config/categoryindex/" + categoryID + "-category-index.properties").getPath();
//		Properties p = new Properties();
//		// 读取、加载配置文件
//		FileInputStream in = new FileInputStream(configFile);
//		p.load(in);
//		// 将配置文件内容写入缓存
//		// DataContainerPool.categoryIndexContainer.putOrReplace(categoryID + "_NEW", p.getProperty("new-products"));
//		// DataContainerPool.categoryIndexContainer.putOrReplace(categoryID + "_RECOMMEND", p.getProperty("recommend-brands"));
//		
//		getNewProducts(p.getProperty("new-products"), categoryID);
//		getRecommendBrands(p.getProperty("recommend-brands"), categoryID);
//		if (in != null) {
//			in.close();
//			in = null;
//		}
//	}

	/**
	 * 重新读取品类首页中的配置文件
	 */
//	public static void loadCategoryIndex() {
//		try {
//		
//			LoadCategoryIndex.load("A01B01");
//			LoadCategoryIndex.load("A01B02");
//			LoadCategoryIndex.load("A01B03");
//			LoadCategoryIndex.load("A01B04");
//			LoadCategoryIndex.load("A02B01");
//			LoadCategoryIndex.load("A02B02");
//			LoadCategoryIndex.load("A02B03");
//			LoadCategoryIndex.load("A02B04");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 获取2个新品商品，并将商品详情数据转换为json对象写入缓存
	 * 
	 * @param jsonStr
	 *            新品商品json字符串
	 * @param categoryid
	 *            品类ID
	 * @return
	 */
//	private static void getNewProducts(String jsonStr, String categoryID) {
//		Map<String, String> map = new HashMap<String, String>();
//		JSONArray jarray = new JSONArray();
//		try {
//			if (StringUtils.isNotEmpty(jsonStr)) {
//				JSONArray array = JSONArray.fromObject(jsonStr);
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject jobj = (JSONObject) array.get(i);
//					// 配置文件中商品id
//					String productid = jobj.getString("productid");
//					map.put("productid", productid);
//					map.put("picw", "{w}");
//					map.put("pich", "{h}");
//					// 获取商品信息，并加入数组中
//					jarray.add(getproduct(map));
//				}
//			}
//		} catch (Exception e) {
//			jarray = new JSONArray();
//			e.printStackTrace();
//		}
//		DataContainerPool.categoryIndexContainer.putOrReplace(categoryID + "_NEW", jarray);
//	}

	/**
	 * 获取推荐品牌列表
	 * 
	 * @param jsonStr
	 * @param categoryid
	 * @return
	 */
//	private static void getRecommendBrands(String jsonStr, String categoryID) {
//		JSONArray array = new JSONArray();
//		try {
//			if (StringUtils.isNotEmpty(jsonStr)) {
//				JSONArray jarray = new JSONArray();
//				Map<String, String> map = new HashMap<String, String>();
//				array = JSONArray.fromObject(jsonStr);
//				if (array.size() > 0) {
//					for (int i = 0; i < array.size(); i++) {
//						JSONObject jobj = (JSONObject) array.get(i);
//						JSONArray prod = (JSONArray) jobj.get("products");
//						jarray.clear();
//						if (prod.size() > 0) {
//							for (int j = 0; j < prod.size(); j++) {
//								String productid = prod.getString(j);
//								map.clear();
//								// 获取商品信息
//								map.put("productid", productid);
//								map.put("picw", "{w}");
//								map.put("pich", "{h}");
//								jarray.add(getproduct(map));
//							}
//							prod.clear();
//							// 重新组装商品信息
//							prod.addAll(jarray);
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			array = new JSONArray();
//			e.printStackTrace();
//		}
//		DataContainerPool.categoryIndexContainer.putOrReplace(categoryID + "_RECOMMEND", array);
//	}

	/**
	 * 通过参数，获取商品简要信息
	 * 
	 * @param map
	 *            productid、userid、picw、pich
	 * @return 商品简要信息（json类型）
	 */
	private static JSONObject getproduct(Map<String, String> map) throws Exception {
		String url = Constants.SP_BASE_URL + "SPProductDetail/";
		SPGoodsDetailServerResponse serverResponse = new SPGoodsDetailServerResponse();
		String data = WebUtil.readContentFromGet(url, map);
		return serverResponse.json2SummaryObj(data);
	}

	/**
	 * 重新读取APP下载地址配置文件，并缓存
	 */
	public static void loadAppAddress() {
		loadProperties2Cache("/config/appAddress.properties", DataContainerPool.appAddressContainer);
	}

	/**
	 * 重新读取文件类型配置文件，并缓存
	 */
	public static void loadFileType() {
		loadProperties2Cache("/config/fileType.properties", DataContainerPool.fileTypeContainer);
	}

	/**
	 * 读取properties配置文件，到缓存中
	 * 
	 * @param path
	 *            properties配置文件路径
	 * @param container
	 *            缓存
	 */
	private static void loadProperties2Cache(String path, DataContainer container) {
		try {
			// 获取配置文件路径
			String configFile = ProReader.class.getResource(path).getPath();
			Properties prop = new Properties();
			// 读取、加载配置文件
			FileInputStream in = new FileInputStream(configFile);
			prop.load(in);
			// 将配置文件内容写入缓存
			for (Object t : prop.keySet()) {
				container.putOrReplace(t.toString(), prop.getProperty(t.toString()));
			}
			if (in != null) {
				in.close();
				in = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
}
