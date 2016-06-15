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
 * ActivityManage 
 */
@Entity
@Table(name = "activityManage")
public class ActivityManage implements java.io.Serializable {

    private static final long serialVersionUID = -6678234237068846921L;
    private Long id;
    private String activityDesc;
    private String activityId;
    private String activityName;
    private Date endDate;
    private String iphonePic;
    private String mobilePic;
    private Date preTime;
    private String shareUrl;
    private Date showEndDate;
    private String showPosition;
    private Date showStartDate;
    private Date startDate;
    private String status;
    private String subTitle;

    public ActivityManage() {
    }

    public ActivityManage(String activityDesc, String activityId, String activityName, Date endDate, String iphonePic, String mobilePic, Date preTime, String shareUrl,
            Date showEndDate, String showPosition, Date showStartDate, Date startDate, String status, String subTitle) {
        this.activityDesc = activityDesc;
        this.activityId = activityId;
        this.activityName = activityName;
        this.endDate = endDate;
        this.iphonePic = iphonePic;
        this.mobilePic = mobilePic;
        this.preTime = preTime;
        this.shareUrl = shareUrl;
        this.showEndDate = showEndDate;
        this.showPosition = showPosition;
        this.showStartDate = showStartDate;
        this.startDate = startDate;
        this.status = status;
        this.subTitle = subTitle;
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

    @Column(name = "activityDesc", length = 150)
    public String getActivityDesc() {
        return this.activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    @Column(name = "activityID", length = 150)
    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Column(name = "activityName", length = 150)
    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate", length = 19)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "iphonePic", length = 150)
    public String getIphonePic() {
        return this.iphonePic;
    }

    public void setIphonePic(String iphonePic) {
        this.iphonePic = iphonePic;
    }

    @Column(name = "mobilePic", length = 150)
    public String getMobilePic() {
        return this.mobilePic;
    }

    public void setMobilePic(String mobilePic) {
        this.mobilePic = mobilePic;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "preTime", length = 19)
    public Date getPreTime() {
        return this.preTime;
    }

    public void setPreTime(Date preTime) {
        this.preTime = preTime;
    }

    @Column(name = "shareUrl", length = 150)
    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "showEndDate", length = 19)
    public Date getShowEndDate() {
        return this.showEndDate;
    }

    public void setShowEndDate(Date showEndDate) {
        this.showEndDate = showEndDate;
    }

    @Column(name = "showPosition", length = 150)
    public String getShowPosition() {
        return this.showPosition;
    }

    public void setShowPosition(String showPosition) {
        this.showPosition = showPosition;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "showStartDate", length = 19)
    public Date getShowStartDate() {
        return this.showStartDate;
    }

    public void setShowStartDate(Date showStartDate) {
        this.showStartDate = showStartDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate", length = 19)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "status", length = 150)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "subTitle", length = 150)
    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

}
