package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @author qinyingchun
 *跑步馆品类实体类
 */
public class RunCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sid;
	private String status;
	private String discription;
	private String qtime;
	private List<Category> channelCategorys;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getQtime() {
		return qtime;
	}
	public void setQtime(String qtime) {
		this.qtime = qtime;
	}
	public List<Category> getChannelCategorys() {
		return channelCategorys;
	}
	public void setChannelCategorys(List<Category> channelCategorys) {
		this.channelCategorys = channelCategorys;
	}
	
}
