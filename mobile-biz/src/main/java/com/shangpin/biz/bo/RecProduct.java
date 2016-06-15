package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

import com.shangpin.biz.utils.StringUtil;

public class RecProduct extends Product implements Serializable{

	private static final long serialVersionUID = -1879146863602116617L;
	/*private String adverTitle;//广告语
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
//*///	private String annualSales;
//	private String merchantType;
	
/*	private String SalePrice;
	private String MarketPriceNew;*/
	
	
	
	
	
   // new 	
   private String ProductName;
//   private String brandName;
//   private String categoryName;
   
  // private String spuId;
  // private String skuId;
  // private String imageUrl;
  // private String showPrice;
   
   
   
	

	/*public String getAnnualSales() {
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
	}*/
   
   
   

/**
 * 两个productName 有冲突,所以获取的时候加个判断
 * @return
 */
public String getProductName() {
	if(StringUtil.isNotEmpty(ProductName)){
		return ProductName;
	}else{
		return super.getProductName();
	}
}
/*public void setProductName(String productName) {
	ProductName = productName;
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
public String getSkuId() {
	return skuId;
}
public void setSkuId(String skuId) {
	this.skuId = skuId;
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
}*/

}
