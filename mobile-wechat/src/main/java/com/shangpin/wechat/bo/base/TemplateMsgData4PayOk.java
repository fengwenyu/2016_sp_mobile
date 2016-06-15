/**
 * 
 */
package com.shangpin.wechat.bo.base;

/**
 * @author ZRS
 *
 */
public class TemplateMsgData4PayOk extends TemplateMsgDataInfo {
	
	private TemplateMsgDataModel orderMoneySum;
	private TemplateMsgDataModel orderProductName;
	private TemplateMsgDataModel Remark;
	
	public TemplateMsgDataModel getRemark() {
		return Remark;
	}
	public void setRemark(TemplateMsgDataModel Remark) {
		this.Remark = Remark;
	}
	public void setRemark(String Remark) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(Remark);
		this.Remark = templateMsgDataModel;
	}
	public TemplateMsgDataModel getOrderMoneySum() {
		return orderMoneySum;
	}
	public void setOrderMoneySum(TemplateMsgDataModel orderMoneySum) {
		this.orderMoneySum = orderMoneySum;
	}
	public void setOrderMoneySum(String orderMoneySum) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(orderMoneySum);
		this.orderMoneySum = templateMsgDataModel;
	}
	public TemplateMsgDataModel getOrderProductName() {
		
		return orderProductName;
	}
	public void setOrderProductName(TemplateMsgDataModel orderProductName) {
		this.orderProductName = orderProductName;
	}
	public void setOrderProductName(String orderProductName) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(orderProductName);
		this.orderProductName = templateMsgDataModel;
	}
	
	
	
}
