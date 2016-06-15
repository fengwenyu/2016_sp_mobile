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
 * Accountweixinbind 
 */
@Entity
@Table(name = "accountweixinbind")
public class AccountWeixinBind implements java.io.Serializable {

    private static final long serialVersionUID = -4094701048231417960L;
    private Long id;
    private String weixinId;
    private String userId;
    private String loginName;
    private String gender;
    private String nickname;
    private String city;
    private String regOrigin;
    private Date regTime;
    private Date createTime;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;
    private Date bindTime;
    private Date unbindTime;
    private String markup;

    public AccountWeixinBind() {
    }

    public AccountWeixinBind(String weixinId, String userId, String loginName, String gender, String nickname, String city, String regOrigin, Date regTime, Date createTime,
            String reserve0, String reserve1, String reserve2, String reserve3, String reserve4, String reserve5, Date subscribeTime, String sex, Date bindTime, Date unbindTime,
            String markup) {
        this.weixinId = weixinId;
        this.userId = userId;
        this.loginName = loginName;
        this.gender = gender;
        this.nickname = nickname;
        this.city = city;
        this.regOrigin = regOrigin;
        this.regTime = regTime;
        this.createTime = createTime;
        this.reserve0 = reserve0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.reserve3 = reserve3;
        this.reserve4 = reserve4;
        this.reserve5 = reserve5;
        this.bindTime = bindTime;
        this.unbindTime = unbindTime;
        this.markup = markup;
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

    @Column(name = "weixinId", length = 50)
    public String getWeixinId() {
        return this.weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
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

    @Column(name = "nickname", length = 50)
    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "city", length = 50)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "regOrigin", length = 10)
    public String getRegOrigin() {
        return this.regOrigin;
    }

    public void setRegOrigin(String regOrigin) {
        this.regOrigin = regOrigin;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "bindTime", length = 19)
    public Date getBindTime() {
        return this.bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "unbindTime", length = 19)
    public Date getUnbindTime() {
        return this.unbindTime;
    }

    public void setUnbindTime(Date unbindTime) {
        this.unbindTime = unbindTime;
    }

    @Column(name = "markup", length = 8)
    public String getMarkup() {
        return this.markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

}
