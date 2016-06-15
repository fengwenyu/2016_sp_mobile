package com.shangpin.biz.bo.message;

import java.io.Serializable;


/**
 * 退货消息的结构体
 * @author qinyingchun
 *
 */
public class ReturnGoodsMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String desc;
	private String url;
	private String imageUrl;
	private String returnId;
	private String picNo;
	
	
	public String getReturnId() {
		return returnId;
	}
	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}
	public String getPicNo() {
		return picNo;
	}
	public void setPicNo(String picNo) {
		this.picNo = picNo;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
