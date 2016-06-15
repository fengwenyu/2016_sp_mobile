package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 省市区基类
 * 
 * @author zhanghongwei
 */
public class Region  implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 省市区id */
    private String id;
    /** 省市区对象的上级ID */
    private String parentid;
    /** 省市区名称 */
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
