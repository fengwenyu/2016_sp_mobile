/**
 * 
 */
package com.shangpin.wechat.bo.base;

/**
 * @author ZRS
 *
 */
public class TemplateMsgData4SubmitOrder extends TemplateMsgDataInfo {
	
	private TemplateMsgDataModel orderID;
	private TemplateMsgDataModel orderMoneySum;
	
	private TemplateMsgDataModel backupFieldName;
	private TemplateMsgDataModel backupFieldData;


	

	public TemplateMsgDataModel getOrderID() {
		return orderID;
	}

	public void setOrderID(TemplateMsgDataModel orderID) {
		this.orderID = orderID;
	}

	public TemplateMsgDataModel getOrderMoneySum() {
		return orderMoneySum;
	}

	public void setOrderMoneySum(TemplateMsgDataModel orderMoneySum) {
		this.orderMoneySum = orderMoneySum;
	}

	public TemplateMsgDataModel getBackupFieldName() {
		return backupFieldName;
	}

	public void setBackupFieldName(TemplateMsgDataModel backupFieldName) {
		this.backupFieldName = backupFieldName;
	}

	public TemplateMsgDataModel getBackupFieldData() {
		return backupFieldData;
	}

	public void setBackupFieldData(TemplateMsgDataModel backupFieldData) {
		this.backupFieldData = backupFieldData;
	}

	public void setOrderID(String orderID) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(orderID);
		this.orderID = templateMsgDataModel;
	}

	public void setOrderMoneySum(String orderMoneySum) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(orderMoneySum);
		this.orderMoneySum = templateMsgDataModel;
	}
	public void setBackupFieldName(String backupFieldName) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(backupFieldName);
		this.backupFieldName = templateMsgDataModel;
	}
	public void setBackupFieldData(String backupFieldData) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(backupFieldData);
		this.backupFieldData = templateMsgDataModel;
	}

	
}
