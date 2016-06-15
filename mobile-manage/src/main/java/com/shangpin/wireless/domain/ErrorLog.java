package com.shangpin.wireless.domain;

import java.util.Date;

/**
 * @Author zhouyu
 * @CreateDate  2012-07-12 错误日志
 */
public class ErrorLog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;// 主键
	private String platform;// 运行平台
	private Long productNum;// 产品编号
	private String productVersion;// 产品版本
	private Long channelNum;// 渠道编号
	private Date createTime;// 创建日期
	private String ip;// IP地址
	private String shortmsg;// 简短信息
	private String longmsg;// 详细信息
	private String city;// 地区
	private String phoneType;// 手机型号
	private String imei;// IMEI号
	private String systemVersoin;// 系统版本
	private String apn;// 接入点
	private String reserve0;// 备用字段
	private String reserve1;// 备用字段
	private String reserve2;// 备用字段
	private String reserve3;// 备用字段
	private String reserve4;// 备用字段
	private String reserve5;// 备用字段

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public Long getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(Long channelNum) {
		this.channelNum = channelNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getShortmsg() {
		return shortmsg;
	}

	public void setShortmsg(String shortmsg) {
		this.shortmsg = shortmsg;
	}

	public String getLongmsg() {
		return longmsg;
	}

	public void setLongmsg(String longmsg) {
		this.longmsg = longmsg;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getReserve0() {
		return reserve0;
	}

	public void setReserve0(String reserve0) {
		this.reserve0 = reserve0;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getSystemVersoin() {
		return systemVersoin;
	}

	public void setSystemVersoin(String systemVersoin) {
		this.systemVersoin = systemVersoin;
	}

	public String getApn() {
		return apn;
	}

	public void setApn(String apn) {
		this.apn = apn;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

}
