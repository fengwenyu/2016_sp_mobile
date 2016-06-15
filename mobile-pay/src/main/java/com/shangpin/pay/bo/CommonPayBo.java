package com.shangpin.pay.bo;

import java.io.Serializable;

public class CommonPayBo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4458983166741016404L;
	/**订单编号*/
	private String orderNo;
	/**订单标题*/
	private String subject;
	/**订单总金额*/
	private String totalFee;
	/**优惠金额*/
	private String discountAmount;
	/**币种,默认人民币*/
	private String currency;
	/**
	 * 交易超时时间 小时为单位*/
	private String timeOut;
	private String gateWay;
	private String notifyUrl;
	private String returnUrl;
	/**分期期数*/
	private Integer thirdLevel;
	
	/**支付平台下的二级银行<br/>
	 * 平安、北京
	 * */
	private String secondLevel;
	/**
	 * 微信支付的openId
	 */
	private String openId;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}
	public String getGateWay() {
		return gateWay;
	}
	public void setGateWay(String gateWay) {
		this.gateWay = gateWay;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public Integer getThirdLevel() {
		return thirdLevel;
	}
	public void setThirdLevel(Integer thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	public String getSecondLevel() {
		return secondLevel;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
}
