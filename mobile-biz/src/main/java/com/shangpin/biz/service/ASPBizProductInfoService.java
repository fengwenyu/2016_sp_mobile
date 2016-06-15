package com.shangpin.biz.service;



/**
 * 商品详情
 * 
 * @author wangfeng
 *
 */
public interface ASPBizProductInfoService {
	/**
	 * 商品详情
	 * 
	 * @param activityId
	 * @param productId
	 * @param userId
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String queryProductInfo(String activityId, String productId,String userId,String picNo,String type,String version) throws Exception;

	/**
	 * 
	 * 礼品卡详情
	 * 
	 * @param userId用户ID
	 * @param productId商品ID
	 * @param type默认为0 0代表普通商品 1代表礼品卡-实物卡 2代表礼品卡-电子卡
	 * @return
	 * @author zghw
	 */
	public String queryProductInfoGiftCard(String userId, String productId, String type)throws Exception;
}
