package com.shangpin.biz.bo;

import java.io.Serializable;

public class ProductAtt implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String spu;
	private String order;
	public String getSpu() {
		return spu;
	}
	public void setSpu(String spu) {
		this.spu = spu;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
