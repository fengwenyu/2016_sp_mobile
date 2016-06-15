package com.shangpin.base.vo;

import java.io.Serializable;

public class CategoryVo  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//分类ID
	private String name;//分类名字
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
