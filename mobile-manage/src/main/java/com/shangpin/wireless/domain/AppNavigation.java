package com.shangpin.wireless.domain;

import java.util.Date;

/**
 * 奥莱App导航表
 * 
 * @Author: liling
 * @CreateDate: 2014-06-08
 */
public class AppNavigation {
	// 主键id
	private Long id;
	//显示类型
	private ShowTypeEnum showType;
	//导航名称
	private String name;
	//链接
	private String link;
	//导航id
	private String tabId;
	//创建时间
	private Date createDate;
	//展示类型为wap页的时候，需要配置展示时间
	private Date startDate;
	private Date endDate;
	private String sort;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ShowTypeEnum getShowType() {
		return showType;
	}
	public void setShowType(ShowTypeEnum showType) {
		this.showType = showType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	
}
