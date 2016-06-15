package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class QRcodeCreateResult implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ticket;
	private long expire_seconds;
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public long getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(long expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	

}
