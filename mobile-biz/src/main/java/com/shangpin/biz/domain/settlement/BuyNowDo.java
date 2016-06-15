package com.shangpin.biz.domain.settlement;

import java.io.Serializable;

/**
 * <br/>
 * Date: 2016/4/22<br/>
 *
 * @author ZRS
 * @since JDK7
 */
public class BuyNowDo implements Serializable {


    public static final String  DEFAULTUSECOUPON = "1";
    public static final String DEFAULTNOTUSECOUPON = "0";
    private String userId;//  用户IdString;//  必须
    private String skuId;// 	skuid	String	必须
    private String productId;// 	商品id	String	必须
    private String activityId;// 	活动id	String	必须
    private String amount;// 	购买商品数量	String	必须
//    private String region;// 	商品地域标识	String	必须  1：国内商品 2：海外商品
    private String isDefaultUseCoupon;// 	是否默认使用优惠券	string	 	1：使用，0：不使用。默认为0

    private String channelId;//推广参数
    private String channelNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getIsDefaultUseCoupon() {
        return isDefaultUseCoupon;
    }

    public void setIsDefaultUseCoupon(String isDefaultUseCoupon) {
        this.isDefaultUseCoupon = isDefaultUseCoupon;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }
}
