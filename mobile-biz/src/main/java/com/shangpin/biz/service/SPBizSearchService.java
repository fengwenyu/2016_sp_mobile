package com.shangpin.biz.service;


import com.shangpin.biz.bo.SearchProductResult;
import com.shangpin.biz.bo.SearchResult;
import com.shangpin.biz.bo.SearchType;

public interface SPBizSearchService {
	/**
	 * 
	 * @Title: searchProductList
	 * @Description:商品列表搜索
	 * @param
	 * @return SearchBrandResult
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月23日
	 */
	SearchProductResult searchProductList(String keyword,String navId, String start, String end,String tagId, String brandNo, String price, String color, String size, String categoryNo, String order, String userLv,SearchType searchType, String postArea);
	/**
	 * 
	 * @Title: newProductList
	 * @Description:新品商品列表搜索
	 * @param
	 * @return SearchBrandResult
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月23日
	 */
	SearchProductResult newProductList(String keyword,String navId, String start, String end, String brandNo, String price, String color, String size, String categoryNo, String order, String gender, String userLv, SearchType searchType, String postArea);
	/**
	 * @Title: queryLabels
	 * @Description:搜索标签列表
	 * @return SearchResultApp
	 * @Create By liling
	 * @Create Date 2015年10月29日
	 */
	//Map<String,Object> queryLabels(String pageIndex, String pageSize, String userLv, String tagId, String order, String filters, String type) throws Exception;
	/**
	 * @Title: queryLabels
	 * @Description:搜索标签列表
	 * @return SearchResultApp
	 * @Create By liling
	 * @Create Date 2015年10月29日
	 */
	SearchResult queryLabels(String pageIndex, String pageSize, String userLv, String tagId, String order, String filters, String type) throws Exception;
}
