package com.shangpin.mobileShangpin.businessbean;

import java.io.Serializable;
import java.util.Date;

/**
 * ip黑名单实体类
 */
public class IPBlacklist implements Serializable {
	/** 序列化ID **/
	private static final long serialVersionUID = -5331285510764444859L;
	/** 主键ID **/
	private int id;
	/** IP地址 **/
	private String ip;
	/** 用户名 **/
	private String userName;
	/** 添加到黑名单次数(写入数据库次数) **/
	private int count;
	/** 最后违规时间(写入数据库时间) **/
	private Date time;
	/** 5秒内违规次数 **/
	private int statisticsCount;
	/** 统计违规初始时间 **/
	private long statisticsTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getStatisticsCount() {
		return statisticsCount;
	}

	public void setStatisticsCount(int statisticsCount) {
		this.statisticsCount = statisticsCount;
	}

	public long getStatisticsTime() {
		return statisticsTime;
	}

	public void setStatisticsTime(long statisticsTime) {
		this.statisticsTime = statisticsTime;
	}
}
