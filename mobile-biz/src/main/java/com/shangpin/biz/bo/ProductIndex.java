package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: ProductIndex 
* @Description:商品指数列表实体类 
* @author qinyingchun
* @date 2014年11月3日
* @version 1.0 
*/
public class ProductIndex implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private String current;
	private List<ProductIndexValues> values;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public List<ProductIndexValues> getValues() {
		return values;
	}
	public void setValues(List<ProductIndexValues> values) {
		this.values = values;
	}
	

}
