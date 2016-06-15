package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class SearchAttributeGroup implements Serializable {

	private static final long serialVersionUID = 4257436072269067391L;

	private String parentId;

	private String parentSortType;

	private String name;

	private List<SearchAttributeValue> value;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentSortType() {
		return parentSortType;
	}

	public void setParentSortType(String parentSortType) {
		this.parentSortType = parentSortType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SearchAttributeValue> getValue() {
		return value;
	}

	public void setValue(List<SearchAttributeValue> value) {
		this.value = value;
	}

}
