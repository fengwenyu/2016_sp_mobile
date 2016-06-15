package com.shangpin.mobileAolai.platform.vo;


import java.io.Serializable;
import java.util.List;



/**
 * 确定订单对象信息
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-06-24
 */
public class SettlmentOrderVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3906929734136991573L;
    private String amount ;//总金额
    private String codincart;//购物车是否商品是否都支持货到付款
    private String lastInvoiceTitle;//上一次发票公司名称
    private String lastReceiveId;//上一次成功订单收货地址
    private String lastInvoiceConsigneeID;//上一次成功订单发票地址
    private String lastDeliveryType;//上一次配送方式
    private String giftCardBalance;//礼品卡余额
    private List<ConsigneeAddressVO> receive;//收货地址
    private List<ConsigneeAddressVO> invoiceaddrs;//发票地址
    private List<CouponVO> coupon;//优惠券
    private LastPaymentVO lastPayment;//  上一次成功订单的支付方式
    private CarriageVO  carriage;//运费    
    private ProductAllCartVO    productAllCartVO;//购物车商品信息
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
    public String getGiftCardBalance() {
        return giftCardBalance;
    }
    public void setGiftCardBalance(String giftCardBalance) {
        this.giftCardBalance = giftCardBalance;
    }
    public List<ConsigneeAddressVO> getReceive() {
        return receive;
    }
    public void setReceive(List<ConsigneeAddressVO> receive) {
        this.receive = receive;
    }
    public List<ConsigneeAddressVO> getInvoiceaddrs() {
        return invoiceaddrs;
    }
    public void setInvoiceaddrs(List<ConsigneeAddressVO> invoiceaddrs) {
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
    
    
   
	
	
    
}
