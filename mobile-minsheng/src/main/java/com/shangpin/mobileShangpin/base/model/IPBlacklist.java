package com.shangpin.mobileShangpin.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * ip黑名单实体类
 */
public class IPBlacklist implements Serializable {
	/** 序列化ID **/
	private static final long serialVersionUID = 1578219993380856910L;
	/** 主键ID **/
	private int id;
	/** IP地址 **/
	private String ip;
	/** 用户名 **/
	private String userName;
	/** 添加到黑名单次数 **/
	private int count;
	/** 最后违规时间 **/
	private Date time;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
