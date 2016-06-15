package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;

import com.shangpin.mobileAolai.common.util.Constants;

import net.sf.json.JSONObject;


public class AppAuthVO implements Serializable{
    
    private static final long serialVersionUID = -1022369562065671202L;
    
    private String code;
	private String msg;
	private boolean isLogin;

	/**
	 * 解析API返回的json数据
	 * 
	 * @author mengqiang@shangpin.com
	 * @param jsonStr
	 *            API返回的json数据
	 * @Return
	 */
	public AppAuthVO json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		if (obj.containsKey("msg")) {
			this.setMsg(obj.getString("msg"));
		}
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			this.setLogin(obj.getBoolean("isLogin"));
		}
		return this;
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
