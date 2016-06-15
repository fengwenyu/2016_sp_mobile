package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class Brands implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<BrandList> brandList;

	public List<BrandList> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<BrandList> brandList) {
		this.brandList = brandList;
	}
	

}
