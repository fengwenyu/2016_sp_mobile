package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class QueryCardDetailResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String errcode;      
	private String errmsg;       
	private CardPattern card;
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
	public CardPattern getCard() {
		return card;
	}
	public void setCard(CardPattern card) {
		this.card = card;
	} 
	
}
