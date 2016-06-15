package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class Advert implements Serializable{

	private static final long serialVersionUID = -2862613606862910059L;
	private String height;
	private String width;
	private List<AdvertModel> model;
	public List<AdvertModel> getModel() {
		return model;
	}
	public void setModel(List<AdvertModel> model) {
		this.model = model;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}

}
