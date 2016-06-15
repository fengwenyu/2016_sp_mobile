package com.shangpin.mobileShangpin.platform.vo;

/**
 * 专题传输数据对象，用于前台展示
 * 
 * @Author yumeng
 * @CreateDate 2012-10-29
 */
public class TopicVO {

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
	private String shareurl;//活动访问地址
	private String id;//活动编号
	private String desc;//活动描述

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

	public String getShareurl() {
		return shareurl;
	}

	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	

}
