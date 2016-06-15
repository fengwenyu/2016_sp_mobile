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

import com.shangpin.core.entity.Idable;

@Entity
@Table(name = "manage_online_manage")
public class OnlineManage implements Idable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;// id

    @Column(length = 20)
    private Integer productNum;// 商品号

    @Column(length = 20)
    private Long channelNum;// 渠道号

    @Column(length = 10)
    private String versionForceMax;// 强制升级最大版本号

    @Column(length = 10)
    private String versionLatest;// 产品版本号

    @Column(length = 255)
    private String fileName;// 文件名

    @Column(length = 255)
    private String downloadPath;// 下载地址

    @Column(length = 65535)
    private String prompt;// 信息提示

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;// 创建时间

    @Column(length = 10)
    private String versionNoForceMin;// 不强制升级最小版本号

    @Column(length = 10)
    private Integer inuse;// 次数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Long getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(Long channelNum) {
        this.channelNum = channelNum;
    }

    public String getVersionForceMax() {
        return versionForceMax;
    }

    public void setVersionForceMax(String versionForceMax) {
        this.versionForceMax = versionForceMax;
    }

    public String getVersionLatest() {
        return versionLatest;
    }

    public void setVersionLatest(String versionLatest) {
        this.versionLatest = versionLatest;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVersionNoForceMin() {
        return versionNoForceMin;
    }

    public void setVersionNoForceMin(String versionNoForceMin) {
        this.versionNoForceMin = versionNoForceMin;
    }

    public Integer getInuse() {
        return inuse;
    }

    public void setInuse(Integer inuse) {
        this.inuse = inuse;
    }

}
