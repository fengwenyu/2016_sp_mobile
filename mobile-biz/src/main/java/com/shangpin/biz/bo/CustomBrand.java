package com.shangpin.biz.bo;

import java.io.Serializable;

public class CustomBrand implements Serializable {

	private static final long serialVersionUID = 4161906072526977507L;
	private String brandNo;
	private String brandEnName;
	private String brandCnName;
	private String brandLogo;

	private String nameEN;
	private String nameCN;
	private String name;
	private String isFlagship;
	private String pic;
	private String type;
	private String refContent;

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getBrandEnName() {
		return brandEnName;
	}

	public void setBrandEnName(String brandEnName) {
		this.brandEnName = brandEnName;
	}

	public String getBrandCnName() {
		return brandCnName;
	}

	public void setBrandCnName(String brandCnName) {
		this.brandCnName = brandCnName;
	}

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		if ("".equals(nameEN)) {
			nameEN = getBrandEnName();
		}
		this.nameEN = nameEN;
	}

	public String getNameCN() {
		return nameCN;
	}

	public void setNameCN(String nameCN) {
		this.nameCN = getBrandCnName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = getBrandEnName();
	}

	public String getIsFlagship() {
		return isFlagship;
	}

	public void setIsFlagship(String isFlagship) {
		this.isFlagship = "0";
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
		this.type = "3";
	}

	public String getRefContent() {
		return refContent;
	}

	public void setRefContent(String refContent) {
		this.refContent = getBrandNo();
	}

}
