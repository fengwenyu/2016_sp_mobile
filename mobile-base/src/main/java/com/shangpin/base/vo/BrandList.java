package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

public class BrandList  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;
	
	private List<MallBrand> list;
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MallBrand> getList() {
		return this.list;
	}
	public void setList(List<MallBrand> list) {
		this.list = list;
	}
	
}
