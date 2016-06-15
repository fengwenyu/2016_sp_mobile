package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Product
 * @Description:页面展示商品实体类
 * @author qinyingchun
 * @date 2014年10月25日
 * @version 1.0
 */

public class RecNewProduct implements Serializable {
	private static final long serialVersionUID = 8444924836095691106L;
	private String adverTitle;//广告语
	private String productId;// 商品编号
	private String productNo;// 商品编号
	private String productName;// 商品名称
	private String marketPrice;// 市场价
	private String limitedPrice;// 普通会员价
	private String sellPrice;// 黄金会员价
	private String goldPrice;// 黄金会员价
	private String platinumPrice;// 白金会员价
	private String diamondPrice;// 钻石会员价
	private String limitedVipPrice;// 限时价
	private String promotionPrice;// 促销价
	private String productPicFile;// 商品图片编号
	private String productPicUrl;// 商品图片路径
	private String productModelPicFile;// 人模图片编号
	private String productModelPicUrl;// 人模图片路径
	private String productShowFlag;// 商品显示标识 010000
	private String brandNo;// 品牌编号
	private String brandNameEN;// 品牌中文名
	private String brandNameCN;// 品牌英文名
	private String brandCnName;// 品牌中文名
	private String brandEnName;// 品牌英文名
	private String isSupportDiscount;// 是否支持会员权益0，否；1，是
	private String availableStock;// 有效库存数
	private String productPromotionFlag;// 商品促销标记 0，否；1，是
	private String delProductPrice;// 显示 1 否 0
	private String productName_HighLight;
	private String hasStock;// 是否有库存 1 有 0 没有
	private String count;// 有效库存数
	private List<String> categoryNo;
	private String isModelPic;// 是否是模特图
	private String pic;// 商品图片路径
	private String status;// 促销状态
	private String erpCategoryNo;// erp分类
	private String postArea;// 发货地来源 1 国内 2海外
	private String picNo;// 显示图片编号
	private String countryPic;// 海外尚品国旗图片
	private List<Tag> tag;// 单品的标签
	private String skuId;
	private String promotionDesc;
	private int strongPrice;
	private int delPrice; 
	private String name;
	private String type;
	private String refContent;

	private String advertWord;
	private String collections;
	private String comments;
	private String scenePic;// 场景图片
	private String isPromotion;// 是否是
	private String prefix;// 促销前缀
	private String suffix;// 促销后缀
	private String promotionNotice;// 促销语
	private String postAreaPic;//发货源国旗
	private String isNewSeasonal;// 新品标  0：否；1：是；
	private String hotValue;//热度值
	private String postAreaId;//发货地
	private String width;//图片宽度
	private String height;//图片高度
	private String guaranteeImg;
	private String productStatus;//商品状态 1.售罄 2.新品
	private String promoLogo;//促销标
	private String priceTitle;//价格title
	private String priceColor;//价格颜色
	private String descColor;//描述颜色
	private String priceDesc; //价格描述
	private String expressLogo;//直发图片
	
	private String newLimitedPrice;// 原始普通会员价
	private String newSellPrice;// 原始黄金会员价
	private String newPlatinumPrice;// 原始白金会员价
	private String newDiamondPrice;// 原始钻石会员价
	private String annualSales;
	private String merchantType;
	
	private String SalePrice;
	private String MarketPriceNew;
	
	
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSpuId() {
		return spuId;
	}

