package com.shangpin.biz.bo.message;

import java.io.Serializable;

/**
 * 订单消息实体
 * @author qinyingchun
 *
 */
public class OrderMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1900008062954965019L;
	
	private String mainOrderNo;
	private String orderNo;
	private String isSplit;
	private String imageUrl;
	private String desc;
	private String picNo;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getMainOrderNo() {
		return mainOrderNo;
	}
	public void setMainOrderNo(String mainOrderNo) {
		this.mainOrderNo = mainOrderNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getIsSplit() {
		return isSplit;
	}
	public void setIsSplit(String isSplit) {
		this.isSplit = isSplit;
	}
	public String getPicNo() {
		return picNo;
	}
	public void setPicNo(String picNo) {
		this.picNo = picNo;
	}
}
