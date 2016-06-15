package com.shangpin.biz.bo.base;

import java.io.Serializable;
import java.util.List;

public class ResultListContent<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private List<T> content;

	
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

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

}
