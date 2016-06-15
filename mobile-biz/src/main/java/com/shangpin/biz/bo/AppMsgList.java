package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class AppMsgList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3784247012208217516L;
	private List<AppMsg> list;
	private String unreadMessage = "";
	private String messageCount = "";
	public List<AppMsg> getList() {
		return list;
	}
	public void setList(List<AppMsg> list) {
		this.list = list;
	}
	public String getUnreadMessage() {
		return unreadMessage;
	}
	public void setUnreadMessage(String unreadMessage) {
		this.unreadMessage = unreadMessage;
	}
	public String getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(String messageCount) {
		this.messageCount = messageCount;
	}
	
}
