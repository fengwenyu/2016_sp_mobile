package com.shangpin.mobileAolai.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;

/**
 * 功能：读取属性文件config.properties
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-08-9
 */
public final class ProReader extends Properties {

	private static final long serialVersionUID = -143027453907113416L;
	// 定义ProReader 类静态对象 instance
	private static ProReader instance;
	private static long lastLoadTime;

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
	private void load () {
		Long now = System.currentTimeMillis();
		if (now - lastLoadTime > 1800000) {	//	半小时更新一次
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
	public static void load (String str) {
		if("paymentch".equals(str)){
			// 获取配置文件路径
			String configFile = ProReader.class.getResource("/resources/config/paymentch/" + str + ".properties").getPath();
			Properties p = new Properties();
			// 读取、加载配置文件
			FileInputStream in;
			try {
				in = new FileInputStream(configFile);
				 BufferedReader bf = new BufferedReader(new InputStreamReader(in));
					p.load(bf);
					// 将配置文件内容写入缓存
					getPaymentContent(p.getProperty("payment_content"),str);
					if (in != null) {
						in.close();
						in = null;
					}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * 获取支付方式
	 * 
	 * @param jsonStr
	 * @return
	 */
	private static void getPaymentContent(String jsonStr,String str) {
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
}
