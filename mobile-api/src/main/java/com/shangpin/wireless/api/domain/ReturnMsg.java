package com.shangpin.wireless.api.domain;


/**
 * 用于封装接口的返回数据
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-08-27
 */
public class ReturnMsg {
	private String code;
	private String msg;
	private Object content = new Object();

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

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
