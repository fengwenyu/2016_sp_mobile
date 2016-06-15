package com.shangpin.base.vo;

import java.io.Serializable;

public class Category  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//分类编号
	private String name;//分类名称
	private String parentId;//父分类编号
	private String status;//分类有效状态
	private String count;//条数
	private String level;//分类层级
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
}
