package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索属性对象
 * 
 * @author wangfeng
 * 
 */
public class SearchAttribute implements Serializable {

	private static final long serialVersionUID = 4600270645287911827L;
	private String name;// 属性名称
	private String dynamicName;// 新属性名称
	private String isMultiSelect;// 是否可以多选 0 不支持 1支持
	private String desc;// 描述
	private List<SearchAttributeValue> value;
	private List<SearchAttributeGroup> group;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDynamicName() {
		return dynamicName;
	}

	public void setDynamicName(String dynamicName) {
		this.dynamicName = dynamicName;
	}

	public String getIsMultiSelect() {
		return isMultiSelect;
	}

	public void setIsMultiSelect(String isMultiSelect) {
		this.isMultiSelect = isMultiSelect;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<SearchAttributeValue> getValue() {
		return value;
	}

	public void setValue(List<SearchAttributeValue> value) {
		this.value = value;
	}

	public List<SearchAttributeGroup> getGroup() {
		return group;
	}

	public void setGroup(List<SearchAttributeGroup> group) {
		this.group = group;
	}

}
