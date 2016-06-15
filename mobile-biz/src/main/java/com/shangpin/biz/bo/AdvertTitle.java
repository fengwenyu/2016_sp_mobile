package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class AdvertTitle implements Serializable {

	private static final long serialVersionUID = 8481774666316063919L;

	private String title;

	private List<Operation> list;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Operation> getList() {
		return list;
	}

	public void setList(List<Operation> list) {
		this.list = list;
	}

}
