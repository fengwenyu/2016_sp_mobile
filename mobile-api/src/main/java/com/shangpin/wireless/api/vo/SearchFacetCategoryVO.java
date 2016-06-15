package com.shangpin.wireless.api.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类
 * @author Administrator
 *
 */
public class SearchFacetCategoryVO {
	private final String filterKey = "categoryid";	//	参数名称
	private String name;//分类名称
	private String level;	//	分类的级别
	private SearchFacetCategoryItemVO categoryItem = new SearchFacetCategoryItemVO();//查询条件
	private List<SearchFacetCategoryItemVO> searchCategoryItems = new ArrayList<SearchFacetCategoryItemVO>();

	public String getFilterKey() {
		return filterKey;
	}
	public SearchFacetCategoryItemVO getCategoryItem() {
		return categoryItem;
	}
	public void setCategoryItem(SearchFacetCategoryItemVO categoryItem) {
		this.categoryItem = categoryItem;
	}
	public List<SearchFacetCategoryItemVO> getSearchCategoryItems() {
		return searchCategoryItems;
	}
	public void setSearchCategoryItems(List<SearchFacetCategoryItemVO> searchCategoryItems) {
		this.searchCategoryItems = searchCategoryItems;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
