package com.shangpin.core.entity.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.shangpin.core.entity.Idable;

/**
 * 反馈信息实体
 * 
 * @author zhanghongwei
 * 
 */
@Entity
@Table(name = "manage_feedback")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.shangpin.core.entity.main.Feedback")
public class Feedback implements Idable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", length = 50)
    private String userId;// 用户ID

    @Column(name = "login_name", length = 50)
    private String loginName;// 登录的用户名

    @Column(name = "msg", length = 1024)
    private String msg;// 反馈信息内容

    @Column(name = "chanel_id")
    private Long channelId;// 渠道号

    @Column(name = "product_id")
    private Long productId;// 产品号

    @Column(name = "product_version", length = 10)
    private String productVersion;// 产品版本

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;// 创建时间

    @Column(name = "platform", length = 10)
    private String platform;// 手机平台

    @Column(name = "phone", length = 20)
    private String phone;// 手机信息

    @Column(name = "phone_type", length = 20)
    private String phoneType;// 手机信号类型比如(GSM、CDMA)

    @Column(name = "phone_model", length = 40)
    private String phoneModel;// 手机型号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

}
