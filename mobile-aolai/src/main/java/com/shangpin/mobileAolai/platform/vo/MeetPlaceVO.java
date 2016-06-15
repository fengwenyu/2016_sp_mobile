package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 会场列表实体类
 */
public class MeetPlaceVO implements Serializable{
	
	/**
     * 
     */
    private static final long serialVersionUID = 6345718077649144276L;
    private String code;	//返回错误码，0为返回成功
	private String msg;		//	错误信息
	private List<MeetPlaceActiveVO> list = new ArrayList<MeetPlaceActiveVO>();//所有进行中的会场
	private String status;	//状态：1开启、2关闭
	private String starttime;	//会场开始时间(格式：2013-10-08 00:00:00)(日期和小时之间有空格)
	private String endtime;	//会场结束时间(格式：2013-10-08 00:00:00)
	private String prehottime;	//会场预热状态开始时间(如果没有预热状态，则传空字符串””)
	private String css;	//会场模板css文件绝对路径
	private String js;	//会场模板js文件绝对路径
	private String html;	//会场模板页面文件绝对路径
	private String prehotcss;	//会场预热模板css文件绝对路径
	private String prehotjs;	//会场预热模板js文件绝对路径
	private String prehothtml;	//会场预热模板页面文件绝对路径

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<MeetPlaceActiveVO> getList() {
		return list;
	}
	public void setList(List<MeetPlaceActiveVO> list) {
		this.list = list;
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