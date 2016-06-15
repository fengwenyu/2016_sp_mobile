package com.shangpin.biz.service;
import java.util.List;

import com.shangpin.biz.bo.Category;
import com.shangpin.biz.bo.CategoryProductList;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.RunBrandsHotList;
import com.shangpin.biz.bo.RunGalleryList;
import com.shangpin.biz.bo.RunVo;
/**
 * @author qinyingchun
 *跑步馆业务处理接口
 */
public interface SPBizRunningService {
	

	/**
	 * 跑步馆单品集合
	 * @param userId 用户ID
	 * @param channelNo 频道编号
	 * @param categoryNo 品类编号
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的条目数
	 * @return
	 */
	public List<Product> runProducts(String userId, String channelNo, String categoryNo, String pageIndex, String pageSize);
	
	/**
	 * 跑步馆品类列表
	 * @param userId
	 * @param categoryNo
	 * @param pageIndex 页码
	 * @param pageSize 每页显示的条目数
	 * @return
	 */
	public List<Category> runCategorys(String userId, String categoryNo,String channelNO, String pageIndex, String pageSize);
	/**
	 * 跑步馆品牌热卖方法
	 * @param type
	 * @param frames
	 * @return
	 */
	public RunBrandsHotList getRunBrandsHost(String type,String frames);
	
    
    /**
     * 跑步馆轮播图接口
     * @param type  类型
     * @param frames    所需帧数
     * @return
     */
    public RunGalleryList getRunGalleryList(String type, String frames);
    
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
	 * 客户端频道分类商品列表接口(M站也可以调用)
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
