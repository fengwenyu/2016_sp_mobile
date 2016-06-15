package com.shangpin.biz.bo;

import java.io.Serializable;


/** 
* @ClassName: SearchPrice 
* @Description: 搜索价格实体类
* @author qinyingchun
* @date 2014年10月28日
* @version 1.0 
*/
public class SearchPrice implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String among;//价格区间
	private String count;//条数
	public String getAmong() {
		return among;
	}
	public void setAmong(String among) {
		this.among = among;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	

}
