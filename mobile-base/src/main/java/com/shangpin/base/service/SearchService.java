package com.shangpin.base.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.DocumentException;

import com.shangpin.base.vo.SearchResult;

/**
 * 搜索相关接口
 * 
 * @author xupengcheng
 * 
 */
public interface SearchService {

	/**
	 * 品牌下的商品列表
	 * 
	 * @param pageSize
	 * @param pageIndex
	 * @param brandId
	 * @param price
	 *            价格区间
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param userLv
	 *            用户级别
	 * @return
	 * @throws Exception
	 */
	public SearchResult searchBrandProductList(String navId, String pageIndex, String pageSize, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String userLv, String postArea) throws Exception;

	/**
	 * 品牌下的商品列表
	 * 
	 * @param pageSize
	 * @param pageIndex
	 * @param brandId
	 * @param price
	 *            价格区间
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param userLv
	 *            用户级别
	 * @return
	 * @throws Exception
	 */
	public String searchBrandShopProductList(String navId, String pageIndex, String pageSize, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String userLv, String postArea, String imei);

	/**
	 * 品类下的商品列表
	 * 
	 * @param start
	 * @param end
	 * @param brandId
	 * @param price
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param order
	 * @param userLv
	 *            用户级别
	 * @return
	 * @throws Exception
	 */
	public SearchResult searchCategoryProductList(String pageIndex, String pageSize, String tagId, String brandId, String price, String colorId, String size, String categoyId,
			String order, String userLv, String postArea) throws Exception;

	/**
	 * 品类下的商品列表
	 * 
	 * @param start
	 * @param end
	 * @param brandId
	 * @param price
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param order
	 * @param userLv
	 *            用户级别
	 * @return
	 * @throws Exception
	 */
	public SearchResult searchCategoryProductList(String pageIndex, String pageSize, String tagId, String brandId, String price, String colorId, String size, String categoyId,
			String order, String userLv, String gender, String postArea) throws Exception;

	/**
	 * 关键字搜索商品
	 * 
	 * @param keyWord
	 * @param pageIndex
	 * @param pageSize
	 * @param brandId
	 * @param price
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param order
	 * @param userLv
	 *            用户级别
	 * @return
	 * @throws Exception
	 */
	public SearchResult searchKeyWordProductList(String keyWord, String pageIndex, String pageSize, String tagId, String brandId, String price, String colorId, String size,
			String categoyId, String order, String userLv, String postArea) throws Exception;

	/**
	 * 分类查询接口，通过主站数据库获取数据，这个获取的可能品类下没有商品
	 * 
	 * @param parentId
	 * @param deep
	 * @return
	 */
	public String queryCategory(String parentId, String deep) throws Exception;

	/**
	 * 分类查询接口, 0的时候默认查询主站头部分类，类似一级分类，通过搜索接口获取数据，这个获取的品类都是有商品的
	 * 
	 * @param parentId
	 * @param type
	 *            =1，运营分类
	 * @return
	 */
	public String querySearchCategory(String parentId, String type) throws Exception;

	/**
	 * 2.9.0版本以后分类查询接口, 0的时候默认查询主站头部分类，类似一级分类，通过搜索接口获取数据，这个获取的品类都是有商品的
	 * 
	 * @param parentId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	String queryCategorys(String parentId, String type) throws Exception;

	/**
	 * 分类查询接口（因图片尺寸兼容问题添加标识mark，区分版本。）
	 * 
	 * @param parentId
	 * @param type
	 * @param mark
	 * @return
	 * @throws Exception
	 */
	public String querySearchCategory(String parentId, String type, String mark) throws Exception;

	/**
	 * 品类下的列表
	 * 
	 * @param start
	 * @param end
	 * @param brandId
	 * @param price
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param order
	 * @param userLv
	 *            用户级别
	 * @return
	 * @throws Exception
	 */
	public SearchResult searchCategoryIndexList(String start, String end, String brandId, String price, String colorId, String size, String categoyId, String order, String userLv,
			String gender, String postArea) throws Exception;

