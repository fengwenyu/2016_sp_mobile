package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class MallCategory implements Serializable {

	private static final long serialVersionUID = 8202748671767166675L;

	private String categoryImage;

	private List<CategoryVo> categoryList;

	public String getCategoryImage() {
		return this.categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	public List<CategoryVo> getCategoryList() {
		return this.categoryList;
	}

	public void setCategoryList(List<CategoryVo> categoryList) {
		this.categoryList = categoryList;
	}

}
