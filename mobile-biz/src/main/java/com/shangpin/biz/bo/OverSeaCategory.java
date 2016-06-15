package com.shangpin.biz.bo;

import java.io.Serializable;

public class OverSeaCategory implements Serializable{
	private static final long serialVersionUID = 1L;
	private String shopCategoryNo;
	private String shopCategoryName;
	public String getShopCategoryNo() {
		return shopCategoryNo;
	}
	public void setShopCategoryNo(String shopCategoryNo) {
		this.shopCategoryNo = shopCategoryNo;
	}
	public String getShopCategoryName() {
		return shopCategoryName;
	}
	public void setShopCategoryName(String shopCategoryName) {
		this.shopCategoryName = shopCategoryName;
	}
}
