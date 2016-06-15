package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class RunVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String abroad;
	//private List<Condition> conditionList;跑步馆没有热词，标签条件
	private List<APPRunCategory> categoryList;
	private List<APPBrand> brandList;
	private List<Size> sizeList;
	private List<Color> colorList;
	private List<APPProduct> productList;
	public String getAbroad() {
		return abroad;
	}
	public void setAbroad(String abroad) {
		this.abroad = abroad;
	}
	
	public List<APPRunCategory> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<APPRunCategory> categoryList) {
		this.categoryList = categoryList;
	}
	public List<APPBrand> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<APPBrand> brandList) {
		this.brandList = brandList;
	}
	public List<Size> getSizeList() {
		return sizeList;
	}
	public void setSizeList(List<Size> sizeList) {
		this.sizeList = sizeList;
	}
	public List<Color> getColorList() {
		return colorList;
	}
	public void setColorList(List<Color> colorList) {
		this.colorList = colorList;
	}
	public List<APPProduct> getProductList() {
		return productList;
	}
	public void setProductList(List<APPProduct> productList) {
		this.productList = productList;
	}
}
