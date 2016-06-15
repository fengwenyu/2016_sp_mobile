package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class CreateCardResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String errcode;// 	错误码，0为正常。
	private String errmsg;// 	错误信息。
	private String card_id;// 	卡券ID。 
	
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
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

}
