package com.shangpin.biz.bo;

import java.io.Serializable;


public class ActivityHead implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String startTime;
	private String endTime;
	private String datepreTime;
	private String priceTag;
	private String priceDesc;
	private String isPre;
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
	public String getDatepreTime() {
		return datepreTime;
	}
	public void setDatepreTime(String datepreTime) {
		this.datepreTime = datepreTime;
	}
	public String getPriceTag() {
		return priceTag;
	}
	public void setPriceTag(String priceTag) {
		this.priceTag = priceTag;
	}
	public String getPriceDesc() {
		return priceDesc;
	}
	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}
    public String getIsPre() {
        return isPre;
    }
    public void setIsPre(String isPre) {
        this.isPre = isPre;
    }
	
	
}
