package com.shangpin.mobileShangpin.common.util;


import java.io.Serializable;
import java.util.Date;

public class LogObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ip;
	private String interfaceName;
	private Date date;
	private Header header;
	private String params;

	public LogObject(String logStr,String type) {
		String[] split = logStr.split("\\|");
		if (split.length == 5) {
			this.ip = split[0];
			this.date = new Date(Long.valueOf(split[1]));
			this.interfaceName = split[2];
			this.header = new Header(split[3],type);
			this.params = split[4];
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
