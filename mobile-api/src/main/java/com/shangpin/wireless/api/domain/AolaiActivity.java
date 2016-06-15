package com.shangpin.wireless.api.domain;



import java.util.Date;

public class AolaiActivity {
	//主键id
	private Long id;
	//链接
	private String getUrl;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//是否 显示
	private DisplayEnum display;
	private Date createDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGetUrl() {
		return getUrl;
	}
	public void setGetUrl(String getUrl) {
		this.getUrl = getUrl;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public DisplayEnum getDisplay() {
		return display;
	}
	public void setDisplay(DisplayEnum display) {
		this.display = display;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
