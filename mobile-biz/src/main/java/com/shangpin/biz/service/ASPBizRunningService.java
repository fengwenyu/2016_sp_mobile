package com.shangpin.biz.service;

import com.shangpin.biz.bo.CategoryProductList;
import com.shangpin.biz.bo.RunVo;

public interface ASPBizRunningService {
	
public String categoryProductList(String start, String end, String channelCategoryNO, String categoryNO, String userType, String price, String productSize, String primaryColorId, String brandNO, String order, String postArea);
	
	/**
	 * 跑步馆频道分类商品列表
	 * @param start 开始索引
	 * @param end 结束索引
	 * @param channelCategoryNO 频道分类编号
	 * @param categoryNO  商城分类编号
	 * @param userType 用户类型gold、platinum、diamond，普通用户不填
	 * @param price 价格区间
	 * @param productSize 商品尺码
	 * @param primaryColorId 颜色ID
	 * @param brandNO 品牌编号
	 * @param order 排序
	 * @param postArea 发货地参数  1:大陆；2：海外；
	 * @return
	 */
	public CategoryProductList CategoryProductListObj(String start, String end, String channelCategoryNO, String categoryNO, String userType, String price, String productSize, String primaryColorId, String brandNO, String order, String postArea);
	
	/**
	 * 客户端频道分类商品列表接口
	 * @param pageIndex
	 * @param pageSize
	 * @param userLv
	 * @param price
	 * @param size
	 * @param color
	 * @param channelCategoryNo
	 * @param categoryId
	 * @param brandId
	 * @param order
	 * @param postArea
	 * @return
	 */
	public RunVo categoryProductListVo(String pageIndex, String pageSize, String userLv, String price, String size, String color, String channelCategoryNo, String categoryId, String brandId, String order, String postArea);

}
