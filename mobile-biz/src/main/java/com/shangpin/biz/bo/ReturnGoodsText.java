package com.shangpin.biz.bo;

import java.io.Serializable;

public class ReturnGoodsText implements Serializable{

	private static final long serialVersionUID = 727328334860416920L;
	private String color;
	private String desc;
	private String isClick;
	private String fontSize;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getIsClick() {
		return isClick;
	}
	public void setIsClick(String isClick) {
		this.isClick = isClick;
	}
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

}
