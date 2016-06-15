package com.shangpin.wireless.vo;

import java.util.Map;

public class BrandRecommendVO {
	private Map<String, String> brand;
	private String[] products;
	
	
	public Map<String, String> getBrand() {
		return brand;
	}
	public void setBrand(Map<String, String> brand) {
		this.brand = brand;
	}
	public String[] getProducts() {
		return products;
	}
	public void setProducts(String[] products) {
		this.products = products;
	}

	
}
