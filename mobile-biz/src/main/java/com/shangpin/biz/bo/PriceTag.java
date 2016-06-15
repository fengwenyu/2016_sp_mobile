package com.shangpin.biz.bo;

import java.io.Serializable;

public class PriceTag implements Serializable {

	private static final long serialVersionUID = 2722259212386946703L;
	private String type;// 1实际售卖价格2对比专柜价
	private String priceTitle;// 到手价
	private String priceDesc;// 含关税
	private String color;// 特殊颜色
	private String isShow;// 是否显示

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriceTitle() {
		return priceTitle;
	}

	public void setPriceTitle(String priceTitle) {
		this.priceTitle = priceTitle;
	}

	public String getPriceDesc() {
		return priceDesc;
	}

	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

}
