package com.shangpin.core.entity.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.shangpin.core.entity.Idable;

/**
 * 消息推送实体
 * 
 * @author qinyingchun
 * @since 2014-8-6
 * 
 */
@Entity
@Table(name = "manage_push")
public class Push implements Idable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "platform_type")
    private int platformType;// 0表示android，1表示ios
    
    @Column(name = "product_code")
    private int productCode;// 产品编号
    
    @Column(name = "channel_code")
    private int channelCode;// 渠道编号
    
    @Column(name = "action_type")
    private String actionType;// 事件类型
    
    @Column(name = "action_param")
    private String actionParam;// 事件参数
    
    @Column(name = "action_detail_param")
    private String actionDetailParam;// 事件详细参数
    
    @Column(name = "user_id")
    private String userId;// 用户编号
    
    @Column(name = "user_name")
    private String username;// 用户名
    
    @Column(name = "status")
    private int status;// 发送状态 0表示未发送，1表示发送
    
    @Column(name = "content")
    private String content;// 推送内容
    
    @Column(name = "sex")
    private int sex;// 0表示男性，1表示女性
    
    @Column(name = "notice")
    private String notice;// 通知内容
    
    @Column(name = "title")
    private String title;// 标题
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 19, updatable = false)
    private Date createTime;// 创建时间
    
    @Column(name = "start_time")
    private Date startTime;// 开始时间
    
    @Column(name = "end_time")
    private Date endTime;// 结束时间
    
    @Transient
    private PushContent pushContent;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the plateformType
     */
    public int getPlatformType() {
        return platformType;
    }

    /**
     * @param plateformType
     *            the plateformType to set
     */
    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }

    /**
     * @return the productCode
     */
    public int getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     *            the productCode to set
     */
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the channelCode
     */
    public int getChannelCode() {
        return channelCode;
    }

    /**
     * @param channelCode
     *            the channelCode to set
     */
    public void setChannelCode(int channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * @return the actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @param actionType
     *            the actionType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * @return the actionParam
     */
    public String getActionParam() {
        return actionParam;
    }

    /**
     * @param actionParam
     *            the actionParam to set
     */
    public void setActionParam(String actionParam) {
        this.actionParam = actionParam;
    }

    /**
     * @return the actionDetailParam
     */
    public String getActionDetailParam() {
        return actionDetailParam;
    }

    /**
     * @param actionDetailParam
     *            the actionDetailParam to set
     */
    public void setActionDetailParam(String actionDetailParam) {
        this.actionDetailParam = actionDetailParam;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the sex
     */
    public int getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * @return the notice
     */
    public String getNotice() {
        return notice;
    }

    /**
     * @param notice
     *            the notice to set
     */
    public void setNotice(String notice) {
        this.notice = notice;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Push [id=" + id + ", plateformType=" + platformType + ", productCode=" + productCode + ", channelCode=" + channelCode + ", actionType=" + actionType
                + ", actionParam=" + actionParam + ", actionDetailParam=" + actionDetailParam + ", userId=" + userId + ", username=" + username + ", status=" + status
                + ", content=" + content + ", sex=" + sex + ", notice=" + notice + ", title=" + title + ", createTime=" + createTime + ", startTime=" + startTime + ", endTime="
                + endTime + "]";
    }

    /**
     * @return the pushContent
     */
    public PushContent getPushContent() {
        return pushContent;
    }

    /**
     * @param pushContent
     *            the pushContent to set
     */
    public void setPushContent(PushContent pushContent) {
        this.pushContent = pushContent;
    }
}
