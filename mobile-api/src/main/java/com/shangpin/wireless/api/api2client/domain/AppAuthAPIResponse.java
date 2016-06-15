package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

/**
 * 返给mobile判断用户是否登录
 * @author mengqiang@shangpin.com
 *
 */
public class AppAuthAPIResponse {
	private String code;
	private String msg;
	private boolean isLogin;
	
	/**
	 * 返给客户端的json数据
	 */
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("msg", msg);
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(code)) {
			content.put("isLogin", isLogin);
		}
		obj.put("content", content);
		return obj.toString();
	}
	
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
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
		
}
