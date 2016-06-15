package com.shangpin.wechat.bo.base;

/**
 * 微信返回token的对象
 * 
 * @author huangxiaoliang
 *
 */
public class WeChatToken {

	private String access_token;

	private String expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

}
