/**
 * 
 */
package com.shangpin.wechat.bo.base;

/**
 * @author ZRS
 *
 */
public class TemplateMsgData4RefundNotice extends TemplateMsgDataInfo {
	
	private TemplateMsgDataModel reason;
	private TemplateMsgDataModel refund;
	
	public TemplateMsgDataModel getReason() {
		return reason;
	}

	public void setReason(TemplateMsgDataModel reason) {
		this.reason = reason;
	}
	public void setReason(String reason) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(reason);
		this.reason = templateMsgDataModel;
	}

	public TemplateMsgDataModel getRefund() {
		return refund;
	}

	public void setRefund(TemplateMsgDataModel refund) {
		this.refund = refund;
	}
	public void setRefund(String refund) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(refund);
		this.refund = templateMsgDataModel;
	}
	
}
