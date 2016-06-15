package com.shangpin.wireless.api.vo;

import java.util.Date;

public class WeixinTokenVO {
	private Long id;// 主键
	private String status;// token状态
	private String serverId;// 更新的主机id
	private Date lastUpdated;// 最后更新的时间
	private Date efftime; // 时效性

	private String access_token;// 获取到的凭证
	private String expires_in; // 凭证有效时间，单位：秒
	private String errcode; // 返回错误码
	private String errmsg;// 返回的错误信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Date getEfftime() {
		return efftime;
	}

	public void setEfftime(Date efftime) {
		this.efftime = efftime;
	}

}
