package com.shangpin.biz.bo;

import java.io.Serializable;

public class AppMsg implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5852282643063567873L;
	private String msgId;
	private String messageType;//1评论   2订单详情  3退货   4换货    暂选择 6全部发货  9交易完成
	private String readFlag;// 0未读消息  1已读消息
	private String message;//用户消息描述
	private String time;
	private String messageContent;
	private String mainOrderId;//主订单号
	private String orderId;//子单号
	private String isSplitOrder;//是否拆单
	private String typeFlag;//消息类型，1对应评价，2对应订单详情,3进退货详情的wap
	private String title;//消息标题
	private String imageUrl;//商品图片url
	private String icon;//图标
	private String refConten;//type为3时对应的退货详情的url
	public String getMainOrderId() {
		return mainOrderId;
	}
	public void setMainOrderId(String mainOrderId) {
		this.mainOrderId = mainOrderId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getIsSplitOrder() {
		return isSplitOrder;
	}
	public void setIsSplitOrder(String isSplitOrder) {
		this.isSplitOrder = isSplitOrder;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getTypeFlag() {
		return typeFlag;
	}
	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getRefConten() {
		return refConten;
	}
	public void setRefConten(String refConten) {
		this.refConten = refConten;
	}
}
