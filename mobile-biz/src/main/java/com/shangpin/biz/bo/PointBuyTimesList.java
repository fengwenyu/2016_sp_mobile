package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class PointBuyTimesList implements Serializable{
	
	private static final long serialVersionUID = 111L;

	private String systemTime;//系统当前时间
	private List<PointBuyPhase> list;//时间轴列表
	public String getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
	public List<PointBuyPhase> getList() {
		return list;
	}
	public void setList(List<PointBuyPhase> list) {
		this.list = list;
	}
}
