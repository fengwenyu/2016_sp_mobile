package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class GiftCardBuyProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	private String buyId;
	private String id;
	private String name;
	private String sku;
	private String price;
	private String priceTitle = "商品金额";
	private String quantityTitle = "数量";
	private String brandNameCN;
	private String brandNameEN;
	private String pic;
	private String quantity;
	private List<Property> attribute;

	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Property> getAttribute() {
		return attribute;
	}

	public void setAttribute(List<Property> attribute) {
		this.attribute = attribute;
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

}
