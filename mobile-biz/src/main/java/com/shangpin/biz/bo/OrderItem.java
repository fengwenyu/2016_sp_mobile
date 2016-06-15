package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 订单项:单个订单的所有信息
 *
 */
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String __type;
	private String cancancel;
	private String canconfirmgoods;
	private String canpay;
	private String date;
	private String discountamount;
	private String expirydate;
	private String freight;
	private String freightdescription;
	private String giftbardbalance;
	private String giftcardamount;
	private String iscod;
	private String isusablegiftcardpay;
	private String isEverUsedGiftCard;
	private String isusegiftcard;
	private String mainorderno;
	private String onlineamount;
	private String formatamount;
	private List<Order> order;
	private String payamount;
	private String paystatus;
	private String paytypechildid;
	private String paytypechildname;
	private String paytypeid;
	private String paytypename;
	private String status;
	private String statusname;
	private String totalamount;
	private String postArea;
	private String payCategory;// 1国内2 国外 3 分账
	private String internalAmount;//分账金额

	public String get__type() {
		return __type;
	}

	public void set__type(String __type) {
		this.__type = __type;
	}

	public String getPayamount() {
		return payamount;
	}

	public void setPayamount(String payamount) {
		this.payamount = payamount;
	}

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}

	public String getPaytypechildid() {
		return paytypechildid;
	}

	public void setPaytypechildid(String paytypechildid) {
		this.paytypechildid = paytypechildid;
	}

	public String getPaytypechildname() {
		return paytypechildname;
	}

	public void setPaytypechildname(String paytypechildname) {
		this.paytypechildname = paytypechildname;
	}

	public String getPaytypeid() {
		return paytypeid;
	}

	public void setPaytypeid(String paytypeid) {
		this.paytypeid = paytypeid;
	}

	public String getPaytypename() {
		return paytypename;
	}

	public void setPaytypename(String paytypename) {
		this.paytypename = paytypename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public String getCancancel() {
		return cancancel;
	}

	public void setCancancel(String cancancel) {
		this.cancancel = cancancel;
	}

	public String getCanconfirmgoods() {
		return canconfirmgoods;
	}

	public void setCanconfirmgoods(String canconfirmgoods) {
		this.canconfirmgoods = canconfirmgoods;
	}

	public String getCanpay() {
		return canpay;
	}

	public void setCanpay(String canpay) {
		this.canpay = canpay;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDiscountamount() {
		return discountamount;
	}

	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getFreightdescription() {
		return freightdescription;
	}

	public void setFreightdescription(String freightdescription) {
		this.freightdescription = freightdescription;
	}

	public String getGiftbardbalance() {
		return giftbardbalance;
	}

	public void setGiftbardbalance(String giftbardbalance) {
		this.giftbardbalance = giftbardbalance;
	}

	public String getGiftcardamount() {
		return giftcardamount;
	}

	public void setGiftcardamount(String giftcardamount) {
		this.giftcardamount = giftcardamount;
	}

	public String getIscod() {
		return iscod;
	}

	public void setIscod(String iscod) {
		this.iscod = iscod;
	}

	public String getIsusablegiftcardpay() {
		return isusablegiftcardpay;
	}

	public void setIsusablegiftcardpay(String isusablegiftcardpay) {
		this.isusablegiftcardpay = isusablegiftcardpay;
	}

	public String getIsusegiftcard() {
		return isusegiftcard;
	}

	public void setIsusegiftcard(String isusegiftcard) {
		this.isusegiftcard = isusegiftcard;
	}

	public String getMainorderno() {
		return mainorderno;
	}

	public void setMainorderno(String mainorderno) {
		this.mainorderno = mainorderno;
	}

	public String getOnlineamount() {
		return onlineamount;
	}

	public void setOnlineamount(String onlineamount) {
		this.onlineamount = onlineamount;
	}

	public String getFormatamount() {
		 String[] aa= this.onlineamount.split("\\.");
		 formatamount=aa[0];
		return formatamount;
	}

	public void setFormatamount(String formatamount) {
		this.formatamount = formatamount;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public String getPostArea() {
		return postArea;
	}

	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}

	public String getIsEverUsedGiftCard() {
		return isEverUsedGiftCard;
	}

	public void setIsEverUsedGiftCard(String isEverUsedGiftCard) {
		this.isEverUsedGiftCard = isEverUsedGiftCard;
	}

	public String getPayCategory() {
		return payCategory;
	}

	public void setPayCategory(String payCategory) {
		this.payCategory = payCategory;
	}

	public String getInternalAmount() {
		return internalAmount;
	}

	public void setInternalAmount(String internalAmount) {
		this.internalAmount = internalAmount;
	}
}
