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
 * Lottery 
 */
@Entity
@Table(name = "lottery")
public class Lottery implements java.io.Serializable {

    private static final long serialVersionUID = 1742059975979707588L;
    private Long id;
    private String userId;
    private String activeId;
    private String winningLevel;
    private Date createTime;
    private String winningName;
    private String activeName;
    private String userName;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;

    public Lottery() {
    }

    public Lottery(String userId, String activeId, String winningLevel, Date createTime, String winningName, String activeName, String userName, String reserve0, String reserve1,
            String reserve2, String reserve3, String reserve4, String reserve5) {
        this.userId = userId;
        this.activeId = activeId;
        this.winningLevel = winningLevel;
        this.createTime = createTime;
        this.winningName = winningName;
        this.activeName = activeName;
        this.userName = userName;
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

    @Column(name = "userId", length = 50)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "activeId", length = 10)
    public String getActiveId() {
        return this.activeId;
    }

    public void setActiveId(String activeId) {
        this.activeId = activeId;
    }

    @Column(name = "winningLevel", length = 10)
    public String getWinningLevel() {
        return this.winningLevel;
    }

    public void setWinningLevel(String winningLevel) {
        this.winningLevel = winningLevel;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "winningName", length = 20)
    public String getWinningName() {
        return this.winningName;
    }

    public void setWinningName(String winningName) {
        this.winningName = winningName;
    }

    @Column(name = "activeName", length = 50)
    public String getActiveName() {
        return this.activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    @Column(name = "userName", length = 20)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
