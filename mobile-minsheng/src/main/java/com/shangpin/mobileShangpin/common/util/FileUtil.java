package com.shangpin.mobileShangpin.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.shangpin.mobileShangpin.businessbean.TopicConfig;

/**
 * 日志的输出和解析
 * 
 * @Author zhouyu
 * @CreatDate 2012-08-31
 */
public class FileUtil {
	private static Vector<String> dataVector = new Vector<String>();

	/**
	 * 将日志写入文件
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-31
	 * @param
	 * @Return void
	 */
	public static void write() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fileName = sdf.format(new Date()) + ".txt";
			// String path = "/home/api/logs/";
			String path = ProReader.getInstance().getProperty("clientOperationLogsPath");
			File filePath = new File(path);
			if (!filePath.exists())
				filePath.mkdirs();
			RandomAccessFile randomFile = new RandomAccessFile(path + fileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			for (String log : dataVector) {
				randomFile.write((log + "\n").getBytes("UTF-8"));
			}
			dataVector.clear();
			// for (int i = dataVector.size() - 1; i >= 0; i--) {
			// String log = dataVector.get(i);
			// randomFile.write((log + "\n").getBytes());
			// dataVector.remove(i);
			// }
			randomFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析日志文件
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-31
	 * @param
	 * @Return void
	 */
	public static void parse() {
	}

	/**
	 * 将日志加入内存中
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-31
	 * @param request
	 *            作用域对象
	 * @param interfaceName
	 *            接口名称
	 * @param params
	 *            动态参数绑定
	 * @Return void
	 */
	public synchronized static void addLog(HttpServletRequest request, String interfaceName, String channelNo, String... params) {
		String ip = request.getRemoteAddr();
		// imei、os、osv、p、ch、ver、apn、wh、model、mt、operator运营商
		String imei = request.getHeader("imei");
		String os = request.getHeader("os");// 手机平台:ios、android
		String osv = request.getHeader("osv");// 系统版本
		String productNo = "10000";// 产品号
		String version = request.getHeader("ver");// 版本号
		String apn = request.getHeader("apn");// 接入点
		String wh = request.getHeader("wh");// 屏幕宽高640x960
		String mt = request.getHeader("mt"); // 手机类型（gsm，cdma）
		String model = request.getHeader("model"); // 手机型号(iPhone 4S)
		String operator = request.getHeader("operator"); // 运营商
		String ua = request.getHeader("User-Agent");// ua
		// String browser = getBrowser(ua);// 浏览器
		// System.out.println(browser);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append(ip == null || "".equals(ip) ? "#" : ip);
		sb.append("|");
		sb.append(System.currentTimeMillis());
		sb.append("|");
		sb.append(interfaceName == null || "".equals(interfaceName) ? "#" : interfaceName);
		sb.append("|");
		sb.append(imei == null || "".equals(imei) ? "#" : imei);
		sb.append("%");
		sb.append(os == null || "".equals(os) ? "#" : os);
		sb.append("%");
		sb.append(osv == null || "".equals(osv) ? "#" : osv);
		sb.append("%");
		sb.append(productNo == null || "".equals(productNo) ? "#" : productNo);
		sb.append("%");
		sb.append(channelNo == null || "".equals(channelNo) ? "#" : channelNo);
		sb.append("%");
		sb.append(version == null || "".equals(version) ? "#" : version);
		sb.append("%");
		sb.append(apn == null || "".equals(apn) ? "#" : apn);
		sb.append("%");
		sb.append(wh == null || "".equals(wh) ? "#" : wh);
		sb.append("%");
		sb.append(mt == null || "".equals(mt) ? "#" : mt);
		sb.append("%");
		sb.append(model == null || "".equals(model) ? "#" : model);
		sb.append("%");
		sb.append(operator == null || "".equals(operator) ? "#" : operator);
		sb.append("%");
		sb.append(ua == null || "".equals(ua) ? "#" : ua);
		// sb.append("%");
		// sb.append(browser == null || "".equals(browser) ? "#" : browser);
		sb.append("|");
		final int length = params.length >> 1;
		for (int i = 0; i < length; i++) {// #为占位符
			sb.append(params[i << 1] == null ? "#" : params[i << 1]);
			sb.append(":");
			sb.append(params[(i << 1) + 1] == null ? "#" : params[(i << 1) + 1]);
			if (length - 1 != i)
				sb.append(",");
		}
		// System.out.println(sb.toString());
		dataVector.add(sb.toString());
		if (dataVector.size() >= 10) {
			write();
		}
	}

	/**
	 * 将日志加入内存中
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-31
	 * @param request
	 *            作用域对象
	 * @param interfaceName
	 *            接口名称
	 * @param params
	 *            已组织好的参数
	 * @Return void
	 */
	public synchronized static void addLog(HttpServletRequest request, String interfaceName, String channelNo, String params) {
		String ip = request.getRemoteAddr();
		// imei、os、osv、p、ch、ver、apn、wh、model、mt、operator运营商
		String imei = request.getHeader("imei");
		String os = request.getHeader("os");// 手机平台:ios、android
		String osv = request.getHeader("osv");// 系统版本
		String productNo = "10000";// 产品号
		String version = request.getHeader("ver");// 版本号
		String apn = request.getHeader("apn");// 接入点
		String wh = request.getHeader("wh");// 屏幕宽高640x960
		String mt = request.getHeader("mt"); // 手机类型（gsm，cdma）
		String model = request.getHeader("model"); // 手机型号(iPhone 4S)
		String operator = request.getHeader("operator"); // 运营商
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ua = request.getHeader("User-Agent");// ua
		// String browser = getBrowser(ua);// 浏览器
		// System.out.println(browser);
		StringBuilder sb = new StringBuilder();
		sb.append(ip == null || "".equals(ip) ? "#" : ip);
		sb.append("|");
		sb.append(System.currentTimeMillis());
		sb.append("|");
		sb.append(interfaceName == null || "".equals(interfaceName) ? "#" : interfaceName);
		sb.append("|");
		sb.append(imei == null || "".equals(imei) ? "#" : imei);
		sb.append("%");
		sb.append(os == null || "".equals(os) ? "#" : os);
		sb.append("%");
		sb.append(osv == null || "".equals(osv) ? "#" : osv);
		sb.append("%");
		sb.append(productNo == null || "".equals(productNo) ? "#" : productNo);
		sb.append("%");
		sb.append(channelNo == null || "".equals(channelNo) ? "#" : channelNo);
		sb.append("%");
		sb.append(version == null || "".equals(version) ? "#" : version);
		sb.append("%");
		sb.append(apn == null || "".equals(apn) ? "#" : apn);
		sb.append("%");
		sb.append(wh == null || "".equals(wh) ? "#" : wh);
		sb.append("%");
		sb.append(mt == null || "".equals(mt) ? "#" : mt);
		sb.append("%");
		sb.append(model == null || "".equals(model) ? "#" : model);
		sb.append("%");
		sb.append(operator == null || "".equals(operator) ? "#" : operator);
		sb.append("%");
		sb.append(ua == null || "".equals(ua) ? "#" : ua);
		// sb.append("%");
		// sb.append(browser == null || "".equals(browser) ? "#" : browser);
		sb.append("|");
		sb.append(params);
		dataVector.add(sb.toString());
		// System.out.println(sb.toString());
		if (dataVector.size() >= 10) {
			write();
		}
	}
	
	/**
	 * 将银联工具类日志加入内存中
	 * 
	 * @param request
	 *            作用域对象
	 * @param interfaceName
	 *            接口名称
	 * @param params
	 *            动态参数绑定
	 * @Return void
	 */
	public synchronized static void unionutilAddLog(HttpServletRequest request, String interfaceName, String... params) {
		String ip = null;
		// imei、os、osv、p、ch、ver、apn、wh、model、mt、operator运营商
		String imei = null;
		String os = null;// 手机平台:ios、android
		String osv = null;// 系统版本
		String productNo = null;// 产品号
		String channelNo = null;// 渠道号
		String version = null;// 版本号
		String apn = null;// 接入点
		String wh = null;// 屏幕宽高640x960
		String mt = null; // 手机类型（gsm，cdma）
		String model = null; // 手机型号(iPhone 4S)
		String operator = null; // 运营商
		if (request != null) {
			ip = request.getRemoteAddr();
			// imei、os、osv、p、ch、ver、apn、wh、model、mt、operator运营商
			imei = request.getHeader("imei");
			os = request.getHeader("os");// 手机平台:ios、android
			osv = request.getHeader("osv");// 系统版本
			productNo = request.getHeader("p");// 产品号
			channelNo = request.getHeader("ch");// 渠道号
			version = request.getHeader("ver");// 版本号
			apn = request.getHeader("apn");// 接入点
			wh = request.getHeader("wh");// 屏幕宽高640x960
			mt = request.getHeader("mt"); // 手机类型（gsm，cdma）
			model = request.getHeader("model"); // 手机型号(iPhone 4S)
			operator = request.getHeader("operator"); // 运营商
		}
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append((ip == null || "".equals(ip)) ? "#" : ip);
		sb.append("|");
		sb.append(System.currentTimeMillis());
		sb.append("|");
		sb.append((interfaceName == null || "".equals(interfaceName)) ? "#" : interfaceName);
		sb.append("|");
		sb.append((imei == null || "".equals(imei)) ? "#" : imei);
		sb.append(",");
		sb.append((os == null || "".equals(os)) ? "#" : os);
		sb.append(",");
		sb.append((osv == null || "".equals(osv)) ? "#" : osv);
		sb.append(",");
		sb.append((productNo == null || "".equals(productNo)) ? "#" : productNo);
		sb.append(",");
		sb.append((channelNo == null || "".equals(channelNo)) ? "#" : channelNo);
		sb.append(",");
		sb.append((version == null || "".equals(version)) ? "#" : version);
		sb.append(",");
		sb.append((apn == null || "".equals(apn)) ? "#" : apn);
		sb.append(",");
		sb.append((wh == null || "".equals(wh)) ? "#" : wh);
		sb.append(",");
		sb.append((mt == null || "".equals(mt)) ? "#" : mt);
		sb.append(",");
		sb.append((model == null || "".equals(model)) ? "#" : model);
		sb.append(",");
		sb.append((operator == null || "".equals(operator)) ? "#" : operator);
		sb.append("|");
		final int length = params.length >> 1;
		for (int i = 0; i < length; i++) {// #为占位符
			sb.append(params[i << 1] == null ? "#" : params[i << 1]);
			sb.append(":");
			sb.append(params[(i << 1) + 1] == null ? "#" : params[(i << 1) + 1]);
			if (length - 1 != i)
				sb.append(",");
		}
		dataVector.add(sb.toString());
		if (dataVector.size() >= 10) {
			write();
		}
	}
	public static Vector<String> getDataVector() {
		return dataVector;
	}

	public static String getBrowser(String ua) {
		// if(ua == null || "".equals(ua)) {
		// return "";
		// }else if(ua.indexOf("Opera") > -1){
		// return "Opera";
		// }else if(ua.indexOf("Firefox") > -1){
		// return "Firefox";
		// }else if(ua.indexOf("Safari") > -1){
		// return "Safari";
		// }else if(ua.indexOf("compatible") > -1 && ua.indexOf("MSIE") > -1 && !(ua.indexOf("Opera") > -1)){
		// return "IE";
		// }
		if (ua == null || "".equals(ua)) {
			return "";
		} else if (ua.indexOf("Trident") > -1) {
			return "IE内核";
		} else if (ua.indexOf("Presto") > -1) {
			return "opera内核";
		} else if (ua.indexOf("AppleWebKit") > -1) {
			return "苹果、谷歌内核";
		} else if (ua.indexOf("Gecko") > -1 && ua.indexOf("KHTML") > -1) {
			return "火狐内核";
		} else if (ua.indexOf("Android") > -1 || ua.indexOf("Linux") > -1) {
			return "android终端或者uc浏览器";
		} else if (ua.indexOf("iPhone") > -1 || ua.indexOf("Mac") > -1) {
			return "iPhone或者QQHD浏览器";
		} else if (ua.indexOf("iPad") > -1) {
			return "iPad";
		} else if (ua.indexOf("Safari") == -1) {
			return "web应该程序";
		}
		return "";
	}

	/**
	 * 读取topicConfig.properties配置文件，转换为TopicConfig类型，并保存到内存中
	 */
	public static void loadTopicConfig() {
		Properties prop = null;
		try {
			// 获取配置文件路径
			String configFile = FileUtil.class.getResource("/topicConfig.properties").getPath();
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
				DataContainerPool.topicConfigContainer.clear();
				for (Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
					String key = it.next().toString();
					String value = prop.getProperty(key);
					TopicConfig tc = new TopicConfig();
					tc.json2Obj(value);
					// 将进行的活动保存到缓存
					if (tc.getStatus() == 0 && tc.getEndDate().getTime() > new Date().getTime())
						DataContainerPool.topicConfigContainer.putOrReplace(tc.getCh(), tc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试
	public static void main(String[] args) {
		// for (int i = 0; i < 1000; i++) {
		// dataVector.add("访问" + i);
		// }
		// write();
		// long timeMillis = System.currentTimeMillis();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println(sdf.format(new Date(timeMillis)));
		// for (int i = 0; i < 2001; i++) {
		// final int count = i;
		// Thread t = new Thread(new Runnable() {
		// public void run() {
		// try {
		// java.net.URL url = new java.net.URL("http://192.168.5.243:8080/api/activitylist?actid=20120810328");
		// // java.net.URL url = new java.net.URL("http://127.0.0.1:8080/api/activitylist?actid=20120810328");
		// java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
		// conn.setRequestMethod("GET");
		// conn.setRequestProperty("imei", "123");
		// int resCode = conn.getResponseCode();
		// System.out.println(count + " resCode = " + resCode);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// });
		// t.start();
		// }
	}
}
