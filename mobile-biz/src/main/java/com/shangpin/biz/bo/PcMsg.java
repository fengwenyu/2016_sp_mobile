package com.shangpin.biz.bo;

import java.io.Serializable;

import com.shangpin.biz.utils.DataUtils;

public class PcMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5106191752453809L;
	private String messageId;
	private String messageType;//1评论   2订单详情  3退货   4换货    暂选择 6全部发货  9交易完成
	private String dataStatus;
	private String readStatus;// 0未读消息  1已读消息
	private String messageName;//用户消息描述
	private String createDate;
	private String userId;
	private String messageContent;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
	    createDate =String.valueOf(DataUtils.formatDateStringToInt(createDate));
	    if (createDate.length()<13) {
	        createDate="";
        }
		this.createDate = createDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

}
