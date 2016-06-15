package com.shangpin.base.service;

/**
 * @author qinyingchun
 *跑步馆功能相关接口
 */
public interface RunningService {
	
	/**
	 * 商品列表接口
	 * @param userId 用户ID
	 * @param categoryNo 品牌编号
	 * @param start 开始索引
	 * @param end 结束索引
	 * @return
	 */
	public String productList(String userId, String channelNo, String categoryNo, String start, String end);
	
	/**
	 * 跑步馆分类接口
	 * @param userId 用户ID
	 * @param categoryNo 上级品类编号
	 * @param channelNO 频道编号
	 * @param start 开始索引
	 * @param end 结束索引
	 * @return
	 */
	public String categoryList(String userId, String categoryNo,String channelNO, String start, String end);
	
	/**
	 * 跑步馆轮播图接口
	 * @param type 类型
	 * @param frames 所需帧数
	 * @return
	 */
	public String galleryList(String type, String frames);

	/**
	 * 跑步馆品牌热卖接口
	 * @param type  类型
	 * @param frames  帧数
	 * @return
	 */
	public String getRunBrandsHost(String type, String frames);
	
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
	public String categoryProductList(String start, String end, String channelCategoryNO, String categoryNO, String userType, String price, String productSize, String primaryColorId, String brandNO, String order, String postArea);

}
