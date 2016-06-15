package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class ConsumeCardCodeResult implements Serializable{

	private static final long serialVersionUID = 1L;
	private String errcode;// 	错误码。
	private String errmsg;// 	错误信息。
	private String openid;// 	用户在该公众号内的唯一身份标识。
	private CardInfo card;
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public CardInfo getCard() {
		return card;
	}
	public void setCard(CardInfo card) {
		this.card = card;
	}
	
	@Override
	public String toString() {
		return "errcode:" + errcode + ",errmsg:" + errmsg + ",openid:" + openid
				+ ",card:{" + (card == null ? "" : "card_id:"+card.getCard_id()) + "}";
	}	

}
