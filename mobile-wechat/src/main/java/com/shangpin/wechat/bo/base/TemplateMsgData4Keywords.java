/**
 * 
 */
package com.shangpin.wechat.bo.base;

/**
 * @author ZRS
 *
 */
public class TemplateMsgData4Keywords extends TemplateMsgDataInfo {
	
	private TemplateMsgDataModel keyword1;
	private TemplateMsgDataModel keyword2;
	private TemplateMsgDataModel keyword3;
	private TemplateMsgDataModel keyword4;
	public TemplateMsgDataModel getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(TemplateMsgDataModel keyword1) {
		this.keyword1 = keyword1;
	}
	public void setKeyword1(String keyword1) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(keyword1);
		this.keyword1 = templateMsgDataModel;
	}
	public TemplateMsgDataModel getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(TemplateMsgDataModel keyword2) {
		this.keyword2 = keyword2;
	}
	public void setKeyword2(String keyword2) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(keyword2);
		this.keyword2 = templateMsgDataModel;
	}
	public TemplateMsgDataModel getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(TemplateMsgDataModel keyword3) {
		this.keyword3 = keyword3;
	}
	public void setKeyword3(String keyword3) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(keyword3);
		this.keyword3 = templateMsgDataModel;
	}
	public TemplateMsgDataModel getKeyword4() {
		return keyword4;
	}
	public void setKeyword4(TemplateMsgDataModel keyword4) {
		this.keyword4 = keyword4;
	}
	public void setKeyword4(String keyword4) {
		TemplateMsgDataModel templateMsgDataModel = new TemplateMsgDataModel();
		templateMsgDataModel.setValue(keyword4);
		this.keyword4 = templateMsgDataModel;
	}
	
	
	
}
