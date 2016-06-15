package com.shangpin.base.vo;

import java.io.Serializable;

public class ActivityOfMain implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 活动名称 */
	private String name;
	/** 活动副标题 */
	private String subtitle;
	/** 活动描述 */
	private String desc;
	/** 活动状态 1为开启 */
	private String status;
	/** 活动开始时间 */
	private String starttime;
	/** 活动结束时间 */
	private String endtime;
	/** 活动预热时间 */
	private String pretime;
	/** 活动编号 */
	private String id;
	/** 活动分享链接 */
	private String shareurl;
	/** 客户端图片，目前业务用的是m站的图片 */
	private String iphonepic;
	/** m站图片 */
	private String mobilepic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public String getPretime() {
		return pretime;
	}

	public void setPretime(String pretime) {
		this.pretime = pretime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShareurl() {
		return shareurl;
	}

	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}

	public String getIphonepic() {
		return iphonepic;
	}

	public void setIphonepic(String iphonepic) {
		this.iphonepic = iphonepic;
	}

	public String getMobilepic() {
		return mobilepic;
	}

	public void setMobilepic(String mobilepic) {
		this.mobilepic = mobilepic;
	}

}