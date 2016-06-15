package com.shangpin.wireless.api.vo;

import java.util.ArrayList;
import java.util.List;



/**
 * 搜索品牌条件
 * 
 */
public class SearchFacetBrandVO {
	private final String filterKey = "brandid";	//	参数名称
	private String name;//条件名称
	private SearchFacetBrandItemVO brandItem = new SearchFacetBrandItemVO();//  品牌key类
	private List<SearchFacetBrandItemVO> searchFacetBrandItems = new ArrayList<SearchFacetBrandItemVO>();//品牌搜索列表
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchFacetBrandItemVO> getSearchFacetBrandItems() {
		return searchFacetBrandItems;
	}
	public void setSearchFacetBrandItems(List<SearchFacetBrandItemVO> searchFacetBrandItems) {
		this.searchFacetBrandItems = searchFacetBrandItems;
	}
	public String getFilterKey() {
		return filterKey;
	}
	
}
