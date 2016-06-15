package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class IndexBrands implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String name;
	private List<Brand> list;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Brand> getList() {
		return list;
	}
	public void setList(List<Brand> list) {
		this.list = list;
	}
	
	

}
