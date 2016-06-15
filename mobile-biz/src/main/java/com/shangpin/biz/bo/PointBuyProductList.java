package com.shangpin.biz.bo;

import java.io.Serializable;

public class PointBuyProductList implements Serializable{
	
	private static final long serialVersionUID = 11L;

	private String productId;//商品编号
	private String productName;//商品名称
	private String brandNameCN;//品牌中文
	private String brandNameEN;//品牌英文
	private String brandNo;//品牌编号
	private String name;//碎花连衣裙 通用规则所用	
	private String marketPrice;//市场价
	private String promotionPrice;//促销价
	private String promotionDesc;//促销描述
	private String pic;//
	private String postArea;//1国内;2海外
	private String discount;//折扣
	private String participateCount;//参与人数
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandNameCN() {
		return brandNameCN;
	}
	public void setBrandNameCN(String brandNameCN) {
		this.brandNameCN = brandNameCN;
	}
	public String getBrandNameEN() {
		return brandNameEN;
	}
	public void setBrandNameEN(String brandNameEN) {
		this.brandNameEN = brandNameEN;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getPromotionDesc() {
		return promotionDesc;
	}
	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPostArea() {
		return postArea;
	}
	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getParticipateCount() {
		return participateCount;
	}
	public void setParticipateCount(String participateCount) {
		this.participateCount = participateCount;
	}
	
}
