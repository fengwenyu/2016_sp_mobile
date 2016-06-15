package com.shangpin.biz.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 购物车信息类
 * 
 * @author cuibinqiang
 * @date 2014-11-5
 *
 */
public class CartInfoList implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty(value = "ShoppingCartDetailId")
	private String shoppingCartDetailId;// 购物车详情Id
	@JsonProperty(value = "ProductNo")
	private String productNo;// 商品编号
	@JsonProperty(value = "SkuNo")
	private String skuNo;// 商品sku
	@JsonProperty(value = "CategoryNo")
	private String categoryNo;// 活动/专题编号
	@JsonProperty(value = "Quantity")
	private String quantity;// 单品购买数量
	@JsonProperty(value = "DateAdd")
	private String dateAdd;// 添加购物车时间
	@JsonProperty(value = "Price")
	private String price;// 单价
	@JsonProperty(value = "TotalAmount")
	private String totalAmount;// 单品总价
	@JsonProperty(value = "TotalQuantity")
	private String totalQuantity;// 单品总数量
	@JsonProperty(value = "CartType")
	private String cartType;// 购物车类型：尚品/奥莱
	@JsonProperty(value = "MsgType")
	private String msgType;// 报错类型（1:售罄 2：剩余量 3：活动过期 4:商品已下架）
	@JsonProperty(value = "Msg")
	private String msg;// 报错信息
	@JsonProperty(value = "SubjectDateEnd")
	private String subjectDateEnd;//
	@JsonProperty(value = "InventoryQuantity")
	private String inventoryQuantity;
	@JsonProperty(value = "ProductName")
	private String productName;
	@JsonProperty(value = "BrandEnName")
	private String brandEnName;
	@JsonProperty(value = "BrandCnName")
	private String brandCnName;
	@JsonProperty(value = "IsSupportDiscount")
	private String isSupportDiscount;
	@JsonProperty(value = "ProductPicFile")
	private String productPicFile;
	@JsonProperty(value = "ProductCategoryNo")
	private String productCategoryNo;
	@JsonProperty(value = "ProductUrl")
	private String productUrl;
	@JsonProperty(value = "TopicSubjectFlag")
	private String topicSubjectFlag;
	@JsonProperty(value = "FavoritePrice")
	private String favoritePrice;
	@JsonProperty(value = "IsUserTicket")
	private String isUserTicket;
	@JsonProperty(value = "IsSupportCod")
	private String isSupportCod;
	@JsonProperty(value = "IsPromotion")
	private String isPromotion;
	@JsonProperty(value = "CardType")
	private String cardType;
	@JsonProperty(value = "BuyType")
	private String buyType;
	@JsonProperty(value = "DynamicAttributeText")
	private String dynamicAttributeText;
	@JsonProperty(value = "ColorSize")
	private String colorSize;
	@JsonProperty(value = "IsLimitedOutlet")
	private String isLimitedOutlet;
	@JsonProperty(value = "skuFrom")
	private String skuFrom;
	@JsonProperty(value = "AolaiSubjectNo")
	private String aolaiSubjectNo;
	@JsonProperty(value = "BrandType")
	private String brandType;
	@JsonProperty(value = "BrandNo")
	private String brandNo;
	@JsonProperty(value = "SubjectNo")
	private String subjectNo;
	@JsonProperty(value = "LimitedPrice")
	private String limitedPrice;
	@JsonProperty(value = "PromotionPrice")
	private String promotionPrice;
	@JsonProperty(value = "PromotionNo")
	private String promotionNo;
	@JsonProperty(value = "PromotionType")
	private String promotionType;
	@JsonProperty(value = "PromotionContent")
	private String promotionContent;
	@JsonProperty(value = "PromotionName")
	private String promotionName;
	@JsonProperty(value = "PromotionDesc")
	private String promotionDesc;
	@JsonProperty(value = "PromotionUrl")
	private String promotionUrl;
	private String multiCondition;
	@JsonProperty(value = "IsCartCoupon")
	private String isCartCoupon;
	@JsonProperty(value = "MobileImg")
	private String mobileImg;
	@JsonProperty(value = "MobileSkuAttrText")
	private String mobileSkuAttrText;
	@JsonProperty(value = "MobileSubjectEndTime")
	private String mobileSubjectEndTime;
	@JsonProperty(value = "Stock")
	private String stock;
	@JsonProperty(value = "IsOutside")
	private String isOutside;
	@JsonProperty(value = "BelongMerchantNo")
	private String belongMerchantNo;
	@JsonProperty(value = "BelongMerchantName")
	private String belongMerchantName;
	@JsonProperty(value = "OriginCurrencyNo")
	private String originCurrencyNo;
	@JsonProperty(value = "OriginCurrency")
	private String originCurrency;
	@JsonProperty(value = "OriginCurrencyAmount")
	private String originCurrencyAmount;
	@JsonProperty(value = "GrossWeightUnit")
	private String grossWeightUnit;
	@JsonProperty(value = "GrossWeight")
	private String grossWeight;
	@JsonProperty(value = "TaxRate")
	private String taxRate;
	@JsonProperty(value = "Freight")
	private String freight;
	@JsonProperty(value = "Customs")
	private String customs;
	@JsonProperty(value = "ComeToHandPrice")
	private String comeToHandPrice;
	@JsonProperty(value = "PromoId")
	private String promoId;
	@JsonProperty(value = "priceTitle")
	private String priceTitle;
	@JsonProperty(value = "Merchant")
    private String Merchant;
	@JsonProperty(value = "countryPic")
    private String countryPic;
	public String getShoppingCartDetailId() {
		return shoppingCartDetailId;
	}

	public void setShoppingCartDetailId(String shoppingCartDetailId) {
		this.shoppingCartDetailId = shoppingCartDetailId;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(String dateAdd) {
		this.dateAdd = dateAdd;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getCartType() {
		return cartType;
	}

	public void setCartType(String cartType) {
		this.cartType = cartType;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSubjectDateEnd() {
		return subjectDateEnd;
	}

	public void setSubjectDateEnd(String subjectDateEnd) {
		this.subjectDateEnd = subjectDateEnd;
	}

	public String getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(String inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrandEnName() {
		return brandEnName;
	}

	public void setBrandEnName(String brandEnName) {
		this.brandEnName = brandEnName;
	}

	public String getBrandCnName() {
		return brandCnName;
	}

	public void setBrandCnName(String brandCnName) {
		this.brandCnName = brandCnName;
	}

	public String getIsSupportDiscount() {
		return isSupportDiscount;
	}

	public void setIsSupportDiscount(String isSupportDiscount) {
		this.isSupportDiscount = isSupportDiscount;
	}

	public String getProductPicFile() {
		return productPicFile;
	}

	public void setProductPicFile(String productPicFile) {
		this.productPicFile = productPicFile;
	}

	public String getProductCategoryNo() {
		return productCategoryNo;
	}

	public void setProductCategoryNo(String productCategoryNo) {
		this.productCategoryNo = productCategoryNo;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getTopicSubjectFlag() {
		return topicSubjectFlag;
	}

	public void setTopicSubjectFlag(String topicSubjectFlag) {
		this.topicSubjectFlag = topicSubjectFlag;
	}

	public String getFavoritePrice() {
		return favoritePrice;
	}

	public void setFavoritePrice(String favoritePrice) {
		this.favoritePrice = favoritePrice;
	}

	public String getIsUserTicket() {
		return isUserTicket;
	}

	public void setIsUserTicket(String isUserTicket) {
		this.isUserTicket = isUserTicket;
	}

	public String getIsSupportCod() {
		return isSupportCod;
	}

	public void setIsSupportCod(String isSupportCod) {
		this.isSupportCod = isSupportCod;
	}

	public String getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(String isPromotion) {
		this.isPromotion = isPromotion;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBuyType() {
		return buyType;
	}

	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}

	public String getDynamicAttributeText() {
		return dynamicAttributeText;
	}

	public void setDynamicAttributeText(String dynamicAttributeText) {
		this.dynamicAttributeText = dynamicAttributeText;
	}

	public String getColorSize() {
		return colorSize;
	}

	public void setColorSize(String colorSize) {
		this.colorSize = colorSize;
	}

	public String getIsLimitedOutlet() {
		return isLimitedOutlet;
	}

	public void setIsLimitedOutlet(String isLimitedOutlet) {
		this.isLimitedOutlet = isLimitedOutlet;
	}

	public String getSkuFrom() {
		return skuFrom;
	}

	public void setSkuFrom(String skuFrom) {
		this.skuFrom = skuFrom;
	}

	public String getAolaiSubjectNo() {
		return aolaiSubjectNo;
	}

	public void setAolaiSubjectNo(String aolaiSubjectNo) {
		this.aolaiSubjectNo = aolaiSubjectNo;
	}

	public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getLimitedPrice() {
		return limitedPrice;
	}

	public void setLimitedPrice(String limitedPrice) {
		this.limitedPrice = limitedPrice;
	}

	public String getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getPromotionNo() {
		return promotionNo;
	}

	public void setPromotionNo(String promotionNo) {
		this.promotionNo = promotionNo;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public String getPromotionContent() {
		return promotionContent;
	}

	public void setPromotionContent(String promotionContent) {
		this.promotionContent = promotionContent;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getPromotionDesc() {
		return promotionDesc;
	}

	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}

	public String getPromotionUrl() {
		return promotionUrl;
	}

	public void setPromotionUrl(String promotionUrl) {
		this.promotionUrl = promotionUrl;
	}

	public String getMultiCondition() {
		return multiCondition;
	}

	public void setMultiCondition(String multiCondition) {
		this.multiCondition = multiCondition;
	}

	public String getIsCartCoupon() {
		return isCartCoupon;
	}

	public void setIsCartCoupon(String isCartCoupon) {
		this.isCartCoupon = isCartCoupon;
	}

	public String getMobileImg() {
		return mobileImg;
	}

	public void setMobileImg(String mobileImg) {
		this.mobileImg = mobileImg;
	}

	public String getMobileSkuAttrText() {
		return mobileSkuAttrText;
	}

	public void setMobileSkuAttrText(String mobileSkuAttrText) {
		this.mobileSkuAttrText = mobileSkuAttrText;
	}

	public String getMobileSubjectEndTime() {
		return mobileSubjectEndTime;
	}

	public void setMobileSubjectEndTime(String mobileSubjectEndTime) {
		this.mobileSubjectEndTime = mobileSubjectEndTime;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getIsOutside() {
		return isOutside;
	}

	public void setIsOutside(String isOutside) {
		this.isOutside = isOutside;
	}

	public String getBelongMerchantNo() {
		return belongMerchantNo;
	}

	public void setBelongMerchantNo(String belongMerchantNo) {
		this.belongMerchantNo = belongMerchantNo;
	}

	public String getBelongMerchantName() {
		return belongMerchantName;
	}

	public void setBelongMerchantName(String belongMerchantName) {
		this.belongMerchantName = belongMerchantName;
	}

	public String getOriginCurrencyNo() {
		return originCurrencyNo;
	}

	public void setOriginCurrencyNo(String originCurrencyNo) {
		this.originCurrencyNo = originCurrencyNo;
	}

	public String getOriginCurrency() {
		return originCurrency;
	}

	public void setOriginCurrency(String originCurrency) {
		this.originCurrency = originCurrency;
	}

	public String getOriginCurrencyAmount() {
		return originCurrencyAmount;
	}

	public void setOriginCurrencyAmount(String originCurrencyAmount) {
		this.originCurrencyAmount = originCurrencyAmount;
	}

	public String getGrossWeightUnit() {
		return grossWeightUnit;
	}

	public void setGrossWeightUnit(String grossWeightUnit) {
		this.grossWeightUnit = grossWeightUnit;
	}

	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getCustoms() {
		return customs;
	}

	public void setCustoms(String customs) {
		this.customs = customs;
	}

	public String getComeToHandPrice() {
		return comeToHandPrice;
	}

	public void setComeToHandPrice(String comeToHandPrice) {
		this.comeToHandPrice = comeToHandPrice;
	}

	public String getPromoId() {
		return promoId;
	}

	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}

	public String getPriceTitle() {
		return priceTitle;
	}

	public void setPriceTitle(String priceTitle) {
		this.priceTitle = priceTitle;
	}

    public String getMerchant() {
        return Merchant;
    }

    public void setMerchant(String merchant) {
        Merchant = merchant;
    }

	public String getCountryPic() {
		return countryPic;
	}

	public void setCountryPic(String countryPic) {
		this.countryPic = countryPic;
	}
	

}
