package com.shangpin.wireless.api.domain;

/**
 * 旗舰店，用于前台展示
 * 
 * @Author wanghaibo
 * @CreateDate 2013-12-28
 */
public class Flagship {
	//会场入口图片链接
	private String imgUrl;
	//会场入口a标签title
	private String title;
	//会场入口a标签id
	private String id;
	//点击会场入口图片跳转url
	private String url;
	//旗舰店详细页html
	private String html;
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
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
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
