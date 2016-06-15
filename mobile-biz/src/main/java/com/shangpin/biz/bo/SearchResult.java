package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 主站搜索结果对象
 * 
 * @author wangfeng
 * 
 */
public class SearchResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private String count; // 结果总数
	private String qTime;// 查询耗费时间
	private String abroad;// 是否含有海外商品
	private List<SearchBrand> brandList;// 品牌列表
	private SearchCategory parentCategory;// 父级品类
	private List<SearchCategory> categoryList;// 品类列表
	private List<SearchCategory> categoryList3;// 三级品类列表
	private List<SearchCategory> categoryList2;// 二级品类列表
	private List<SearchColor> colorList;// 颜色列表
	private List<SearchSize> sizeList;// 尺码列表
	private List<SearchHotWords> conditionList;
	private List<Product> productList;
	private List<SearchDynamicAttr> attributes;// 动态属性
	private List<SearchPostArea> postAreaList;// 商品来源
	private String suggest;// 纠错

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

	public List<SearchBrand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<SearchBrand> brandList) {
		this.brandList = brandList;
	}

	public List<SearchCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<SearchCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public List<SearchCategory> getCategoryList3() {
		return categoryList3;
	}

	public void setCategoryList3(List<SearchCategory> categoryList3) {
		this.categoryList3 = categoryList3;
	}

	public List<SearchCategory> getCategoryList2() {
		return categoryList2;
	}

	public void setCategoryList2(List<SearchCategory> categoryList2) {
		this.categoryList2 = categoryList2;
	}

	public List<SearchColor> getColorList() {
		return colorList;
	}

	public void setColorList(List<SearchColor> colorList) {
		this.colorList = colorList;
	}

	public List<SearchSize> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<SearchSize> sizeList) {
		this.sizeList = sizeList;
	}

	public List<SearchHotWords> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<SearchHotWords> conditionList) {
		this.conditionList = conditionList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public SearchCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(SearchCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<SearchDynamicAttr> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SearchDynamicAttr> attributes) {
		this.attributes = attributes;
	}

	public List<SearchPostArea> getPostAreaList() {
		return postAreaList;
	}

	public void setPostAreaList(List<SearchPostArea> postAreaList) {
		this.postAreaList = postAreaList;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

}
