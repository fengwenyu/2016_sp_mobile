package com.shangpin.mobileShangpin.platform.service;

import java.util.List;
import java.util.Map;

import com.shangpin.mobileShangpin.platform.vo.MerchandiseVO;

/**
 * 购物袋业务逻辑接口
 * 
 * @author yumeng
 * @date:2012-11-5
 */
public interface CartService {
	/**
	 * 根据参数，添加购物袋
	 * 
	 * @param map
	 *            参数集合
	 * 
	 * 
	 * @return 返回操作结果
	 */
	public String addcart(Map<String, String> map) throws Exception;

	/**
	 * 根据用户ID，获取用户购物袋中商品列表
	 * 
	 * @param userID
	 *            用户ID
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * 
	 * @return 返回购物袋中商品列表
	 */
	public List<MerchandiseVO> getCartOfMerchandiseList(String userid, String shoptype);

	/**
	 * 根据用户ID、商品skuID，删除购物袋中的商品
	 * 
	 * @param userid
	 *            用户ID
	 * @param skuid
	 *            商品skuID
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * 
	 * @return 返回操作是否成功
	 */
	public String removeCartOfMerchandise(String userid,String skuid, String gid, String groupno, String shoppingcartdetailid, String count,String shoptype,String flag);
}
