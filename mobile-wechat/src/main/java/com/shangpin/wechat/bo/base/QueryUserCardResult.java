package com.shangpin.wechat.bo.base;

import java.io.Serializable;
import java.util.List;

public class QueryUserCardResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String errcode;
	private String errmsg;
	private List<CardInfo> card_list;
	
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
	public List<CardInfo> getCard_list() {
		return card_list;
	}
	public void setCard_list(List<CardInfo> card_list) {
		this.card_list = card_list;
	}
	 
	 
}
