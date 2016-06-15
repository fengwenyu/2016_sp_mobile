package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class SearchFacetPrice implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private List<SearchPrice> searchPrices;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchPrice> getSearchPrices() {
		return searchPrices;
	}
	public void setSearchPrices(List<SearchPrice> searchPrices) {
		this.searchPrices = searchPrices;
	}
	

}
