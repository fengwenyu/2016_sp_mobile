package com.shangpin.biz.bo;

import java.io.Serializable;

public class Attribute implements Serializable{

	private static final long serialVersionUID = -983616760506989852L;
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
