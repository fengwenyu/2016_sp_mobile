package com.shangpin.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/** 
 * 泛型工具类
 * @date    2016年5月28日 <br/> 
 * @author   陈小峰
 * @since    JDK 7
 */
public class GenericsUtil {
	/**
	 * 获取方法的返回类型 
	 * <br/>
	 * @param methodName 方法名
	 * @param c 类
	 * @return
	 */
	public static Type getReturnType(String methodName, Class<?> c){
    	Method[] mes=c.getMethods();
    	for (Method method : mes) {
			if(method.getName().equals(methodName)){
				Type type=method.getGenericReturnType();
				return type;
			}
		}
    	
    	return null;
    }
	/**
	 * 获取方法的返回类型 
	 * <br/>
	 * @param method 类方法
	 * @return 
	 */
	public static Type getReturnType(Method method){
		Type type=method.getGenericReturnType();
		return type;
	}
	

	
	
}
