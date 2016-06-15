package com.shangpin.wireless.util;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class JsonUtil {

	private static JsonConfig jsonConfig = new JsonConfig();

	static {
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonTimestampValueProcessor("yyyy-MM-dd HH:mm:ss"));
	}

	public static String convertToJSonStr(Object obj) {
		return JSONObject.fromObject(obj, jsonConfig).toString();
	}

	public static String convertToJSonArrayStr(Object obj) {
		return JSONArray.fromObject(obj, jsonConfig).toString();
	}

	public static JsonConfig getJsonConfig() {
		return jsonConfig;
	}

	public static void setJsonConfig(JsonConfig jsonConfig) {
		JsonUtil.jsonConfig = jsonConfig;
	}
	
	public static String getJsonString4JavaPOJO(Object javaObj,
			String dataFormat) {

		JSONObject json;

		JsonConfig jsonConfig = configJson(dataFormat);

		json = JSONObject.fromObject(javaObj, jsonConfig);

		return json.toString();

	}

	public static JsonConfig configJson(String datePattern) {

		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.setExcludes(new String[] { "" });

		jsonConfig.setIgnoreDefaultExcludes(false);

		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor(datePattern));

		return jsonConfig;

	}
	public static JsonConfig configJson(String[] excludes,

	String datePattern) {

		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.setExcludes(excludes);

		jsonConfig.setIgnoreDefaultExcludes(false);

		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		jsonConfig.registerJsonValueProcessor(Date.class,

		new DateJsonValueProcessor(datePattern));

		return jsonConfig;

	}

}
