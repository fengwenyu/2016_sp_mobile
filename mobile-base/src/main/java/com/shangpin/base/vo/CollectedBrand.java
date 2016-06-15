package com.shangpin.base.vo;

import java.io.Serializable;

public class CollectedBrand  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String brandEN;
	private String name;
	private String isFlagship;
	private String pic;
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrandEN() {
		return this.brandEN;
	}
	public void setBrandEN(String brandEN) {
		this.brandEN = brandEN;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsFlagship() {
		return this.isFlagship;
	}
	public void setIsFlagship(String isFlagship) {
		this.isFlagship = isFlagship;
	}
	public String getPic() {
		return this.pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
}
