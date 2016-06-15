package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: ProductLogistics 
* @Description: 新版物流中的sku属性和属性值
* @author 李灵
* @date 2014年11月29日 
* @version 1.0 
*/
public class LogisticSkuProp  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
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
