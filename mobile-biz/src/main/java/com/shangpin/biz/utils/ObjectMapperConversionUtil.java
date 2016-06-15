package com.shangpin.biz.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperConversionUtil {
	/**
	 * 由于以前的接口返回的金额的数据类型是double类型，小数点后保留两位，我们目前使用的是jackson的框架，
	 * 这样在做数据显示的时候需要对数据在前台页面做处理，经过查看api，目前处理方式如下：
	 * 
	 * @param mapper
	 */
	public static void Conversion(ObjectMapper mapper) {
		mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

	}

}
