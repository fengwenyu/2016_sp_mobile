package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 海外提交订单参数设置（此类仅用做海外订单提交对象）
 * 
 * @author zghw
 *
 */
public class OverseasOrderParam implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 用户Id */
	private String userid;
	/** 商品sku */
	private String skuId;
	/** 收货地址id */
	private String addrid;
	/** 发票地址id */
	private String invoiceaddrid;
	/** 是否开发票0：不开发票，1：开发票 */
	private String invoiceflag;
	/** 发票类型0：个人，1：公司 */
	private String invoicetype;
	/** 发票抬头 */
	private String invoicetitle;
	/** 发票内容 */
	private String invoicecontent;
	/** 使用优惠券类型无：传空；1：优惠券；2：优惠码 */
	private String couponflag;
	/** 优惠券或折扣码编号没有传”0“ */
	private String coupon;
	/** 配送发送1：工作日收货；2：工作日，节假日收货；3：双休，节假日收货 */
	private String express;
	/** 订单来源9：ios；18：安卓；19：M站订单渠道 */
	private String orderfrom;
	/** 购物车商品id多个用“|‘分开 */
	private String buysIds;
	/** 主支付方式编号 */
	private String paytypeid;
	/** 子支付方式编号 */
	private String paytypechildid;
	/** 订单类型尚品1，奥莱2 */
	private String ordertype;
	/** 是否使用礼品卡0：为不使用；1：使用 */
	private String isUseGiftCardPay;
	/**1：从购物车 2：立即购买*/
	private String orderSource;
	/**商品属于哪个地域1：国内 2：海外*/
	private String postArea;
	
	public OverseasOrderParam() {
	}

	/**
	 * 海外购最少配置参数设置
	 * 
	 * @param userId
	 * @param skuId
	 * @param addressId
	 * @param invoiceAddressId
	 * @param invoiceType
	 * @param invoiceTitle
	 * @param invoiceContent
	 * @param express
	 * @param buysIds
	 */
	public OverseasOrderParam(String userId, String skuId, String addressId, String invoiceAddressId,
			String invoiceFlag, String invoiceType, String invoiceTitle, String invoiceContent, String express,
			String buysIds, String payId, String payChildId, String couponflag, String coupon, String orderSource) {
		this.userid = userId;
		this.skuId = skuId;
		this.addrid = addressId;
		this.invoiceaddrid = invoiceAddressId;
		this.invoicetype = invoiceType;
		this.invoicetitle = invoiceTitle;
		this.invoicecontent = invoiceContent;
		this.express = express;
		this.buysIds = buysIds;
		this.invoiceflag = invoiceFlag;
		this.couponflag = couponflag;
		this.coupon = coupon;
		this.orderfrom = "19";
		this.ordertype = "1";
		this.isUseGiftCardPay = "0";
		this.paytypeid = payId;
		this.paytypechildid = payChildId;
		this.postArea="2";
		this.orderSource=orderSource;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getAddrid() {
		return addrid;
	}

	public void setAddrid(String addrid) {
		this.addrid = addrid;
	}

	public String getInvoiceaddrid() {
		return invoiceaddrid;
	}

	public void setInvoiceaddrid(String invoiceaddrid) {
		this.invoiceaddrid = invoiceaddrid;
	}

	public String getInvoiceflag() {
		return invoiceflag;
	}

	public void setInvoiceflag(String invoiceflag) {
		this.invoiceflag = invoiceflag;
	}

	public String getInvoicetype() {
		return invoicetype;
	}

	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}

	public String getInvoicetitle() {
		return invoicetitle;
	}

	public void setInvoicetitle(String invoicetitle) {
		this.invoicetitle = invoicetitle;
	}

	public String getInvoicecontent() {
		return invoicecontent;
	}

	public void setInvoicecontent(String invoicecontent) {
		this.invoicecontent = invoicecontent;
	}

	public String getCouponflag() {
		return couponflag;
	}

	public void setCouponflag(String couponflag) {
		this.couponflag = couponflag;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getOrderfrom() {
		return orderfrom;
	}

	public void setOrderfrom(String orderfrom) {
		this.orderfrom = orderfrom;
	}

	public String getBuysIds() {
		return buysIds;
	}

	public void setBuysIds(String buysIds) {
		this.buysIds = buysIds;
	}

	public String getPaytypeid() {
		return paytypeid;
	}

	public void setPaytypeid(String paytypeid) {
		this.paytypeid = paytypeid;
	}

	public String getPaytypechildid() {
		return paytypechildid;
	}

	public void setPaytypechildid(String paytypechildid) {
		this.paytypechildid = paytypechildid;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getIsUseGiftCardPay() {
		return isUseGiftCardPay;
	}

	public void setIsUseGiftCardPay(String isUseGiftCardPay) {
		this.isUseGiftCardPay = isUseGiftCardPay;
	}

	public String getPostArea() {
		return postArea;
	}

	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

}
