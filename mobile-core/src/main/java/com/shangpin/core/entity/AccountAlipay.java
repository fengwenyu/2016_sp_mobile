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
 * Accountalipay
 * 
 * @author sunweiwei
 */
@Entity
@Table(name = "accountalipay")
public class AccountAlipay implements java.io.Serializable {

    private static final long serialVersionUID = 7422774811330433199L;
    private Long id;
    private String aliUserId;
    private String userId;
    private String gender;
    private String nickname;
    private String city;
    private String mobile;
    private String email;
    private String authcode;
    private String accesstoken;
    private Integer accessExpiresSec;
    private String refreshtoken;
    private Integer refreshExpiresSec;
    private Date regTime;
    private Date updateTime;
    private Date createTime;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;
    private String product;

    public AccountAlipay() {
    }

    public AccountAlipay(String aliUserId, String userId, String gender, String nickname, String city, String mobile, String email, String authcode, String accesstoken,
            Integer accessExpiresSec, String refreshtoken, Integer refreshExpiresSec, Date regTime, Date updateTime, Date createTime, String reserve0, String reserve1,
            String reserve2, String reserve3, String reserve4, String reserve5, String product) {
        this.aliUserId = aliUserId;
        this.userId = userId;
        this.gender = gender;
        this.nickname = nickname;
        this.city = city;
        this.mobile = mobile;
        this.email = email;
        this.authcode = authcode;
        this.accesstoken = accesstoken;
        this.accessExpiresSec = accessExpiresSec;
        this.refreshtoken = refreshtoken;
        this.refreshExpiresSec = refreshExpiresSec;
        this.regTime = regTime;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.reserve0 = reserve0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.reserve3 = reserve3;
        this.reserve4 = reserve4;
        this.reserve5 = reserve5;
        this.product = product;
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

    @Column(name = "aliUserId", length = 50)
    public String getAliUserId() {
        return this.aliUserId;
    }

    public void setAliUserId(String aliUserId) {
        this.aliUserId = aliUserId;
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

    @Column(name = "mobile", length = 20)
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "email", length = 40)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "authcode", length = 50)
    public String getAuthcode() {
        return this.authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    @Column(name = "accesstoken", length = 50)
    public String getAccesstoken() {
        return this.accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    @Column(name = "accessExpiresSec")
    public Integer getAccessExpiresSec() {
        return this.accessExpiresSec;
    }

    public void setAccessExpiresSec(Integer accessExpiresSec) {
        this.accessExpiresSec = accessExpiresSec;
    }

    @Column(name = "refreshtoken", length = 50)
    public String getRefreshtoken() {
        return this.refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }

    @Column(name = "refreshExpiresSec")
    public Integer getRefreshExpiresSec() {
        return this.refreshExpiresSec;
    }

    public void setRefreshExpiresSec(Integer refreshExpiresSec) {
        this.refreshExpiresSec = refreshExpiresSec;
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
    @Column(name = "updateTime", length = 19)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    @Column(name = "product", length = 8)
    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

}
