package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索属性子对象
 * 
 * @author wangfeng
 * 
 */
public class SearchAttributeValue implements Serializable {

	private static final long serialVersionUID = -2233996509633081163L;
	private String id;// 编号
	private String name;// 名称
	private String isChecked;// 0:本次未选中；1:选中
	private List<SearchAttributeExt> ext;

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

	public List<SearchAttributeExt> getExt() {
		return ext;
	}

	public void setExt(List<SearchAttributeExt> ext) {
		this.ext = ext;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

}
