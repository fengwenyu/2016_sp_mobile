package com.shangpin.wireless.api.domain;



import java.math.BigDecimal;
import java.util.Date;



public class WeixinPayOrder implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6139841663140233992L;
	/** 主键ID **/
	private int id;
	//交易模式 1是即时到帐，其他保留
	private String tradeMode;
	//支付结果 0成功 其他保留
	private String tradeState ;
	//支付结果信息 支付成功时为空
	private String  payInfo;
	//付款银行 银行类型在微信中使用WX
	private String bankType;
	//银行订单号
	private String bankBillNo;
	//总金额
	private BigDecimal totalFee;
	//币种默认值1人民币
	private String feeType;
	//财付通交易订单号
	private String transId;
	//商户订单号
	private String orderNo;
	//支付完成时间
	private Date timeEnd;
	
	//支付该笔订单用户id
	private String openId;
	//支付结果通知id
	private String notifyId;
	//买家别名对应买家帐号的一个加密串
	private String buyerAlias;
	//运费
	private BigDecimal transportFee;
	//商品价
	private BigDecimal productFee;
	//折扣
	private BigDecimal discount;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTradeMode() {
		return tradeMode;
	}

	public void setTradeMode(String tradeMode) {
		this.tradeMode = tradeMode;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankBillNo() {
		return bankBillNo;
	}

	public void setBankBillNo(String bankBillNo) {
		this.bankBillNo = bankBillNo;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getBuyerAlias() {
		return buyerAlias;
	}

	public void setBuyerAlias(String buyerAlias) {
		this.buyerAlias = buyerAlias;
	}

	public BigDecimal getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(BigDecimal transportFee) {
		this.transportFee = transportFee;
	}

	public BigDecimal getProductFee() {
		return productFee;
	}

	public void setProductFee(BigDecimal productFee) {
		this.productFee = productFee;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	
	
	

}
