package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class SearchProductResult implements Serializable{
	private static final long serialVersionUID = 1L;
	private String count; // 结果总数
	private String qTime;// 查询耗费时间
	private List<SearchBrand> brandList;// 品牌列表
	private List<SearchCategory> categoryLv2List;// 品类列表
	private List<SearchCategory> categoryLv3List;// 品类列表
	private List<SearchCategory> categoryLv4List;// 品类列表
	private List<SearchColor> colorList;// 颜色列表
	private List<SearchSize> sizeList;// 尺码列表
	private List<Product> productList;
	private List<SearchHotWords> tagList;// 热词
	private List<SearchCategory> manFacetCategory;//男士分类
	private List<SearchCategory> womanFacetCategory;//女士分类
	private List<Sex> sexs;
	private List<SearchPrice> priceList;//价格
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
	public List<SearchBrand> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<SearchBrand> brandList) {
		this.brandList = brandList;
	}
	public List<SearchCategory> getCategoryLv2List() {
		return categoryLv2List;
	}
	public void setCategoryLv2List(List<SearchCategory> categoryLv2List) {
		this.categoryLv2List = categoryLv2List;
	}
	public List<SearchCategory> getCategoryLv3List() {
		return categoryLv3List;
	}
	public void setCategoryLv3List(List<SearchCategory> categoryLv3List) {
		this.categoryLv3List = categoryLv3List;
	}
	public List<SearchCategory> getCategoryLv4List() {
		return categoryLv4List;
	}
	public void setCategoryLv4List(List<SearchCategory> categoryLv4List) {
		this.categoryLv4List = categoryLv4List;
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
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public List<SearchCategory> getManFacetCategory() {
		return manFacetCategory;
	}
	public void setManFacetCategory(List<SearchCategory> manFacetCategory) {
		this.manFacetCategory = manFacetCategory;
	}
	public List<SearchCategory> getWomanFacetCategory() {
		return womanFacetCategory;
	}
	public void setWomanFacetCategory(List<SearchCategory> womanFacetCategory) {
		this.womanFacetCategory = womanFacetCategory;
	}
	public List<Sex> getSexs() {
		return sexs;
	}
	public void setSexs(List<Sex> sexs) {
		this.sexs = sexs;
	}
	public List<SearchPrice> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<SearchPrice> priceList) {
		this.priceList = priceList;
	}
    public List<SearchHotWords> getTagList() {
        return tagList;
    }
    public void setTagList(List<SearchHotWords> tagList) {
        this.tagList = tagList;
    }
	
	
	
	
}
