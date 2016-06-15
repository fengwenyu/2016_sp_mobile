package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @ClassName: CartItem
 * @Description: 购物车商品条目信息
 * @author qinyingchun
 * @date 2014年11月6日
 * @version 1.0
 */
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ShoppingCartDetailId;
	private String ProductNo;
	private String SkuNo;
	private String CategoryNo;
	private String Quantity;
	private String DateAdd;
	private String Price;
	private String TotalAmount;
	private String TotalQuantity;
	private String CartType;
	private String MsgType;
	private String Msg;
	private String SubjectDateEnd;
	private String InventoryQuantity;
	private String ProductName;
	private String BrandEnName;
	private String BrandCnName;
	private String IsSupportDiscount;
	private String ProductPicFile;
	private String ProductCategoryNo;
	private String ProductUrl;
	private String TopicSubjectFlag;
	private String FavoritePrice;
	private String IsUserTicket;
	private String IsSupportCod;
	private String IsPromotion;
	private String CardType;
	private String BuyType;
	private String DynamicAttributeText;
	private String ColorSize;
	private String IsLimitedOutlet;
	private String skuFrom;
	private String AolaiSubjectNo;
	private String BrandType;
	private String BrandNo;
	private String SubjectNo;
	private String LimitedPrice;
	private String PromotionPrice;
	private String PromotionNo;
	private String PromotionType;
	private String PromotionContent;
	private String PromotionName;
	private String PromotionDesc;
	private String PromotionUrl;
	private String multiCondition;
	private String IsCartCoupon;
	private String MobileImg;
	private String MobileSkuAttrText;
	private String MobileSubjectEndTime;
	private String Stock;
	private String IsOutside;
	private String BelongMerchantNo;
	private String BelongMerchantName;
	private String OriginCurrencyNo;
	private String OriginCurrency;
	private String OriginCurrencyAmount;
	private String GrossWeightUnit;
	private String GrossWeight;
	private String TaxRate;
	private String Freight;
	private String Customs;
	private String ComeToHandPrice;
	private String PromoId;
	private String countryPic;
	

	public String getCountryPic() {
		return countryPic;
	}

	public void setCountryPic(String countryPic) {
		this.countryPic = countryPic;
	}

	public String getComeToHandPrice() {
		return ComeToHandPrice;
	}

	public void setComeToHandPrice(String comeToHandPrice) {
		ComeToHandPrice = comeToHandPrice;
	}

	public String getPromoId() {
		return PromoId;
	}

	public void setPromoId(String promoId) {
		PromoId = promoId;
	}

	public String getShoppingCartDetailId() {
		return ShoppingCartDetailId;
	}

	public void setShoppingCartDetailId(String shoppingCartDetailId) {
		ShoppingCartDetailId = shoppingCartDetailId;
	}

	public String getProductNo() {
		return ProductNo;
	}

	public void setProductNo(String productNo) {
		ProductNo = productNo;
	}

	public String getSkuNo() {
		return SkuNo;
	}

	public void setSkuNo(String skuNo) {
		SkuNo = skuNo;
	}

	public String getCategoryNo() {
		return CategoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		CategoryNo = categoryNo;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getDateAdd() {
		return DateAdd;
	}

	public void setDateAdd(String dateAdd) {
		DateAdd = dateAdd;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
	}

	public String getTotalQuantity() {
		return TotalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		TotalQuantity = totalQuantity;
	}

	public String getCartType() {
		return CartType;
	}

	public void setCartType(String cartType) {
		CartType = cartType;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public String getSubjectDateEnd() {
		return SubjectDateEnd;
	}

	public void setSubjectDateEnd(String subjectDateEnd) {
		SubjectDateEnd = subjectDateEnd;
	}

	public String getInventoryQuantity() {
		return InventoryQuantity;
	}

	public void setInventoryQuantity(String inventoryQuantity) {
		InventoryQuantity = inventoryQuantity;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getBrandEnName() {
		return BrandEnName;
	}

	public void setBrandEnName(String brandEnName) {
		BrandEnName = brandEnName;
	}

	public String getBrandCnName() {
		return BrandCnName;
	}

	public void setBrandCnName(String brandCnName) {
		BrandCnName = brandCnName;
	}

	public String getIsSupportDiscount() {
		return IsSupportDiscount;
	}

	public void setIsSupportDiscount(String isSupportDiscount) {
		IsSupportDiscount = isSupportDiscount;
	}

	public String getProductPicFile() {
		return ProductPicFile;
	}

	public void setProductPicFile(String productPicFile) {
		ProductPicFile = productPicFile;
	}

	public String getProductCategoryNo() {
		return ProductCategoryNo;
	}

	public void setProductCategoryNo(String productCategoryNo) {
		ProductCategoryNo = productCategoryNo;
	}

	public String getProductUrl() {
		return ProductUrl;
	}

	public void setProductUrl(String productUrl) {
		ProductUrl = productUrl;
	}

	public String getTopicSubjectFlag() {
		return TopicSubjectFlag;
	}

	public void setTopicSubjectFlag(String topicSubjectFlag) {
		TopicSubjectFlag = topicSubjectFlag;
	}

	public String getFavoritePrice() {
		return FavoritePrice;
	}

	public void setFavoritePrice(String favoritePrice) {
		FavoritePrice = favoritePrice;
	}

	public String getIsUserTicket() {
		return IsUserTicket;
	}

	public void setIsUserTicket(String isUserTicket) {
		IsUserTicket = isUserTicket;
	}

	public String getIsSupportCod() {
		return IsSupportCod;
	}

	public void setIsSupportCod(String isSupportCod) {
		IsSupportCod = isSupportCod;
	}

	public String getIsPromotion() {
		return IsPromotion;
	}

	public void setIsPromotion(String isPromotion) {
		IsPromotion = isPromotion;
	}

	public String getCardType() {
		return CardType;
	}

	public void setCardType(String cardType) {
		CardType = cardType;
	}

	public String getBuyType() {
		return BuyType;
	}

	public void setBuyType(String buyType) {
		BuyType = buyType;
	}

	public String getDynamicAttributeText() {
		return DynamicAttributeText;
	}

	public void setDynamicAttributeText(String dynamicAttributeText) {
		DynamicAttributeText = dynamicAttributeText;
	}

	public String getColorSize() {
		return ColorSize;
	}

	public void setColorSize(String colorSize) {
		ColorSize = colorSize;
	}

	public String getIsLimitedOutlet() {
		return IsLimitedOutlet;
	}

	public void setIsLimitedOutlet(String isLimitedOutlet) {
		IsLimitedOutlet = isLimitedOutlet;
	}

	public String getSkuFrom() {
		return skuFrom;
	}

	public void setSkuFrom(String skuFrom) {
		this.skuFrom = skuFrom;
	}

	public String getAolaiSubjectNo() {
		return AolaiSubjectNo;
	}

	public void setAolaiSubjectNo(String aolaiSubjectNo) {
		AolaiSubjectNo = aolaiSubjectNo;
	}

	public String getBrandType() {
		return BrandType;
	}

	public void setBrandType(String brandType) {
		BrandType = brandType;
	}

	public String getBrandNo() {
		return BrandNo;
	}

	public void setBrandNo(String brandNo) {
		BrandNo = brandNo;
	}

	public String getSubjectNo() {
		return SubjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		SubjectNo = subjectNo;
	}

	public String getLimitedPrice() {
		return LimitedPrice;
	}

	public void setLimitedPrice(String limitedPrice) {
		LimitedPrice = limitedPrice;
	}

	public String getPromotionPrice() {
		return PromotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		PromotionPrice = promotionPrice;
	}

	public String getPromotionNo() {
		return PromotionNo;
	}

	public void setPromotionNo(String promotionNo) {
		PromotionNo = promotionNo;
	}

	public String getPromotionType() {
		return PromotionType;
	}

	public void setPromotionType(String promotionType) {
		PromotionType = promotionType;
	}

	public String getPromotionContent() {
		return PromotionContent;
	}

	public void setPromotionContent(String promotionContent) {
		PromotionContent = promotionContent;
	}

	public String getPromotionName() {
		return PromotionName;
	}

	public void setPromotionName(String promotionName) {
		PromotionName = promotionName;
	}

	public String getPromotionDesc() {
		return PromotionDesc;
	}

	public void setPromotionDesc(String promotionDesc) {
		PromotionDesc = promotionDesc;
	}

	public String getPromotionUrl() {
		return PromotionUrl;
	}

	public void setPromotionUrl(String promotionUrl) {
		PromotionUrl = promotionUrl;
	}

	public String getMultiCondition() {
		return multiCondition;
	}

	public void setMultiCondition(String multiCondition) {
		this.multiCondition = multiCondition;
	}

	public String getIsCartCoupon() {
		return IsCartCoupon;
	}

	public void setIsCartCoupon(String isCartCoupon) {
		IsCartCoupon = isCartCoupon;
	}

	public String getMobileImg() {
		return MobileImg;
	}

	public void setMobileImg(String mobileImg) {
		MobileImg = mobileImg;
	}

	public String getMobileSkuAttrText() {
		return MobileSkuAttrText;
	}

	public void setMobileSkuAttrText(String mobileSkuAttrText) {
		MobileSkuAttrText = mobileSkuAttrText;
	}

	public String getMobileSubjectEndTime() {
		return MobileSubjectEndTime;
	}

	public void setMobileSubjectEndTime(String mobileSubjectEndTime) {
		MobileSubjectEndTime = mobileSubjectEndTime;
	}

	public String getStock() {
		return Stock;
	}

	public void setStock(String stock) {
		Stock = stock;
	}

	public String getIsOutside() {
		return IsOutside;
	}

	public void setIsOutside(String isOutside) {
		IsOutside = isOutside;
	}

	public String getBelongMerchantNo() {
		return BelongMerchantNo;
	}

	public void setBelongMerchantNo(String belongMerchantNo) {
		BelongMerchantNo = belongMerchantNo;
	}

	public String getBelongMerchantName() {
		return BelongMerchantName;
	}

	public void setBelongMerchantName(String belongMerchantName) {
		BelongMerchantName = belongMerchantName;
	}

	public String getOriginCurrencyNo() {
		return OriginCurrencyNo;
	}

	public void setOriginCurrencyNo(String originCurrencyNo) {
		OriginCurrencyNo = originCurrencyNo;
	}

	public String getOriginCurrency() {
		return OriginCurrency;
	}

	public void setOriginCurrency(String originCurrency) {
		OriginCurrency = originCurrency;
	}

	public String getOriginCurrencyAmount() {
		return OriginCurrencyAmount;
	}

	public void setOriginCurrencyAmount(String originCurrencyAmount) {
		OriginCurrencyAmount = originCurrencyAmount;
	}

	public String getGrossWeightUnit() {
		return GrossWeightUnit;
	}

	public void setGrossWeightUnit(String grossWeightUnit) {
		GrossWeightUnit = grossWeightUnit;
	}

	public String getGrossWeight() {
		return GrossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		GrossWeight = grossWeight;
	}

	public String getTaxRate() {
		return TaxRate;
	}

	public void setTaxRate(String taxRate) {
		TaxRate = taxRate;
	}

	public String getFreight() {
		return Freight;
	}

	public void setFreight(String freight) {
		Freight = freight;
	}

	public String getCustoms() {
		return Customs;
	}

	public void setCustoms(String customs) {
		Customs = customs;
	}

}
