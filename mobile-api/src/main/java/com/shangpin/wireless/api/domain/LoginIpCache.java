package com.shangpin.wireless.api.domain;

/**
 * 登录缓存
 * 
 * @Author: zhouyu
 * @CreateDate: 2013-05-15
 */
public class LoginIpCache implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long timestamp;
	private int count;
	private long limitTimestamp;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getLimitTimestamp() {
		return limitTimestamp;
	}

	public void setLimitTimestamp(long limitTimestamp) {
		this.limitTimestamp = limitTimestamp;
	}
}
