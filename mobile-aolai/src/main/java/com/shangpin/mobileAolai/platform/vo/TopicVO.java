package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;

/**
 * 专题传输数据对象，用于前台展示
 * 
 * @Author yumeng
 * @CreateDate 2012-10-29
 */
public class TopicVO implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = -6749454052292145473L;
    /*** 专题ID */
	private String topicid;
	/*** 专题名称 */
	private String name;
	/*** 专题的宣传图片路径 */
	private String pic;
	/*** 专题的开始时间*/
	private String starttime;
	/*** 专题的结束时间*/
	private String endtime;
	/*** 根据API主站对接接口文档 2012-12-27的修改 返回数据中添加“desc”属性（专题push描述）*/
	private String desc;
	private String showtime;//显示时间
	

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	
	
	
}
