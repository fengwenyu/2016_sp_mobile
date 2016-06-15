package com.shangpin.base.vo;

import java.io.Serializable;

public class MallBrand  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;

	private String nameEN;

	private String nameCN;

	private String isFlagship;

	private String pic;

	private String desc;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameEN() {
		return this.nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNameCN() {
		return this.nameCN;
	}

	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
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

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
	

}
