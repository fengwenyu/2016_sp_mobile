package com.shangpin.biz.bo;

import java.io.Serializable;

public class ReturnProgress implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String timestamp;//时间戳
	private String desc;//事件描述

	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
