package com.shangpin.biz.service;


/**
 * 立即购买接口
 * 
 * @author qinyingchun
 */
public interface ASPBizBuyNowService {

	/**
	 * 返回给客户端的立即购买
	 * 
	 * @return
	 */
	String queryBuyNow(String userId, String skuId, String productId, String activityId, String amount, String region, String version) throws Exception;

}
