package com.shangpin.utils;

import java.lang.reflect.Type;

import org.junit.Test;

/** 
 * TODO 该类的作用<br/>
 * @date    2016年5月28日 <br/> 
 * @author   陈小峰
 * @since    JDK 7
 */
public class TestReflectionUtil {
	
	@Test
	public void testReturnType(){
		Type t=GenericsUtil.getReturnType("testBean", CaptchTest.class);
		t=GenericsUtil.getReturnType("testX", CaptchTest.class);
		t=GenericsUtil.getReturnType("testReturnBean", CaptchTest.class);
		System.out.println(t);
	}
}
