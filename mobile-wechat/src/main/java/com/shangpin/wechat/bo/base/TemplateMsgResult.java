/**
 * 
 */
package com.shangpin.wechat.bo.base;

import java.io.Serializable;

/**
 * @author ZRS
 *
 */
public class TemplateMsgResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String errcode;// 	错误码，0为正常。
	private String errmsg;// 	错误信息。
	private String msgid;//     消息id
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
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	
	
}
