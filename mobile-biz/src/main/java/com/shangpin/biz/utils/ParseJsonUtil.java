package com.shangpin.biz.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * json解析相关方法
 * 
 * @author cuibinqiang
 * @date 2014-11-5
 *
 */
public class ParseJsonUtil {

	/**
	 * Jackson将json字符串转成特定集合对象
	 * 
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
		ObjectMapper  mapper = new ObjectMapper();
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	}
	
}
