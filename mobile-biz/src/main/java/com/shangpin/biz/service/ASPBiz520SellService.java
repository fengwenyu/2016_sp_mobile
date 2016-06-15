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
public interface ASPBiz520SellService {
	
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
	
}
