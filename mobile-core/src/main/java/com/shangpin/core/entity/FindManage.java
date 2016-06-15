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
 * FindManage 
 */
@Entity
@Table(name = "findManage")
public class FindManage implements java.io.Serializable {

    private static final long serialVersionUID = -8402939751217370092L;
    private Long id;
    private String activityId;
    private Date startDate;
    private Date endDate;
    private Date showStartDate;
    private Date showEndDate;
    private String iphonePic;
    private String mobilePic;
    private String shareUrl;
    private Date preTime;
    private String status;
    private String description;
    private String subTitle;
    private String type;
    private int isSlider;
    private String imgUrl;
    private String imgWidth;
    private String imgHeight;
    private String title;
    private String getUrl;
    private String isTop;
    private String sliderImgUrl;
    private int display;
    private Date mobilePreTime;
    private String sort;

    public FindManage() {
    }

    public FindManage(int isSlider, int display) {
        this.isSlider = isSlider;
        this.display = display;
    }

    public FindManage(String activityId, Date startDate, Date endDate, Date showStartDate, Date showEndDate, String iphonePic, String mobilePic, String shareUrl, Date preTime,
            String status, String description, String subTitle, String type, int isSlider, String imgUrl, String imgWidth, String imgHeight, String title, String getUrl,
            String isTop, String sliderImgUrl, int display, Date mobilePreTime, String sort) {
        this.activityId = activityId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.showStartDate = showStartDate;
        this.showEndDate = showEndDate;
        this.iphonePic = iphonePic;
        this.mobilePic = mobilePic;
        this.shareUrl = shareUrl;
        this.preTime = preTime;
        this.status = status;
        this.description = description;
        this.subTitle = subTitle;
        this.type = type;
        this.isSlider = isSlider;
        this.imgUrl = imgUrl;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.title = title;
        this.getUrl = getUrl;
        this.isTop = isTop;
        this.sliderImgUrl = sliderImgUrl;
        this.display = display;
        this.mobilePreTime = mobilePreTime;
        this.sort = sort;
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

    @Column(name = "activityID", length = 150)
    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate", length = 19)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate", length = 19)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
    @Column(name = "showEndDate", length = 19)
    public Date getShowEndDate() {
        return this.showEndDate;
    }

    public void setShowEndDate(Date showEndDate) {
        this.showEndDate = showEndDate;
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

    @Column(name = "shareUrl", length = 150)
    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "preTime", length = 19)
    public Date getPreTime() {
        return this.preTime;
    }

    public void setPreTime(Date preTime) {
        this.preTime = preTime;
    }

    @Column(name = "status", length = 150)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "description", length = 150)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "subTitle", length = 150)
    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Column(name = "type", length = 50)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "isSlider", nullable = false)
    public int getIsSlider() {
        return this.isSlider;
    }

    public void setIsSlider(int isSlider) {
        this.isSlider = isSlider;
    }

    @Column(name = "imgUrl", length = 200)
    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Column(name = "imgWidth", length = 50)
    public String getImgWidth() {
        return this.imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    @Column(name = "imgHeight", length = 50)
    public String getImgHeight() {
        return this.imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    @Column(name = "title", length = 150)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "getUrl", length = 200)
    public String getGetUrl() {
        return this.getUrl;
    }

    public void setGetUrl(String getUrl) {
        this.getUrl = getUrl;
    }

    @Column(name = "isTop", length = 5)
    public String getIsTop() {
        return this.isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    @Column(name = "sliderImgUrl", length = 200)
    public String getSliderImgUrl() {
        return this.sliderImgUrl;
    }

    public void setSliderImgUrl(String sliderImgUrl) {
        this.sliderImgUrl = sliderImgUrl;
    }

    @Column(name = "display", nullable = false)
    public int getDisplay() {
        return this.display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mobilePreTime", length = 19)
    public Date getMobilePreTime() {
        return this.mobilePreTime;
    }

    public void setMobilePreTime(Date mobilePreTime) {
        this.mobilePreTime = mobilePreTime;
    }

    @Column(name = "sort", length = 5)
    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
