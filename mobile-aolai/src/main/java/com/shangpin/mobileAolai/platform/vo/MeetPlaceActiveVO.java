package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;

/**
 * 会场活动
 * 
 * @Author wangfeng
 * @CreateDate 2013-10-06
 */
public class MeetPlaceActiveVO implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 4339323804579979687L;
    private String name;	//会场名称
	private String id;	//会场编号
	private String uri;	//会场相对地址
	private String website;	//所属网站：1(尚品)、2(奥莱)
	private String status;	//状态：1开启、2关闭
	private String starttime;	//会场开始时间(格式：2013-10-08 00:00:00)(日期和小时之间有空格)
	private String endtime;	//会场结束时间(格式：2013-10-08 00:00:00)
	private String prehottime;	//会场预热状态开始时间(如果没有预热状态，则传空字符串””)
	private String logo;	//会场入口图(手机端使用)
	private String css;	//会场模板css文件绝对路径
	private String js;	//会场模板js文件绝对路径
	private String html;	//会场模板页面文件绝对路径
	private String prehotcss;	//会场预热模板css文件绝对路径
	private String prehotjs;	//会场预热模板js文件绝对路径
	private String prehothtml;	//会场预热模板页面文件绝对路径
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getPrehottime() {
		return prehottime;
	}
	public void setPrehottime(String prehottime) {
		this.prehottime = prehottime;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getPrehotcss() {
		return prehotcss;
	}
	public void setPrehotcss(String prehotcss) {
		this.prehotcss = prehotcss;
	}
	public String getPrehotjs() {
		return prehotjs;
	}
	public void setPrehotjs(String prehotjs) {
		this.prehotjs = prehotjs;
	}
	public String getPrehothtml() {
		return prehothtml;
	}
	public void setPrehothtml(String prehothtml) {
		this.prehothtml = prehothtml;
	}


	
}
