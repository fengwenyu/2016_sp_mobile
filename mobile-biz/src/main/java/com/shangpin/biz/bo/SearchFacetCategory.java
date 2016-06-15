package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class SearchFacetCategory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private List<SearchCategory> searchCategories;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchCategory> getSearchCategories() {
		return searchCategories;
	}
	public void setSearchCategories(List<SearchCategory> searchCategories) {
		this.searchCategories = searchCategories;
	}
	

}
