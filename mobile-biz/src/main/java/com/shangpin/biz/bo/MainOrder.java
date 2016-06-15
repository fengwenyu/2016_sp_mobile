package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 订单项:主订单的所有信息
 *
 */
public class MainOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	private PayType selectPayment;
	private PayType payment;
	private String payAmount;       
	private List<Order> orderList;
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

	public PayType getSelectPayment() {
		return selectPayment;
	}
	public void setSelectPayment(PayType selectPayment) {
		this.selectPayment = selectPayment;
	}
	
	public PayType getPayment() {
		return payment;
	}
	public void setPayment(PayType payment) {
		this.payment = payment;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}            
          

}
