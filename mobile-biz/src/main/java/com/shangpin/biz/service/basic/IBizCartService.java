package com.shangpin.biz.service.basic;

import com.shangpin.biz.bo.CartContent;
import com.shangpin.biz.bo.CartQuantity;
import com.shangpin.biz.bo.RecProduct;
import com.shangpin.biz.bo.Settlement;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;

public interface IBizCartService {

	/**
	 * 添加商品到购物车
	 * 
	 * @param userId
	 *            用户ID
	 * @param productNo
	 *            商品ID
	 * @param quantity
	 *            单品购买数量
	 * @param skuNo
	 *            商品sku
	 * @param categoryNo
	 *            活动/专题编号
	 * @param dynamicattributetext
	 * @param topicSubjectFlag
	 * @param skuFrom
	 * @param vipNo
	 * @param siteNo
	 * @return
	 * @author zghw
	 */
	public String fromAddToCart(String userId, String productNo, String quantity, String sku, String categoryNo,
			String dynamicattributetext, String topicSubjectFlag, String skuFrom, String vipNo, String siteNo);

	/**
	 * 
	 * @param userId
	 *            用户ID
	 * @param productNo
	 *            商品ID
	 * @param quantity
	 *            单品购买数量
	 * @param skuNo
	 *            商品sku
	 * @param categoryNo
	 *            活动/专题编号
	 * @param dynamicattributetext
	 * @param topicSubjectFlag
	 * @param skuFrom
	 * @param vipNo
	 * @param siteNo
	 * @return
	 * @author zghw
	 */
	public ResultBase beAddToCart(String userId, String productNo, String quantity, String sku, String categoryNo,String dynamicattributetext, String topicSubjectFlag, String skuFrom, String vipNo, String siteNo);
	
	public ResultBase beAddToCart(String userId, String productNo, String quantity, String sku, String categoryNo,String dynamicattributetext, String topicSubjectFlag, String skuFrom, String vipNo, String siteNo,String chanelNo,String chanelId);

	/**
	 * 购物车列表
	 * 
	 * @param userId
	 *            用户标识
	 * @param isPromotion
	 *            是否参加购物车促销 1:不参加；0:为参加
	 * @param width
	 *            购物车商品图片宽度
	 * @param height
	 *            购物车商品图片高度
	 *
	 * @author zghw
	 */
	public String fromCartList(String userId, String isPromotion, String width, String height, String postArea);

	/**
	 * 购物车列表
	 * 
	 * @param userId
	 *            用户标识
	 * @param isPromotion
	 *            是否参加购物车促销 1:不参加；0:为参加
	 * @param width
	 *            购物车商品图片宽度
	 * @param height
	 *            购物车商品图片高度
	 *
	 * @author zghw
	 */
	public ResultObjOne<CartContent> beCartList(String userId, String isPromotion, String width, String height, String postArea);

	/**
	 * 删除购物车商品
	 * 
	 * @param userId
	 *            用户ID
	 * @param shopId
	 *            购物车详情Id
	 * @return
	 *
	 * @author zghw
	 */
	public String fromDelCart(String userId, String shopId);

	/**
	 * 删除购物车商品
	 * 
	 * @param userId
	 *            用户ID
	 * @param shopId
	 *            购物车详情Id
	 * @return
	 *
	 * @author zghw
	 */
	public ResultBase beDelCart(String userId, String shopId);

	/**
	 * 修改购物车产品数量
	 * 
	 * @param userId
	 *            用户ID
	 * @param shopId
	 *            购物车详情ID
	 * @param count
	 *            数量
	 * @return
	 *
	 * @author zghw
	 */
	public String fromUpdateCart(String userId, String shopId, String count);

	/**
	 * 修改购物车产品数量
	 * 
	 * @param userId
	 *            用户ID
	 * @param shopId
	 *            购物车详情ID
	 * @param count
	 *            数量
	 * @return
	 *
	 * @author zghw
	 */
	public ResultObjOne<CartQuantity> beUpdateCart(String userId, String shopId, String count);

	/**
	 * 提交购物车选中的商品
	 * 
	 * @param userId
	 *            用户ID
	 * @param shopId
	 *            选中的商品
	 * @param picW
	 *            图片宽度
	 * @param picH
	 *            图片高度
	 * @return
	 *
	 * @author zghw
	 */
	public String fromSubmitCart(String userId, String shopId, String picW, String picH);

	/**
	 * 提交购物车选中的商品
	 * 
	 * @param userId
	 *            用户ID
	 * @param shopId
	 *            选中的商品
	 * @param picW
	 *            图片宽度
	 * @param picH
	 *            图片高度
	 * @return
	 *
	 * @author zghw
	 */
	public ResultObjOne<CartContent> beSubmitCart(String userId, String shopId, String picW, String picH);

	/**
	 * 获取结算生成订单的信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param isPromotion
	 *            是否促销
	 * @param picW
	 *            图片宽度
	 * @param picH
	 *            图片高度
	 * @return
	 *
	 * @author zghw
	 */
	public String fromGetSettlement(String userId, String isPromotion, String picW, String picH, String postArea);

	/**
	 * 获取结算生成订单的信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param isPromotion
	 *            是否促销
	 * @param picW
	 *            图片宽度
	 * @param picH
	 *            图片高度
	 * @return
	 *
	 * @author zghw
	 */
	public ResultObjOne<Settlement> beGetSettlement(String userId, String isPromotion, String picW, String picH, String postArea);

	/**
	 * 页面无内容时获得推荐
	 * 
	 * @param userId
	 *            用户id
	 * @param type
	 *            1:用于收藏页面
	 * @param shopType
	 *            1:尚品;2:奥莱
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author zghw
	 */
	public String fromRecProduct(String userId, String type, String shopType, String pageIndex, String pageSize);

	/**
	 * 页面无内容时获得推荐
	 * 
	 * @param userId
	 *            用户id
	 * @param type
	 *            1:用于收藏页面
	 * @param shopType
	 *            1:尚品;2:奥莱
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author zghw
	 */
	public ResultObjMapList<RecProduct> beRecProduct(String userId, String type, String shopType, String pageIndex,
			String pageSize);
}
