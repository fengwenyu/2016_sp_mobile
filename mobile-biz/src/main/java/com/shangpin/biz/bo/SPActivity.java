package com.shangpin.biz.bo;

import java.io.Serializable;

public class SPActivity implements Serializable {

	private static final long serialVersionUID = 3939579369091298859L;

	private String name;

	private String type;

	private String startTag;

	private String startTime;

	private String endTime;

	private String desc;

	private String picNew;
	
	private String pic;
	
	private String refContent;
	
	private String height;
	
	private String width;
	
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

	public String getStartTag() {
		return startTag;
	}

	public void setStartTag(String startTag) {
		this.startTag = startTag;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getRefContent() {
		return refContent;
	}

	public void setRefContent(String refContent) {
		this.refContent = refContent;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

    public String getPicNew() {
        return picNew;
    }

    public void setPicNew(String picNew) {
        this.picNew = picNew;
    }
	
	
}
