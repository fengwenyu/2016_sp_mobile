package com.shangpin.core.entity.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shangpin.core.entity.Idable;

/**
 * 发现管理实体
 * 
 * @author liling
 */
@Entity
@Table(name = "manage_find")
public class Find implements Idable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "activity_id", length = 40)
    private String activityId;
    @Column(name = "start_time", length = 19)
    private Date startTime;
    @Column(name = "end_time", length = 19)
    private Date endTime;
    @Column(name = "show_starttime", length = 19)
    private Date showStartTime;
    @Column(name = "show_endtime", length = 19)
    private Date showEndTime;
    @Column(name = "create_time", length = 19)
    private Date createTime;
    @Column(name = "mobile_pic", length = 200)
    private String mobilePic;
    @Column(name = "iphone_pic", length = 200)
    private String iphonePic;
    @Column(name = "share_url", length = 200)
    private String shareUrl;
    @Column(name = "pre_Time", length = 19)
    private Date preTime;
    @Column(name = "status", length = 50)
    private String status;
    @Column(name = "description", length = 5)
    private String description;
    @Column(name = "sub_title", length = 200)
    private String subTitle;
    @Column(name = "img_url", length = 200)
    private String imgUrl;
    @Column(name = "img_width", length = 10)
    private String imgWidth;
    @Column(name = "img_height", length = 10)
    private String imgHeight;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", length = 20)
    private Type type;
    @Column(name = "title", length = 150)
    private String title;
    @Column(name = "get_url", length = 200)
    private String getUrl;
    // 是否设为APP轮播图，0为不是，1为是
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "slider")
    private Slider slider;
    @Column(name = "slider_imgurl", length = 200)
    private String sliderImgUrl;
    // 移动端预热时间
    @Column(name = "mobile_pretime", length = 19)
    private Date mobilePreTime;
    // 排序（目前只支持置顶 0代表置顶）
    @Column(name = "sort_by")
    private Integer sortBy;

    public Find() {
    }

    public Find(Long id, String activityId, Date startTime, Date endTime, Date showStartTime, Date showEndTime, Date createTime, String mobilePic, String iphonePic,
            String shareUrl, Date preTime, String status, String description, String subTitle, String imgUrl, String imgWidth, String imgHeight, Type type, String title,
            String getUrl, Slider slider, String sliderImgUrl, Date mobilePreTime, Integer sortBy) {
        super();
        this.id = id;
        this.activityId = activityId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.showStartTime = showStartTime;
        this.showEndTime = showEndTime;
        this.createTime = createTime;
        this.mobilePic = mobilePic;
        this.iphonePic = iphonePic;
        this.shareUrl = shareUrl;
        this.preTime = preTime;
        this.status = status;
        this.description = description;
        this.subTitle = subTitle;
        this.imgUrl = imgUrl;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.type = type;
        this.title = title;
        this.getUrl = getUrl;
        this.slider = slider;
        this.sliderImgUrl = sliderImgUrl;
        this.mobilePreTime = mobilePreTime;
        this.sortBy = sortBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(Date showStartTime) {
        this.showStartTime = showStartTime;
    }

    public Date getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(Date showEndTime) {
        this.showEndTime = showEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMobilePic() {
        return mobilePic;
    }

    public void setMobilePic(String mobilePic) {
        this.mobilePic = mobilePic;
    }

    public String getIphonePic() {
        return iphonePic;
    }

    public void setIphonePic(String iphonePic) {
        this.iphonePic = iphonePic;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Date getPreTime() {
        return preTime;
    }

    public void setPreTime(Date preTime) {
        this.preTime = preTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    public String getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGetUrl() {
        return getUrl;
    }

    public void setGetUrl(String getUrl) {
        this.getUrl = getUrl;
    }

    public Slider getSlider() {
        return slider;
    }

    public void setSlider(Slider slider) {
        this.slider = slider;
    }

    public String getSliderImgUrl() {
        return sliderImgUrl;
    }

    public void setSliderImgUrl(String sliderImgUrl) {
        this.sliderImgUrl = sliderImgUrl;
    }

    public Date getMobilePreTime() {
        return mobilePreTime;
    }

    public void setMobilePreTime(Date mobilePreTime) {
        this.mobilePreTime = mobilePreTime;
    }

    public Integer getSortBy() {
        return sortBy;
    }

    public void setSortBy(Integer sortBy) {
        this.sortBy = sortBy;
    }

}
