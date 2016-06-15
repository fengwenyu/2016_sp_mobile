package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class AdvertNewTitle implements Serializable {

	private static final long serialVersionUID = -7810116782029919820L;

	private String title;

	private List<Advert> list;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Advert> getList() {
		return list;
	}

	public void setList(List<Advert> list) {
		this.list = list;
	}

}
