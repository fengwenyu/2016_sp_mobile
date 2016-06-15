package com.shangpin.biz.service;

import com.shangpin.biz.bo.BrandShop;
import com.shangpin.biz.bo.SearchResult;

/**
 * 品牌店首页
 * 
 * @author wangfeng
 *
 */
public interface ASPBizBrandShopService {

	public String queryShop(String userId, String brandId, String pageIndex, String pageSize, String userLv, String price, String size, String colorId, String tagId, String categoryId, String order, String postArea, String imei) throws Exception;

	BrandShop queryBrandShop(String userId, String brandId, String pageIndex, String pageSize, String userLv, String price, String size, String colorId, String tagId, String categoryId, String order, String postArea, String imei) throws Exception;

	SearchResult queryBrandShopProduct(String brandId, String pageIndex, String pageSize, String userLv, String price, String size, String colorId, String tagId, String categoryId, String order, String postArea, String imei) throws Exception;

	public String queryShopProduct(String userId, String pageIndex, String pageSize, String userLv, String tagId, String order, String filters, boolean isPromotion, String imei) throws Exception;
	
	String queryShopProduct(String userId, String pageIndex, String pageSize, String userLv, String tagId, String order, String filters, boolean isPromotion, String imei, String originalFilters, String dynamicFilters, String version) throws Exception;

	public String queryProductList(String userId, String pageIndex, String pageSize, String userLv, String tagId, String order, String filters, String imei) throws Exception;
	
	String queryProductList(String userId, String pageIndex, String pageSize, String userLv, String tagId, String order, String filters, String originalFilters, String dynamicFilters, String imei, String version) throws Exception;

}
