package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @author qinyingchun
 *支付方式实体
 */
public class PayType implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String mainPayCode;
	private String subPayCode;
	private String desc;
	
	public String getMainPayCode() {
		return mainPayCode;
	}
	public void setMainPayCode(String mainPayCode) {
		this.mainPayCode = mainPayCode;
	}
	public String getSubPayCode() {
		return subPayCode;
	}
	public void setSubPayCode(String subPayCode) {
		this.subPayCode = subPayCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
