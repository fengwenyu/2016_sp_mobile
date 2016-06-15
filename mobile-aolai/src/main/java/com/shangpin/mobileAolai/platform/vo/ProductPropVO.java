package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;

/**
 * 购物车商品动态属性
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-04-24
 */
public class ProductPropVO implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 6502567032946461929L;
    private String name;// 属性名称
	private String value;//属性值
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
    
}
