package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SearchTheme
 * @Description:搜索动态属性对象
 * @author wangfeng
 * @date 2015年09月06日
 * @version 1.0
 */
public class SearchDynamicAttr implements Serializable {

	private static final long serialVersionUID = -1991120257400514315L;
	private String name;
	private String type;
	private String dynamicType;
	private List<SearchDynamicValue> searchDynamicValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDynamicType() {
		return dynamicType;
	}

	public void setDynamicType(String dynamicType) {
		this.dynamicType = dynamicType;
	}

	public List<SearchDynamicValue> getSearchDynamicValue() {
		return searchDynamicValue;
	}

	public void setSearchDynamicValue(List<SearchDynamicValue> searchDynamicValue) {
		this.searchDynamicValue = searchDynamicValue;
	}

}
