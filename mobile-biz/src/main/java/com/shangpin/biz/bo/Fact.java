package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class Fact implements Serializable{
	private static final long serialVersionUID = 1L;
	private String facetName;
	private List<Item> items;
	public String getFacetName() {
		return facetName;
	}
	public void setFacetName(String facetName) {
		this.facetName = facetName;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	

}
