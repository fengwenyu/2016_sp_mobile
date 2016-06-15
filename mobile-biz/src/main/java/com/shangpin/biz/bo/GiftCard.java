package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 礼品卡实体类
 * @author fengwenyu
 *
 */
public class GiftCard implements Serializable{
	private String userId;//用户id 
	private String giftOrder;//礼品卡订单号
	private String giftCardId;//礼品卡唯一标识号
	private String buyerName;//购买人账户
	private String sendPhoneNum;//赠送人手机号
	private String sendTime;//赠送时间的时间戳 
	private String wishMsg;//祝福语
	private String wishPic;//祝福图片url
	private String faceValue;//礼品卡面额
	
	
	public String getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGiftOrder() {
		return giftOrder;
	}
	public void setGiftOrder(String giftOrder) {
		this.giftOrder = giftOrder;
	}
	public String getGiftCardId() {
		return giftCardId;
	}
	public void setGiftCardId(String giftCardId) {
		this.giftCardId = giftCardId;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getSendPhoneNum() {
		return sendPhoneNum;
	}
	public void setSendPhoneNum(String sendPhoneNum) {
		this.sendPhoneNum = sendPhoneNum;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getWishMsg() {
		return wishMsg;
	}
	public void setWishMsg(String wishMsg) {
		this.wishMsg = wishMsg;
	}
	public String getWishPic() {
		return wishPic;
	}
	public void setWishPic(String wishPic) {
		this.wishPic = wishPic;
	}
	
	
}
