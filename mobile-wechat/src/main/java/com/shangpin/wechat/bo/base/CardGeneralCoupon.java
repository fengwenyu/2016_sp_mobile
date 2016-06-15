package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class CardGeneralCoupon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CardBaseInfo base_info;
	private String default_detail;
	public CardBaseInfo getBase_info() {
		return base_info;
	}
	public void setBase_info(CardBaseInfo base_info) {
		this.base_info = base_info;
	}
	public String getDefault_detail() {
		return default_detail;
	}
	public void setDefault_detail(String default_detail) {
		this.default_detail = default_detail;
	}
	
}
