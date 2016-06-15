package com.shangpin.wireless.api.vo;

import java.util.ArrayList;
import java.util.List;



/**
 * 搜索颜色条件
 * 
 */
public class SearchFacetColorVO {
	private final String filterKey = "firstpropid";	//	参数名称
	private String name;//条件名称
	private SearchFacetColorItemVO colorsItem = new SearchFacetColorItemVO();//搜索条件
	private List<SearchFacetColorItemVO> searchFacetColorItems = new ArrayList<SearchFacetColorItemVO>();//颜色搜索列表
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchFacetColorItemVO> getSearchFacetColorItems() {
		return searchFacetColorItems;
	}
	public void setSearchFacetColorItems(List<SearchFacetColorItemVO> searchFacetColorItems) {
		this.searchFacetColorItems = searchFacetColorItems;
	}
	public String getFilterKey() {
		return filterKey;
	}
 
	
	

	
	
}
