package com.shangpin.biz.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonRulesUtil {

	/**
	 * 设置api通用规则
	 * 
	 * @param obj
	 *            该obj必须继承CommonRules.java
	 * @param args
	 *            第一元素是当前使用该方法的地方 ...剩下的元素根据需求追加
	 */

	public static void mapToBean(Object obj, String... args) {
		if (obj == null) {
			return;
		}
		int len = args.length;
		Map<String, Object> map = new HashMap<String, Object>();
		if (len == 3 && "category".equals(args[0])) {
			map.put("type", args[1]);
			map.put("refContent", args[2]);
		}
		if (!map.isEmpty()) {
			ObjectUtil.mapToBean(map, obj);
		}
		
	}

}
