package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;

public class GetAlipayTokenAPIResponse {
	
	private String code;
	private String msg;
	private String aliUserId;	//	支付宝userid
	private String accesstoken;	//	访问令牌(短token)
	private int accessExpiresSec;	//	访问令牌生命周期的秒数
	private String refreshtoken;	//	刷新令牌(长token)
	private int refreshExpiresSec;	//	刷新令牌生命周期的秒数
	
	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: wangwenguan
	 * @CreatDate: 2013-08-05
	 * @param
	 * @Return
	 */
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("msg", msg);
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(code)) {
			content.put("alipayuid", aliUserId);
			content.put("accesstoken", accesstoken);
			content.put("accessExpiresSec", accessExpiresSec);
			content.put("refreshtoken", refreshtoken);
			content.put("refreshExpiresSec", refreshExpiresSec);
		}
		obj.put("content", content);
		return obj.toString();
	}
	
	public GetAlipayTokenAPIResponse json2Obj_wallet (String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		if (obj.has("alipay_system_oauth_token_response")) {
			JSONObject resp = obj.getJSONObject("alipay_system_oauth_token_response");
			setAliUserId(resp.getString("alipay_user_id"));
			setAccesstoken(resp.getString("access_token"));	//	访问令牌(短token)
			setAccessExpiresSec(resp.getInt("expires_in"));	//	访问令牌生命周期的秒数
			setRefreshtoken(resp.getString("refresh_token"));	//	刷新令牌(长token)
			setRefreshExpiresSec(resp.getInt("re_expires_in"));	//	刷新令牌生命周期的秒数
			
			setCode("0");
			setMsg("");
		} else if (obj.has("isv")) {
			setCode("1");
			setMsg(obj.getString("isv"));
		} else if (obj.has("aop")) {
			setCode("2");
			setMsg(obj.getString("aop"));
		} else if (obj.has("error_response")) {
			JSONObject error = obj.getJSONObject("error_response");
			setCode(error.getString("code"));
			setMsg(error.getString("msg") + "\n" + error.getString("sub_msg") );
		}
		
		return this;
	}
	
	public GetAlipayTokenAPIResponse json2Obj (String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		if (obj.has("alipay.open.auth.token.exchange_response")) {
			JSONObject resp = obj.getJSONObject("alipay.open.auth.token.exchange_response");
			setAliUserId(resp.getString("user_id"));
			setAccesstoken(resp.getString("access_token"));	//	访问令牌(短token)
			setAccessExpiresSec(resp.getInt("expires_in"));	//	访问令牌生命周期的秒数
			setRefreshtoken(resp.getString("refresh_token"));	//	刷新令牌(长token)
			setRefreshExpiresSec(resp.getInt("re_expires_in"));	//	刷新令牌生命周期的秒数
			
			setCode("0");
			setMsg("");
		} else if (obj.has("isv")) {
			setCode("1");
			setMsg(obj.getString("isv"));
		} else if (obj.has("aop")) {
			setCode("2");
			setMsg(obj.getString("aop"));
		} else if (obj.has("error_response")) {
			JSONObject error = obj.getJSONObject("error_response");
			setCode(error.getString("code"));
			setMsg(error.getString("msg") + "\n" + error.getString("sub_msg") );
		}
		
		return this;
	}
	
	public GetAlipayTokenAPIResponse xml2Obj (String xmlStr) {
		if ("T".equals(StringUtil.getTagContent(xmlStr, "<is_success>", "</is_success>"))) {
			setAliUserId(StringUtil.getTagContent(xmlStr, "<user_id>", "</user_id>"));
			setAccesstoken(StringUtil.getTagContent(xmlStr, "<access_token>", "</access_token>"));	//	访问令牌(短token)
//			setAccessExpiresSec(StringUtil.getTagContent(xmlStr, "<expires_in>", "</expires_in>"));	//	访问令牌生命周期的秒数
			setRefreshtoken(StringUtil.getTagContent(xmlStr, "<refresh_token>", "</refresh_token>"));	//	刷新令牌(长token)
//			setRefreshExpiresSec(StringUtil.getTagContent(xmlStr, "<re_expires_in>", "</re_expires_in>"));	//	刷新令牌生命周期的秒数
			
			setCode("0");
			setMsg("");
		} else {
			setCode("1");
			setMsg(StringUtil.getTagContent(xmlStr, "<error>", "</error>"));
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

	public String getAliUserId() {
		return aliUserId;
	}

	public void setAliUserId(String aliUserId) {
		this.aliUserId = aliUserId;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public int getAccessExpiresSec() {
		return accessExpiresSec;
	}

	public void setAccessExpiresSec(int accessExpiresSec) {
		this.accessExpiresSec = accessExpiresSec;
	}

	public String getRefreshtoken() {
		return refreshtoken;
	}

	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}

	public int getRefreshExpiresSec() {
		return refreshExpiresSec;
	}

	public void setRefreshExpiresSec(int refreshExpiresSec) {
		this.refreshExpiresSec = refreshExpiresSec;
	}

}
