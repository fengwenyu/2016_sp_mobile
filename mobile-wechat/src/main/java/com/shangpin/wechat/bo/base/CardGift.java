package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class CardGift implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CardBaseInfo base_info;
	private String gift;
	public CardBaseInfo getBase_info() {
		return base_info;
	}
	public void setBase_info(CardBaseInfo base_info) {
		this.base_info = base_info;
	}
	public String getGift() {
		return gift;
	}
	public void setGift(String gift) {
		this.gift = gift;
	}
	 
}
