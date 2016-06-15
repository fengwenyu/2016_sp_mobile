package com.shangpin.wireless.domain;

import java.io.Serializable;
import java.util.Date;

public class PageView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String apiname;// 20
	private String visitIp;// 20
	// imei、os、osv、p、ch、ver、apn header信息
	private String imei; // 40
	private String os; // 10
	private String osv; // 8	系统版本
	private Long productNum;
	private Long channelNum;
	private String apn; // 40
	private String ver; // 16	产品版本
	private String phoneType;// 1024
	private String phoneModel;// 1024
	private String params;// 1024
	private Date logTime;
	private Date createTime;
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
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getVisitIp() {
		return visitIp;
	}
	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
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
	public String getApn() {
		return apn;
	}
	public void setApn(String apn) {
		this.apn = apn;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
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
	public String getApiname() {
		return apiname;
	}
	public void setApiname(String apiname) {
		this.apiname = apiname;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getPhoneModel() {
		return phoneModel;
	}
	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

}
