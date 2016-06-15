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
 * Account
 * 
 * @author sunweiwei
 */
@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {

    private static final long serialVersionUID = -6050803074739272263L;
    private Long id;
    private String userId;
    private String loginName;
    private String gender;
    private Long channel;
    private Long product;
    private String regOrigin;
    private String platform;
    private String phoneType;
    private String phoneModel;
    private Date loginTime;
    private Date regTime;
    private Date createTime;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;
    private String companyname;
    private String password;
    private String phonenum;

    public Account() {
    }

    public Account(String userId, String loginName, String gender, Long channel, Long product, String regOrigin, String platform, String phoneType, String phoneModel,
            Date loginTime, Date regTime, Date createTime, String reserve0, String reserve1, String reserve2, String reserve3, String reserve4, String reserve5,
            String companyname, String password, String phonenum) {
        this.userId = userId;
        this.loginName = loginName;
        this.gender = gender;
        this.channel = channel;
        this.product = product;
        this.regOrigin = regOrigin;
        this.platform = platform;
        this.phoneType = phoneType;
        this.phoneModel = phoneModel;
        this.loginTime = loginTime;
        this.regTime = regTime;
        this.createTime = createTime;
        this.reserve0 = reserve0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.reserve3 = reserve3;
        this.reserve4 = reserve4;
        this.reserve5 = reserve5;
        this.companyname = companyname;
        this.password = password;
        this.phonenum = phonenum;
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

    @Column(name = "loginName", length = 50)
    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "gender", length = 4)
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "channel")
    public Long getChannel() {
        return this.channel;
    }

    public void setChannel(Long bigInteger) {
        this.channel = bigInteger;
    }

    @Column(name = "product")
    public Long getProduct() {
        return this.product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    @Column(name = "regOrigin", length = 10)
    public String getRegOrigin() {
        return this.regOrigin;
    }

    public void setRegOrigin(String regOrigin) {
        this.regOrigin = regOrigin;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "loginTime", length = 19)
    public Date getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regTime", length = 19)
    public Date getRegTime() {
        return this.regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
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

    @Column(name = "companyname", length = 150)
    public String getCompanyname() {
        return this.companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @Column(name = "password", length = 50)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "phonenum", length = 30)
    public String getPhonenum() {
        return this.phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

}
