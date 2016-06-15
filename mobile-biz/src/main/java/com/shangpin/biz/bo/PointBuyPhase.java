package com.shangpin.biz.bo;

import java.io.Serializable;

public class PointBuyPhase implements Serializable{
	
	private static final long serialVersionUID = 11L;

	private String pharseId;//改时间的唯一id标示
	private String startTime;
	private String endTime;
	private String timePoint;//时间轴上的时间，如12:00
	private String status;//状态，三种：已结束，抢购中，即将开始
	private String statusDesc;//状态的中文描述
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
	public String getTimePoint() {
		return timePoint;
	}
	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
}
