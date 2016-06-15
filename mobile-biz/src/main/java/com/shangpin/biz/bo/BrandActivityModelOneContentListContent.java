/**
 * 
 */
package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @author ZRS
 *
 */
public class BrandActivityModelOneContentListContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pic;
	private String type;
	private String refContent;
	private String height;
	private String width;
	private String name;
	private String refParam;
	private String filters;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
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
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getRefParam() {
		return refParam;
	}
	public void setRefParam(String refParam) {
		this.refParam = refParam;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	
	
}
