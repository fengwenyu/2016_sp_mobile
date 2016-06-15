package com.shangpin.wechat.bo.base;

import java.io.Serializable;
import java.util.List;

public class QueryBatchCardResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String errcode;      
	private String errmsg;       
	private List<String> card_id_list; 
	private String total_num;
	
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
	public List<String> getCard_id_list() {
		return card_id_list;
	}
	public void setCard_id_list(List<String> card_id_list) {
		this.card_id_list = card_id_list;
	}
	public String getTotal_num() {
		return total_num;
	}
	public void setTotal_num(String total_num) {
		this.total_num = total_num;
	}

}
