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
public class BrandActivityModelOneContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;//"1行一张 2左一右二 3左二右一",
	private List<BrandActivityModelOneContentListContent> model;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<BrandActivityModelOneContentListContent> getModel() {
		return model;
	}
	public void setModel(List<BrandActivityModelOneContentListContent> model) {
		this.model = model;
	}

	
	
	          
}
