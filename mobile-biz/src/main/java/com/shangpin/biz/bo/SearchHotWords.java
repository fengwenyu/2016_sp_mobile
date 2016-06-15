package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: SearchHotWords
* @Description:搜索热词实体类 
* @author qinyingchun
* @date 2014年10月28日
* @version 1.0 
*/
public class SearchHotWords implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;//id
	private String name;//名称
	private String type;//热词类型
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
	

}
