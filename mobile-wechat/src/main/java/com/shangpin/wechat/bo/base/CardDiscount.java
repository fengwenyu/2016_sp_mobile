package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class CardDiscount implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CardBaseInfo base_info;
	private String discount;
	public CardBaseInfo getBase_info() {
		return base_info;
	}
	public void setBase_info(CardBaseInfo base_info) {
		this.base_info = base_info;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
}
