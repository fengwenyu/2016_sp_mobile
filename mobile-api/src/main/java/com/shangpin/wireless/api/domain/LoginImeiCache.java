package com.shangpin.wireless.api.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 登录缓存
 * 
 * @Author: zhouyu
 * @CreateDate: 2013-05-14
 */
public class LoginImeiCache implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String imei;
	private String ip;
	private long timestamp;
	Set<String> set = new HashSet<String>();

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

}
