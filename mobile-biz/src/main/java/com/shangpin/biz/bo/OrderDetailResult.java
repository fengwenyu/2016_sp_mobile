package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 子订单详情返回
 * @author zrs
 *
 */
public class OrderDetailResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String systime;            
	private String mainOrderId;        
	private String canCancel;          
	private String canPay;             
	private String status;             
	private String statusName;         
	private String date;               
	private String expiryDate;         
	private String orderType;          
	private String orderTypeDesc;      
	private String isUseGiftCard;      
	private String giftCardBalance;    
	private String giftCardAmount;     
	private String payAmount;          
	private String noticeInfo;         
	private PayType selectPayment;     
	private List<Pay> payment;            
	private List<DeliveryVo> delivery;           
	private String isProductCod;              
	private Receive receive;           
	private List<Receive> receiveList;        
	private Invoice invoice;           
	private List<Invoice> invoiceList;        
	private List<Order> orderList;          
	private List<PriceShow> priceShow;
	public String getSystime() {
		return systime;
	}
	public void setSystime(String systime) {
		this.systime = systime;
	}
	public String getMainOrderId() {
		return mainOrderId;
	}
	public void setMainOrderId(String mainOrderId) {
		this.mainOrderId = mainOrderId;
	}
	public String getCanCancel() {
		return canCancel;
	}
	public void setCanCancel(String canCancel) {
		this.canCancel = canCancel;
	}
	public String getCanPay() {
		return canPay;
	}
	public void setCanPay(String canPay) {
		this.canPay = canPay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderTypeDesc() {
		return orderTypeDesc;
	}
	public void setOrderTypeDesc(String orderTypeDesc) {
		this.orderTypeDesc = orderTypeDesc;
	}
	public String getIsUseGiftCard() {
		return isUseGiftCard;
	}
	public void setIsUseGiftCard(String isUseGiftCard) {
		this.isUseGiftCard = isUseGiftCard;
	}
	public String getGiftCardBalance() {
		return giftCardBalance;
	}
	public void setGiftCardBalance(String giftCardBalance) {
		this.giftCardBalance = giftCardBalance;
	}
	public String getGiftCardAmount() {
		return giftCardAmount;
	}
	public void setGiftCardAmount(String giftCardAmount) {
		this.giftCardAmount = giftCardAmount;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getNoticeInfo() {
		return noticeInfo;
	}
	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}
	public PayType getSelectPayment() {
		return selectPayment;
	}
	public void setSelectPayment(PayType selectPayment) {
		this.selectPayment = selectPayment;
	}
	public List<Pay> getPayment() {
		return payment;
	}
	public void setPayment(List<Pay> payment) {
		this.payment = payment;
	}
	public List<DeliveryVo> getDelivery() {
		return delivery;
	}
	public void setDelivery(List<DeliveryVo> delivery) {
		this.delivery = delivery;
	}
	public String getIsProductCod() {
		return isProductCod;
	}
	public void setIsProductCod(String isProductCod) {
		this.isProductCod = isProductCod;
	}
	public Receive getReceive() {
		return receive;
	}
	public void setReceive(Receive receive) {
		this.receive = receive;
	}
	public List<Receive> getReceiveList() {
		return receiveList;
	}
	public void setReceiveList(List<Receive> receiveList) {
		this.receiveList = receiveList;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	public List<PriceShow> getPriceShow() {
		return priceShow;
	}
	public void setPriceShow(List<PriceShow> priceShow) {
		this.priceShow = priceShow;
	} 
	
	
}
