package com.shangpin.biz.bo;

import java.io.Serializable;

public class GiftCardRechargePasswd implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String rechargePasswd;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRechargePasswd() {
		return rechargePasswd;
	}

	public void setRechargePasswd(String rechargePasswd) {
		this.rechargePasswd = rechargePasswd;
	}

}
