package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopCartList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String promoId;
	private String title;
	private String header;
	private String promoTag;
	private List<ShopCartProductList> productList;
	private String DateAdd;
	private String IsValid;

	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPromoTag() {
		return promoTag;
	}

	public void setPromoTag(String promoTag) {
		this.promoTag = promoTag;
	}

	public String getIsValid() {
		return IsValid;
	}

	public void setIsValid(String isValid) {
		IsValid = isValid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPromoId() {
		return promoId;
	}

	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ShopCartProductList> getProductList() {
		return productList;
	}

	public void setProductList(List<ShopCartProductList> productList) {
		this.productList = productList;
	}

	public String getDateAdd() {
		return DateAdd;
	}

	public void setDateAdd(String dateAdd) {
		DateAdd = dateAdd;
	}
	
}
