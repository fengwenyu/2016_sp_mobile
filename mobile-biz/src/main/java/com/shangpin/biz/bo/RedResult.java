package com.shangpin.biz.bo;

import java.io.Serializable;

public class RedResult implements Serializable {
	private static final long serialVersionUID = 4305374828766081854L;
	private Red commenBean;
	private String type;
	public Red getCommenBean() {
		return commenBean;
	}
	public void setCommenBean(Red commenBean) {
		this.commenBean = commenBean;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
