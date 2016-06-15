package com.shangpin.wireless.api.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信Token实体
 * 
 * @author xupengcheng
 * @createDate 2014-1-10 上午10:27:02
 * 
 */
public class Token implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;// 主键
	private String code;// token值
	private String status;// token状态
	private String serverId;// 更新的主机id
	private Date lastUpdated;// 最后更新的时间
	private Date efftime;//时效性
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Date getEfftime() {
		return efftime;
	}
	public void setEfftime(Date efftime) {
		this.efftime = efftime;
	}
	
	
}
