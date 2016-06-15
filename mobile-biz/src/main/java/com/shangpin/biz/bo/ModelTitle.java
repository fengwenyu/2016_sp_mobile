package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class ModelTitle implements Serializable {

	private static final long serialVersionUID = 1734364817941460293L;

	private String title;

	private List<Model> list;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Model> getList() {
		return list;
	}

	public void setList(List<Model> list) {
		this.list = list;
	}

}
