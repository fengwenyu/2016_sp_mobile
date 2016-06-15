package com.shangpin.biz.bo;

import java.io.Serializable;

public class TimeBucketSendCoupon implements Serializable{
	private static final long serialVersionUID = 1L;
	private String startDate;
	private String endDate;
	private String coupon;
	private String desc;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
