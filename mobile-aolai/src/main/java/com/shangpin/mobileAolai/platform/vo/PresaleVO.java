package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 预售日历
 * 
 * @Author zhouyu
 * @CreateDate 2012-10-30
 */
public class PresaleVO implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 1548025540498637865L;
    private String id;//
	private String date;// 用于页面显示的日期
	private String startTime;// 根据日期获取活动
	private String endTime;// 根据日期获取活动
	private String weekDay;// 周几
	private int cssFlag;// 样式控制 0：表示未选中 1：选中
	private List<ActivitiesVO> activityList = new ArrayList<ActivitiesVO>(); // 活动集合

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

	public List<ActivitiesVO> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivitiesVO> activityList) {
		this.activityList = activityList;
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
}
