package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;
/**
 * @ClassName: ProductSizeProperty
 * @Description:商品尺码属性实体类
 * @author liling
 * @date 2015年3月16日
 * @version 1.0
 */
public class ProductSizeProperty implements Serializable{
	private static final long serialVersionUID = 6563635159211443794L;
	private List<String> values;
	private String title;
	private String current;
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
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
	
}
