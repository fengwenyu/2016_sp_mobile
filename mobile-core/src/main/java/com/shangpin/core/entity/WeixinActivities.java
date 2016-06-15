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
 * Weixinactivities 
 */
@Entity
@Table(name = "weixinactivities")
public class WeixinActivities implements java.io.Serializable {

    private static final long serialVersionUID = 278695837489754872L;
    private Long id;
    private String issueId;
    private String issueName;
    private Integer type;
    private Integer isOpen;
    private String startOrder;
    private String queryOrder;
    private String notStartReply;
    private String endedReply;
    private String overflowReply;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;

    public WeixinActivities() {
    }

    public WeixinActivities(String issueId, String issueName, Integer type, Integer isOpen, String startOrder, String queryOrder, String notStartReply, String endedReply,
            String overflowReply, Date startTime, Date endTime, Date createTime, String reserve0, String reserve1, String reserve2, String reserve3, String reserve4,
            String reserve5) {
        this.issueId = issueId;
        this.issueName = issueName;
        this.type = type;
        this.isOpen = isOpen;
        this.startOrder = startOrder;
        this.queryOrder = queryOrder;
        this.notStartReply = notStartReply;
        this.endedReply = endedReply;
        this.overflowReply = overflowReply;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
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

    @Column(name = "issueId", length = 20)
    public String getIssueId() {
        return this.issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    @Column(name = "issueName", length = 50)
    public String getIssueName() {
        return this.issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    @Column(name = "type")
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "isOpen")
    public Integer getIsOpen() {
        return this.isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    @Column(name = "startOrder", length = 30)
    public String getStartOrder() {
        return this.startOrder;
    }

    public void setStartOrder(String startOrder) {
        this.startOrder = startOrder;
    }

    @Column(name = "queryOrder", length = 30)
    public String getQueryOrder() {
        return this.queryOrder;
    }

    public void setQueryOrder(String queryOrder) {
        this.queryOrder = queryOrder;
    }

    @Column(name = "notStartReply", length = 5000)
    public String getNotStartReply() {
        return this.notStartReply;
    }

    public void setNotStartReply(String notStartReply) {
        this.notStartReply = notStartReply;
    }

    @Column(name = "endedReply", length = 5000)
    public String getEndedReply() {
        return this.endedReply;
    }

    public void setEndedReply(String endedReply) {
        this.endedReply = endedReply;
    }

    @Column(name = "overflowReply", length = 5000)
    public String getOverflowReply() {
        return this.overflowReply;
    }

    public void setOverflowReply(String overflowReply) {
        this.overflowReply = overflowReply;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startTime", length = 19)
    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endTime", length = 19)
    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
