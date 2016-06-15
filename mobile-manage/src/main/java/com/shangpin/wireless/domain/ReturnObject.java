package com.shangpin.wireless.domain;

import java.util.List;

/**
 * 用于异步加载封装数据
 * @Author  zhouyu
 * @CreatDate  2012-07-16
 */
public class ReturnObject {

	private Integer total;
	// private Integer page;
	private List rows;

	private String returnInfo;// 1 right;0 error
	private String returnCode;
	private String platform;
	public String getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
