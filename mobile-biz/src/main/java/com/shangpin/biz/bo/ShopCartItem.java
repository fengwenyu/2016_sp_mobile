package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopCartItem implements Serializable{
	 /**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 private String totalAmount;
	 private String spareAmount;
	 private List<ShopCartList> cartList;
	 private String prompt;
	 private String maxQuantity;
	 private String isCheckedAll;
	 private String totalSettlement;
	 private String domesticTitle;
	 private String abroadTitle;
	 private String customsPrompt;
	 private List<ShopCartItem> abroad;
	 
	public List<ShopCartItem> getAbroad() {
		return abroad;
	}
	public void setAbroad(List<ShopCartItem> abroad) {
		this.abroad = abroad;
	}
	public String getMaxQuantity() {
		return maxQuantity;
	}
	public void setMaxQuantity(String maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	public String getIsCheckedAll() {
		return isCheckedAll;
	}
	public void setIsCheckedAll(String isCheckedAll) {
		this.isCheckedAll = isCheckedAll;
	}
	public String getTotalSettlement() {
		return totalSettlement;
	}
	public void setTotalSettlement(String totalSettlement) {
		this.totalSettlement = totalSettlement;
	}
	public String getDomesticTitle() {
		return domesticTitle;
	}
	public void setDomesticTitle(String domesticTitle) {
		this.domesticTitle = domesticTitle;
	}
	public String getAbroadTitle() {
		return abroadTitle;
	}
	public void setAbroadTitle(String abroadTitle) {
		this.abroadTitle = abroadTitle;
	}
	public String getCustomsPrompt() {
		return customsPrompt;
	}
	public void setCustomsPrompt(String customsPrompt) {
		this.customsPrompt = customsPrompt;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getSpareAmount() {
		return spareAmount;
	}
	public void setSpareAmount(String spareAmount) {
		this.spareAmount = spareAmount;
	}
	public List<ShopCartList> getCartList() {
		return cartList;
	}
	public void setCartList(List<ShopCartList> cartList) {
		this.cartList = cartList;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	 
}
