package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: CartItems 
* @Description: 购物车商品条目集合
* @author qinyingchun
* @date 2014年11月6日
* @version 1.0 
*/
public class CartItems implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String IsPromotion;
	private String PromotionNo;
	private String PromotionType;
	private String PromotionContent;
	private String PromotionDesc;
	private String PromotionUrl;
	private String CouponPrice;
	private String PromotionName;
	private String multiCondition;
	private List<CartItem> CartItemList;
	public String getIsPromotion() {
		return IsPromotion;
	}
	public void setIsPromotion(String isPromotion) {
		IsPromotion = isPromotion;
	}
	public String getPromotionNo() {
		return PromotionNo;
	}
	public void setPromotionNo(String promotionNo) {
		PromotionNo = promotionNo;
	}
	public String getPromotionType() {
		return PromotionType;
	}
	public void setPromotionType(String promotionType) {
		PromotionType = promotionType;
	}
	public String getPromotionContent() {
		return PromotionContent;
	}
	public void setPromotionContent(String promotionContent) {
		PromotionContent = promotionContent;
	}
	public String getPromotionDesc() {
		return PromotionDesc;
	}
	public void setPromotionDesc(String promotionDesc) {
		PromotionDesc = promotionDesc;
	}
	public String getPromotionUrl() {
		return PromotionUrl;
	}
	public void setPromotionUrl(String promotionUrl) {
		PromotionUrl = promotionUrl;
	}
	public String getCouponPrice() {
		return CouponPrice;
	}
	public void setCouponPrice(String couponPrice) {
		CouponPrice = couponPrice;
	}
	public String getPromotionName() {
		return PromotionName;
	}
	public void setPromotionName(String promotionName) {
		PromotionName = promotionName;
	}
	public String getMultiCondition() {
		return multiCondition;
	}
	public void setMultiCondition(String multiCondition) {
		this.multiCondition = multiCondition;
	}
	public List<CartItem> getCartItemList() {
		return CartItemList;
	}
	public void setCartItemList(List<CartItem> cartItemList) {
		CartItemList = cartItemList;
	}

}
