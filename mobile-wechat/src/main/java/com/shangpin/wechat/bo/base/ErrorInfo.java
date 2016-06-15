package com.shangpin.wechat.bo.base;

import java.io.Serializable;

/**调用微信接口返回的错误信息（code,msg）
 * @author qinyingchun
 *
 */
public class ErrorInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errcode;
	private String errmsg;
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
	

}
