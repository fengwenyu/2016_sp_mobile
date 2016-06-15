package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * push消息实体类
 * @author qinyingchun
 *
 */
public class PushInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5485517568914910330L;
	
	private String title;
	private String content;
	private String icon;
	private String imageUrl;
	private String type;
	private String refContent;
	private String createTime;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRefContent() {
		return refContent;
	}
	public void setRefContent(String refContent) {
		this.refContent = refContent;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