	public void setSpuId(String spuId) {
		this.spuId = spuId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getShowPrice() {
		return showPrice;
	}

	public void setShowPrice(String showPrice) {
		this.showPrice = showPrice;
	}

	private String brandName;
    private String categoryName;
    private String spuId;
    private String imageUrl;
    private String showPrice;
	
	
	
	
	public String getScenePic() {
		return scenePic;
	}

	public void setScenePic(String scenePic) {
		this.scenePic = scenePic;
	}

	public String getPostAreaId() {
		return postAreaId;
	}

	public void setPostAreaId(String postAreaId) {
		this.postAreaId = postAreaId;
	}

	public String getIsNewSeasonal() {
		return isNewSeasonal;
	}

	public void setIsNewSeasonal(String isNewSeasonal) {
		this.isNewSeasonal = isNewSeasonal;
	}

	public String getHotValue() {
		return hotValue;
	}

	public void setHotValue(String hotValue) {
		this.hotValue = hotValue;
	}

	public String getPostAreaPic() {
		return postAreaPic;
	}

	public void setPostAreaPic(String postAreaPic) {
		this.postAreaPic = postAreaPic;
	}

	public String getAdverTitle() {
		return adverTitle;
	}

	public void setAdverTitle(String adverTitle) {
		this.adverTitle = adverTitle;
	}

	public String getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(String isPromotion) {
		this.isPromotion = isPromotion;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefContent() {
		return refContent;
	}

	public void setRefContent(String refContent) {
		this.refContent = refContent;
	}

	public List<String> getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(List<String> categoryNo) {
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

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		if (sellPrice != null && sellPrice.indexOf(".") > -1) {
			this.sellPrice = sellPrice.substring(0, sellPrice.indexOf("."));
		} else {
			this.sellPrice = sellPrice;
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

	public String getProductPicFile() {
		return productPicFile;
	}

	public void setProductPicFile(String productPicFile) {
		this.productPicFile = productPicFile;
	}

	public String getProductPicUrl() {
		return productPicUrl;
	}

	public void setProductPicUrl(String productPicUrl) {
		this.productPicUrl = productPicUrl;
	}

	public String getProductModelPicFile() {
		return productModelPicFile;
	}

	public void setProductModelPicFile(String productModelPicFile) {
		this.productModelPicFile = productModelPicFile;
	}

	public String getProductModelPicUrl() {
		return productModelPicUrl;
	}

	public void setProductModelPicUrl(String productModelPicUrl) {
		this.productModelPicUrl = productModelPicUrl;
	}

	public String getProductShowFlag() {
		return productShowFlag;
	}

	public void setProductShowFlag(String productShowFlag) {
		this.productShowFlag = productShowFlag;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
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

	public String getProductPromotionFlag() {
		return productPromotionFlag;
	}

	public void setProductPromotionFlag(String productPromotionFlag) {
		this.productPromotionFlag = productPromotionFlag;
	}

	public String getDelProductPrice() {
		return delProductPrice;
	}

	public void setDelProductPrice(String delProductPrice) {
		this.delProductPrice = delProductPrice;
	}

	public String getProductName_HighLight() {
		return productName_HighLight;
	}

	public void setProductName_HighLight(String productName_HighLight) {
		this.productName_HighLight = productName_HighLight;
	}

	public String getHasStock() {
		return hasStock;
	}

	public void setHasStock(String hasStock) {
		this.hasStock = hasStock;
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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
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

	public String getPromotionDesc() {
		return promotionDesc;
	}

	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}

	public String getPostArea() {
		return postArea;
	}

	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}

	public String getPicNo() {
		return picNo;
	}

	public void setPicNo(String picNo) {
		this.picNo = picNo;
	}

	public String getAdvertWord() {
		return advertWord;
	}

	public void setAdvertWord(String advertWord) {
		this.advertWord = advertWord;
	}

	public String getCollections() {
		return collections;
	}

	public void setCollections(String collections) {
		this.collections = collections;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCountryPic() {
		return countryPic;
	}

	public void setCountryPic(String countryPic) {
		this.countryPic = countryPic;
	}

	public List<Tag> getTag() {
		return tag;
	}

	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
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

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

    public String getGuaranteeImg() {
        return guaranteeImg;
    }

    public void setGuaranteeImg(String guaranteeImg) {
        this.guaranteeImg = guaranteeImg;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getPromoLogo() {
        return promoLogo;
    }

    public void setPromoLogo(String promoLogo) {
        this.promoLogo = promoLogo;
    }



    public String getPriceTitle() {
        return priceTitle;
    }

    public void setPriceTitle(String priceTitle) {
        this.priceTitle = priceTitle;
    }

    public String getPriceColor() {
        return priceColor;
    }

    public void setPriceColor(String priceColor) {
        this.priceColor = priceColor;
    }

    public String getDescColor() {
        return descColor;
    }

    public void setDescColor(String descColor) {
        this.descColor = descColor;
    }

    public String getPriceDesc() {
        return priceDesc;
    }

    public void setPriceDesc(String priceDesc) {
        this.priceDesc = priceDesc;
    }

    public String getExpressLogo() {
        return expressLogo;
    }

    public void setExpressLogo(String expressLogo) {
        this.expressLogo = expressLogo;
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

	public String getAnnualSales() {
		return annualSales;
	}

	public void setAnnualSales(String annualSales) {
		this.annualSales = annualSales;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
    
}
