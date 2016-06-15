package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class PointBuyProduct implements Serializable{
	
	private static final long serialVersionUID = 11L;

	private String pharseId;//改时间的唯一id标示
	private String startTime;
	private String endTime;
	private String status;//状态，三种：已结束，抢购中，即将开始
	private String title;
	private String systemTime;
	private List<PointBuyProductList> list;
	public String getPharseId() {
		return pharseId;
	}
	public void setPharseId(String pharseId) {
		this.pharseId = pharseId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
	public List<PointBuyProductList> getList() {
		return list;
	}
	public void setList(List<PointBuyProductList> list) {
		this.list = list;
	}
}
