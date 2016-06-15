package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

import com.shangpin.biz.utils.StringUtil;





public class Settlement implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String postArea;//商品发货地
	private String amount ;//总金额
	private String payAmount;//支付金额
	private String carriageAmount;//运费
	private String promoAmount;//促销价
	private List<Delivery> delivery;//配送方式
	private List<PriceShowVo> priceShow;//价格体系
    private String codincart;//购物车是否商品是否都支持货到付款
    private String lastInvoiceTitle;//上一次发票公司名称
    private String lastReceiveId;//上一次成功订单收货地址
    private String lastInvoiceFlag;//上一次成功订单发票标记
    private String lastInvoiceType;
    private String lastInvoiceConsigneeID;//上一次成功订单发票地址
    private String lastDeliveryType;//上一次配送方式
    private String giftCardBalance;//礼品卡余额
    private List<ConsigneeAddress> receive;//收货地址
    private List<ConsigneeAddress> invoiceaddrs;//发票地址
    private List<Coupon> coupon;//优惠券
    private LastPayment lastPayMent;//  上一次成功订单的支付方式
    private Carriage  carriage;//运费    
    private CartContent list;
    private String totalSettlement;//结算商品数量
    
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
	public String getTotalSettlement() {
		return totalSettlement;
	}
	public void setTotalSettlement(String totalSettlement) {
		this.totalSettlement = totalSettlement;
	}
	public String getCarriageAmount() {
		return carriageAmount;
	}
	public void setCarriageAmount(String carriageAmount) {
		this.carriageAmount = carriageAmount;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public List<Delivery> getDelivery() {
		return delivery;
	}
	public void setDelivery(List<Delivery> delivery) {
		this.delivery = delivery;
	}
	public List<PriceShowVo> getPriceShow() {
		return priceShow;
	}
	public void setPriceShow(List<PriceShowVo> priceShow) {
		this.priceShow = priceShow;
	}
	public String getPostArea() {
		return postArea;
	}
	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}
	public String getLastInvoiceType() {
		return lastInvoiceType;
	}
	public void setLastInvoiceType(String lastInvoiceType) {
		this.lastInvoiceType = lastInvoiceType;
	}
	public CartContent getList() {
		return list;
	}
	public void setList(CartContent list) {
		this.list = list;
	}
	
	public String getPromoAmount() {
		return promoAmount;
	}
	public void setPromoAmount(String promoAmount) {
		this.promoAmount = promoAmount;
	}
	public String getLastInvoiceFlag() {
		return lastInvoiceFlag;
	}
	public void setLastInvoiceFlag(String lastInvoiceFlag) {
		this.lastInvoiceFlag = lastInvoiceFlag;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	public String getLastDeliveryType() {
		return lastDeliveryType;
	}
	public void setLastDeliveryType(String lastDeliveryType) {
		this.lastDeliveryType = lastDeliveryType;
	}
	public String getGiftCardBalance() {
		return StringUtil.removePoint(giftCardBalance);
	}
	public void setGiftCardBalance(String giftCardBalance) {
		this.giftCardBalance = giftCardBalance;
	}
	public List<ConsigneeAddress> getReceive() {
		return receive;
	}
	public void setReceive(List<ConsigneeAddress> receive) {
		this.receive = receive;
	}
	public List<ConsigneeAddress> getInvoiceaddrs() {
		return invoiceaddrs;
	}
	public void setInvoiceaddrs(List<ConsigneeAddress> invoiceaddrs) {
		this.invoiceaddrs = invoiceaddrs;
	}
	public List<Coupon> getCoupon() {
		return coupon;
	}
	public void setCoupon(List<Coupon> coupon) {
		this.coupon = coupon;
	}
	
	public LastPayment getLastPayMent() {
		return lastPayMent;
	}
	public void setLastPayMent(LastPayment lastPayMent) {
		this.lastPayMent = lastPayMent;
	}
	public Carriage getCarriage() {
		return carriage;
	}
	public void setCarriage(Carriage carriage) {
		this.carriage = carriage;
	}
    
}
