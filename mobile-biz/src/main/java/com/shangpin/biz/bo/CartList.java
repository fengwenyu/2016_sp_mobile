package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 购物车相关对象类
 * 
 * @author cuibinqiang
 * @date 2014-11-5
 *
 */
public class CartList implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="IsPromotion")
	private String isPromotion;
	
	@JsonProperty(value="PromotionNo")
	private String promotionNo;
	
	@JsonProperty(value="PromotionType")
	private String promotionType;
	
	@JsonProperty(value="PromotionContent")
	private String promotionContent;
	
	@JsonProperty(value="PromotionDesc")
	private String promotionDesc;
	
	@JsonProperty(value="PromotionUrl")
	private String promotionUrl;
	
	@JsonProperty(value="CouponPrice")
	private String couponPrice;
	
	@JsonProperty(value="PromotionName")
	private String promotionName;
	
	private String multiCondition;
	
	@JsonProperty(value="CartItemList")
	private List<CartInfoList> cartItemList;

	public String getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(String isPromotion) {
		this.isPromotion = isPromotion;
	}

	public String getPromotionNo() {
		return promotionNo;
	}

	public void setPromotionNo(String promotionNo) {
		this.promotionNo = promotionNo;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public String getPromotionContent() {
		return promotionContent;
	}

	public void setPromotionContent(String promotionContent) {
		this.promotionContent = promotionContent;
	}

	public String getPromotionDesc() {
		return promotionDesc;
	}

	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}

	public String getPromotionUrl() {
		return promotionUrl;
	}

	public void setPromotionUrl(String promotionUrl) {
		this.promotionUrl = promotionUrl;
	}

	public String getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(String couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getMultiCondition() {
		return multiCondition;
	}

	public void setMultiCondition(String multiCondition) {
		this.multiCondition = multiCondition;
	}

	public List<CartInfoList> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartInfoList> cartItemList) {
		this.cartItemList = cartItemList;
	}
	
}
