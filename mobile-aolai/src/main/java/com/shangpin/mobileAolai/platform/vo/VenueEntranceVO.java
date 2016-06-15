package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;

/**
 * 会场入口VO，用于前台展示
 * 
 * @Author liling
 * @CreateDate 2013-11-21
 */
public class VenueEntranceVO implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = -7319420973124748388L;
    //会场入口图片链接
	private String imgUrl;
	//会场入口a标签title
	private String title;
	//点击会场入口图片跳转url
	private String url;
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
}
