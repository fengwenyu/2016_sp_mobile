package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class CardCash implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CardBaseInfo base_info;
	private String least_cost;
	private String reduce_cost;
	public CardBaseInfo getBase_info() {
		return base_info;
	}
	public void setBase_info(CardBaseInfo base_info) {
		this.base_info = base_info;
	}
	public String getLeast_cost() {
		return least_cost;
	}
	public void setLeast_cost(String least_cost) {
		this.least_cost = least_cost;
	}
	public String getReduce_cost() {
		return reduce_cost;
	}
	public void setReduce_cost(String reduce_cost) {
		this.reduce_cost = reduce_cost;
	}
	
}
