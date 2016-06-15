/**
 * 
 */
package com.shangpin.wechat.bo.base;

/**
 * @author ZRS
 *
 */
public class TemplateMsgDataInfo {
	private TemplateMsgDataModel first;
	private TemplateMsgDataModel remark;
	
	public TemplateMsgDataModel getFirst() {
		return first;
	}
	public void setFirst(TemplateMsgDataModel first) {
		this.first = first;
	}
	
	public void setFirst(String first) {		
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(first);
		this.first = templateMsgDataModel;
	}
	public TemplateMsgDataModel getRemark() {
		return remark;
	}
	public void setRemark(TemplateMsgDataModel remark) {
		this.remark = remark;
	}
	
	public void setRemark(String remark) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(remark);
		this.remark = templateMsgDataModel;
	}
	
}
