package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @author qinyingchun 立即购买实体类
 */
public class BuyNow implements Serializable {

	private static final long serialVersionUID = 2770111199471467921L;
	private String postArea;
	private String buyId;
	private String totalAmount;
	private String totalSettlement;
	private String carriageAmount;//运费
	private String payAmount;
	private String promoAmount;
	private String codFlag;
	private String lastInvoiceID;
	private String lastInvoiceFlag;
	 private String lastInvoiceType;
	private String lastInvoiceTitle;
	private String lastInvoiceContent;
	private String lastReceiveId;
	private String lastDeliveryType;
	private String giftCardBalance;
	private List<Receive> receive;
	private List<Receive> invoice;
	private List<Coupon> coupon;
	private PayType lastPayType;
	private Carriage carriage;
	private Carriage tariff;
	private List<Pay> payment;
	private List<PayNowProduct> list;
	private List<DeliveryVo> delivery;//配送信息
	private List<PriceShowVo> priceShow;
	private String isInvoiceAddress;//是否显示发票地址，0不显示，显示
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

	/*
	 * 默认使用优惠券功能增加三个参数如下
	 */
	private String couponIsUsed;//未使用优惠券，1已使用
	private String couponAmount;//优惠券优惠金额
	private String couponIndex;//优惠券列表的下标，从0开始
	
	//关税调整增加下列参数 开始
	private String productAmount;//商品裸价（不含税费、邮费）
	private String isHaveDirect;//是否包含直发商品  0:不包含 ，1:包含
	//关税调整增加下列参数 结束
	
	
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

	public String getCarriageAmount() {
		return carriageAmount;
	}

	public void setCarriageAmount(String carriageAmount) {
		this.carriageAmount = carriageAmount;
	}

	public String getLastInvoiceType() {
		return lastInvoiceType;
	}

	public void setLastInvoiceType(String lastInvoiceType) {
		this.lastInvoiceType = lastInvoiceType;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalSettlement() {
		return totalSettlement;
	}

	public void setTotalSettlement(String totalSettlement) {
		this.totalSettlement = totalSettlement;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getPromoAmount() {
		return promoAmount;
	}

	public void setPromoAmount(String promoAmount) {
		this.promoAmount = promoAmount;
	}

	public String getCodFlag() {
		return codFlag;
	}

	public void setCodFlag(String codFlag) {
		this.codFlag = codFlag;
	}

	public String getPostArea() {
		return postArea;
	}

	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}

	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	public String getLastInvoiceID() {
		return lastInvoiceID;
	}

	public void setLastInvoiceID(String lastInvoiceID) {
		this.lastInvoiceID = lastInvoiceID;
	}

	public String getLastInvoiceFlag() {
		return lastInvoiceFlag;
	}

	public void setLastInvoiceFlag(String lastInvoiceFlag) {
		this.lastInvoiceFlag = lastInvoiceFlag;
	}

	public String getLastInvoiceTitle() {
		return lastInvoiceTitle;
	}

	public void setLastInvoiceTitle(String lastInvoiceTitle) {
		this.lastInvoiceTitle = lastInvoiceTitle;
	}

	public String getLastInvoiceContent() {
		return lastInvoiceContent;
	}

	public void setLastInvoiceContent(String lastInvoiceContent) {
		this.lastInvoiceContent = lastInvoiceContent;
	}

	public String getLastReceiveId() {
		return lastReceiveId;
	}

	public void setLastReceiveId(String lastReceiveId) {
		this.lastReceiveId = lastReceiveId;
	}

	public String getLastDeliveryType() {
		return lastDeliveryType;
	}

	public void setLastDeliveryType(String lastDeliveryType) {
		this.lastDeliveryType = lastDeliveryType;
	}

	public String getGiftCardBalance() {
		return giftCardBalance;
	}

	public void setGiftCardBalance(String giftCardBalance) {
		this.giftCardBalance = giftCardBalance;
	}

	public List<Receive> getReceive() {
		return receive;
	}

	public void setReceive(List<Receive> receive) {
		this.receive = receive;
	}

	public List<Receive> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Receive> invoice) {
		this.invoice = invoice;
	}

	public List<Coupon> getCoupon() {
		return coupon;
	}

	public void setCoupon(List<Coupon> coupon) {
		this.coupon = coupon;
	}

	public PayType getLastPayType() {
		return lastPayType;
	}

	public void setLastPayType(PayType lastPayType) {
		this.lastPayType = lastPayType;
	}

	public Carriage getCarriage() {
		return carriage;
	}

	public void setCarriage(Carriage carriage) {
		this.carriage = carriage;
	}

	public Carriage getTariff() {
		return tariff;
	}

	public void setTariff(Carriage tariff) {
		this.tariff = tariff;
	}

	public List<Pay> getPayment() {
		return payment;
	}

	public void setPayment(List<Pay> payment) {
		this.payment = payment;
	}

	public List<PayNowProduct> getList() {
		return list;
	}

	public void setList(List<PayNowProduct> list) {
		this.list = list;
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

}
