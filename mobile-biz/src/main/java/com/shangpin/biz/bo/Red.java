package com.shangpin.biz.bo;

import java.io.Serializable;

public class Red implements Serializable {
	private static final long serialVersionUID = 4305374828766081854L;
	private RedISResult commenBean;
	private String type;
	public RedISResult getCommenBean() {
		return commenBean;
	}
	public void setCommenBean(RedISResult commenBean) {
		this.commenBean = commenBean;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	} 
}
