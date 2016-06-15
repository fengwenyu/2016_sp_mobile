package com.shangpin.pay.bo;

import java.io.Serializable;

public class APPPayCommonBo implements Serializable  {
	
	private static final long serialVersionUID = 7675003315033313325L;
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

	/** 用户IP */
	private String customerIp;
	private String ext;
	private String openId;
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
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public APPPayCommonBo(String gateway, String orderNo, String subject,
			String body, String totalFee, String customerIp, String ext) {
		super();
		this.gateway = gateway;
		this.orderNo = orderNo;
		this.subject = subject;
		this.body = body;
		this.totalFee = totalFee;
		this.customerIp = customerIp;
		this.ext = ext;
	
	}
	public APPPayCommonBo(String gateway, String orderNo, String subject,
			String body, String totalFee, String customerIp, String ext, String openId) {
		super();
		this.gateway = gateway;
		this.orderNo = orderNo;
		this.subject = subject;
		this.body = body;
		this.totalFee = totalFee;
		this.customerIp = customerIp;
		this.ext = ext;
		this.openId=openId;
	}
	
}
