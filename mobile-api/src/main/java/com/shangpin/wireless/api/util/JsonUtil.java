package com.shangpin.wireless.api.util;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

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
}
