package com.shangpin.biz.bo;

import java.io.Serializable;

/** 
* @ClassName: Collect 
* @Description:收藏实体类 
* @author qinyingchun
* @date 2014年11月22日
* @version 1.0 
*/
public class Collect implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String isCollected;// 是否收藏 0没有 1 有
	private String nameEN;
	private String nameCN;
	private String desc;
	private String pic;
	private String startTime;
	private String endTime;
	private String isFlagship;
	
	public String getIsFlagship() {
		return isFlagship;
	}
	public void setIsFlagship(String isFlagship) {
		this.isFlagship = isFlagship;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNameEN() {
		return nameEN;
	}
	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}
	public String getNameCN() {
		return nameCN;
	}
	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
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
    public String getIsCollected() {
        return isCollected;
    }
    public void setIsCollected(String isCollected) {
        this.isCollected = isCollected;
    }
	

}
