package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class CardPattern implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String card_type;
	
    private CardGroupon groupon;
    private CardGift gift;
    private CardDiscount discount;
    private CardCash cash;
    private CardGeneralCoupon general_coupon;
    
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public CardGroupon getGroupon() {
		return groupon;
	}
	public void setGroupon(CardGroupon groupon) {
		this.groupon = groupon;
	}
	public CardGift getGift() {
		return gift;
	}
	public void setGift(CardGift gift) {
		this.gift = gift;
	}
	public CardDiscount getDiscount() {
		return discount;
	}
	public void setDiscount(CardDiscount discount) {
		this.discount = discount;
	}
	public CardCash getCash() {
		return cash;
	}
	public void setCash(CardCash cash) {
		this.cash = cash;
	}
	public CardGeneralCoupon getGeneral_coupon() {
		return general_coupon;
	}
	public void setGeneral_coupon(CardGeneralCoupon general_coupon) {
		this.general_coupon = general_coupon;
	}
    
}
