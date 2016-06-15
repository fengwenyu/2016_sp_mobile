package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @author qinyingchun
 * 收藏商品
 *
 */
public class CollectProduct implements Serializable{
	private static final long serialVersionUID = 1L;
	private String type;
	private String id;
	private String brandId;
	private String brandNameEN;
	private String brandNameCN;
	private String skuId;
	private String productId;
	private String name;
	private String pic;
	private String marketPrice;
	private String limitedPrice;
	private String goldPrice;
	private String platinumPrice;
	private String diamondPrice;
	private String limitedVipPrice;
	private String promotionPrice;
	private String isSupportDiscount;
	private String promotionDesc;
	private String count;
	private String status;
	private int strongPrice;
	private int delPrice;
	private String isShelve;
	public int getStrongPrice() {
		return strongPrice;
	}
	public void setStrongPrice(int strongPrice) {
		this.strongPrice = strongPrice;
	}
	public int getDelPrice() {
		return delPrice;
	}
	public void setDelPrice(int delPrice) {
		this.delPrice = delPrice;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
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
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsShelve() {
		return isShelve;
	}
	public void setIsShelve(String isShelve) {
		this.isShelve = isShelve;
	}

}
