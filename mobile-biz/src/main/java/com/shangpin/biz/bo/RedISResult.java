package com.shangpin.biz.bo;

import java.io.Serializable;

public class RedISResult implements Serializable {
	private static final long serialVersionUID = 4305374828766081854L;
	public String name;//返回通用规则相关联的活动、自定义链接等的名称
	public String type;//后台返回类型  通用规则关联的类型
	public String refContent;//参数不能为空，返回链接、商品编号、活动编号
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRefContent() {
		return refContent;
	}
	public void setRefContent(String refContent) {
		this.refContent = refContent;
	}

	
}
