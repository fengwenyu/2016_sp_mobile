package com.shangpin.wireless.api.businessbean;

import net.sf.json.JSONObject;

/**
 * 用于省市区实体类
 * 
 * @author wangwenguan
 *
 */
public class CityBean {
	public static final int TYPE_PROVINCE	= 1;
	public static final int TYPE_CITY		= 2;
	public static final int TYPE_AREA		= 3;
	private int type;
	private String id;
	private String parentid;
	private String name;
	
	public CityBean json2Obj (JSONObject jsonObj) {
		if (jsonObj.has("id")) {
			setId(jsonObj.getString("id"));
		}
		if (jsonObj.has("parentid")) {
			setParentid(jsonObj.getString("parentid"));
		}
		if (jsonObj.has("name")) {
			setName(jsonObj.getString("name"));
		}
		
		return this;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}