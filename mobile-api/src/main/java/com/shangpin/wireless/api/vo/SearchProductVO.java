package com.shangpin.wireless.api.vo;

/**
 * 搜索商品对象，用于前台展示
 * 
 */
public class SearchProductVO {
	private String productNo;// 商品编号
	private String productName;// 商品名称
	private String marketPrice;// 市场价
	private String limitedPrice;// 普通会员价
	private String sellPrice;// 黄金会员价
	private String platinumPrice;// 白金会员价
	private String diamondPrice;// 钻石会员价
	private String limitedVipPrice;// 限时价
	private String productPicFile;// 商品图片编号
	private String productModelPicFile;// 人模图片编号
	private String productPicUrl;// 商品图片路径
	private String productModelPicUrl;// 人模图片路径
	private String brandNo;// 品牌编号
	private String brandCnName;// 品牌中文名
	private String brandEnName;// 品牌英文名
	private String isSupportDiscount;// 是否支持会员权益0，否；1，是
	private String availableStock;// 有效库存数
	private String isModelPic;// 是否是模特图
    private String erpCategoryNo;//erp分类

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

	public String getLimitedPrice() {
		return limitedPrice;
	}

	public void setLimitedPrice(String limitedPrice) {
		this.limitedPrice = limitedPrice;
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

	public String getLimitedVipPrice() {
		return limitedVipPrice;
	}

	public void setLimitedVipPrice(String limitedVipPrice) {
		this.limitedVipPrice = limitedVipPrice;
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

	public String getProductPicUrl() {
		return productPicUrl;
	}

	public void setProductPicUrl(String productPicUrl) {
		this.productPicUrl = productPicUrl;
	}

	public String getProductModelPicUrl() {
		return productModelPicUrl;
	}

	public void setProductModelPicUrl(String productModelPicUrl) {
		this.productModelPicUrl = productModelPicUrl;
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

    public String getIsModelPic() {
        return isModelPic;
    }

    public void setIsModelPic(String isModelPic) {
        this.isModelPic = isModelPic;
    }

    public String getErpCategoryNo() {
        return erpCategoryNo;
    }

    public void setErpCategoryNo(String erpCategoryNo) {
        this.erpCategoryNo = erpCategoryNo;
    }
	
}
