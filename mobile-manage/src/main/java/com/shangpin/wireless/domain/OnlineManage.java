package com.shangpin.wireless.domain;

import java.util.Date;

/**
 * 上线管理（更新管理）
 * 
 * @Author zhouyu
 * @CreateDate  2012-07-12
 */
public class OnlineManage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;// 主键
	private Long productNum;// 产品编号
	private Long channelNum;// 渠道编号
	private String productVersion;// 产品版本
	private Date createTime;// 创建日期
	private String versionForceMax;// 强制升级最大版本号
	private String versionLatest;// 最新版本号
	private String fileName;// 文件名称
	private String downloadPath;// 下载地址
	private String prompt;// 提示信息
	private int inuse;// 是否删除 0：删除，1：未删除
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

	public String getVersionForceMax() {
		return versionForceMax;
	}

	public void setVersionForceMax(String versionForceMax) {
		this.versionForceMax = versionForceMax;
	}

	public String getVersionLatest() {
		return versionLatest;
	}

	public void setVersionLatest(String versionLatest) {
		this.versionLatest = versionLatest;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
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

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public int getInuse() {
		return inuse;
	}

	public void setInuse(int inuse) {
		this.inuse = inuse;
	}


}
