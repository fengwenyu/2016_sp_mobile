package com.shangpin.biz.bo;

import java.io.Serializable;


/** 
* @ClassName: SearchSize 
* @Description:搜索尺码实体类
* @author qinyingchun
* @date 2014年10月28日
* @version 1.0 
*/
public class SearchSize implements Serializable{
	/**
	 * @Fields serialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;
	private String sizeCode;//尺码
	private String id;
	private String name;//名称
	private String count;//条数
    public String getSizeCode() {
        return sizeCode;
    }
    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
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
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
	
	

}
