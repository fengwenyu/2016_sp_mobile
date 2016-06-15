package com.shangpin.biz.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
//将该标记放在属性上，如果该属性为NULL则不参与序列化  
//如果放在类上边,那对这个类的全部属性起作用  
//Include.Include.ALWAYS 默认  
//Include.NON_DEFAULT 属性为默认值不序列化  
//Include.NON_EMPTY 属性为 空（“”）  或者为 NULL 都不序列化  
//Include.NON_NULL 属性为NULL 不序列化  
@JsonInclude(Include.NON_NULL)  
public class SearchPostArea implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;//海外编号
	private String count;//条数
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
	
}
