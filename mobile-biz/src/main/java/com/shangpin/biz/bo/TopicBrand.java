package com.shangpin.biz.bo;

import java.io.Serializable;

public class TopicBrand implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String imgurl;
	private String chname;//品牌中文名称
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
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getChname() {
		return chname;
	}
	public void setChname(String chname) {
		this.chname = chname;
	}
	
	
}
