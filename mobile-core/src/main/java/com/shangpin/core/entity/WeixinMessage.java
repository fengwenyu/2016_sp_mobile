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
 * WeixinMessage 
 */
@Entity
@Table(name = "weixinMessage")
public class WeixinMessage implements java.io.Serializable {

    private static final long serialVersionUID = 6011951523839096099L;
    private Long id;
    private String fromUser;
    private String toUser;
    private String msgId;
    private String content;
    private String msgType;
    private String eventType;
    private String mediaId;
    private String source;
    private Date createTime;
    private String params;
    private String custom;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;

    public WeixinMessage() {
    }

    public WeixinMessage(String fromUser, String toUser, String msgId, String content, String msgType, String eventType, String mediaId, String source, Date createTime,
            String params, String custom, String reserve0, String reserve1, String reserve2, String reserve3, String reserve4, String reserve5) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.msgId = msgId;
        this.content = content;
        this.msgType = msgType;
        this.eventType = eventType;
        this.mediaId = mediaId;
        this.source = source;
        this.createTime = createTime;
        this.params = params;
        this.custom = custom;
        this.reserve0 = reserve0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.reserve3 = reserve3;
        this.reserve4 = reserve4;
        this.reserve5 = reserve5;
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

    @Column(name = "fromUser", length = 50)
    public String getFromUser() {
        return this.fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    @Column(name = "toUser", length = 50)
    public String getToUser() {
        return this.toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @Column(name = "msgId", length = 32)
    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Column(name = "content")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "msgType", length = 8)
    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Column(name = "eventType", length = 8)
    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Column(name = "mediaId", length = 70)
    public String getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Column(name = "source", length = 8)
    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "params")
    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Column(name = "custom", length = 50)
    public String getCustom() {
        return this.custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    @Column(name = "reserve0")
    public String getReserve0() {
        return this.reserve0;
    }

    public void setReserve0(String reserve0) {
        this.reserve0 = reserve0;
    }

    @Column(name = "reserve1")
    public String getReserve1() {
        return this.reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    @Column(name = "reserve2")
    public String getReserve2() {
        return this.reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    @Column(name = "reserve3")
    public String getReserve3() {
        return this.reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    @Column(name = "reserve4")
    public String getReserve4() {
        return this.reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }

    @Column(name = "reserve5")
    public String getReserve5() {
        return this.reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5;
    }

}
