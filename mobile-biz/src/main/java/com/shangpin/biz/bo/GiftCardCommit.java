package com.shangpin.biz.bo;

import java.io.Serializable;

public class GiftCardCommit implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderid;
	private String date;
	private String amount;
	private String cod;
	private String codmsg;
	private String objectcounts;
	private String carriage;
	private String skucounts;
	private String giftcard;
	private String giftcardbalance;
	private String type;
	private String systime;
	private String expiretime;
	private String payCategory;


	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getCodmsg() {
		return codmsg;
	}
	public void setCodmsg(String codmsg) {
		this.codmsg = codmsg;
	}
	public String getObjectcounts() {
		return objectcounts;
	}
	public void setObjectcounts(String objectcounts) {
		this.objectcounts = objectcounts;
	}
	public String getCarriage() {
		return carriage;
	}
	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}
	public String getSkucounts() {
		return skucounts;
	}
	public void setSkucounts(String skucounts) {
		this.skucounts = skucounts;
	}
	public String getGiftcard() {
		return giftcard;
	}
	public void setGiftcard(String giftcard) {
		this.giftcard = giftcard;
	}
	public String getGiftcardbalance() {
		return giftcardbalance;
	}
	public void setGiftcardbalance(String giftcardbalance) {
		this.giftcardbalance = giftcardbalance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSystime() {
		return systime;
	}
	public void setSystime(String systime) {
		this.systime = systime;
	}
	public String getExpiretime() {
		return expiretime;
	}
	public void setExpiretime(String expiretime) {
		this.expiretime = expiretime;
	}

	public String getPayCategory() {
		return payCategory;
	}

	public void setPayCategory(String payCategory) {
		this.payCategory = payCategory;
	}
}
