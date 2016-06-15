package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 预售日历
 * 
 * @author cuibinqiang
 */
public class PreSell  implements Serializable{
    private static final long serialVersionUID = 8460644198769804603L;
    private String id;
	// 用于页面显示的日期
	private String date;
	// 开始日期
	private String startTime;
	// 结束日期
	private String endTime;
	// 周几
	private String weekDay;
	// 样式控制 0：表示未选中 1：选中
	private int cssFlag;
	// 活动集合
	private List<Activity> activityList = new ArrayList<Activity>(); 

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public int getCssFlag() {
		return cssFlag;
	}

	public void setCssFlag(int cssFlag) {
		this.cssFlag = cssFlag;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	
}
