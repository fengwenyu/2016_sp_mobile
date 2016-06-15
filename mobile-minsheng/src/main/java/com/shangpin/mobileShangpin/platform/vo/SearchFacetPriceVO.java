package com.shangpin.mobileShangpin.platform.vo;

import java.util.ArrayList;
import java.util.List;



/**
 * 搜索价格条件
 * 
 */
public class SearchFacetPriceVO {
	private String name;//条件名称
	private SearchPriceItemVO priceItemVO = new SearchPriceItemVO();
	private List<SearchPriceItemVO> searchPriceItemVO=new ArrayList<SearchPriceItemVO>();//价格搜索列表
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SearchPriceItemVO getPriceItemVO() {
		return priceItemVO;
	}
	public void setPriceItemVO(SearchPriceItemVO priceItemVO) {
		this.priceItemVO = priceItemVO;
	}
	public List<SearchPriceItemVO> getSearchPriceItemVO() {
		return searchPriceItemVO;
	}
	public void setSearchPriceItemVO(List<SearchPriceItemVO> searchPriceItemVO) {
		this.searchPriceItemVO = searchPriceItemVO;
	}
 
	
	

	
	
}
