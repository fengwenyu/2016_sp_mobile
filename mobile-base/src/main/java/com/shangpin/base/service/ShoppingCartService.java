package com.shangpin.base.service;

import java.util.Map;

/**
 * 购物车相关接口的Service
 * 
 * @author sunweiwei
 * 
 */
public interface ShoppingCartService {

	/**
	 * 加入购物车（奥莱）
	 * 
	 * @param userId 用户ID       
	 * @param productNo 商品ID    
	 * @param quantity 单品购买数量   
	 * @param skuNo 商品sku       
	 * @param categoryNo 活动/专题编号
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public String addToCart(String userId, String productNo, String quantity, String sku, String categoryNo);
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
	public String addToCart(String userId, String productNo, String quantity, String sku, String categoryNo,
			String dynamicattributetext, String topicSubjectFlag, String skuFrom, String vipNo, String siteNo);
	/**
	 * 购物车列表（奥莱）
	 * 
	 * 	@param userId 用户ID                  
	 *	@param pich 图片高度                    
	 *	@param picw 图片宽度                    
	 *	@param shopType 购物车类型：1：尚品   2：奥莱   
	 *	@param isPromotion 是否促销:0为促销，1为不参加促销
	 *
	 *  @author cuibinqiang
	 */
	public String listCart(String userId, String pich, String picw, String shopType,String isPromotion);
	
	/**
	 * 删除购物车商品
	 * 
	 * @param userId 用户ID   
	 * @param shopId 购物车详情Id
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public String delCart(String userId,String shopId);
	
	/**
	 * 修改购物车产品数量
	 * 
	 * @param userId 用户ID
	 * @param shopId 购物车详情ID
	 * @param count 数量
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public String updateCart(String userId,String shopId,String count);
	
	/**
	 * 
	* @Title: addToCart 
	* @Description:加入购物车
	* @param userId 用户唯一标识
	* @param productNo 商品编号
	* @param quantity 购买数量
	* @param sku sku 编号（移动端）
	* @param categoryNo 活动号/专题号
	* @param dynamicattributetext sku动态属性值(web端,移动端传空)
	* @param topicSubjectFlag 列表/活动/专题类型
	* @param skuFrom sku来源 1尚品 2奥莱 3手机
	* @param vipNo vip编号（暂时传0）
	* @param siteNo 商品添加来源
	* @param @return
	* @return String
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年11月5日
	 */
	public String addToCart(String... params);
	
	/**
	 * 
	* @Title: cartList 
	* @Description:购物车列表
	* @param userId 用户标识
	* @param isPromotion 是否参加购物车促销 1:不参加；0:为参加
	* @param width 购物车商品图片宽度
	* @param height 购物车商品图片高度
	* @return String
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年11月6日
	 */
	public String cartList(String userId, String isPromotion, String width, String height);
	
	/**
     * 提交购物车选中的商品
     * 
     * @param userId 用户ID
     * @param shopId 选中的商品
     * @param picW 图片宽度
     * @param picH 图片高度
     * @return
     *
     * @author cuibinqiang
     */
    public String submitCart(String userId,String shopId, String picW,String picH);
    
    /**
     * 获取生成订单的信息
     * 
     * @param userId 用户ID
     * @param isPromotion 是否促销
     * @param picW 图片宽度
     * @param picH 图片高度
     * @param postArea 1国内，2国外
     * @return
     *
     * @author cuibinqiang
     */
    public String getCart(String userId,String isPromotion, String picW,String picH, String postArea);
    
    /**
     * 支付方式变更
     * 
     * @param userId 用户ID
     * @param orderId 订单ID
     * @param mainPay 主支付方式
     * @param subPay 子支付方式
     * @return
     *
     * @author cuibinqiang
     */
    public String changePay(String userId,String orderId,String mainPay,String subPay);
    
    /**
     * 更新订单状态
     * 
     * @param mainPay 主支付方式
     * @param subPay 子支付方式 
     * @param orderId 订单ID
     * @param payMoney 订单金额
     * @return
     *
     * @author cuibinqiang
     */
    public String updateStatus(String mainPay,String subPay,String orderId,String payMoney);
    
    /**
     * 用于选中、取消选中显示购物车商品的接口
     * @param userId
     * @param checked
     * 			此用户选中的单品，通过“|”线分割组装多个选中单品
     * @return
     */
    String showCart(String userId, String checked);
    
    /**
     * 用于修改购物车商品数量后展示的接口
     * @param userId
     * @param cartItem
     * 			此用户修改的单品，通过“|”线分割组装多个单品和数量，单品和数量用“_”分割；如：9999_9|999_1
     * @param checked
     * 			此用户选中的单品，通过“|”线分割组装多个选中单品
     * @return
     */
    String modifyCart(String userId, String cartItem, String checked,String region);
    String modifyCart(String userId, String cartItem, String checked,String region,String channelNo,String channelId);
    
    /**
     * 
     * @param userId
     * @param cartDetailId
     * 			此用户修改的单品，通过“|”线分割组装多个单品
     * @param checked
     * 			此用户选中的单品，通过“|”线分割组装多个选中单品
     * @return
     */
    String deleteCart(String userId, String cartDetailId, String checked);


////////////////////////////////////////////////520///////////////////
	/**
	 * 用于选中、取消选中显示购物车商品的接口
	 * @param userId
	 * @param checked
	 * 			此用户选中的单品，通过“|”线分割组装多个选中单品
	 * @return
	 */
	String showCartV2(String userId, String checked);

	/**
	 * 用于修改购物车商品数量后展示的接口
	 * @param userId
	 * @param cartItem
	 * 			此用户修改的单品，通过“|”线分割组装多个单品和数量，单品和数量用“_”分割；如：9999_9|999_1
	 * @param checked
	 * 			此用户选中的单品，通过“|”线分割组装多个选中单品
	 * @return
	 */
	String modifyCartV2(String userId, String cartItem, String checked,String channelNo,String channelId);

	/**
	 *
	 * @param userId
	 * @param cartDetailId
	 * 			此用户修改的单品，通过“|”线分割组装多个单品
	 * @param checked
	 * 			此用户选中的单品，通过“|”线分割组装多个选中单品
	 * @return
	 */
	String deleteCartV2(String userId, String cartDetailId, String checked);



}
