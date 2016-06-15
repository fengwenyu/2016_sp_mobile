package com.shangpin.wireless.api.domain;

import java.util.Date;

public class SessionEntry {

	private String key;
	private String value;
	private Date createTime;
	private byte invalidate;// true 失效 false 不失效
	
	public SessionEntry(String imei) {
		this.key = imei;
		this.createTime = new Date();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isInvalidate() {
		return invalidate == 1;
	}

	public void setInvalidate(boolean invalidate) {
		this.invalidate = (byte)(invalidate ? 1 : 0);
	}

}
