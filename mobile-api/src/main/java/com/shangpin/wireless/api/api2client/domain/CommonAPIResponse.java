package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONObject;

/**
 * 用于API接口的返回数据
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-09-04
 */
public class CommonAPIResponse {

	private String code;
	private String cod;//兼容问题（安卓）
	private String msg;
	private JSONObject content;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public JSONObject getContent() {
		return content;
	}

	public void setContent(JSONObject content) {
		this.content = content;
	}

	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-09-04
	 * @param
	 * @Return
	 */
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", code == null ? "0" : code);
		obj.put("msg", msg == null ? "" : msg);
		obj.put("content", content == null ? new JSONObject() : content);
		return obj.toString();
	}

	public String obj2Json(String producType) {
		JSONObject obj = new JSONObject();
		obj.put("code", code == null ? "0" : code);
		obj.put("msg", msg == null ? "" : msg);
		obj.put("content", content == null ? new JSONObject() : content);
		return obj.toString();
	}

	/**
	 * 返给客户端的json数据(兼容订单运费的单独处理)
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-09-04
	 * @param
	 * @Return
	 */
	public String obj2Json(String producType, String product, String version) {
		JSONObject obj = new JSONObject();
		obj.put("code", code == null ? "0" : code);
		obj.put("msg", msg == null ? "" : msg);
		obj.put("content", content == null ? new JSONObject() : content);
		return obj.toString();
	}

	public String obj2Json(String fromMethod, String producType, String product, String version) {
		JSONObject obj = new JSONObject();
		obj.put("code", code == null ? "0" : code);
		obj.put("msg", msg == null ? "" : msg);
		obj.put("content", content == null ? new JSONObject() : content);
		return obj.toString();
	}

	@Override
	public String toString() {
		return obj2Json();
	}

}
