package com.shangpin.biz.bo;

import java.io.Serializable;

public class APPProduct implements Serializable{

	private static final long serialVersionUID = 1L;
	private String adverTitle;
	private String brandNameCN;
	private String brandNameEN;
	private String brandNo;
	private String count;
	private String diamondPrice;
	private String goldPrice;
	private String isSupportDiscount;
	private String limitedPrice;
	private String limitedVipPrice;
	private String marketPrice;
	private String name;
	private String pic;
	private String platinumPrice;
	private String productId;
	private String productName;
	private String promotionDesc;
	private String promotionPrice;
	private String refContent;
	private String status;
	private String postArea;
	private String type;
	private String prefix;// 促销前缀
	private String suffix;// 促销后缀
	
	
	
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
	public String getAdverTitle() {
		return adverTitle;
	}
	public void setAdverTitle(String adverTitle) {
		this.adverTitle = adverTitle;
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
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDiamondPrice() {
		return diamondPrice;
	}
	public void setDiamondPrice(String diamondPrice) {
		if (diamondPrice != null && diamondPrice.indexOf(".") > -1) {
			this.diamondPrice = diamondPrice.substring(0, diamondPrice.indexOf("."));
		} else {
			this.diamondPrice = diamondPrice;
		}
	}
	public String getGoldPrice() {
		return goldPrice;
	}
	public void setGoldPrice(String goldPrice) {
		if (goldPrice != null && goldPrice.indexOf(".") > -1) {
			this.goldPrice = goldPrice.substring(0, goldPrice.indexOf("."));
		} else {
			this.goldPrice = goldPrice;
		}
	}
	public String getIsSupportDiscount() {
		return isSupportDiscount;
	}
	public void setIsSupportDiscount(String isSupportDiscount) {
		this.isSupportDiscount = isSupportDiscount;
	}
	public String getLimitedPrice() {
		return limitedPrice;
	}
	public void setLimitedPrice(String limitedPrice) {
		if (limitedPrice != null && limitedPrice.indexOf(".") > -1) {
			this.limitedPrice = limitedPrice.substring(0, limitedPrice.indexOf("."));
		} else {
			this.limitedPrice = limitedPrice;
		}
	}
	public String getLimitedVipPrice() {
		return limitedVipPrice;
	}
	public void setLimitedVipPrice(String limitedVipPrice) {
		if (limitedVipPrice != null && limitedVipPrice.indexOf(".") > -1) {
			this.limitedVipPrice = limitedVipPrice.substring(0, limitedVipPrice.indexOf("."));
		} else {
			this.limitedVipPrice = limitedVipPrice;
		}
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		if (marketPrice != null && marketPrice.indexOf(".") > -1) {
			this.marketPrice = marketPrice.substring(0, marketPrice.indexOf("."));
		} else {
			this.marketPrice = marketPrice;
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPlatinumPrice() {
		return platinumPrice;
	}
	public void setPlatinumPrice(String platinumPrice) {
		if (platinumPrice != null && platinumPrice.indexOf(".") > -1) {
			this.platinumPrice = platinumPrice.substring(0, platinumPrice.indexOf("."));
		} else {
			this.platinumPrice = platinumPrice;
		}
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
	public String getPromotionDesc() {
		return promotionDesc;
	}
	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}
	public String getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(String promotionPrice) {
		if (promotionPrice != null && promotionPrice.indexOf(".") > -1) {
			this.promotionPrice = promotionPrice.substring(0, promotionPrice.indexOf("."));
		} else {
			this.promotionPrice = promotionPrice;
		}
	}
	public String getRefContent() {
		return refContent;
	}
	public void setRefContent(String refContent) {
		this.refContent = refContent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPostArea() {
		return postArea;
	}
	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
