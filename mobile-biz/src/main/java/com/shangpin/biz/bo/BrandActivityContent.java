package com.shangpin.biz.bo;

import java.io.Serializable;

public class BrandActivityContent implements Serializable{
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private BrandActivityHead content;
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
	public BrandActivityHead getContent() {
		return content;
	}
	public void setContent(BrandActivityHead content) {
		this.content = content;
	}

}
