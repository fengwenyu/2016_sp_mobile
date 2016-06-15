package com.shangpin.mobileShangpin.platform.vo;

/**
 * 省市区数据传输对象，用于前台展示
 * 
 * @Author yumeng
 * @CreateDate 2012-11-9
 */
public class ProvinceVO {
	/*** 省市区ID */
	private String id;
	/*** 省市区名称 */
	private String name;
	/*** 省市区父ID */
	private String parentid;

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

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
}
