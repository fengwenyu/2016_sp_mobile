package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 主站搜索结果对象
 * 
 * @author xupengcheng
 * 
 */
public class SearchResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private String count; // 结果总数
	private String qTime;// 查询耗费时间
	private String abroad;// 是否含有海外商品
	private List<SearchHotWords> conditionList;
	private List<Brand> brandList;// 品牌列表
	private Category parentCategory;// 父级品类
	private List<Category> categoryList;// 品类列表
	private List<Color> colorList;// 颜色列表
	private List<Size> sizeList;// 尺码列表
	private List<Product> productList;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getqTime() {
		return qTime;
	}

	public void setqTime(String qTime) {
		this.qTime = qTime;
	}

	public String getAbroad() {
		return abroad;
	}

	public void setAbroad(String abroad) {
		this.abroad = abroad;
	}

	public List<Brand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<Brand> brandList) {
		this.brandList = brandList;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Color> getColorList() {
		return colorList;
	}

	public void setColorList(List<Color> colorList) {
		this.colorList = colorList;
	}

	public List<Size> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<Size> sizeList) {
		this.sizeList = sizeList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<SearchHotWords> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<SearchHotWords> conditionList) {
		this.conditionList = conditionList;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

}
