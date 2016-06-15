package com.shangpin.wechat.bo.base;

import java.io.Serializable;
import java.util.List;

public class QueryPoiListResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String errcode;        
	private String errmsg;         
	private List<PoiInfo> business_list;  
	private String total_count;
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
	public List<PoiInfo> getBusiness_list() {
		return business_list;
	}
	public void setBusiness_list(List<PoiInfo> business_list) {
		this.business_list = business_list;
	}
	public String getTotal_count() {
		return total_count;
	}
	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}   
	
	

}
