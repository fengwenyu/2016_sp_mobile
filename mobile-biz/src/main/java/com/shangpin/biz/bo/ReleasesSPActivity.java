package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class ReleasesSPActivity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private List<SPActivity> list;

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
