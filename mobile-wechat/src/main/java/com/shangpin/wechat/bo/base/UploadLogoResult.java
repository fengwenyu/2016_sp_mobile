package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class UploadLogoResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String errcode;// 	错误码
	private String errmsg;//	错误信息
	private String url;// 	商户logo_url，用于创建卡券接口中填入。特别注意：该链接仅用于微信相关业务，不支持引用。 
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
