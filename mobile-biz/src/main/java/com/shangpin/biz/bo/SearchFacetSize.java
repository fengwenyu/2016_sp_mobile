package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class SearchFacetSize implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private List<SearchSize> searchSizes;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchSize> getSearchSizes() {
		return searchSizes;
	}
	public void setSearchSizes(List<SearchSize> searchSizes) {
		this.searchSizes = searchSizes;
	}
	

}
