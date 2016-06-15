package com.shangpin.biz.bo.cart;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CartList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5692491986114962380L;

	private String id;
	private String promoId;
	private String title;
	private String header;
	private String promoTag;
	private Click click;
	private List<CartProductList> productList;
	
	
	
	public Click getClick() {
		return click;
	}
	public void setClick(Click click) {
		this.click = click;
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
	public List<CartProductList> getProductList() {
		return productList;
	}
	public void setProductList(List<CartProductList> productList) {
		this.productList = productList;
	}
	
	
}
