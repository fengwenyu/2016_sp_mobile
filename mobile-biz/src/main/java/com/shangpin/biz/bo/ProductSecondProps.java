package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;


/**
 * @ClassName: ProductFirstProps
 * @Description:商品详情sku属性实体类（支持sku不同价格体系）
 * @author wangfeng
 * @date 2015年3月11日
 * @version 1.0
 */
public class ProductSecondProps implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4106633560680239069L;

  
    
    private String sku;//sku编号
    private String secondProp;//第二属性值
    private String isSupportDiscount;//是否支持会员权益 1支持，0不支持
    private String diamondPrice;//钻石会员价
    private String platinumPrice;//白金价
    private String goldPrice;//黄金价
    private String promotionPrice;// 促销价
    private String limitedPrice;//普通会员价
    private String marketPrice;//市场价
    private String isExchange;//是否支持退换换1支持，0不支持
    private String isPromotion;//是否促销 0 不1 促销
    private String count;//库存
    private List<PriceTag> priceTag;//针对海外商品添加的有关价格的信息
    private String shopPrice;
    private List<Property> sizeAbout;//尺码参考
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getSecondProp() {
        return secondProp;
    }
    public void setSecondProp(String secondProp) {
        this.secondProp = secondProp;
    }
    public String getIsSupportDiscount() {
        return isSupportDiscount;
    }
    public void setIsSupportDiscount(String isSupportDiscount) {
        this.isSupportDiscount = isSupportDiscount;
    }
    public String getDiamondPrice() {
        return diamondPrice;
    }
    public void setDiamondPrice(String diamondPrice) {
        this.diamondPrice = diamondPrice;
    }
    public String getPlatinumPrice() {
        return platinumPrice;
    }
    public void setPlatinumPrice(String platinumPrice) {
        this.platinumPrice = platinumPrice;
    }
    public String getGoldPrice() {
        return goldPrice;
    }
    public void setGoldPrice(String goldPrice) {
        this.goldPrice = goldPrice;
    }
    public String getPromotionPrice() {
        return promotionPrice;
    }
    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
    public String getLimitedPrice() {
        return limitedPrice;
    }
    public void setLimitedPrice(String limitedPrice) {
        this.limitedPrice = limitedPrice;
    }
    public String getMarketPrice() {
        return marketPrice;
    }
    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }
    public String getIsExchange() {
        return isExchange;
    }
    public void setIsExchange(String isExchange) {
        this.isExchange = isExchange;
    }
    public String getIsPromotion() {
        return isPromotion;
    }
    public void setIsPromotion(String isPromotion) {
        this.isPromotion = isPromotion;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
	public List<PriceTag> getPriceTag() {
		return priceTag;
	}
	public void setPriceTag(List<PriceTag> priceTag) {
		this.priceTag = priceTag;
	}
	public String getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(String shopPrice) {
		this.shopPrice = shopPrice;
	}
    public List<Property> getSizeAbout() {
        return sizeAbout;
    }
    public void setSizeAbout(List<Property> sizeAbout) {
        this.sizeAbout = sizeAbout;
    }


}
