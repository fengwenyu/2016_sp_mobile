package com.shangpin.biz.service;

import com.shangpin.biz.bo.ProductDetail;

/**
 * 商品详情
 * 
 * @author wangfeng
 *
 */
public interface ASPBizProductDetailService {
	/**
	 * 商品详情
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ProductDetail queryProductDetail(String activityId, String productId, String userId, String picNo, boolean isNew);

	ProductDetail queryProductDetail(String activityId, String productId, String userId, String picNo, String isNew) throws Exception;
}
