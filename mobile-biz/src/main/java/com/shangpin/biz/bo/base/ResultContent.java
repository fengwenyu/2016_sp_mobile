package com.shangpin.biz.bo.base;

import java.io.Serializable;

public class ResultContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6629931741367342807L;
	
	private String responseCode;
	private String resopnseMsg;
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResopnseMsg() {
		return resopnseMsg;
	}
	public void setResopnseMsg(String resopnseMsg) {
		this.resopnseMsg = resopnseMsg;
	}
	

}
