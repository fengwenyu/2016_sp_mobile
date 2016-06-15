package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: Promotion 
* @Description:商城首页推广位实体类 
* @author qinyingchun
* @date 2014年10月23日
* @version 1.0 
*/
public class Promotion implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String pic;
	private String link;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
