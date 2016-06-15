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
 * PushconfShangpin 
 */
@Entity
@Table(name = "pushconf_shangpin")
public class PushconfShangpin implements java.io.Serializable {

    private static final long serialVersionUID = -8001552629843406151L;
    private Long id;
    private String userId;
    private String gender;
    private String token;
    private String imei;
    private int isOpen;
    private int msgType;
    private Date createTime;
    private Date updateTime;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;

    public PushconfShangpin() {
    }

    public PushconfShangpin(String token, String imei, int isOpen, int msgType, Date createTime) {
        this.token = token;
        this.imei = imei;
        this.isOpen = isOpen;
        this.msgType = msgType;
        this.createTime = createTime;
    }

    public PushconfShangpin(String userId, String gender, String token, String imei, int isOpen, int msgType, Date createTime, Date updateTime, String reserve0, String reserve1,
            String reserve2, String reserve3, String reserve4, String reserve5) {
        this.userId = userId;
        this.gender = gender;
        this.token = token;
        this.imei = imei;
        this.isOpen = isOpen;
        this.msgType = msgType;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    @Column(name = "gender", length = 4)
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "token", nullable = false, length = 70)
    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "imei", nullable = false, length = 40)
    public String getImei() {
        return this.imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Column(name = "isOpen", nullable = false)
    public int getIsOpen() {
        return this.isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    @Column(name = "msgType", nullable = false)
    public int getMsgType() {
        return this.msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateTime", length = 19)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
