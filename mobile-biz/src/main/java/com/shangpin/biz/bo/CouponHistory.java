package com.shangpin.biz.bo;

import java.io.Serializable;

public class CouponHistory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String phoneNum;
	private String dateTime;
	private String type;
	private String amount;
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
