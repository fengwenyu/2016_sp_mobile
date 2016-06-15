package com.shangpin.wireless.api.weixinbean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ConfigEntity {

	private String[] keywords;
	private String type;
	private String regular;
	private String regularGroup;
	private String startTime;
	private String endTime;
	private String timeFormat;
	private String gotoKeyword;
	private String access;
	private Object replied;
	private String[] testUsers;
	
	public static ConfigEntity parse (String config) {
		ConfigEntity entity = new ConfigEntity();
		
		JSONObject obj = JSONObject.fromObject(config);
		JSONArray keywords = obj.getJSONArray("keywords");
		entity.keywords = new String[keywords.size()];
		for (int i=0;i<keywords.size();i++) {
			entity.keywords[i] = keywords.getString(i);
		}
		entity.type = obj.getString("type");
		if (obj.has("regular")) {
			entity.regular = obj.getString("regular");
		}
		if (obj.has("reply")) {
			entity.replied = obj.get("reply");
		}
		if (obj.has("starttime")) {
			entity.startTime = obj.getString("starttime");
		}
		if (obj.has("endtime")) {
			entity.endTime = obj.getString("endtime");
		}
		if (obj.has("timeformat")) {
			entity.timeFormat = obj.getString("timeformat");
		}
		if (obj.has("access")) {
			entity.access = obj.getString("access");
		}
		if (obj.has("gotokeyword")) {
			entity.gotoKeyword = obj.getString("gotokeyword");
		}
		if (obj.has("testusers")) {
			JSONArray testusers = obj.getJSONArray("testusers");
			entity.testUsers = new String[testusers.size()];
			for (int i=0;i<testusers.size();i++) {
				entity.testUsers[i] = testusers.getString(i);
			}
		}
		
		return entity;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	public void setKeyword(String keyword) {
		this.keywords = new String[]{keyword};
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegular() {
		return regular;
	}

	public String getRegularGroup() {
		return regularGroup;
	}

	public void setRegularGroup(String regularGroup) {
		this.regularGroup = regularGroup;
	}

	public Object getReplied() {
		return replied;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getGotoKeyword() {
		return gotoKeyword;
	}
	
	public String[] getTestUsers() {
		return testUsers;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public void setReplied(Object replied) {
		this.replied = replied;
	}

	public ConfigEntity clone () {
		ConfigEntity clone = new ConfigEntity();
		clone.keywords = keywords;
		clone.type = type;
		clone.regular = regular;
		clone.startTime = startTime;
		clone.endTime = endTime;
		clone.timeFormat = timeFormat;
		clone.replied = replied;
		
		return clone;
	}
	
	public String toString () {
		StringBuilder strBuff = new StringBuilder();
		strBuff.append("keywords:");
		for (int i=0;i<keywords.length;i++) {
			strBuff.append(keywords[i]);
		}
		strBuff.append("\ntype:").append(type).append("\n");
		
		return strBuff.toString();
	}
}
