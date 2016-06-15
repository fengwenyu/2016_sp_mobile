package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 礼品卡状态
 * @author zghw
 *
 */
public class GiftCardStatus implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String statusCode;
	private String statusName;
	private String amount;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
