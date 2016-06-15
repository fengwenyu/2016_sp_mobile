package com.shangpin.pay.bo;



/**
 * 微信支付请求数据BO
 * User: liling
 * Date: 14-11-10
 * Time: 下午3:23
 */
public class WXPayDataBo {
	/**公共号身份唯一标识*/
	private String appId;
	/**订单详情扩张字符串package*/
	private String packageStr;
	/**时间戳*/
	private String timeStamp;
	/**随机字符串*/
	private String nonceStr;
	/**签名*/
	private String paySign;
	/**签名方式*/
	private String signType;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getPackageStr() {
		return packageStr;
	}
	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}

}
