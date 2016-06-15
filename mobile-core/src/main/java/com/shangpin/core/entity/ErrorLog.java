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
 * ErrorLog 
 */
@Entity
@Table(name = "errorLog")
public class ErrorLog implements java.io.Serializable {

    private static final long serialVersionUID = -2756020831395404409L;
    private Long id;
    private String platform;
    private Long productNum;
    private String productVersion;
    private Long channelNum;
    private String ip;
    private String shortmsg;
    private String longmsg;
    private String city;
    private String phoneType;
    private String imei;
    private String systemVersoin;
    private String apn;
    private Date createTime;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;

    public ErrorLog() {
    }

    public ErrorLog(String platform, Long productNum, String productVersion, Long channelNum, String ip, String shortmsg, String longmsg, String city, String phoneType,
            String imei, String systemVersoin, String apn, Date createTime, String reserve0, String reserve1, String reserve2, String reserve3, String reserve4, String reserve5) {
        this.platform = platform;
        this.productNum = productNum;
        this.productVersion = productVersion;
        this.channelNum = channelNum;
        this.ip = ip;
        this.shortmsg = shortmsg;
        this.longmsg = longmsg;
        this.city = city;
        this.phoneType = phoneType;
        this.imei = imei;
        this.systemVersoin = systemVersoin;
        this.apn = apn;
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

    @Column(name = "platform", length = 10)
    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Column(name = "productNum")
    public Long getProductNum() {
        return this.productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    @Column(name = "productVersion", length = 10)
    public String getProductVersion() {
        return this.productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    @Column(name = "channelNum")
    public Long getChannelNum() {
        return this.channelNum;
    }

    public void setChannelNum(Long channelNum) {
        this.channelNum = channelNum;
    }

    @Column(name = "ip", length = 20)
    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "shortmsg")
    public String getShortmsg() {
        return this.shortmsg;
    }

    public void setShortmsg(String shortmsg) {
        this.shortmsg = shortmsg;
    }

    @Column(name = "longmsg")
    public String getLongmsg() {
        return this.longmsg;
    }

    public void setLongmsg(String longmsg) {
        this.longmsg = longmsg;
    }

    @Column(name = "city", length = 30)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "phoneType", length = 20)
    public String getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    @Column(name = "imei", length = 40)
    public String getImei() {
        return this.imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Column(name = "systemVersoin", length = 8)
    public String getSystemVersoin() {
        return this.systemVersoin;
    }

    public void setSystemVersoin(String systemVersoin) {
        this.systemVersoin = systemVersoin;
    }

    @Column(name = "apn", length = 20)
    public String getApn() {
        return this.apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
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
