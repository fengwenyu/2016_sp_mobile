package com.shangpin.wireless.api.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonTimestampValueProcessor implements JsonValueProcessor {
	private String datePattern = "yyyy-MM-dd";// 日期格式

	public JsonTimestampValueProcessor() {
		super();
	}

	// 构造函数
	public JsonTimestampValueProcessor(String format) {
		super();
		this.datePattern = format;
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value) {
		try {
			if (value instanceof Timestamp) {
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.UK);
				return sdf.format((Timestamp) value);
			}
			return value == null ? "" : value.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePaterns) {
		this.datePattern = datePaterns;
	}

}
