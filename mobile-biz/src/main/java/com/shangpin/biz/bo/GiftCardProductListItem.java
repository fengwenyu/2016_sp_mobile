package com.shangpin.biz.bo;

import java.io.Serializable;

public class GiftCardProductListItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String brandNameCN;
	private String categoryNo;
	private String productId;
	private String productName;
	private String marketPrice;
	private String limitedPrice;
	private String goldPrice;
	private String platinumPrice;
	private String diamondPrice;
	private String limitedVipPrice;
	private String promotionPrice;
	private String isSupportDiscount;
	private String promotionDesc;
	private String pic;
	private String status;
	private String count;
	private String picNo;
	private String prefix = "";// 商品前缀
	private String suffix = "";// 商品后缀
	private String brandNameEN = "";

	public String getBrandNameEN() {
		return brandNameEN;
	}

	public void setBrandNameEN(String brandNameEN) {
		this.brandNameEN = brandNameEN;
	}

	public String getBrandNameCN() {
		return brandNameCN;
	}

	public void setBrandNameCN(String brandNameCN) {
		this.brandNameCN = brandNameCN;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

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

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getLimitedPrice() {
		return limitedPrice;
	}

	public void setLimitedPrice(String limitedPrice) {
		this.limitedPrice = limitedPrice;
	}

	public String getGoldPrice() {
		return goldPrice;
	}

	public void setGoldPrice(String goldPrice) {
		this.goldPrice = goldPrice;
	}

	public String getPlatinumPrice() {
		return platinumPrice;
	}

	public void setPlatinumPrice(String platinumPrice) {
		this.platinumPrice = platinumPrice;
	}

	public String getDiamondPrice() {
		return diamondPrice;
	}

	public void setDiamondPrice(String diamondPrice) {
		this.diamondPrice = diamondPrice;
	}

	public String getLimitedVipPrice() {
		return limitedVipPrice;
	}

	public void setLimitedVipPrice(String limitedVipPrice) {
		this.limitedVipPrice = limitedVipPrice;
	}

	public String getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getIsSupportDiscount() {
		return isSupportDiscount;
	}

	public void setIsSupportDiscount(String isSupportDiscount) {
		this.isSupportDiscount = isSupportDiscount;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPicNo() {
		return picNo;
	}

	public void setPicNo(String picNo) {
		this.picNo = picNo;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
