package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @ClassName: SearchParam
 * @Description:搜索参数类
 * @author wangfeng
 * @date 2015年09月08日
 * @version 1.0
 */
public class SearchParam implements Serializable {

	private static final long serialVersionUID = 2652483827078020983L;
	private String categoryId;// 品类id
	private String brandId;// 品牌id
	private String sizeId;// 尺码id
	private String colorId;// 颜色id
	private String attributeId;// 动态属性id
	private String priceId;// 价格id
	private String postArea;// 发货地
	private String dynamic;// 动态属性

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getSizeId() {
		return sizeId;
	}

	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public String getPostArea() {
		return postArea;
	}

	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}

	public String getDynamic() {
		return dynamic;
	}

	public void setDynamic(String dynamic) {
		this.dynamic = dynamic;
	}

}
