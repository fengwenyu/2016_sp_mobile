package com.shangpin.wireless.api.domain;

import java.util.Date;

/**
 * push配置表
 * 
 * @Author: zhouyu
 * @CreateDate: 2013-02-21
 */
public class PushManageIos implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String platform;
	private Long productNum;
	private Long channelNum;
	private String action;
	private String actionarg;
	private String actionobj;
	private String username;
	private String userId;
	private int pushType; // 0:表示未发送；1:表示已发送
	private String pushContent;
	private int msgType;// 女0；男1；全部
	private String notice;// 公告文字
	private Date showTime;
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getActionarg() {
		return actionarg;
	}

	public void setActionarg(String actionarg) {
		this.actionarg = actionarg;
	}

	public String getActionobj() {
		return actionobj;
	}

	public void setActionobj(String actionobj) {
		this.actionobj = actionobj;
	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public Long getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(Long channelNum) {
		this.channelNum = channelNum;
	}

	public int getPushType() {
		return pushType;
	}

	public void setPushType(int pushType) {
		this.pushType = pushType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPushContent() {
		return pushContent;
	}

	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
}
