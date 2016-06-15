package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class Sale implements Serializable {
	
	private static final long serialVersionUID = -4565568251539900513L;

	private String type;

	private String title;

	private List<SPActivity> list;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SPActivity> getList() {
		return list;
	}

	public void setList(List<SPActivity> list) {
		this.list = list;
	}

}
