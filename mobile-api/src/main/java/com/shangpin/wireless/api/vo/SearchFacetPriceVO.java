package com.shangpin.wireless.api.vo;

import java.util.ArrayList;
import java.util.List;



/**
 * 搜索价格条件
 * 
 */
public class SearchFacetPriceVO {
	private final String filterKey = "pricerange";	//	参数名称
	private String name;//条件名称
	private SearchFacetPriceItemVO priceItem = new SearchFacetPriceItemVO();
	private List<SearchFacetPriceItemVO> searchFacetPriceItems = new ArrayList<SearchFacetPriceItemVO>();//价格搜索列表
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchFacetPriceItemVO> getSearchFacetPriceItems() {
		return searchFacetPriceItems;
	}
	public void setSearchFacetPriceItems(List<SearchFacetPriceItemVO> searchFacetPriceItems) {
		this.searchFacetPriceItems = searchFacetPriceItems;
	}
	public String getFilterKey() {
		return filterKey;
	}
 
	
	

	
	
}
