package com.shangpin.biz.bo;

import java.util.List;

public class RecProductItem {
	private String systime;
	private String  recommendNum;
	private List<RecProduct> productList;
	public String getSystime() {
		return systime;
	}
	public void setSystime(String systime) {
		this.systime = systime;
	}
	public String getRecommendNum() {
		return recommendNum;
	}
	public void setRecommendNum(String recommendNum) {
		this.recommendNum = recommendNum;
	}
	public List<RecProduct> getProductList() {
		return productList;
	}
	public void setProductList(List<RecProduct> productList) {
		this.productList = productList;
	}

}
