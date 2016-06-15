package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;
/**
 * 搜索返回的product的json数据
 * @author fengwenyu
 *
 */
public class ProductSreach implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private  String productNo;// 商品编号
	private  String productName;// 商品名称
	private  String marketPrice;// 市场价
	private  String sellPrice;// 黄金会员价
	private  String handPrice;
	private  String platinumPrice;// 白金会员价
	private  String diamondPrice;// 钻石会员价
	private  String limitedPrice;// 普通会员价
	private  String newLimitedPrice;
	private  String newSellPrice;
	private  String newPlatinumPrice;
	private  String newDiamondPrice;
	private  String promotionPrice;// 促销价
	private  String productPicFile;// 商品图片编号
	private  String productModelPicFile;// 人模图片编号
	private  String erpCategoryNo;// erp分类
	private  String brandNo;// 品牌编号
	private  String brandCnName;// 品牌中文名
	private  String brandEnName;// 品牌英文名
	private  String isSupportDiscount;// 是否支持会员权益0，否；1，是
	private  String availableStock;// 有效库存数
	private  String isPromotion;// 是否是促销 0不是, 1是
	private  String postArea;// 发货地来源 1 国内 2海外
	private  String prefix;// 促销前缀
	private  String suffix;// 促销后缀
	private  String promotionNotice;// 促销语
	private  String postAreaPic;//发货源国旗
	private  String isNewSeasonal;// 新品标  0：否；1：是；
	private  String annualSales;
	private  String sort;//排序
	private List<String> categoryNo;

	public List<String> getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(List<String> categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String getHandPrice() {
		return handPrice;
	}
	public void setHandPrice(String handPrice) {
		this.handPrice = handPrice;
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
	public String getNewLimitedPrice() {
		return newLimitedPrice;
	}
	public void setNewLimitedPrice(String newLimitedPrice) {
		this.newLimitedPrice = newLimitedPrice;
	}
	public String getNewSellPrice() {
		return newSellPrice;
	}
	public void setNewSellPrice(String newSellPrice) {
		this.newSellPrice = newSellPrice;
	}
	public String getNewPlatinumPrice() {
		return newPlatinumPrice;
	}
	public void setNewPlatinumPrice(String newPlatinumPrice) {
		this.newPlatinumPrice = newPlatinumPrice;
	}
	public String getNewDiamondPrice() {
		return newDiamondPrice;
	}
	public void setNewDiamondPrice(String newDiamondPrice) {
		this.newDiamondPrice = newDiamondPrice;
	}
	public String getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getProductPicFile() {
		return productPicFile;
	}
	public void setProductPicFile(String productPicFile) {
		this.productPicFile = productPicFile;
	}
	public String getProductModelPicFile() {
		return productModelPicFile;
	}
	public void setProductModelPicFile(String productModelPicFile) {
		this.productModelPicFile = productModelPicFile;
	}
	public String getErpCategoryNo() {
		return erpCategoryNo;
	}
	public void setErpCategoryNo(String erpCategoryNo) {
		this.erpCategoryNo = erpCategoryNo;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
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
	public String getIsSupportDiscount() {
		return isSupportDiscount;
	}
	public void setIsSupportDiscount(String isSupportDiscount) {
		this.isSupportDiscount = isSupportDiscount;
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
	public String getPostArea() {
		return postArea;
	}
	public void setPostArea(String postArea) {
		this.postArea = postArea;
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
	public String getPromotionNotice() {
		return promotionNotice;
	}
	public void setPromotionNotice(String promotionNotice) {
		this.promotionNotice = promotionNotice;
	}
	public String getPostAreaPic() {
		return postAreaPic;
	}
	public void setPostAreaPic(String postAreaPic) {
		this.postAreaPic = postAreaPic;
	}
	public String getIsNewSeasonal() {
		return isNewSeasonal;
	}
	public void setIsNewSeasonal(String isNewSeasonal) {
		this.isNewSeasonal = isNewSeasonal;
	}
	public String getAnnualSales() {
		return annualSales;
	}
	public void setAnnualSales(String annualSales) {
		this.annualSales = annualSales;
	}
	
	
}
