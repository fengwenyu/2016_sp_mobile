package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @ClassName: AttributeInfo
 * @Description:商品属性实体类
 * @author wangfeng
 * @date 2015年08月22日
 * @version 1.0
 */
public class AttributeInfo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 753562769386897707L;
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
