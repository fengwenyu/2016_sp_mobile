/**
 * 
 */
package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @author ZRS
 *
 */
public class LogoNew implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String pic;
	private String height;
	private String width;
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
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
