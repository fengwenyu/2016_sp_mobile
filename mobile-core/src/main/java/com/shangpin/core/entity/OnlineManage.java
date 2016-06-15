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
 * OnlineManage 
 */
@Entity
@Table(name = "onlineManage")
public class OnlineManage implements java.io.Serializable {

    private static final long serialVersionUID = 2017276211808733767L;
    private Long id;
    private Long productNum;
    private Long channelNum;
    private String versionForceMax;
    private String versionLatest;
    private String fileName;
    private String downloadPath;
    private String prompt;
    private Date createTime;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;
    private String versionNoForceMin;
    private Integer inuse;

    public OnlineManage() {
    }

    public OnlineManage(Long productNum, Long channelNum, String versionForceMax, String versionLatest, String fileName, String downloadPath, String prompt, Date createTime,
            String reserve0, String reserve1, String reserve2, String reserve3, String reserve4, String reserve5, String versionNoForceMin, Integer inuse) {
        this.productNum = productNum;
        this.channelNum = channelNum;
        this.versionForceMax = versionForceMax;
        this.versionLatest = versionLatest;
        this.fileName = fileName;
        this.downloadPath = downloadPath;
        this.prompt = prompt;
        this.createTime = createTime;
        this.reserve0 = reserve0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.reserve3 = reserve3;
        this.reserve4 = reserve4;
        this.reserve5 = reserve5;
        this.versionNoForceMin = versionNoForceMin;
        this.inuse = inuse;
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

    @Column(name = "productNum")
    public Long getProductNum() {
        return this.productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    @Column(name = "channelNum")
    public Long getChannelNum() {
        return this.channelNum;
    }

    public void setChannelNum(Long channelNum) {
        this.channelNum = channelNum;
    }

    @Column(name = "versionForceMax", length = 10)
    public String getVersionForceMax() {
        return this.versionForceMax;
    }

    public void setVersionForceMax(String versionForceMax) {
        this.versionForceMax = versionForceMax;
    }

    @Column(name = "versionLatest", length = 10)
    public String getVersionLatest() {
        return this.versionLatest;
    }

    public void setVersionLatest(String versionLatest) {
        this.versionLatest = versionLatest;
    }

    @Column(name = "fileName")
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "downloadPath")
    public String getDownloadPath() {
        return this.downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    @Column(name = "prompt", length = 65535)
    public String getPrompt() {
        return this.prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
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

    @Column(name = "versionNoForceMin", length = 10)
    public String getVersionNoForceMin() {
        return this.versionNoForceMin;
    }

    public void setVersionNoForceMin(String versionNoForceMin) {
        this.versionNoForceMin = versionNoForceMin;
    }

    @Column(name = "inuse")
    public Integer getInuse() {
        return this.inuse;
    }

    public void setInuse(Integer inuse) {
        this.inuse = inuse;
    }

}
