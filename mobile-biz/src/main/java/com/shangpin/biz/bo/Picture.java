package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: Picture 
* @Description:图片实体类 
* @author qinyingchun
* @date 2014年10月28日
* @version 1.0 
*/
public class Picture implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String picno;
	private String picurl;
	private String success;
	private String PicUrl;
	private String Extension;
	private String FileName;
	private String type;
	private String contentlength;
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getExtension() {
		return Extension;
	}
	public void setExtension(String extension) {
		Extension = extension;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContentlength() {
		return contentlength;
	}
	public void setContentlength(String contentlength) {
		this.contentlength = contentlength;
	}
	public String getPicno() {
		return picno;
	}
	public void setPicno(String picno) {
		this.picno = picno;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

}
