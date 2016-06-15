package com.shangpin.mobileAolai.platform.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.shangpin.mobileAolai.platform.vo.OrderVO;
import com.shangpin.mobileAolai.platform.vo.PayVO;


/**
 * 订单业务逻辑接口，用于订单相关操作
 * 
 * @author yumeng
 * @date:2012-12-29
 */
public interface OrderService {

	/**
	 * 根据订单对象，生成订单
	 * 
	 * @param orderVO
	 *            订单实体
	 * 
	 * @return 订单对象
	 */
	public OrderVO addOrder(OrderVO orderVO);

	/**
	 * 获取订单信息
	 * @param userId 
	 * 				用户ID
	 * @param status
	 * 				订单状态
	 * @return
	 */
	public JSONObject getOrderInfoOfJson(String userId, String status);
	/**
	 * 根据用户ID、订单状态，获取3个月以内的订单列表
	 * 
	 * @param userid
	 *            用户ID
	 * @param statustype
	 *            订单状态(all全部、waitpay待支付、preparegoods配货中、delivergoods发货中)
	 * 
	 * @return 获取3个月以内的订单列表
	 */
	public List<OrderVO> getOrderList(String userid, String statustype);

	/**
	 * 根据用户ID、订单状态，获取3个月以内的Json格式订单数据
	 * 
	 * @param userid
	 *            用户ID
	 * @param statustype
	 *            订单状态(all全部、waitpay待支付、preparegoods配货中、delivergoods发货中)
	 * @param ordertype
	 *            表示订单来自尚品1，奥莱2
	 * @param flag
	 *           否需要详情标识符（添加flag参数，为1时获取商品详情信息，为0时，detail属性的值为””     
	 * @return 获取3个月以内的Json格式订单数据
	 */
	public JSONObject getOrderJson(String userid, String statustype,String ordertype,String flag );

	/**
	 * 根据用户ID、订单ID，获取订单详细信息
	 * 
	 * @param userid
	 *            用户ID
	 * @param orderid
	 *            订单ID
	 * 
	 * @return 订单详细信息
	 */
	public OrderVO getOrderDetail(String userid, String orderid);

	/**
	 * 根据用户ID、订单ID，取消订单
	 * 
	 * @param userid
	 *            用户ID
	 * @param orderid
	 *            订单ID
	 * 
	 * @return 操作结果
	 */
	public JSONObject cancelOrder(String userid, String orderid);

	/**
	 * 根据用户ID，获取生成订单相关数据，如：商品是否支持货到付款、收货人地址、发票收货人地址、订单总金额等
	 * 
	 * @param userid
	 *            用户ID
	 * 
	 * @return 返回订单实体
	 */
	public Map<String, Object> getPreparedOrder(String userid,String shopType);

	public  Map<String, Object> chagePayMode(String userid, String orderid, String mainpaymode, String subpaymode);
	
	/**
	 * 根据渠道号获取支付方式
	 * 
	 * @param c 渠道号
	 *  @return 返回支付实体
	 */
	public List<PayVO> getPayment(String ch);
	   /**
     * 选中要提交的商品
     * 
     * @param shopId 商品
     * @param shopType 商品
     *  @return 返回支付实体
     */
    public boolean getCheckShopId(String userId,String shopId, String shopTpye);

}
