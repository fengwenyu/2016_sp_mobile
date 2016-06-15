package com.shangpin.base.vo;

import java.io.Serializable;

public class Activities implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String mobilepic;
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
	public String getMobilepic() {
		return mobilepic;
	}
	public void setMobilepic(String mobilepic) {
		this.mobilepic = mobilepic;
	}
	
}
