package com.shangpin.pay.bo;

import java.io.Serializable;

public class CommonPayBackBo implements Serializable {

	private static final long serialVersionUID = -7154297369818283834L;
	/**
     * 支付状态
     */
    private String payStatus;
    /**
     * 交易号 支付平台生成
     */
    private String tradeNo;

    /**
     * 汇率转换之后的金额(如果没有该值为totalFee)
     */
    private String rateFee;
    /**
     * 通知时间
     */
    private String notifyTime;

    /**
     * 交易创建时间
     */
    private String createTime;
    /**
     * 支付号（银联支付短信验证码时生成，返回前端系统，后续正式交易传入，其他平台不需要传入）
     */
    private String payNo;
    /**
     * 订单号 最长32位
     */
    private String orderNo;

    /**
     * 订单交易金额
     */
    private String totalFee;

    /**
     * 交易币种
     */
    private String currency;

    /**
     * 一级分类,传入网关类型
     */
    private String gateway;

    /**
     * 二级分类 如：支付宝/中信快捷/中信网银
     */
    private String secondLevel;
    /**
     * 分期期数 如1期 3期 6期(针对信用卡分期)
     */
    private String thirdLevel;
    
    /**
     * 支付请求扩展字段 以"key:value,key:value,..."形式表示<br/>
     * 针对特殊需要如：wap，pc支付<br/>
     * spdb的transType， 招商银行返回参考号refCode，
     */
    private String extend;
    /**
     * 银行通知反馈的交易时间
     */
    private String tradeDate;
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getRateFee() {
		return rateFee;
	}
	public void setRateFee(String rateFee) {
		this.rateFee = rateFee;
	}
	public String getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
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
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
   
}
