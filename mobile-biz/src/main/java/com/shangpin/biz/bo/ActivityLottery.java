package com.shangpin.biz.bo;

import java.io.Serializable;

public class ActivityLottery implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String activityId;
	private String isDraw;
	private String prizeLevel;
	private String remainCount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getIsDraw() {
		return isDraw;
	}

	public void setIsDraw(String isDraw) {
		this.isDraw = isDraw;
	}

	public String getPrizeLevel() {
		return prizeLevel;
	}

	public void setPrizeLevel(String prizeLevel) {
		this.prizeLevel = prizeLevel;
	}

	public String getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(String remainCount) {
		this.remainCount = remainCount;
	}
}
