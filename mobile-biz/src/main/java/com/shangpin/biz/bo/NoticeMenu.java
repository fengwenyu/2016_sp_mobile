package com.shangpin.biz.bo;

import java.io.Serializable;

public class NoticeMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; //名称
	private String type; //类型
	private String pic;  //图标
	private String unRead; //未读数量
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getUnRead() {
		return unRead;
	}
	public void setUnRead(String unRead) {
		this.unRead = unRead;
	}
}
