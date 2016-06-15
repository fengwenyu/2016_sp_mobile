package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class NewGoodsTitle implements Serializable {

	private static final long serialVersionUID = -2287857399391781898L;

	private String title;

	private List<Brand> list;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Brand> getList() {
		return list;
	}

	public void setList(List<Brand> list) {
		this.list = list;
	}

}
