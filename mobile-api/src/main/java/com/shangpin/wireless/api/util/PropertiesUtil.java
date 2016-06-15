package com.shangpin.wireless.api.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 功能：读取属性文件.properties
 * 
 * @Author: Gavin
 * @CreatDate: 2012-08-9
 */
public final class PropertiesUtil extends Properties {
	private static final long serialVersionUID = -143027453907113416L;
	protected static final Log log = LogFactory.getLog(PropertiesUtil.class.getSimpleName());
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
				is = new FileInputStream(PropertiesUtil.class.getResource(filename).getPath());
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

}
