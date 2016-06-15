/**
 * 
 */
package com.shangpin.wechat.bo.base;

/**
 * @author ZRS
 *
 */
public class TemplateMsgData4ExpiryRemind extends TemplateMsgDataInfo {
	
	private TemplateMsgDataModel name;
	private TemplateMsgDataModel expDate;
	
	private TemplateMsgDataModel theme;
	private TemplateMsgDataModel code;
	private TemplateMsgDataModel date;
	
	
	
	public TemplateMsgDataModel getName() {
		return name;
	}
	public void setName(TemplateMsgDataModel name) {
		this.name = name;
	}
	public TemplateMsgDataModel getExpDate() {
		return expDate;
	}
	public void setExpDate(TemplateMsgDataModel expDate) {
		this.expDate = expDate;
	}
	public TemplateMsgDataModel getTheme() {
		return theme;
	}
	public void setTheme(TemplateMsgDataModel theme) {
		this.theme = theme;
	}
	public TemplateMsgDataModel getCode() {
		return code;
	}
	public void setCode(TemplateMsgDataModel code) {
		this.code = code;
	}
	public TemplateMsgDataModel getDate() {
		return date;
	}
	public void setDate(TemplateMsgDataModel date) {
		this.date = date;
	}


	public void setTheme(String theme) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(theme);
		this.theme = templateMsgDataModel;
	}

	public void setCode(String code) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(code);
		this.code = templateMsgDataModel;
	}
	public void setDate(String date) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(date);
		this.date = templateMsgDataModel;
	}
	public void setName(String name) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(name);
		this.name = templateMsgDataModel;
	}
	public void setExpDate(String expDate) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(expDate);
		this.expDate = templateMsgDataModel;
	}
	
}
