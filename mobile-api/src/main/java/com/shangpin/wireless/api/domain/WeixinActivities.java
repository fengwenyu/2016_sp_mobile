package com.shangpin.wireless.api.domain;

import java.util.Date;

/**
 * 微信的活动列表
 * 
 * @Author: wangwenguan
 * @CreateDate: 2013-07-28
 */
public class WeixinActivities implements java.io.Serializable {

	private static final long serialVersionUID = 100L;
	private long id;
	private String issueId; // 活动期号
	private String issueName; // 活动名称
	private int isOpen; // 是否开启
	private int type; // 活动类型(1普通答题；2问卷调查；3性格测试)
	private String startOrder; // 开始指令
	private String queryOrder; // 查询成绩指令
	private String notStartReply; // 活动未开始时的自动回复
	private String endedReply; // 活动已结束时的自动回复
	private String overflowReply; // 回复选项超出备选个数时的自动回复
	private Date startTime; // 开始时间
	private Date endTime; // 结束时间
	private Date createTime; // 创建时间
	private String reserve0;// 备用字段
	private String reserve1;// 备用字段
	private String reserve2;// 备用字段
	private String reserve3;// 备用字段
	private String reserve4;// 备用字段
	private String reserve5;// 备用字段

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getStartOrder() {
		return startOrder;
	}

	public void setStartOrder(String startOrder) {
		this.startOrder = startOrder;
	}

	public String getQueryOrder() {
		return queryOrder;
	}

	public void setQueryOrder(String queryOrder) {
		this.queryOrder = queryOrder;
	}

	public String getNotStartReply() {
		return notStartReply;
	}

	public void setNotStartReply(String notStartReply) {
		this.notStartReply = notStartReply;
	}

	public String getEndedReply() {
		return endedReply;
	}

	public void setEndedReply(String endedReply) {
		this.endedReply = endedReply;
	}

	public String getOverflowReply() {
		return overflowReply;
	}

	public void setOverflowReply(String overflowReply) {
		this.overflowReply = overflowReply;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getReserve0() {
		return reserve0;
	}

	public void setReserve0(String reserve0) {
		this.reserve0 = reserve0;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

}
