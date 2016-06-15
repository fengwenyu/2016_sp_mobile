package com.shangpin.biz.bo.message;

import java.io.Serializable;

public class MessageCount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String total;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
