package com.shangpin.wireless.api.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

/**
 * 将运行中的ApplicationContext放入内存
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-09-17
 */
public class ApplicationContextUtil {
	private static Map<String, ApplicationContext> map = new HashMap<String, ApplicationContext>();

	public static void add(ApplicationContext ac) {
		map.put("ac", ac);
	}

	public static ApplicationContext get(String key) {
		return map.get(key);
	}
}
