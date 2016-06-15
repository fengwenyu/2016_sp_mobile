package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class BrandActivityPromotions implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private List<BrandActivityPromotion> list;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BrandActivityPromotion> getList() {
		return list;
	}
	public void setList(List<BrandActivityPromotion> list) {
		this.list = list;
	}

}
