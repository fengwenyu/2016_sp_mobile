package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class CommentDetail implements Serializable{

	private static final long serialVersionUID = 6774043592312418752L;
	private String giftType;
	private String productNo;
	private String productName;
	private String brandNameCN;
	private String brandNameEN;
	private String pic;
	private String status;
	private String skuNo;
	private String orderNo;
	private String orderDetailNo;
	private String price;
	private String priceTitle;
	private String quantity;
	private String quantityTitle;
	private String categoryNo;
	private List<Attribute> attribute;
	public String getGiftType() {
		return giftType;
	}
	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSkuNo() {
		return skuNo;
	}
	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderDetailNo() {
		return orderDetailNo;
	}
	public void setOrderDetailNo(String orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public List<Attribute> getAttribute() {
		return attribute;
	}
	public void setAttribute(List<Attribute> attribute) {
		this.attribute = attribute;
	}
	public String getPriceTitle() {
		return priceTitle;
	}
	public void setPriceTitle(String priceTitle) {
		this.priceTitle = priceTitle;
	}
	public String getQuantityTitle() {
		return quantityTitle;
	}
	public void setQuantityTitle(String quantityTitle) {
		this.quantityTitle = quantityTitle;
	}
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

    
}
