package com.shangpin.biz.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
//将该标记放在属性上，如果该属性为NULL则不参与序列化  
//如果放在类上边,那对这个类的全部属性起作用  
//Include.Include.ALWAYS 默认  
//Include.NON_DEFAULT 属性为默认值不序列化  
//Include.NON_EMPTY 属性为 空（“”）  或者为 NULL 都不序列化  
//Include.NON_NULL 属性为NULL 不序列化  

@JsonInclude(Include.NON_NULL)
public class SearchCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;// 分类编号
	private String code;// 分类编号
	private String name;// 分类名称
	private String parentCode;// 父分类编号
	private String status;// 分类有效状态
	private String sortType;// 分类的排序
	private String count;// 条数
	private String parentId;// 父分类编号
	private String level;// 分类层级

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

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
