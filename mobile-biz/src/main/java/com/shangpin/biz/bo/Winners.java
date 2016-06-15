package com.shangpin.biz.bo;

import java.io.Serializable;

public class Winners implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userName;
	private String prizeLevel;
	private String prizeAmount;
	private String prizeDesc;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPrizeLevel() {
		return prizeLevel;
	}
	public void setPrizeLevel(String prizeLevel) {
		this.prizeLevel = prizeLevel;
	}
	public String getPrizeAmount() {
		return prizeAmount;
	}
	public void setPrizeAmount(String prizeAmount) {
		this.prizeAmount = prizeAmount;
	}
	public String getPrizeDesc() {
		return prizeDesc;
	}
	public void setPrizeDesc(String prizeDesc) {
		this.prizeDesc = prizeDesc;
	}
	
}
