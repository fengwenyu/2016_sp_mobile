package com.shangpin.biz.bo;

import java.io.Serializable;

public class GiftCardKeytContent  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 146387441928690367L;
	private String id;
	private String orderId;
	private String desc;
	private String keyt;
	private String faceValue;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getKeyt() {
		return keyt;
	}
	public void setKeyt(String keyt) {
		this.keyt = keyt;
	}
	public String getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}
	
	
}
