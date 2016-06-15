package com.shangpin.biz.bo;

import java.io.Serializable;

public class SearchFacet implements Serializable{
	private static final long serialVersionUID = 1L;
	private SearchFacetBrand searchFacetBrand;//品牌
	private SearchFacetPrice searchFacetPrice;//价格
	private SearchFacetColor searchFacetColor;//颜色
	private SearchFacetSize searchFacetSize;//尺码
	private SearchFacetHotWords searchFacetHotWords;//热词
	private SearchFacetCategory searchFacet2Category;//2级分类
	private SearchFacetCategory searchFacet3Category;//3级分类
	private SearchFacetCategory searchFacet4Category;//4级分类
	private SearchFacetCategory manFacetCategory;//男士分类
	private SearchFacetCategory womanFacetCategory;//女士分类
	private SearchFacetSex searchFacetSex;
	public SearchFacetBrand getSearchFacetBrand() {
		return searchFacetBrand;
	}
	public void setSearchFacetBrand(SearchFacetBrand searchFacetBrand) {
		this.searchFacetBrand = searchFacetBrand;
	}
	public SearchFacetPrice getSearchFacetPrice() {
		return searchFacetPrice;
	}
	public void setSearchFacetPrice(SearchFacetPrice searchFacetPrice) {
		this.searchFacetPrice = searchFacetPrice;
	}
	public SearchFacetColor getSearchFacetColor() {
		return searchFacetColor;
	}
	public void setSearchFacetColor(SearchFacetColor searchFacetColor) {
		this.searchFacetColor = searchFacetColor;
	}
	public SearchFacetSize getSearchFacetSize() {
		return searchFacetSize;
	}
	public void setSearchFacetSize(SearchFacetSize searchFacetSize) {
		this.searchFacetSize = searchFacetSize;
	}
	public SearchFacetCategory getSearchFacet2Category() {
		return searchFacet2Category;
	}
	public void setSearchFacet2Category(SearchFacetCategory searchFacet2Category) {
		this.searchFacet2Category = searchFacet2Category;
	}
	public SearchFacetCategory getSearchFacet3Category() {
		return searchFacet3Category;
	}
	public void setSearchFacet3Category(SearchFacetCategory searchFacet3Category) {
		this.searchFacet3Category = searchFacet3Category;
	}
	public SearchFacetCategory getSearchFacet4Category() {
		return searchFacet4Category;
	}
	public void setSearchFacet4Category(SearchFacetCategory searchFacet4Category) {
		this.searchFacet4Category = searchFacet4Category;
	}
	public SearchFacetCategory getManFacetCategory() {
		return manFacetCategory;
	}
	public void setManFacetCategory(SearchFacetCategory manFacetCategory) {
		this.manFacetCategory = manFacetCategory;
	}
	public SearchFacetCategory getWomanFacetCategory() {
		return womanFacetCategory;
	}
	public void setWomanFacetCategory(SearchFacetCategory womanFacetCategory) {
		this.womanFacetCategory = womanFacetCategory;
	}
	public SearchFacetSex getSearchFacetSex() {
		return searchFacetSex;
	}
	public void setSearchFacetSex(SearchFacetSex searchFacetSex) {
		this.searchFacetSex = searchFacetSex;
	}
    public SearchFacetHotWords getSearchFacetHotWords() {
        return searchFacetHotWords;
    }
    public void setSearchFacetHotWords(SearchFacetHotWords searchFacetHotWords) {
        this.searchFacetHotWords = searchFacetHotWords;
    }

}
