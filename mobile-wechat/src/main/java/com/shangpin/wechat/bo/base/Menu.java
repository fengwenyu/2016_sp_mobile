package com.shangpin.wechat.bo.base;

import java.io.Serializable;
import java.util.List;

/**微信菜单实体类
 * @author qinyingchun
 *
 */
public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	private String key;
	private String url;
	private List<Menu> sub_button;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Menu> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<Menu> sub_button) {
		this.sub_button = sub_button;
	}
	
}
