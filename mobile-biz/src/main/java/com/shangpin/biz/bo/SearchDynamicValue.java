package com.shangpin.biz.bo;

import java.io.Serializable;


/** 
* @ClassName: SearchTheme 
* @Description:搜索动态属性值对象
* @author wangfeng
* @date 2015年09月06日
* @version 1.0 
*/
public class SearchDynamicValue implements Serializable{
	/**
	 * @Fields serialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;//名称
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
