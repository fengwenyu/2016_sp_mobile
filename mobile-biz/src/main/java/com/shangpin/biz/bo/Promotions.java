package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class Promotions implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Promotion> promotion;
	public List<Promotion> getPromotion() {
		return promotion;
	}
	public void setPromotion(List<Promotion> promotion) {
		this.promotion = promotion;
	}
	
	
}
