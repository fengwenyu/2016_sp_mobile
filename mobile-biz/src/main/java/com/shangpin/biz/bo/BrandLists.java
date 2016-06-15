package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class BrandLists implements Serializable {
	
	private static final long serialVersionUID = -6397757503957450498L;

	private String name;

	private List<MallBrand> list;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MallBrand> getList() {
		return list;
	}

	public void setList(List<MallBrand> list) {
		this.list = list;
	}
	
}
