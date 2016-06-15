package com.shangpin.pay.bo;

import java.io.Serializable;

public class WeChatPayBo implements Serializable {

	private static final long serialVersionUID = -1006788497557146365L;

	private String payCode;
	private String payMsg;
	private String appId;
	private String nonceStr;
	private String dataPackage;
	private String partnerId;
	private String prepayId;
	private String sign;
	private String timestamp;

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getPayMsg() {
		return payMsg;
	}

	public void setPayMsg(String payMsg) {
		this.payMsg = payMsg;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getDataPackage() {
		return dataPackage;
	}

	public void setDataPackage(String dataPackage) {
		this.dataPackage = dataPackage;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
