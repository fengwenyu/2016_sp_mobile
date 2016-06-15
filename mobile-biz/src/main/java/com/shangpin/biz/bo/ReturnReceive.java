package com.shangpin.biz.bo;

import java.io.Serializable;

public class ReturnReceive implements Serializable {
	private static final long serialVersionUID = 4305374828766081854L;
	private String fOrderId;//发起订单
	private String fSkuNo;//发起SkuNO
	private String lOrderId;//领取订单号
	private String lSkuNO;//领取SKUNO
	private String isOrderId;//是否撞衫订单
	public String getfOrderId() {
		return fOrderId;
	}
	public void setfOrderId(String fOrderId) {
		this.fOrderId = fOrderId;
	}
	public String getfSkuNo() {
		return fSkuNo;
	}
	public void setfSkuNo(String fSkuNo) {
		this.fSkuNo = fSkuNo;
	}
	public String getlOrderId() {
		return lOrderId;
	}
	public void setlOrderId(String lOrderId) {
		this.lOrderId = lOrderId;
	}
	public String getlSkuNO() {
		return lSkuNO;
	}
	public void setlSkuNO(String lSkuNO) {
		this.lSkuNO = lSkuNO;
	}
	public String getIsOrderId() {
		return isOrderId;
	}
	public void setIsOrderId(String isOrderId) {
		this.isOrderId = isOrderId;
	}
	
}
