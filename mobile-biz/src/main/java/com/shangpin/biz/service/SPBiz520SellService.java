package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.Freebie;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.ProductDetail;

/**
 * 520买赠服务
 * Date: 2016年5月12日 <br/> 
 * @author fengwenyu
 * @since JDK7
 */
public interface SPBiz520SellService {
	
	/**
	 * 根据订单id确定是否有赠送品
	 * @param orderId 订单id
	 * @param userId 用户id，<br/>用于加密参数串（应该可以不要的）
	 * @return 订单中可分享商品的集合
	 */
	public Freebie getOrderFreeBie(String userId,String orderId);
	
	/**
	 * 获取用户在所有的订单中是否有符合买赠的商品，并给出分享连接
	 * <br/>用于在app的订单列表中展示分享按钮
	 * @param userId 用户id
	 * @return 用户订单中有符合买赠的商品时，返回买赠分享连接
	 */
	public Freebie getUserAllFreebies(String userId);
	
	/**
	 * 初始分享列表页数据
	 * @param userid
	 */
    public List<Product> initFreebieList(String userid);
    
    
    /**
     * 分享买赠详情页 根据当前spu获取可换购的商品
     * @param price
     * @return
     */
    public List<Product> recommendProduct(String spu);
	
	
	/**
	 * 获取用户下所有可参与活动的商品
	 * @param userId
	 * @return
	 */
	public List<Product> fromUserFreebieProducts(String userId);
	
	/**
	 * 获取加密后的url
	 * @param userId
	 * @param orderId
	 * @param spu
	 * @param sku
	 * @param sortNo
	 * @return
	 */
	public String getShareUrl(String userId,String orderId,String spu,String sku,String sortNo);
	
	
	/**
	 * 查询用户下订单下的 某个spu的购买数量
	 * @param userId 用户id
	 * @param OrderId 订单id
	 * @param spu spuid
	 * @return
	 */
	public String checkSpuNum(String userId,String OrderId,String spu);
	
	/**
	 * 判断某个spu是否是属于一个活动 
	 * @param spu
	 * @return
	 */
	public boolean checkSpuIsTopic(String spu);
	
	/**
	 * 检查订单状态。 true 为正常状态， false为不正常状态
	 * @param userId
	 * @param orderId
	 * @return
	 */
	public boolean getOrderStatus(String userId,String orderId);
	
	/**
	 * 获取商品详情
	 */
	public ProductDetail getProductDetail(String spu);
}
