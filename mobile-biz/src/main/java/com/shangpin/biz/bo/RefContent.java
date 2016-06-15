package com.shangpin.biz.bo;

import java.io.Serializable;


/**
 * 通用规则实体类
 * @author qinyingchun
 *
 */
public class RefContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1657511385700472619L;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 通用规则类型；1：活动商品列表2：品类商品列表3：品牌商品列表4：商品详情页5：H5页面
	 */
	private String type;
	
	/**
	 * 通用规则内容，与type一一对应
	 */
	private String refContent;

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
