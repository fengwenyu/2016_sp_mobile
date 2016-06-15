package com.shangpin.mobileAolai.platform.service;

import java.util.List;
import java.util.Map;

import com.shangpin.mobileAolai.platform.vo.MerchandiseVO;

/**
 * 购物袋业务逻辑接口
 * 
 * @author yumeng
 * @date:2012-11-5
 */
public interface CartService {

	/**
	 * 根据用户ID，获取用户购物袋中商品列表
	 * 
	 * @param userID
	 * 
	 * @return 返回购物袋中商品列表
	 */
	public List<MerchandiseVO> getCartOfMerchandiseList(String userid);

	/**
	 * 根据用户ID、商品skuID，删除购物袋中的商品
	 * 
	 * @param userid
	 *            用户ID
	 * @param skuid
	 *            商品skuID
	 * @param gid
	 *            商品gid，删除奥莱组合时将gid “-”符号连接
	 * @shoppingcartdetailid 商品detailid，删除奥莱组合时将detailid用“，”符号连接
	 * @groupno 组合编号，奥莱组合时必传
	 * @count 删除商品的总数量
	 * @shoptype 表示来自尚品1，奥莱2
	 * @flag 是否需要content属性（此属性，仅支持奥莱流程，值为1时只需返回操作成功、失败即可）
	 * @return 返回操作是否成功
	 */
	public String removeCartOfMerchandise(String userid,String skuid, String gid, String groupno, String shoppingcartdetailid, String count,String shoptype,String flag);
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
}
