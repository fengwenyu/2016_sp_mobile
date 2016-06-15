package com.shangpin.wireless.api.api2server.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * 用于封装主站订单信息接口的返回数据
 * 
 * @Author: wangwenguan
 * @CreateDate: 2013-11-19
 */
public class OrderInfoServerResponse extends CommonServerResponse {
	
	private String orderId;
	private String userId;
	private Date orderTime;	//	下单时间(格式2013-08-15 23:15:08)
	private String status;	//	订单状态
	private String statusDesc;	//	状态中文描述
	private String orderType;	//	尚品/奥莱(1or2)
	private String orderOrigin;	//	订单来源
	private String orderOriginDesc;	//	来源中文描述
	private String orderAmount;	//	订单商品金额
	private String payAmount;	//	订单支付金额
	private String giftcardAmount;	//	礼品卡支付金额
	private String onlineAmount;	//	在线支付金额
	private String couponAmount;	//	优惠券优惠金额
	private String carriage;	//	运费
	private Date payTime;	//	支付时间
	private String payTypeId;	//	主支付
	private String payTypeChildId;	//	子支付

	/**
	 * 解析主站返回的订单信息json数据
	 * 
	 * @Author: wangwenguan
	 * @CreatDate: 2013-11-19
	 * @param jsonStr 主站返回的json数据
	 * @Return
	 */
//	public OrderInfoServerResponse json2Obj(String jsonStr) {
//		JSONObject obj = JSONObject.fromObject(jsonStr);
//		this.setCode(obj.getString("code"));
//		this.setMsg(obj.getString("msg"));
//		if (Constants.CODE_SUCCESS.equals(code)) {
//			obj = JSONObject.fromObject(obj.getJSONObject("content"));
//			this.setContent(obj);
//		}
//		return this;
//	}
	
	public void setContent (JSONObject content) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		setOrderid(content.getString("orderid"));
		setUserid(content.getString("userid"));
		setOrderTime(sdf.parse(content.getString("orderTime")));
		setStatus(content.getString("status"));
		setStatusDesc(content.getString("statusDesc"));
		setOrderType(content.getString("orderType"));
		setOrderOrigin(content.getString("orderOrigin"));
		setOrderOriginDesc(content.getString("orderOriginDesc"));
		setOrderAmount(content.getString("orderAmount"));
		setPayAmount(content.getString("payAmount"));
		setGiftcardAmount(content.getString("giftcardAmount"));
		setOnlineAmount(content.getString("onlineAmount"));
		setCouponAmount(content.getString("couponAmount"));
		setCarriage(content.getString("carriage"));
		final String paytime = content.getString("payTime");
		if (paytime.length() > 0 && !"1900-01-01 00:00:00".equals(paytime))
			setPayTime(sdf.parse(paytime));
		setPayTypeId(content.getString("payTypeId"));
		setPayTypeChildId(content.getString("payTypeChildId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderid(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserid(String userId) {
		this.userId = userId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderOrigin() {
		return orderOrigin;
	}

	public void setOrderOrigin(String orderOrigin) {
		this.orderOrigin = orderOrigin;
	}

	public String getOrderOriginDesc() {
		return orderOriginDesc;
	}

	public void setOrderOriginDesc(String orderOriginDesc) {
		this.orderOriginDesc = orderOriginDesc;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getGiftcardAmount() {
		return giftcardAmount;
	}

	public void setGiftcardAmount(String giftcardAmount) {
		this.giftcardAmount = giftcardAmount;
	}

	public String getOnlineAmount() {
		return onlineAmount;
	}

	public void setOnlineAmount(String onlineAmount) {
		this.onlineAmount = onlineAmount;
	}

	public String getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getCarriage() {
		return carriage;
	}

	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getPayTypeId() {
		return payTypeId;
	}

	public void setPayTypeId(String payTypeId) {
		this.payTypeId = payTypeId;
	}

	public String getPayTypeChildId() {
		return payTypeChildId;
	}

	public void setPayTypeChildId(String payTypeChildId) {
		this.payTypeChildId = payTypeChildId;
	}

}
