package com.shangpin.wireless.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

/**
 * 将运行中的ApplicationContext放入内存
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-10-24
 */
public class ApplicationContextUtil {
	private static Map<String, ApplicationContext> map = new HashMap<String, ApplicationContext>();

	public static void add(ApplicationContext ac) {
		map.put("ac", ac);
	}

	public static ApplicationContext get() {
		return map.get("ac");
	}
}
