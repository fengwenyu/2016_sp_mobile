package com.shangpin.biz.bo.cart;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6889070295059685101L;
	
	private String totalAmount;
	private String totalAmountDesc;
	private String spareAmount;
	private String maxQuantity;
	private String prompt;
	private String isCheckedAll;
	private String totalSettlement;
	private List<CartList> cartList;
	
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalAmountDesc() {
		return totalAmountDesc;
	}
	public void setTotalAmountDesc(String totalAmountDesc) {
		this.totalAmountDesc = totalAmountDesc;
	}
	public String getSpareAmount() {
		return spareAmount;
	}
	public void setSpareAmount(String spareAmount) {
		this.spareAmount = spareAmount;
	}
	public String getMaxQuantity() {
		return maxQuantity;
	}
	public void setMaxQuantity(String maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
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
	public List<CartList> getCartList() {
		return cartList;
	}
	public void setCartList(List<CartList> cartList) {
		this.cartList = cartList;
	}
	
	

}
