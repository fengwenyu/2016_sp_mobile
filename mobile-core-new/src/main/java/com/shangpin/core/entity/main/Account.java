package com.shangpin.core.entity.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shangpin.core.entity.Idable;

@Entity
@Table(name = "manage_account")
public class Account implements Idable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "login_name")
    private String loginName;
    @Column(name = "gender")
    private int gender;// 0表示女，1表示男
    @Column(name = "channel")
    private int channel;
    @Column(name = "product")
    private int product;
    @Column(name = "reg_origin")
    private String regOrigin;
    @Column(name = "platform")
    private String platform;
    @Column(name = "phone_type")
    private String phoneType;
    @Column(name = "phone_mode")
    private String phoneMode;
    @Column(name = "login_time")
    private Date loginTime;
    @Column(name = "reg_time")
    private Date regTime;
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     *            the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * @return the channel
     */
    public int getChannel() {
        return channel;
    }

    /**
     * @param channel
     *            the channel to set
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    /**
     * @return the product
     */
    public int getProduct() {
        return product;
    }

    /**
     * @param product
     *            the product to set
     */
    public void setProduct(int product) {
        this.product = product;
    }

    /**
     * @return the regOrigin
     */
    public String getRegOrigin() {
        return regOrigin;
    }

    /**
     * @param regOrigin
     *            the regOrigin to set
     */
    public void setRegOrigin(String regOrigin) {
        this.regOrigin = regOrigin;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform
     *            the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @return the phoneType
     */
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * @param phoneType
     *            the phoneType to set
     */
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    /**
     * @return the phoneMode
     */
    public String getPhoneMode() {
        return phoneMode;
    }

    /**
     * @param phoneMode
     *            the phoneMode to set
     */
    public void setPhoneMode(String phoneMode) {
        this.phoneMode = phoneMode;
    }

    /**
     * @return the loginTime
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime
     *            the loginTime to set
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return the regTime
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * @param regTime
     *            the regTime to set
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Account [id=" + id + ", userId=" + userId + ", loginName=" + loginName + ", gender=" + gender + ", channel=" + channel + ", product=" + product + ", regOrigin="
                + regOrigin + ", platform=" + platform + ", phoneType=" + phoneType + ", phoneMode=" + phoneMode + ", loginTime=" + loginTime + ", regTime=" + regTime
                + ", createTime=" + createTime + "]";
    }

}
