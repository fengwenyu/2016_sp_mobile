package com.shangpin.wireless.domain;

import java.io.Serializable;
import java.util.Date;

public class OrderStatistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String orderId;
	private Float orderAmount;	//	订单金额
	private Float onlineAmount;	//	在线支付金额
	private Float couponAmount;	//	优惠金额
	private Float giftcardAmount;	//	礼品卡支付金额
	private int skucounts;	//	订单的独立商品数量
	private int objectcounts;	//	订单的物品个数
	private Long orderProduct; //
	private Long orderChannel; //
	private String isok;	//	是否成功下单
	private Long payProduct; //
	private Long payChannel; //
	private String ispaid;	//	是否成功支付
	private Date orderTime;	//	下单时间
	private Date payTime;	//	支付时间
	private String mainPayMode;	//	主支付方式
	private String subPayMode;	//	子支付方式
	private String orderOrigin;	//	订单来源
	private String orderType;	//	订单类型：尚品 or 奥莱
	private String orderRetcode;	//	订单提交返回码
	private String ip;	//	40
	private String city;	//	20
	private Date createTime;	//	数据库记录创建时间
	private String markup;// 备注
	private String reserve0;// 备用字段
	private String reserve1;// 备用字段
	private String reserve2;// 备用字段
	private String reserve3;// 备用字段
	private String reserve4;// 备用字段
	private String reserve5;// 备用字段
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Float getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Float orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Float getOnlineAmount() {
		return onlineAmount;
	}
	public void setOnlineAmount(Float onlineAmount) {
		this.onlineAmount = onlineAmount;
	}
	public Float getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(Float couponAmount) {
		this.couponAmount = couponAmount;
	}
	public Float getGiftcardAmount() {
		return giftcardAmount;
	}
	public void setGiftcardAmount(Float giftcardAmount) {
		this.giftcardAmount = giftcardAmount;
	}
	public int getSkucounts() {
		return skucounts;
	}
	public void setSkucounts(int skucounts) {
		this.skucounts = skucounts;
	}
	public int getObjectcounts() {
		return objectcounts;
	}
	public void setObjectcounts(int objectcounts) {
		this.objectcounts = objectcounts;
	}
	public Long getOrderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(Long orderProduct) {
		this.orderProduct = orderProduct;
	}
	public Long getOrderChannel() {
		return orderChannel;
	}
	public void setOrderChannel(Long orderChannel) {
		this.orderChannel = orderChannel;
	}
	public String getIsok() {
		return isok;
	}
	public void setIsok(String isok) {
		this.isok = isok;
	}
	public Long getPayProduct() {
		return payProduct;
	}
	public void setPayProduct(Long payProduct) {
		this.payProduct = payProduct;
	}
	public Long getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(Long payChannel) {
		this.payChannel = payChannel;
	}
	public String getIspaid() {
		return ispaid;
	}
	public void setIspaid(String ispaid) {
		this.ispaid = ispaid;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getMainPayMode() {
		return mainPayMode;
	}
	public void setMainPayMode(String mainPayMode) {
		this.mainPayMode = mainPayMode;
	}
	public String getSubPayMode() {
		return subPayMode;
	}
	public void setSubPayMode(String subPayMode) {
		this.subPayMode = subPayMode;
	}
	public String getOrderOrigin() {
		return orderOrigin;
	}
	public void setOrderOrigin(String orderOrigin) {
		this.orderOrigin = orderOrigin;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderRetcode() {
		return orderRetcode;
	}
	public void setOrderRetcode(String orderRetcode) {
		this.orderRetcode = orderRetcode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getReserve0() {
		return reserve0;
	}
	public void setReserve0(String reserve0) {
		this.reserve0 = reserve0;
	}
	public String getReserve1() {
		return reserve1;
	}
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	public String getReserve2() {
		return reserve2;
	}
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	public String getReserve3() {
		return reserve3;
	}
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	public String getReserve4() {
		return reserve4;
	}
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}
	public String getReserve5() {
		return reserve5;
	}
	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}
	public String getMarkup() {
		return markup;
	}
	public void setMarkup(String markup) {
		this.markup = markup;
	}

}
