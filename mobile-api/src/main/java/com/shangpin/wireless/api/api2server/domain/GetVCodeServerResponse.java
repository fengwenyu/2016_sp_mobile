package com.shangpin.wireless.api.api2server.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

/**
 * 用于封装主站接口的返回数据
 * 
 * @Author: Gavin
 * @CreateDate: 2013-05-02
 */
public class GetVCodeServerResponse {

	private String code;
	private String msg;
	private JSONArray list;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public JSONArray getList() {
		return list;
	}

	public void setList(JSONArray list) {
		this.list = list;
	}

	public GetVCodeServerResponse json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = obj.getJSONObject("content");
			list = obj.getJSONArray("list");
		}
		return this;
	}

}
