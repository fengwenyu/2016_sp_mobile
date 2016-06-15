package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class IndexHotBrands implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<IndexBrands> brandList;

	public List<IndexBrands> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<IndexBrands> brandList) {
		this.brandList = brandList;
	}
	
	

}
