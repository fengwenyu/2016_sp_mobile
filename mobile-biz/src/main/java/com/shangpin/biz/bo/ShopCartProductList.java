package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopCartProductList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cartDetailId;
	private String spu;
	private String name;
	private String brand;
	private String sku;
	private String price;
	private String pic;
	private String quantity;
	private String isChecked;
	private String count;
	private String valid;
	private String msgType;
	private String msg;
	private String postArea;
	private String countryPic;
	private List<ShopCartProductAttribute> attribute;
	
	private String TotalAmount;
	private String DateAdd;
	private String PromoId;
	private String priceTag;
	
	public String getPostArea() {
		return postArea;
	}
	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}
	public String getCountryPic() {
		return countryPic;
	}
	public void setCountryPic(String countryPic) {
		this.countryPic = countryPic;
	}
	public String getCartDetailId() {
		return cartDetailId;
	}
	public void setCartDetailId(String cartDetailId) {
		this.cartDetailId = cartDetailId;
	}
	public String getSpu() {
		return spu;
	}
	public void setSpu(String spu) {
		this.spu = spu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<ShopCartProductAttribute> getAttribute() {
		return attribute;
	}
	public void setAttribute(List<ShopCartProductAttribute> attribute) {
		this.attribute = attribute;
	}
	public String getPromoId() {
		return PromoId;
	}
	public void setPromoId(String promoId) {
		PromoId = promoId;
	}
	public String getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
	}
	public String getDateAdd() {
		return DateAdd;
	}
	public void setDateAdd(String dateAdd) {
		DateAdd = dateAdd;
	}
	public String getPriceTag() {
		return priceTag;
	}
	public void setPriceTag(String priceTag) {
		this.priceTag = priceTag;
	}

	
}
