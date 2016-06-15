package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: ProductIndexValues 
* @Description:商品指数规格实体类
* @author qinyingchun
* @date 2014年11月3日
* @version 1.0 
*/
public class ProductIndexValues implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String icon;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	

}
