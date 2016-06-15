package com.shangpin.mobileShangpin.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

/**
 * 功能：读取属性文件config.properties
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-08-9
 */
@SuppressWarnings("unchecked")
public final class ProReader extends Properties {
	private static final long serialVersionUID = -143027453907113416L;
	// 定义ProReader 类静态对象 instance
	private static ProReader instance;
	private static long lastLoadTime;
	private static List<String> wxunlimitedurls;

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
			// 将config.properties 文件读取到 InputStream 流中
			InputStream is = ProReader.class.getClassLoader().getResourceAsStream("config.properties");
			try {
				// 从 InputStream 流中读取属性列表(键-值对)
				load(is);
				lastLoadTime = now;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("读取属性文件失败，请确认文件是否存在！");
				return;
			}
		}
	}

	/**
	 * 读取活动首页中的配置文件并写入缓存
	 * 
	 * @param ID
	 *            品类ID
	 * @throws Exception
	 */
	public static void load(String str) throws Exception {
		if ("man-brand-index".equals(str)) {// 尚品男士品牌配置
			// 获取配置文件路径
			String configFile = ProReader.class.getResource("/resources/config/activityindex/man-brand-index.properties").getPath();
			Properties p = new Properties();
			// 读取、加载配置文件
			FileInputStream in = new FileInputStream(configFile);
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			p.load(bf);
			// 将配置文件内容写入缓存
			getBrandContent(p.getProperty("brand_content"));
			if (in != null) {
				in.close();
				in = null;
			}
		} else if ("man".equals(str)) {// 尚品男士活动
			// 获取配置文件路径
			String configFile = ProReader.class.getResource("/resources/config/activityindex/" + str + "-activity-index.properties").getPath();
			Properties p = new Properties();
			// 读取、加载配置文件
			FileInputStream in = new FileInputStream(configFile);
			p.load(in);
			// 将配置文件内容写入缓存
			getActivityContent(p.getProperty("activity_content"), str);
			if (in != null) {
				in.close();
				in = null;
			}
		} else if("paymentch".equals(str)){//不同渠道获取不同支付方式
			// 获取配置文件路径
			String configFile = ProReader.class.getResource("/resources/config/payment/" + str + ".properties").getPath();
			Properties p = new Properties();
			// 读取、加载配置文件
			FileInputStream in = new FileInputStream(configFile);
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			p.load(bf);
			// 将配置文件内容写入缓存
			getPaymentContent(p.getProperty("payment_content"), str);
			if (in != null) {
				in.close();
				in = null;
			}
		}
	}

	/**
	 * 获取专题活动列表
	 * 
	 * @param jsonStr
	 * @return
	 */
	private static void getActivityContent(String jsonStr, String gender) {
		JSONArray array = new JSONArray();
		try {
			if (StringUtils.isNotEmpty(jsonStr)) {
				array = JSONArray.fromObject(jsonStr);
			}
		} catch (Exception e) {
			array = new JSONArray();
			e.printStackTrace();
		}
		DataContainerPool.activityConfigContainer.putOrReplace(gender + "_ACTIVITY", array);
	}

	/**
	 * 获取推荐品牌列表
	 * 
	 * @param jsonStr
	 * @return
	 */
	private static void getBrandContent(String jsonStr) {
		JSONArray array = new JSONArray();
		try {
			if (StringUtils.isNotEmpty(jsonStr)) {
				array = JSONArray.fromObject(jsonStr);
			}
		} catch (Exception e) {
			array = new JSONArray();
			e.printStackTrace();
		}
		DataContainerPool.brandConfigContainer.putOrReplace("man_BRAND", array);
	}

	/**
	 * 获取支付方式
	 * 
	 * @param jsonStr
	 * @return
	 */
	private static void getPaymentContent(String jsonStr, String str) {
		JSONArray array = new JSONArray();
		try {
			if (StringUtils.isNotEmpty(jsonStr)) {
				array = JSONArray.fromObject(jsonStr);
			}
		} catch (Exception e) {
			array = new JSONArray();
			e.printStackTrace();
		}
		DataContainerPool.paymentConfigContainer.putOrReplace("payment", array);
	}

	/**
	 * 重新读取品类首页中的配置文件
	 */
	public static void loadIndex() {
		try {
			load("man");
			load("man-brand-index");
			load("paymentch");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取wxunlimitedurl.properties配置文件（跳转无访问限制的页面），并保存到内存中
	 */
	public static void loadWXUnlimitedURL() {
		Properties prop = null;
		try {
			// 获取配置文件路径
			String configFile = FileUtil.class.getResource("/resources/config/weixin/wxunlimitedurl.properties").getPath();
			prop = new Properties();
			// 读取、加载配置文件
			FileInputStream in = new FileInputStream(configFile);
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
			prop = null;
		}
		try {
			if (null != prop && prop.size() > 0) {
				Set<Object> keyValue = prop.keySet();
				for (Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
					String key = it.next().toString();
					String value = prop.getProperty(key);
					JSONArray jarray = JSONArray.fromObject(value);
					wxunlimitedurls = JSONArray.toList(jarray, new String(), new JsonConfig());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getWxunlimitedurls() {
		return wxunlimitedurls;
	}
}
