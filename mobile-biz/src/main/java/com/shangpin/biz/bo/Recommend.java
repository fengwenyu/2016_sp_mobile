package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class Recommend implements Serializable {

	private static final long serialVersionUID = -8828814540096202859L;
	private String type;
	private String title;

	private List<Product> list;
	
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

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> products) {
		this.list = products;
	}

}
