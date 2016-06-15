package com.shangpin.biz.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象操作工具类
 * 
 * @author huangxiaoliang
 *
 */
public class ObjectUtil {

	private final static Log log = LogFactory.getLog(ObjectUtil.class);

	/**
	 * 将map转换为bean
	 * 
	 * @param map
	 * @param obj
	 */
	public static void mapToBean(Map<String, Object> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			log.warn("mapToBean Error " + e);
		}
	}

	/**
	 * bean转换为map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			log.warn("beanToMap Error " + e);
		}

		return map;

	}

	/**
	 * bean转换为map
	 *
	 * @param obj
	 * @return
	 */
	public static void beanToMap(Object obj,Map<String, String> map) {

		if (obj == null) {
			return;
		}
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value == null? null : value.toString());
				}

			}
		} catch (Exception e) {
			log.warn("beanToMap Error " + e);
		}

	}

}
