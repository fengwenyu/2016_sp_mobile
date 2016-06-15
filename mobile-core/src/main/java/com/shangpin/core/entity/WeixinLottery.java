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
 * Weixinlottery 
 */
@Entity
@Table(name = "weixinlottery")
public class WeixinLottery implements java.io.Serializable {

    private static final long serialVersionUID = -1363664318187554247L;
    private Long id;
    private String issueId;
    private String weixinId;
    private String prizeLevel;
    private String activateCode;
    private Date createTime;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;
    private String userId;

    public WeixinLottery() {
    }

    public WeixinLottery(String issueId, String weixinId, String prizeLevel, String activateCode, Date createTime, String reserve0, String reserve1, String reserve2,
            String reserve3, String reserve4, String reserve5, String userId) {
        this.issueId = issueId;
        this.weixinId = weixinId;
        this.prizeLevel = prizeLevel;
        this.activateCode = activateCode;
        this.createTime = createTime;
        this.reserve0 = reserve0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.reserve3 = reserve3;
        this.reserve4 = reserve4;
        this.reserve5 = reserve5;
        this.userId = userId;
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

    @Column(name = "weixinId", length = 40)
    public String getWeixinId() {
        return this.weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    @Column(name = "prizeLevel", length = 4)
    public String getPrizeLevel() {
        return this.prizeLevel;
    }

    public void setPrizeLevel(String prizeLevel) {
        this.prizeLevel = prizeLevel;
    }

    @Column(name = "activateCode", length = 20)
    public String getActivateCode() {
        return this.activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode;
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

    @Column(name = "userId", length = 50)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
