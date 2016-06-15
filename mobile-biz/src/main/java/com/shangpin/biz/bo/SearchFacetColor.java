package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class SearchFacetColor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private List<SearchColor> searchColors;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchColor> getSearchColors() {
		return searchColors;
	}
	public void setSearchColors(List<SearchColor> searchColors) {
		this.searchColors = searchColors;
	}
	

}
