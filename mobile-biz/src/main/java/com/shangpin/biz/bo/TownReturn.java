package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 街道信息实体类 
 * 
 * @author cuibinqiang
 *
 */
public class TownReturn implements Serializable{

	
	private static final long serialVersionUID = 1L;
	/** 省市区id */
    private String id;
    /** 省市区对象的上级ID */
    private String areaId;
    /** 省市区名称 */
    private String name;
    
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
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

}
