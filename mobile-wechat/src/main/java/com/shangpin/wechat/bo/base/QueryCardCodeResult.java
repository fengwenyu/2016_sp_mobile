package com.shangpin.wechat.bo.base;

import java.io.Serializable;

/**
 * 查询Code接口返回信息
 * @author zrs
 * 
 */
public class QueryCardCodeResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String errcode;// 	错误码
	private String errmsg;// 	错误信息
	private String openid;// 	用户openid
	private CardInfo card;
	private String  can_consume;
	private String user_card_status;
	
	
	public String getCan_consume() {
		return can_consume;
	}
	public void setCan_consume(String can_consume) {
		this.can_consume = can_consume;
	}
	public String getUser_card_status() {
		return user_card_status;
	}
	public void setUser_card_status(String user_card_status) {
		this.user_card_status = user_card_status;
	}
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
				+ ",card:{" + (card == null ? "" : card.toString()) +",can_consume:"+can_consume+",user_card_status:"+user_card_status+ "}";
	}
	
	
}
