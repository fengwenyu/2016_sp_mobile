package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: LogisticsStatus 
* @Description: 物流状态
* @author 李灵
* @date 2014年11月29日 
* @version 1.0 
*/
public class LogisticsStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private String date;
	private String desc;
	private String address;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
