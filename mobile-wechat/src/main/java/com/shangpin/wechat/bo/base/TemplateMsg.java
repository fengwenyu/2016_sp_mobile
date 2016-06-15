/**
 * 
 */
package com.shangpin.wechat.bo.base;

/**
 * @author ZRS
 *
 */
public class TemplateMsg<T> {
	private String touser;
	private String template_id;
	private String url;      
	private T data;
	
	public TemplateMsg(){
		
	}
	
	/**
	 * @param touser
	 * @param template_id
	 * @param url
	 * @param data
	 */
	public TemplateMsg(String touser, String template_id, String url, T data) {
		super();
		this.touser = touser;
		this.template_id = template_id;
		this.url = url;
		this.data = data;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
