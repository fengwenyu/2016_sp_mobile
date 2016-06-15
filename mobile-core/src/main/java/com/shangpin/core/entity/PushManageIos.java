package com.shangpin.core.entity;

// Generated 2014-5-26 18:24:56 

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PushManageIos 
 */
@Entity
@Table(name = "pushManage_ios")
public class PushManageIos implements java.io.Serializable {

    private static final long serialVersionUID = -7031108949235473218L;
    private Long id;
    private String platform;
    private Long productNum;
    private Long channelNum;
    private String username;
    private String userId;
    private String action;
    private String actionarg;
    private String actionobj;
    private String pushContent;
    private Integer msgType;
    private Integer pushType;
    private String notice;
    private Date showTime;
    private Date createTime;
    private String title;
    private Long dictId;

    public PushManageIos() {
    }

    public PushManageIos(String platform, Long productNum, Long channelNum, String username, String userId, String action, String actionarg, String actionobj, String pushContent,
            Integer msgType, Integer pushType, String notice, Date showTime, Date createTime, String title) {
        this.platform = platform;
        this.productNum = productNum;
        this.channelNum = channelNum;
        this.username = username;
        this.userId = userId;
        this.action = action;
        this.actionarg = actionarg;
        this.actionobj = actionobj;
        this.pushContent = pushContent;
        this.msgType = msgType;
        this.pushType = pushType;
        this.notice = notice;
        this.showTime = showTime;
        this.createTime = createTime;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "platform", length = 10)
    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Column(name = "productNum")
    public Long getProductNum() {
        return this.productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    @Column(name = "channelNum")
    public Long getChannelNum() {
        return this.channelNum;
    }

    public void setChannelNum(Long channelNum) {
        this.channelNum = channelNum;
    }

    @Column(name = "username", length = 50)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "userId", length = 150)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "action", length = 50)
    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column(name = "actionarg", length = 500)
    public String getActionarg() {
        return this.actionarg;
    }

    public void setActionarg(String actionarg) {
        this.actionarg = actionarg;
    }

    @Column(name = "actionobj", length = 500)
    public String getActionobj() {
        return this.actionobj;
    }

    public void setActionobj(String actionobj) {
        this.actionobj = actionobj;
    }

    @Column(name = "pushContent", length = 500)
    public String getPushContent() {
        return this.pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    @Column(name = "msgType")
    public Integer getMsgType() {
        return this.msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    @Column(name = "pushType")
    public Integer getPushType() {
        return this.pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    @Column(name = "notice", length = 500)
    public String getNotice() {
        return this.notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "showTime", length = 19)
    public Date getShowTime() {
        return this.showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "title", length = 50)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "dict_id")
	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

}
