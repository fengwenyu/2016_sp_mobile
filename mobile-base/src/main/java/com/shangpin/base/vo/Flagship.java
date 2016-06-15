package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 旗舰店，用于前台展示
 * 
 * @Author wangfeng
 * @CreateDate 2014-09-17
 */
public class Flagship implements Serializable {

	private static final long serialVersionUID = 1L;
	// 会场入口a标签title
	private String title;
	// 会场入口a标签id
	private String id;
	// 点击会场入口图片跳转url
	private String url;
	private String brandEN;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrandEN() {
		return brandEN;
	}

	public void setBrandEN(String brandEN) {
		this.brandEN = brandEN;
	}

}
