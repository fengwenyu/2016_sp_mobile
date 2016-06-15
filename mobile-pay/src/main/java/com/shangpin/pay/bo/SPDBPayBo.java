package com.shangpin.pay.bo;

import java.io.Serializable;

public class SPDBPayBo implements Serializable {
	private static final long serialVersionUID = -2313151552171710108L;
	/** 网关枚举 值请参考网关枚举列表 */
	private String gateway;
	/** 订单号 最长32位 */
	private String orderNo;
	/** 交易名称 显示于第三方平台支付页面，如，“18K金项链等2件”，小于256字 和支付组确认此参数无用*/
	private String subject;
	/** 交易描述，显示于第三方平台支付页面，如，“18K金项链：1，皮绳：1”，小于400字，可为空,和支付组确认此参数无用 */
	private String body;
	/** 交易金额 */
	private String totalFee;
	/** 支付成功以后的通知地址 */
	private String notifyUrl;
	/** 支付成功以后的跳转地址 可以为空 */
	private String returnUrl;
	/** 交易币种 CNY人民币 HKD港币 USD美元 */
	private String currency;
	/** 支付超时时间(以小时算) 整数值 如1、2、3 */
	private String timeout;
	/** 用户IP */
	private String customerIp;
	private String ext;

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

}
