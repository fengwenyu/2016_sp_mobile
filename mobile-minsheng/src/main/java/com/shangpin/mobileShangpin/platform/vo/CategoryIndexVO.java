package com.shangpin.mobileShangpin.platform.vo;

import java.util.List;

public class CategoryIndexVO {

	private String code;//编码
	private String name;//名称
	private String enname;//英文名称
	private String icon;//图片
	private List<CategoryIndexVO> childList;//子类
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<CategoryIndexVO> getChildList() {
		return childList;
	}
	public void setChildList(List<CategoryIndexVO> childList) {
		this.childList = childList;
	}
	
	
}
