package com.shangpin.biz.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DeliveryVo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8184705319317110083L;
	private String id;
	private String desc;
	private String isSelected;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	
}
