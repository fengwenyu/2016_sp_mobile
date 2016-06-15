package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

public class MallBrandList  implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<BrandList> brandList;

	public List<BrandList> getBrandList() {
		return this.brandList;
	}

	public void setBrandList(List<BrandList> brandList) {
		this.brandList = brandList;
	}
	
	

}
