package com.shangpin.biz.bo;

import java.io.Serializable;


/** 
* @ClassName: Sbrand 
* @Description: 尚品M站品牌实体类 
* @author qinyingchun
* @date 2014年10月24日
* @version 1.0 
*/
public class Sbrand implements Serializable{
	private static final long serialVersionUID = -4731652288243505461L;
	private String id;
	private String name;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
