package com.shangpin.mobileShangpin.platform.vo;

import java.util.List;

public class SPBrandVO {
	// 品牌首字母
	private String capital;
	// 品牌列表
	private List<SimpleBrandsVO> brands;

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public List<SimpleBrandsVO> getBrands() {
		return brands;
	}

	public void setBrands(List<SimpleBrandsVO> brands) {
		this.brands = brands;
	}
}
