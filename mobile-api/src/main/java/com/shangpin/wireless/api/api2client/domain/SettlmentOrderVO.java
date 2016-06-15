package com.shangpin.wireless.api.api2client.domain;

import java.util.List;

import com.shangpin.wireless.api.domain.ProductAllCartVO;

/**
 * 确定订单对象信息
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-06-24
 */
public class SettlmentOrderVO {

	private String amount;// 总金额
	private String promoAmount;// 满减优惠金额
	private String carriageAmount;// 运费
	private String postArea;
	private String totalSettlement;
	private String codincart;// 购物车是否商品是否都支持货到付款
	private String lastInvoiceTitle;// 上一次发票公司名称
	private String lastReceiveId;// 上一次成功订单收货地址
	private String lastInvoiceConsigneeID;// 上一次成功订单发票地址
	private String lastDeliveryType;// 上一次配送方式
	private String giftCardBalance;// 礼品卡余额
	private List<AddressVO> receive;// 收货地址
	private List<AddressVO> invoiceaddrs;// 发票地址
	private List<CouponVO> coupon;// 优惠券
	private LastPaymentVO lastPayment;// 上一次成功订单的支付方式
	private CarriageVO carriage;// 运费
	private ProductAllCartVO productAllCartVO;// 购物车商品信息
	private String lastInvoiceFlag;// 上次是否开发票 0没开 1开
	private List<DeliveryVo> delivery;// 配送信息
	private List<PriceShowVo> priceShow;
	/*
	 * 默认使用优惠券功能增加四个参数如下
	 */
	private String couponIsUsed;//未使用优惠券，1已使用
	private String couponAmount;//优惠券优惠金额
	private String couponIndex;//优惠券列表的下标，从0开始
	private String payAmount;
	
	//关税调整增加下列参数 开始
	private String productAmount;//商品裸价（不含税费、邮费）
	private String isHaveDirect;//是否包含直发商品  0:不包含 ，1:包含
	//关税调整增加下列参数 结束
	private String isInvoiceAddress;//是否显示发票地址，0不显示，1显示
	private String invoiceDesc;//开发票的描述
	public String getIsInvoiceAddress() {
		return isInvoiceAddress;
	}

	public void setIsInvoiceAddress(String isInvoiceAddress) {
		this.isInvoiceAddress = isInvoiceAddress;
	}

	public String getInvoiceDesc() {
		return invoiceDesc;
	}

	public void setInvoiceDesc(String invoiceDesc) {
		this.invoiceDesc = invoiceDesc;
	}

	public String getProductAmount() {
		return productAmount;
	}

	public String getIsHaveDirect() {
		return isHaveDirect;
	}

	public void setIsHaveDirect(String isHaveDirect) {
		this.isHaveDirect = isHaveDirect;
	}

	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}

	public String getAmount() {
		return amount;
	}

	public String getTotalSettlement() {
		return totalSettlement;
	}

	public void setTotalSettlement(String totalSettlement) {
		this.totalSettlement = totalSettlement;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPromoAmount() {
		return promoAmount;
	}

	public void setPromoAmount(String promoAmount) {
		this.promoAmount = promoAmount;
	}

	public String getCodincart() {
		return codincart;
	}

	public void setCodincart(String codincart) {
		this.codincart = codincart;
	}

	public String getLastInvoiceTitle() {
		return lastInvoiceTitle;
	}

	public void setLastInvoiceTitle(String lastInvoiceTitle) {
		this.lastInvoiceTitle = lastInvoiceTitle;
	}

	public String getLastReceiveId() {
		return lastReceiveId;
	}

	public void setLastReceiveId(String lastReceiveId) {
		this.lastReceiveId = lastReceiveId;
	}

	public String getLastInvoiceConsigneeID() {
		return lastInvoiceConsigneeID;
	}

	public void setLastInvoiceConsigneeID(String lastInvoiceConsigneeID) {
		this.lastInvoiceConsigneeID = lastInvoiceConsigneeID;
	}

	public String getGiftCardBalance() {
		return giftCardBalance;
	}

	public void setGiftCardBalance(String giftCardBalance) {
		this.giftCardBalance = giftCardBalance;
	}

	public List<AddressVO> getReceive() {
		return receive;
	}

	public void setReceive(List<AddressVO> receive) {
		this.receive = receive;
	}

	public List<AddressVO> getInvoiceaddrs() {
		return invoiceaddrs;
	}

	public void setInvoiceaddrs(List<AddressVO> invoiceaddrs) {
		this.invoiceaddrs = invoiceaddrs;
	}

	public LastPaymentVO getLastPayment() {
		return lastPayment;
	}

	public void setLastPayment(LastPaymentVO lastPayment) {
		this.lastPayment = lastPayment;
	}

	public CarriageVO getCarriage() {
		return carriage;
	}

	public void setCarriage(CarriageVO carriage) {
		this.carriage = carriage;
	}

	public ProductAllCartVO getProductAllCartVO() {
		return productAllCartVO;
	}

	public void setProductAllCartVO(ProductAllCartVO productAllCartVO) {
		this.productAllCartVO = productAllCartVO;
	}

	public List<CouponVO> getCoupon() {
		return coupon;
	}

	public void setCoupon(List<CouponVO> coupon) {
		this.coupon = coupon;
	}

	public String getLastDeliveryType() {
		return lastDeliveryType;
	}

	public void setLastDeliveryType(String lastDeliveryType) {
		this.lastDeliveryType = lastDeliveryType;
	}

	public String getLastInvoiceFlag() {
		return lastInvoiceFlag;
	}

	public void setLastInvoiceFlag(String lastInvoiceFlag) {
		this.lastInvoiceFlag = lastInvoiceFlag;
	}

	public List<DeliveryVo> getDelivery() {
		return delivery;
	}

	public void setDelivery(List<DeliveryVo> delivery) {
		this.delivery = delivery;
	}

	public List<PriceShowVo> getPriceShow() {
		return priceShow;
	}

	public void setPriceShow(List<PriceShowVo> priceShow) {
		this.priceShow = priceShow;
	}

	public String getCarriageAmount() {
		return carriageAmount;
	}

	public void setCarriageAmount(String carriageAmount) {
		this.carriageAmount = carriageAmount;
	}

	public String getPostArea() {
		return postArea;
	}

	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}

	public String getCouponIsUsed() {
		return couponIsUsed;
	}

	public void setCouponIsUsed(String couponIsUsed) {
		this.couponIsUsed = couponIsUsed;
	}

	public String getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getCouponIndex() {
		return couponIndex;
	}

	public void setCouponIndex(String couponIndex) {
		this.couponIndex = couponIndex;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

}
