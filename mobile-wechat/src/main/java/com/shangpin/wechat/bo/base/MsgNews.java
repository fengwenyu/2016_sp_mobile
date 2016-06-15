package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class MsgNews implements Serializable{
	private static final long serialVersionUID = 1L;
	private String touser;
	private String msgtype;
	private News news;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	

}
