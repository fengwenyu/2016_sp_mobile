package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class WorthTitle implements Serializable {

	private static final long serialVersionUID = -2115279298618756985L;

	private String title;

	private List<Product> list;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}
}
