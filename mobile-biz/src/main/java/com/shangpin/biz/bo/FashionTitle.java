package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class FashionTitle implements Serializable {

	private static final long serialVersionUID = 4697407343675108085L;

	private String title;

	private List<Fashion> list;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Fashion> getList() {
		return list;
	}

	public void setList(List<Fashion> list) {
		this.list = list;
	}

}
