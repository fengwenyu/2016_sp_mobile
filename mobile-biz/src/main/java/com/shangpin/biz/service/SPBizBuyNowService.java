package com.shangpin.biz.service;

import com.shangpin.biz.bo.BuyNow;

/**
 * 立即购买接口
 * 
 * @author qinyingchun
 */
public interface SPBizBuyNowService {

	public BuyNow buyNowObj(String userId, String skuId, String productId, String activityId, String amount, String region);
	public BuyNow buyNowObj(String userId, String skuId, String productId, String activityId, String amount, String region,String chanelNo,String chanelId);

}
