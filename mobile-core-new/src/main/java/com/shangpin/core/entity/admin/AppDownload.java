package com.shangpin.core.entity.admin;

// default package
// Generated 2014-11-18 15:20:34

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

/**
 * ManagerAppDownload
 */
@Entity
@Table(name = "manager_app_download")
public class AppDownload implements Idable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "app_name", length = 20)
    private String appName;

    @Column(name = "app_code", length = 20)
    private String appCode;

    @Column(name = "app_version", length = 20)
    private String appVersion;

    @Column(name = "app_platform", length = 10)
    private String appPlatform;

    @Column(name = "ch_name", length = 20)
    private String chName;

    @Column(name = "ch_code", length = 20)
    private String chCode;

    @Column(name = "file_name", length = 50)
    private String fileName;

    @Column(name = "file_path", length = 200)
    private String filePath;

    @Column(name = "description", length = 200)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 19)
    private Date createTime;

    public AppDownload() {
    }

    public AppDownload(Date createTime) {
        this.createTime = createTime;
    }

    public AppDownload(String appName, String appCode, String appVersion, String appPlatform, String chName, String chCode, String fileName, String filePath, String description,
            Date createTime) {
        this.appName = appName;
        this.appCode = appCode;
        this.appVersion = appVersion;
        this.appPlatform = appPlatform;
        this.chName = chName;
        this.chCode = chCode;
        this.fileName = fileName;
        this.filePath = filePath;
        this.description = description;
        this.createTime = createTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCode() {
        return this.appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppPlatform() {
        return this.appPlatform;
    }

    public void setAppPlatform(String appPlatform) {
        this.appPlatform = appPlatform;
    }

    public String getChName() {
        return this.chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChCode() {
        return this.chCode;
    }

    public void setChCode(String chCode) {
        this.chCode = chCode;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
