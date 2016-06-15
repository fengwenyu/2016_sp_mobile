package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 领取接受页面的参数
 * @author 李永桥
 * 前缀 F代表发起人信息  L代表领取人信息
 */
public class ReceiveRequest implements Serializable {

	private static final long serialVersionUID = 3709863332959739759L;
	
	private String fspuId;//发起spuId
	private String forderId;//发起订单号
	private String fuserId;//发起人userid
	private String lskuId;//领取skuId
	private String luserId;//领取人userid
	private String laddressId;//领取地址id
	private String mailAddress;//邮箱
	private String phone;//领取人手机号
	private String fspuNo;//发起人spuno
	private String lorderId;
	private String fspuSend; //用于用户手机信息
	public String getFspuSend() {
		return fspuSend;
	}
	public void setFspuSend(String fspuSend) {
		this.fspuSend = fspuSend;
	}
	public String getLorderId() {
		return lorderId;
	}
	public void setLorderId(String lorderId) {
		this.lorderId = lorderId;
	}
	public String getFspuId() {
		return fspuId;
	}
	public void setFspuId(String fspuId) {
		this.fspuId = fspuId;
	}
	public String getForderId() {
		return forderId;
	}
	public void setForderId(String forderId) {
		this.forderId = forderId;
	}
	public String getFuserId() {
		return fuserId;
	}
	public void setFuserId(String fuserId) {
		this.fuserId = fuserId;
	}
	public String getLskuId() {
		return lskuId;
	}
	public void setLskuId(String lskuId) {
		this.lskuId = lskuId;
	}
	public String getLuserId() {
		return luserId;
	}
	public void setLuserId(String luserId) {
		this.luserId = luserId;
	}
	public String getLaddressId() {
		return laddressId;
	}
	public void setLaddressId(String laddressId) {
		this.laddressId = laddressId;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFspuNo() {
		return fspuNo;
	}
	public void setFspuNo(String fspuNo) {
		this.fspuNo = fspuNo;
	}
	
	
	
	
}

