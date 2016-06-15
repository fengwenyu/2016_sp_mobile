package com.shangpin.biz.bo.base;

import java.io.Serializable;

public class CommonObj<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private T content;

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

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

}
