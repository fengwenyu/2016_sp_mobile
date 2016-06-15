package com.shangpin.wireless.api.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 发现信息表
 * 
 * @author: wanghaibo
 * @date:2013-12-25
 */
public class FindManage implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 主键ID **/
	private Long id;
	private String activityID;
	private Date startDate;
	private Date endDate;
	private Date showStartDate;
	private Date showEndDate;
	private String mobilePic;
	private String iphonePic;
	private String shareUrl;
	private Date preTime;
	private String status;
	private String description;
	private String subTitle;
	private String title;
	//private TypeEnum type;
	private String type;
	private String imgUrl;
	private String imgWidth;
	private String imgHeight;
	private String getUrl;
	private String isTop;
	//是否设为APP轮播图，0为不是，1为0
	private IsSliderEnum isSlider;
	private String sliderImgUrl;
	//类型是活动静态页时静态页是否显示，0为不显示，1为显示
	private DisplayEnum display;
	//移动端预热时间
	private Date mobilePreTime;
	//排序（目前只支持置顶 0代表置顶）
	private String sort;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getActivityID() {
		return activityID;
	}
	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getShowStartDate() {
		return showStartDate;
	}
	public void setShowStartDate(Date showStartDate) {
		this.showStartDate = showStartDate;
	}
	
	public Date getShowEndDate() {
		return showEndDate;
	}
	public void setShowEndDate(Date showEndDate) {
		this.showEndDate = showEndDate;
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
	
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getGetUrl() {
		return getUrl;
	}
	public void setGetUrl(String getUrl) {
		this.getUrl = getUrl;
	}
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	public IsSliderEnum getIsSlider() {
		return isSlider;
	}
	public void setIsSlider(IsSliderEnum isSlider) {
		this.isSlider = isSlider;
	}
	public String getSliderImgUrl() {
		return sliderImgUrl;
	}
	public void setSliderImgUrl(String sliderImgUrl) {
		this.sliderImgUrl = sliderImgUrl;
	}
	public DisplayEnum getDisplay() {
		return display;
	}
	public void setDisplay(DisplayEnum display) {
		this.display = display;
	}
	public Date getMobilePreTime() {
		return mobilePreTime;
	}
	public void setMobilePreTime(Date mobilePreTime) {
		this.mobilePreTime = mobilePreTime;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
}
