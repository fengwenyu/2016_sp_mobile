package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class RecommendProduct implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4944679452065929555L;
	private String productNo;
	private String productName;
	private String brandNo;
	private String goodsNo;
	private String marketPrice;
	private String sellPrice;
	private String platinumPrice;
	private String diamondPrice;
	private String limitedPrice;
	private String productTitle;
	private String productPicFile;
	private String dateCreate;
	private String dateShelf;
	private String erpCategoryNo;
	private String productPromotionFlag;
	private String brandCnName;
	private String brandEnName;
	private String brand;
	private String erpGenderStyle;
	private String isLimitedOutlet;
	private String isShow;
	private String isSupportDiscount;
	private String isShelf;
	private String availableStock;
	private String isPromotion;
	private String hasStock;
	private List<String> categoryNo;
	private List<String> categoryName;
	private List<String> cLv;
	private List<String> categoryNoLv1;
	private List<String> categoryNameLv1;
	private List<String> categoryNoLv2;
	private List<String> categoryNameLv2;
	private List<String> categoryNoLv3;
	private List<String> categoryNameLv3;
	private List<String> categoryNoLv4;
	private List<String> categoryNameLv4;
	private List<String> cLv2;
	private List<String> cLv3;
	private List<String> cLv4;
	private List<String> brandNewArrivalNo;  // 新品到货编号
	private List<String> productSize;  //尺码
	private List<String> primaryColorIds;  //主颜色
	private List<String> productPrimaryColors;  // 主颜色详细
	private String handPrice;
	private String merchantType;	
	private String postAreaId;
	private String postAreaPic;
	private String postArea;
	private String promotionPrice;// 促销价;
	private List<String> skuNos;
	
	
	
	
	private String prefix;// 促销前缀
	private String suffix;// 促销后缀
	private String promotionNotice;// 促销语
	private String productModelPicFile;// 人模图片编号
	private String SalePrice;
	private String MarketPriceNew;
	private String newLimitedPrice;// 原始普通会员价
	
	
	
	public String getNewLimitedPrice() {
		return newLimitedPrice;
	}

	public void setNewLimitedPrice(String newLimitedPrice) {
		this.newLimitedPrice = newLimitedPrice;
	}

	public String getproductNo() {
		return productNo;
	}
	
	/*public String setproductNo() {
		return ProductNo=productNo;
	}*/
	
	
	public String getSalePrice() {
		return SalePrice;
	}
	public void setSalePrice(String salePrice) {
		SalePrice = salePrice;
	}
	public String getMarketPriceNew() {
		return MarketPriceNew;
	}
	public void setMarketPriceNew(String marketPriceNew) {
		MarketPriceNew = marketPriceNew;
	}
	public String getProductModelPicFile() {
		return productModelPicFile;
	}
	public void setProductModelPicFile(String productModelPicFile) {
		this.productModelPicFile = productModelPicFile;
	}
	public String getPromotionNotice() {
		return promotionNotice;
	}
	public void setPromotionNotice(String promotionNotice) {
		this.promotionNotice = promotionNotice;
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
	public String getPromotionPrice() {
		return promotionPrice;
	}
	public List<String> getSkuNos() {
		return skuNos;
	}
	public void setSkuNos(List<String> skuNos) {
		this.skuNos = skuNos;
	}
	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
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
	public String getLimitedPrice() {
		return limitedPrice;
	}
	public void setLimitedPrice(String limitedPrice) {
		this.limitedPrice = limitedPrice;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductPicFile() {
		return productPicFile;
	}
	public void setProductPicFile(String productPicFile) {
		this.productPicFile = productPicFile;
	}
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getDateShelf() {
		return dateShelf;
	}
	public void setDateShelf(String dateShelf) {
		this.dateShelf = dateShelf;
	}
	public String getErpCategoryNo() {
		return erpCategoryNo;
	}
	public void setErpCategoryNo(String erpCategoryNo) {
		this.erpCategoryNo = erpCategoryNo;
	}
	public String getProductPromotionFlag() {
		return productPromotionFlag;
	}
	public void setProductPromotionFlag(String productPromotionFlag) {
		this.productPromotionFlag = productPromotionFlag;
	}
	public String getBrandCnName() {
		return brandCnName;
	}
	public void setBrandCnName(String brandCnName) {
		this.brandCnName = brandCnName;
	}
	public String getBrandEnName() {
		return brandEnName;
	}
	public void setBrandEnName(String brandEnName) {
		this.brandEnName = brandEnName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getErpGenderStyle() {
		return erpGenderStyle;
	}
	public void setErpGenderStyle(String erpGenderStyle) {
		this.erpGenderStyle = erpGenderStyle;
	}
	public String getIsLimitedOutlet() {
		return isLimitedOutlet;
	}
	public void setIsLimitedOutlet(String isLimitedOutlet) {
		this.isLimitedOutlet = isLimitedOutlet;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getIsSupportDiscount() {
		return isSupportDiscount;
	}
	public void setIsSupportDiscount(String isSupportDiscount) {
		this.isSupportDiscount = isSupportDiscount;
	}
	public String getIsShelf() {
		return isShelf;
	}
	public void setIsShelf(String isShelf) {
		this.isShelf = isShelf;
	}
	public String getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(String availableStock) {
		this.availableStock = availableStock;
	}
	public String getIsPromotion() {
		return isPromotion;
	}
	public void setIsPromotion(String isPromotion) {
		this.isPromotion = isPromotion;
	}
	public String getHasStock() {
		return hasStock;
	}
	public void setHasStock(String hasStock) {
		this.hasStock = hasStock;
	}
	public List<String> getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(List<String> categoryNo) {
		this.categoryNo = categoryNo;
	}
	public List<String> getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(List<String> categoryName) {
		this.categoryName = categoryName;
	}
	public List<String> getcLv() {
		return cLv;
	}
	public void setcLv(List<String> cLv) {
		this.cLv = cLv;
	}
	public List<String> getCategoryNoLv1() {
		return categoryNoLv1;
	}
	public void setCategoryNoLv1(List<String> categoryNoLv1) {
		this.categoryNoLv1 = categoryNoLv1;
	}
	public List<String> getCategoryNameLv1() {
		return categoryNameLv1;
	}
	public void setCategoryNameLv1(List<String> categoryNameLv1) {
		this.categoryNameLv1 = categoryNameLv1;
	}
	public List<String> getCategoryNoLv2() {
		return categoryNoLv2;
	}
	public void setCategoryNoLv2(List<String> categoryNoLv2) {
		this.categoryNoLv2 = categoryNoLv2;
	}
	public List<String> getCategoryNameLv2() {
		return categoryNameLv2;
	}
	public void setCategoryNameLv2(List<String> categoryNameLv2) {
		this.categoryNameLv2 = categoryNameLv2;
	}
	public List<String> getCategoryNoLv3() {
		return categoryNoLv3;
	}
	public void setCategoryNoLv3(List<String> categoryNoLv3) {
		this.categoryNoLv3 = categoryNoLv3;
	}
	public List<String> getCategoryNameLv3() {
		return categoryNameLv3;
	}
	public void setCategoryNameLv3(List<String> categoryNameLv3) {
		this.categoryNameLv3 = categoryNameLv3;
	}
	public List<String> getCategoryNoLv4() {
		return categoryNoLv4;
	}
	public void setCategoryNoLv4(List<String> categoryNoLv4) {
		this.categoryNoLv4 = categoryNoLv4;
	}
	public List<String> getCategoryNameLv4() {
		return categoryNameLv4;
	}
	public void setCategoryNameLv4(List<String> categoryNameLv4) {
		this.categoryNameLv4 = categoryNameLv4;
	}
	public List<String> getcLv2() {
		return cLv2;
	}
	public void setcLv2(List<String> cLv2) {
		this.cLv2 = cLv2;
	}
	public List<String> getcLv3() {
		return cLv3;
	}
	public void setcLv3(List<String> cLv3) {
		this.cLv3 = cLv3;
	}
	public List<String> getcLv4() {
		return cLv4;
	}
	public void setcLv4(List<String> cLv4) {
		this.cLv4 = cLv4;
	}
	public List<String> getBrandNewArrivalNo() {
		return brandNewArrivalNo;
	}
	public void setBrandNewArrivalNo(List<String> brandNewArrivalNo) {
		this.brandNewArrivalNo = brandNewArrivalNo;
	}
	public List<String> getProductSize() {
		return productSize;
	}
	public void setProductSize(List<String> productSize) {
		this.productSize = productSize;
	}
	public List<String> getPrimaryColorIds() {
		return primaryColorIds;
	}
	public void setPrimaryColorIds(List<String> primaryColorIds) {
		this.primaryColorIds = primaryColorIds;
	}
	public List<String> getProductPrimaryColors() {
		return productPrimaryColors;
	}
	public void setProductPrimaryColors(List<String> productPrimaryColors) {
		this.productPrimaryColors = productPrimaryColors;
	}
	public String getHandPrice() {
		return handPrice;
	}
	public void setHandPrice(String handPrice) {
		this.handPrice = handPrice;
	}
	public String getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	public String getPostAreaId() {
		return postAreaId;
	}
	public void setPostAreaId(String postAreaId) {
		this.postAreaId = postAreaId;
	}
	public String getPostAreaPic() {
		return postAreaPic;
	}
	public void setPostAreaPic(String postAreaPic) {
		this.postAreaPic = postAreaPic;
	}
	public String getPostArea() {
		return postArea;
	}
	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}
	
	

}
