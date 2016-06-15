package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: GalleryResult 
* @Description:轮播图对主站返回数据二次处理
* @author liling
* @date 2014年12月05日  
* @version 1.0 
*/
public class GalleryResult  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String pic;
	private String url;
	private String type;//5为html.6为列表页
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return this.pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
