package com.shangpin.core.entity;

// Generated 2014-5-26 18:24:56 

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Feedback 
 */
@Entity
@Table(name = "feedback")
public class Feedback implements java.io.Serializable {

    private static final long serialVersionUID = -1913112631084913849L;
    private Long id;
    private String version;
    private String userId;
    private String loginName;
    private String msg;
    private Long product;
    private String ver;
    private Long channel;
    private Date createTime;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;
    private String platform;
    private String phoneType;
    private String phoneModel;
    private String phone;

    public Feedback() {
    }

    public Feedback(String userId, String loginName, String msg, Long product, String ver, Long channel, Date createTime, String reserve0, String reserve1, String reserve2,
            String reserve3, String reserve4, String reserve5, String platform, String phoneType, String phoneModel, String phone) {
        this.userId = userId;
        this.loginName = loginName;
        this.msg = msg;
        this.product = product;
        this.ver = ver;
        this.channel = channel;
        this.createTime = createTime;
        this.reserve0 = reserve0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.reserve3 = reserve3;
        this.reserve4 = reserve4;
        this.reserve5 = reserve5;
        this.platform = platform;
        this.phoneType = phoneType;
        this.phoneModel = phoneModel;
        this.phone = phone;
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

    @Column(name = "version", length = 10)
    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "userId", length = 50)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "loginName", length = 50)
    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "msg", length = 1024)
    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Column(name = "product")
    public Long getProduct() {
        return this.product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    @Column(name = "ver", length = 10)
    public String getVer() {
        return this.ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    @Column(name = "channel")
    public Long getChannel() {
        return this.channel;
    }

    public void setChannel(Long channel) {
        this.channel = channel;
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

    @Column(name = "platform", length = 10)
    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Column(name = "phoneType", length = 20)
    public String getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    @Column(name = "phoneModel", length = 40)
    public String getPhoneModel() {
        return this.phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    @Column(name = "phone", length = 20)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
