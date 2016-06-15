package com.shangpin.base.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * 功能：读取属性文件.properties
 * 
 * @Author: wangfeng
 * @CreatDate: 2014-09-17
 */
public final class PropertiesUtil extends Properties {
	private static final long serialVersionUID = -143027453907113416L;
	private static HashMap<String, PropertiesUtil> instance;
	private String filename;
	private long lastLoadTime;

	// 获取 instance
	public static PropertiesUtil getInstance(String filename) {
		if (instance == null) {
			instance = new HashMap<String, PropertiesUtil>(4);
		}
		PropertiesUtil config = instance.get(filename);
		if (config != null) {
			config.load();
			return config;
		} else {
			config = new PropertiesUtil(filename);
			instance.put(filename, config);
			return config;
		}
	}

	// 类的构造函数，读取属性文件
	private PropertiesUtil(String filename) {
		this.filename = filename;
		load();
	}

	private void load() {
		Long now = System.currentTimeMillis();
		if (now - lastLoadTime > 600000) { // 10分钟更新一次
			InputStream is = null;
			try {
				// 将.properties 文件读取到 InputStream 流中
				is = PropertiesUtil.class.getResourceAsStream(filename);
				clear();
				load(is);
				lastLoadTime = now;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("读取属性文件失败，请确认文件是否存在！");
				return;
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
