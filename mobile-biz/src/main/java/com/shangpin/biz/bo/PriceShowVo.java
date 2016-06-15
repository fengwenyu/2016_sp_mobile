package com.shangpin.biz.bo;

import java.io.Serializable;

public class PriceShowVo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6460471365818378925L;
	private String  type;
	private String  amount;
	private String  title;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
