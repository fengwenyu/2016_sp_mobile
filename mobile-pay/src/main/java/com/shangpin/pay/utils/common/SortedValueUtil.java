package com.shangpin.pay.utils.common;

import java.util.Map;
import java.util.Set;

public class SortedValueUtil {
	/**
	 * 获取当前对象字典顺序排序后的值
	 * @return
	 */
	public static String sortedValue(Map<String,String> fieldMap){
		try {
			StringBuffer sb = new StringBuffer();
			Set<String> fieldSet=fieldMap.keySet();
			for (String fieldName : fieldSet) {
				Object attrValue=fieldMap.get(fieldName);
				if(attrValue!=null){
					sb.append(attrValue.toString()).append("|");
				}
			}
			return sb.substring(0, sb.length()-1);
		} catch (Exception e) {
			return null;
		}
	}
}
