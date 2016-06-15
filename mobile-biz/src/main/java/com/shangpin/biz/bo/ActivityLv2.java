package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 活动对象_二级商品列表
 * 
 * @author cuibinqiang
 *
 */
public class ActivityLv2  implements Serializable{

    private static final long serialVersionUID = 6121505317527794870L;
    /** 活动是否开启  */
	private String openFlag;
	/** 活动开始时间  */
	private String startTime;
	/** 活动的结束时间  */
	private String endTime;
	/** 活动名称  */
	private String name;
	/** 该活动的商品集合  */
	private List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
	public String getOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Merchandise> getMerchandiseList() {
		return merchandiseList;
	}
	public void setMerchandiseList(List<Merchandise> merchandiseList) {
		this.merchandiseList = merchandiseList;
	}
	
}
