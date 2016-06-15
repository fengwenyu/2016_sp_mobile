package com.shangpin.wireless.util;

import java.io.InputStream;
import java.util.Properties;
/**
 * 功能：读取属性文件config.properties
 * 
 * @Author  zhouyu
 * @CreatDate  2012-08-9
 */
public final class ProReader extends Properties {

	private static final long serialVersionUID = -143027453907113416L;
	// 定义ProReader 类静态对象 instance
	private static ProReader instance;

	// 获取 instance
	public static ProReader getInstance() {
		if (instance != null) {
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
		// 将config.properties 文件读取到 InputStream 流中
		InputStream is = ProReader.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			// 从 InputStream 流中读取属性列表(键-值对)
			load(is);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("读取属性文件失败，请确认文件是否存在！");
			return;
		}
	}
}
