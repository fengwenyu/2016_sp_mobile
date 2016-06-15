package com.shangpin.biz.bo;

import java.io.Serializable;

import org.springframework.util.StringUtils;

public class LableSearchConditions implements Serializable{
	private static final long serialVersionUID = 8005210745648715047L;
	private String filters;
	private String type;
	private String order;
	private String tagId;
	private String start;
	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		type=StringUtils.isEmpty(type)?"1":type;
		this.type = type;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}


}
