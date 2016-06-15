package com.shangpin.pay.bo;


/**
 * 微信支付请求参数业务对象
 * User: liling
 * Date: 14-11-10
 * Time: 下午3:23
 */
public class WXPayBo {
	
	/**签名方式（必填）*/
	private String signType;
	
	/**商户订单号,类型String(32)（必填）*/
	private String orderId;
	
	/**商品描述,类型String(127)（必填）*/
	private String productName;
	
	/**订单总金额，单位为分，不能带小数点（必填）*/
	private String totalFee;
	
	/**订单生成时间，格式为yyyyMMddHHmmss（非必填）*/
	private String orderTimeStart;
	
	/**订单失效时间，格式为yyyyMMddHHmmss非必填）*/
	private String orderTimeout;
	
	/**订单生成的机器ip（必填）*/
	private String ip;
	
	/**银行通道类型，字符串类型，固定为"WX"（必填）*/
	private String bankType;
	
	/**支付币种，字符串类型。默认值为1（人民币）*/
	private String feeType;
	
	/**物流费用，单位为分（非必填），如果有值必须保证物流费用+商品费用=订单总金额*/
	private String transportFee;
	
	/**商品费用（非必填）*/
	private String productFee;
	
	/**商品标记（非必填），优惠券时可能用到*/
	private String goodsTag;
	
	/**附加数据，原样返回（非必填*/
	private String attach;

	
	/**notify异步通知url（必填）*/
	private String notifyUrl;
	
	private String openId;
	private String gateway;
	private String returnUrl;
	private String referer;
	private String ext;
	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getOrderTimeStart() {
		return orderTimeStart;
	}

	public void setOrderTimeStart(String orderTimeStart) {
		this.orderTimeStart = orderTimeStart;
	}

	public String getOrderTimeout() {
		return orderTimeout;
	}

	public void setOrderTimeout(String orderTimeout) {
		this.orderTimeout = orderTimeout;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}

	public String getProductFee() {
		return productFee;
	}

	public void setProductFee(String productFee) {
		this.productFee = productFee;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}


	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
	
}	
	
