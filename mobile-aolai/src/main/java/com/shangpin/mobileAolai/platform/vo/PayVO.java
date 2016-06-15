package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;


/**
 * 支付方式数据传输对象，用于前台展示
 * 
 * @Author yumeng
 * @CreateDate 2012-11-8
 */
public class PayVO implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 4913310393637041049L;
    /*** 支付方式ID*/
	private String id;
	/*** 支付方式名称*/
	private String name;
	/*** 是否可见，1表示可见*/
	private String enable;

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
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
}
