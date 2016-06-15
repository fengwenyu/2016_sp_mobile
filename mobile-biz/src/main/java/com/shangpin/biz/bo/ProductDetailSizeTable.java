package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class ProductDetailSizeTable implements Serializable{
	private static final long serialVersionUID = 2670987023083274801L;
	private String name;
	private List<String> value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	
}
