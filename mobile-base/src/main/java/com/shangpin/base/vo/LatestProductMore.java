package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

public class LatestProductMore  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String brandId;
	
	private String brandNameEN;
	
	private String brandNameCN;
	
	private String isFlagship;
	
	private String pic;
	
	private String name;
	
	private String type;

	private String refContent;
	
	private List<ProductMore> latestProductMoreList;

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandNameEN() {
		return brandNameEN;
	}

	public void setBrandNameEN(String brandNameEN) {
		this.brandNameEN = brandNameEN;
	}

	public String getBrandNameCN() {
		return brandNameCN;
	}

	public void setBrandNameCN(String brandNameCN) {
		this.brandNameCN = brandNameCN;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<ProductMore> getLatestProductMoreList() {
		return latestProductMoreList;
	}

	public void setLatestProductMoreList(List<ProductMore> latestProductMoreList) {
		this.latestProductMoreList = latestProductMoreList;
	}

}
