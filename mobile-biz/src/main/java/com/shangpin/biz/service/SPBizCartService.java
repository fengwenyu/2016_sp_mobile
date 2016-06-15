package com.shangpin.biz.service;

import java.util.List;
import java.util.Map;

import com.shangpin.biz.bo.CartContent;
import com.shangpin.biz.bo.RecProduct;
import com.shangpin.biz.bo.Settlement;
import com.shangpin.biz.bo.ShopCartItem;
import com.shangpin.biz.bo.cart.Cart;
import com.shangpin.biz.bo.orderUnion.SettlementUnion;
import com.shangpin.biz.service.basic.IBizCartService;

/**
 * @ClassName: BizCartService
 * @Description:购物车接口
 * @author qinyingchun
 * @date 2014年11月5日
 * @version 1.0
 */
public interface SPBizCartService extends IBizCartService {

	/**
	 * 
	 * @Title: cartList
	 * @Description:购物车列表
	 * @param userId
	 *            用户标识
	 * @param isPromotion
	 *            是否参加购物车促销 1:不参加；0:为参加
	 * @param width
	 *            购物车商品图片宽度
	 * @param height
	 *            购物车商品图片高度
	 * @return CartList
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月6日
	 */
	public CartContent cartList(String userId, String isPromotion, String width, String height, String postArea);

	/**
	 * 修改购物车商品数量
	 * 
	 * @param userId
	 *            用户ID
	 * @param shopDetailId
	 *            购物车详情Id
	 * @param quantity
	 *            单品购买数量
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public Map<String, Object> updateCart(String userId, String shopDetailId, String quantity);

	/**
	 * 提交购物车选中的商品是否成功
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
	 * @author qinyingchun
	 */
	public boolean isSubmitCart(String userId, String shopId, String picW, String picH);

	/**
	 * 获取生成订单的信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param isPromotion
	 *            是否促销 0促销，1不是促销
	 * @param picW
	 *            图片宽度
	 * @param picH
	 *            图片高度
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public Settlement getCart(String userId, String isPromotion, String picW, String picH, String postArea);

	/**
	 * 页面无内容时获得推荐集合
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
	public List<RecProduct> findRecProductObj(String userId, String type, String pageIndex, String pageSize);
	/**
	 * 
	 * @Title: showCart
	 * @Description:新版查看购物车列表
	 * @param userId
	 *            用户标识
	 * @param isChecked
	 *            此用户选中的单品，通过“|”线分割组装多个选中单品
	 * @Create By liling
	 * @Create Date 2015年4月23日
	 */
	public ShopCartItem showCart(String userId, String isChecked);
	/**
	 * 
	 * @Title: delCart
	 * @Description:新版删除购物车
	 * @param userId
	 *            用户标识
	 * @param cartDetailId
	 *            此用户修改的单品，通过“|”线分割组装多个单品     
	 * @param isChecked
	 *            此用户选中的单品，通过“|”线分割组装多个选中单品
	 * @Create By liling
	 * @Create Date 2015年4月23日
	 */
	public Map<String,Object> delCart(String userId, String cartDetailId,String isChecked);
	/**
	 * 
	 * @Title: modifyCart
	 * @Description:新版修改购物车
	 * @param userId
	 *            用户标识
	 * @param cartItem
	 *           此用户修改的单品，通过“|”线分割组装多个单品和数量，单品和数量用“_”分割；如：9999_9|999_1
	 * @param isChecked
	 *            此用户选中的单品，通过“|”线分割组装多个选中单品
	 * @Create By liling
	 * @Create Date 2015年4月23日
	 */
	public Map<String, Object> modifyCart(String userid, String cartItem,
			String isChecked,String region);
	public Map<String, Object> modifyCart(String userid, String cartItem,
			String isChecked,String region,String chanelNo,String chanelId);

	// ///////////////////////////////////////520//////////////////////////////////

	/**
	 * 用于选中、取消选中显示购物车商品的接口
	 *
	 * @param userId
	 * @param isChecked
	 *            此用户选中的单品，通过“|”线分割组装多个选中单品
	 * @return
	 */
	Cart doShowCartV2(String userId, String isChecked);

	/**
	 * 用于修改购物车商品数量后展示的接口
	 *
	 * @param userId
	 * @param cartItem
	 * @param isChecked
	 * @param channelNo
	 *            (非必须)
	 * @param channelId
	 *            （非必须）
	 */
	Map<String, Object> doModifyCartV2(String userId, String cartItem,
			String isChecked, String channelNo, String channelId);

	/**
	 * 用于删除购物车商品后展示的接口
	 *
	 * @param userId
	 * @param cartDetailId
	 * @param isChecked
	 * @return
	 */
	Map<String, Object> doDeleteCartV2(String userId, String cartDetailId,
			String isChecked);

}
