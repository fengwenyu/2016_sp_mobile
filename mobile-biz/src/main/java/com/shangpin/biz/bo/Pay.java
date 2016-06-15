package com.shangpin.biz.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;


/**
 * 支付方式数据传输对象，用于前台展示
 * 
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Pay  implements Serializable{
	private static final long serialVersionUID = 1L;
	/*** 支付方式ID*/
	private String id;
	/*** 支付方式名称*/
	private String name;
	/*** 是否可见，1表示可见*/
	private String enable;

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