	/**
	 * 品类下的列表
	 * 
	 * @param start
	 * @param end
	 * @param brandId
	 * @param price
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param order
	 * @param userLv
	 *            用户级别
	 * @return
	 * @throws Exception
	 */
	public String searchCategoryProduct(String start, String end, String brandId, String price, String colorId, String size, String categoyId, String order, String userLv,
			String gender, String postArea, String attrId, String imei);

	/**
	 * 首页、品类标签列表
	 * 
	 * @param type
	 *            调用位置 1:首页;2:品类
	 * @return
	 */
	String searchCategoryOperations(String pageIndex, String pageSize, String userLv, String price, String size, String color, String tagId, String categoryId, String postArea,
			String brandId, String order, String type, String imei);

	/**
	 * 品牌下的列表
	 * 
	 * @param start
	 * @param end
	 * @param brandId
	 * @param price
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param order
	 * @param userLv
	 *            用户级别
	 * @return
	 */
	public String searchBrandProduct(String navId, String pageIndex, String pageSize, String brandId, String price, String colorId, String size, String categoyId, String order,
			String userLv, String postArea);

	/**
	 * 关键字搜索商品
	 * 
	 * @param start
	 * @param end
	 * @param brandId
	 * @param price
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param order
	 * @param userLv
	 *            用户级别
	 * @return
	 */
	public String searchProductListByKeyWord(String keyWord, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
			String order, String userLv, String postArea);

	/**
	 * 新品搜索
	 * 
	 * @param start
	 * @param end
	 * @param brandId
	 * @param price
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param order
	 * @param order
	 * @param userLv
	 *            用户级别
	 * @return
	 */
	public String newProductList(String keyWord, String navId, String start, String end, String brandId, String price, String colorId, String size, String categoyId, String order,
			String gender, String userLv, String postArea);

	/**
	 * 新品搜索
	 * 
	 * @param start
	 * @param end
	 * @param brandId
	 * @param price
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param order
	 * @param order
	 * @param userLv
	 *            用户级别
	 * @return
	 * @throws DocumentException
	 * @throws UnsupportedEncodingException
	 */
	public String searchActivityProductList(String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
			String order, String userLv, String postArea, String isPre, String imei) throws UnsupportedEncodingException, DocumentException;

	/**
	 * 去除售罄的商品列表页
	 * @param subjectNo
	 * @param start
	 * @param end
	 * @param tagId
	 * @param brandId
	 * @param price
	 * @param colorId
	 * @param size
	 * @param categoyId
	 * @param order
	 * @param userLv
	 * @param postArea
	 * @param isPre
	 * @param imei
	 *
	 * @return
     * @throws UnsupportedEncodingException
     * @throws DocumentException
     */
	public String searchActivityProductListNosellOut(String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
											String order, String userLv, String postArea, String isPre, String imei) throws UnsupportedEncodingException, DocumentException;
	/**
	 * 用于搜索关键词智能联想
	 * 
	 * @param keyword
	 * @param userID
	 * @param userIP
	 * @param encode
	 * @return
	 */
	public String searchSuggestion(String keyword, String userID, String userIP, String encode);

	/**
	 * 标签商品搜索接口
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param userLv
	 * @param price
	 * @param size
	 * @param color
	 * @param tagId
	 * @param categoryId
	 * @param postArea
	 * @param brandId
	 * @param order
	 * @return
	 */
	String searchTagProductList(String pageIndex, String pageSize, String userLv, String price, String size, String color, String tagId, String categoryId, String postArea,
			String brandId, String order);
	
	/**
	 * 根据商品spu批量查询商品
	 * @param spus
	 * @return
	 */
	public String searchProductList(List<String> spus);
	
	/**
	 * 查询某个spu是否是某个活动中的
	 * @param topic
	 * @param spu
	 * @return
	 */
	public String searchSpuIsInTopic(String topic, String spu);

}
