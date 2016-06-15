package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class HomeSecond implements Serializable {

	private static final long serialVersionUID = 7595168514259791287L;

	private List<Recommend> productList;

	private MallCategory category;

	private List<BrandLists> brandList;

	public List<Recommend> getProductList() {
		return productList;
	}

	public void setProductList(List<Recommend> productList) {
		this.productList = productList;
	}

	public MallCategory getCategory() {
		return category;
	}

	public void setCategory(MallCategory category) {
		this.category = category;
	}

	public List<BrandLists> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<BrandLists> brandList) {
		this.brandList = brandList;
	}

}
