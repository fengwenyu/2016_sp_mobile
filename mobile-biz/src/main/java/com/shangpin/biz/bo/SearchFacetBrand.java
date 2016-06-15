package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: SearchFacetBrand 
* @Description: 
* @author qinyingchun
* @date 2014年10月28日
* @version 1.0 
*/
public class SearchFacetBrand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private List<SearchBrand> searchBrands;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchBrand> getSearchBrands() {
		return searchBrands;
	}
	public void setSearchBrands(List<SearchBrand> searchBrands) {
		this.searchBrands = searchBrands;
	}
	
	

}
