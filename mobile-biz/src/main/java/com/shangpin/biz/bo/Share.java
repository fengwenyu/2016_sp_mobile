package com.shangpin.biz.bo;

import java.io.Serializable;

public class Share implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private String desc;
	private String url;
	private String pic;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
