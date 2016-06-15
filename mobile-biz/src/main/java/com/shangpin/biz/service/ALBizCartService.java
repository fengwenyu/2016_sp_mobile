package com.shangpin.biz.service;

import com.shangpin.base.vo.CartOrder;
import com.shangpin.base.vo.ConsigneeAddress;
import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.CartContent;
import com.shangpin.biz.bo.OrderReturn;
import com.shangpin.biz.bo.Settlement;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.basic.IBizCartService;

import java.util.List;
import java.util.Map;


/** 
* @ClassName: BizCartService 
* @Description:购物车接口
* @author qinyingchun
* @date 2014年11月5日
* @version 1.0 
*/
public interface ALBizCartService extends IBizCartService {
	
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
	public ResultBase addToCart(String... params);
	
	/**
	 * 
	* @Title: cartList 
	* @Description:购物车列表
	* @param userId 用户标识
	* @param isPromotion 是否参加购物车促销 1:不参加；0:为参加
	* @param width 购物车商品图片宽度
	* @param height 购物车商品图片高度
	* @return CartList
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年11月6日
	 */
	public CartContent cartList(String userId, String isPromotion, String width, String height);

	
	/**
     * 加入购物车
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
    public Map<String, Object> addToCart(String userId, String productNo, String quantity, String skuNo, String categoryNo);
	
    /**
     * 购物车列表
     * 
     * @param userId 用户ID
     * @param pich 图片高度
     * @param picw 图片宽度
     * @param shopType 购物车类型：1：尚品   2：奥莱
     * @param isPromotion 是否促销:0为促销，1为不参加促销
     * @return
     *
     * @author cuibinqiang
     */
	public Map<String,Object> listCart(String userId, String pich, String picw, String shopType,String isPromotion);
	
	/**
	 * 删除购物车商品
	 * 
	 * @param userId 用户ID
	 * @param shopId 购物车详情Id
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public Map<String, Object> delCart(String userId,String shopId);
	
	/**
	 * 修改购物车商品数量
	 * 
	 * @param userId 用户ID   
	 * @param shopDetailId 购物车详情Id
	 * @param quantity 单品购买数量
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public Map<String, Object> updateCart(String userId, String shopDetailId, String quantity);
	
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
    public CartContent submitCart(String userId,String shopId, String picW,String picH);
    /**
     * 提交购物车选中的商品是否成功
     * 
     * @param userId 用户ID
     * @param shopId 选中的商品
     * @param picW 图片宽度
     * @param picH 图片高度
     * @return
     *
     * @author qinyingchun
     */
    public boolean isSubmitCart(String userId,String shopId, String picW,String picH);
    
    /**
     * 获取生成订单的信息
     * 
     * @param userId 用户ID
     * @param isPromotion 是否促销 0促销，1不是促销
     * @param picW 图片宽度
     * @param picH 图片高度
     * @return
     *
     * @author cuibinqiang
     */
    public Settlement getCart(String userId,String isPromotion, String picW,String picH, String postArea);
    
    /**
     * 添加发票地址
     * 
     * @param consigneeAddress
     * @return
     *
     * @author cuibinqiang
     */
    public List<Address> addInvoice(ConsigneeAddress consigneeAddress);
    
    
    /**
     * 提交订单
     * 
     * @param orderVO
     * @return
     *
     * @author cuibinqiang
     */
    public OrderReturn submitOrder(CartOrder orderVO);
    
    /**
     * 确认订单信息时更改地址和优惠码/券内容
     * 
     * @param userId
     *            用户ID
     * @param couponFlag
     *            1使用优惠券； 2使用优惠码；未使用传空字符串；
     * @param coupon
     *            flag为优惠券时，coupon为优惠券id；flag为优惠码时，coupon为优惠码串
     * @param buyGids
     *            购买商品的gid串，多个用“|”分割
     * @param addressId
     *            使用的收货地址id（兼容以后运费与地址有关的情况）
     * @param topicNo
     *            专题编号
     * @author cuibinqiang
     */
    public Map<String, Object> updateOrder(String userId, String couponFlag, String coupon, String buyGids, String addressId, String topicNo, String orderSource);
    
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
    public Map<String, Object> changePay(String userId,String orderId,String mainPay,String subPay);
    
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
    public Map<String, Object> updateStatus(String mainPay,String subPay,String orderId,String payMoney);
}
