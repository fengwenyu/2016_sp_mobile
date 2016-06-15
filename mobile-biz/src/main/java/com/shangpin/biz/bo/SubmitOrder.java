package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: SubmitOrder 
* @Description: 提交订单接口参数实体类
* @author qinyingchun
* @date 2014年12月2日
* @version 1.0 
*/
public class SubmitOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String userid;
	private String addrid ;
	private String invoiceaddrid;
	private String invoiceflag ;
	private String invoicetype;
	private String invoicetitle;
	private String invoicecontent   ;
	private String couponflag ;
	private String coupon ;
	private String express ;
	private String orderfrom;
	private String buysIds;
	private String paytypeid;
	private String paytypechildid  ;
	private String ordertype;
	private String isUseGiftCardPay;
	private String orderSource;
	private String skuId;
	private String postArea;
	private String type;
	
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getPostArea() {
		return postArea;
	}
	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
