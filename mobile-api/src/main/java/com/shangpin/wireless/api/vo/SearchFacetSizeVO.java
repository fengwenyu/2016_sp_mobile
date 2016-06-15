package com.shangpin.wireless.api.vo;

import java.util.ArrayList;
import java.util.List;



/**
 * 搜索尺码条件
 * 
 */
public class SearchFacetSizeVO {
	private final String filterKey = "productsize";	//	参数名称
	private String name;//条件名称
	private  SearchFacetSizeItemVO sizeItem = new SearchFacetSizeItemVO();
	private List<SearchFacetSizeItemVO> searchFacetSizeItems = new ArrayList<SearchFacetSizeItemVO>();//价格搜索列表
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchFacetSizeItemVO> getSearchFacetSizeItems() {
		return searchFacetSizeItems;
	}
	public void setSearchFacetSizeItems(List<SearchFacetSizeItemVO> searchFacetSizeItems) {
		this.searchFacetSizeItems = searchFacetSizeItems;
	}
	public String getFilterKey() {
		return filterKey;
	}
	

	

	
	
}
