package com.shangpin.wireless.api.cfg;

/**
 * 配置参数类
 * @Author: zhouyu
 * @CreateDate: 2012-07-11
 */
public class Configuration {
	private static int pageSize;

	static {
		// 加载配置文件并初始化配置
		// config.properties
		pageSize = 10;
	}

	public static int getPageSize() {
		return pageSize;
	}

}
