package com.shangpin.biz.bo;

import java.io.Serializable;

public class StyleTheme extends CommonRules implements Serializable {


	/**
     * 
     */
    private static final long serialVersionUID = 1115214539724594508L;


	private String pic;
	private String picNew;
	private String height;
	private String width;

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPicNew() {
		return picNew;
	}

	public void setPicNew(String picNew) {
		this.picNew = picNew;
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
	
	
}
