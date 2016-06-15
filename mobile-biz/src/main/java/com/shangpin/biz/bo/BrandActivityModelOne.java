/**
 * 
 */
package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZRS
 *
 */
public class BrandActivityModelOne implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private List<BrandActivityModelOneContent> list;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<BrandActivityModelOneContent> getList() {
		return list;
	}
	public void setList(List<BrandActivityModelOneContent> list) {
		this.list = list;
	}

}
