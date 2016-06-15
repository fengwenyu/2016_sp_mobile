package com.shangpin.biz.bo;

import java.io.Serializable;

import com.shangpin.utils.DateUtils;

public class GiftCardRecordListItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String childOrderId;
	private String giftCardId;
	private String typeDesc;
	private String type;
	private String statusDesc;
	private String status;
	private String date;
	private String faceValue;
	private String payAmount;
	private String expenseAmount;
	private String mainBalance;
	private String pic;
	/**格式化后的date YYY-MM-dd*/
	@SuppressWarnings("unused")
	private String fmtDate;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getChildOrderId() {
		return childOrderId;
	}

	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
	}

	public String getGiftCardId() {
		return giftCardId;
	}

	public void setGiftCardId(String giftCardId) {
		this.giftCardId = giftCardId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public String getMainBalance() {
		return mainBalance;
	}

	public void setMainBalance(String mainBalance) {
		this.mainBalance = mainBalance;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getFmtDate() {
		if(getDate()!=null){
			return DateUtils.getDateStr(getDate());
		}
		return null;
	}


}
