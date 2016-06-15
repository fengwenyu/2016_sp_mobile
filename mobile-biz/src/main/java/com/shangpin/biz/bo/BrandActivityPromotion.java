package com.shangpin.biz.bo;

import java.io.Serializable;

public class BrandActivityPromotion implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String pic;
	private String type;
	private String refContent;
	private String picNew;
	private String height;
	private String width;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPicNew() {
		return picNew;
	}
	public void setPicNew(String picNew) {
		this.picNew = picNew;
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
