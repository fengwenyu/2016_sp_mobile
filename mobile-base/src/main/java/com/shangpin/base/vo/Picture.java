package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 轮播图
 * 
 * @author huangxiaoliang
 * 
 */
public class Picture  implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;

	private String nameEN;

	private String nameCN;

	private String isFlagship;

	private String pic;

	private String type;

	private String refContent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNameCN() {
		return nameCN;
	}

	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}

	public String getIsFlagship() {
		return isFlagship;
	}

	public void setIsFlagship(String isFlagship) {
		this.isFlagship = isFlagship;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefContent() {
		return refContent;
	}

	public void setRefContent(String refContent) {
		this.refContent = refContent;
	}

}
