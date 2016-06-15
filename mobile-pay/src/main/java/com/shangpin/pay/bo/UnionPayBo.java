package com.shangpin.pay.bo;


/**
 * 银联支付请求参数业务对象
 * User: liling
 * Date: 14-11-10
 * Time: 下午3:23
 */
public class UnionPayBo {
	
	/**订单超时时间日期格式yyyyMMddHHmmss（非必填）*/
	private String orderTimeout;
	
	/**交易币种（非必填）*/
	private String orderCurrency;
	
	/**订单描述（非必填）*/
	private String orderDescription;
	
	// -------------------以下为必选---------------------
	/**交易类型01 消费; 02 预授权（代码中设置了为空时默认）*/	
	private String transType;	
	
	/**交易开始日期时间,日期格式yyyyMMddHHmmss（必填）*/
	private String orderTimeStart;
	
	/**商户订单号（必填）*/
	private String orderNumber;
	
	/**交易金额（必填）*/
	private String totalFee;
	
	/**callback同步通知url（必填）*/
	private String callbackUrl;
	
	/**notify异步通知url（必填）*/
	private String notifyUrl;
	private String gateWay;
	private String productName;
	public String getOrderTimeout() {
		return orderTimeout;
	}
	public void setOrderTimeout(String orderTimeout) {
		this.orderTimeout = orderTimeout;
	}
	public String getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getOrderTimeStart() {
		return orderTimeStart;
	}
	public void setOrderTimeStart(String orderTimeStart) {
		this.orderTimeStart = orderTimeStart;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getGateWay() {
		return gateWay;
	}
	public void setGateWay(String gateWay) {
		this.gateWay = gateWay;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	

}
