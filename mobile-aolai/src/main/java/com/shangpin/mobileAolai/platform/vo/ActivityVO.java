package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动传输数据对象，用于商品列表页展示
 * 
 * @Author zhouyu
 * @CreateDate 2012-11-27
 */
public class ActivityVO implements Serializable{
    
    private static final long serialVersionUID = 3899994445907391389L;
    
    /*** 活动是否开启 */
	private String openFlag;
	/*** 该活动的商品集合 */
	private List<MerchandiseVO> merchandiseList = new ArrayList<MerchandiseVO>();
	/*** 活动开始时间 */
	private String startTime;
	/*** 活动的结束时间 */
	private String endTime;
	/*** 活动名称 */
	private String name;

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public List<MerchandiseVO> getMerchandiseList() {
		return merchandiseList;
	}

	public void setMerchandiseList(List<MerchandiseVO> merchandiseList) {
		this.merchandiseList = merchandiseList;
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

}
