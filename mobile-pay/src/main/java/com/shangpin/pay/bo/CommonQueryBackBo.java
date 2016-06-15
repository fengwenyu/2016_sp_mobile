package com.shangpin.pay.bo;

import java.io.Serializable;

public class CommonQueryBackBo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6964012620915825819L;

	/**
     * 订单号
     */
    private String orderNo;

    /**
     * 支付号
     */
    private String payNo;

    /**
     * 支付网关
     */
    private String gateway;
    /**
     * 支付状态
     */
    private String payStatus;
    /**
     * 银行交易时间
     */
    private String tradeDate;
    /**
     * 银行交易流水
     */
    private String tradeNo;
    /**
     * 币种
     */
    private String currency;

    /**
     * 总金额
     */
    private String totalFee;

    /**
     * 银行列表
     */
    private String secondLevel;

    /**
     * 分期期数
     */
    private String thirdLevel;
    /**附加信息*/
    private String extend;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getSecondLevel() {
		return secondLevel;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	public String getThirdLevel() {
		return thirdLevel;
	}
	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
    
    
}
