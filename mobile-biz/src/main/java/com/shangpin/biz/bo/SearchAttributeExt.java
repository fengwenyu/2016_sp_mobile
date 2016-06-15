package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 搜索属性扩展属性对象
 * 
 * @author wangfeng
 * 
 */
public class SearchAttributeExt implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6537150418453664893L;
    private String name;
    private String value;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
    

    
    
	

}
