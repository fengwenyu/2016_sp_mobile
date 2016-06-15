package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class EntranceTitle implements Serializable {

	private static final long serialVersionUID = -5553136553786453477L;

	private String title;

	private List<Entrance> list;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Entrance> getList() {
		return list;
	}

	public void setList(List<Entrance> list) {
		this.list = list;
	}

}
