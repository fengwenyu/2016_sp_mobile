package com.shangpin.biz.bo;

import java.io.Serializable;


/**
 * 配送方式数据传输对象，用于前台展示
 * 
 * @Author cuibinqiang
 */
public class Delivery implements Serializable {
	private static final long serialVersionUID = 1L;
	/*** 配送方式ID*/
	private String id;
	/*** 配送方式名称*/
	private String name;
	/*** 是否可见，1表示可见*/
	private String enable;

	
	private String desc;//收货方式描述
	private String isSelected;//是否被选中
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
}
