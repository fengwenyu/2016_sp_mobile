package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 尺码
 * @author xupengcheng
 *
 */
public class Size  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String count;//条数
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
}
