package com.shangpin.mobileShangpin.base.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 发现信息表
 * 
 * @author: wanghaibo
 * @date:2013-12-25
 */
@Entity
@Table(name = "findManage")
public class FindManage implements Serializable {

	private static final long serialVersionUID = 7055357338320927854L;
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
	private String type;
	private String imgUrl;
	private String imgWidth;
	private String imgHeight;
	private String getUrl;
	private String isTop;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "activityID", length = 150)
	public String getActivityID() {
		return activityID;
	}
	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startDate")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endDate")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "showStartDate")
	public Date getShowStartDate() {
		return showStartDate;
	}
	public void setShowStartDate(Date showStartDate) {
		this.showStartDate = showStartDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "showEndDate")
	public Date getShowEndDate() {
		return showEndDate;
	}
	public void setShowEndDate(Date showEndDate) {
		this.showEndDate = showEndDate;
	}
	
	@Column(name = "mobilePic", length = 150)
	public String getMobilePic() {
		return mobilePic;
	}
	public void setMobilePic(String mobilePic) {
		this.mobilePic = mobilePic;
	}
	
	@Column(name = "iphonePic", length = 150)
	public String getIphonePic() {
		return iphonePic;
	}
	public void setIphonePic(String iphonePic) {
		this.iphonePic = iphonePic;
	}
	
	@Column(name = "shareUrl", length = 150)
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "preTime")
	public Date getPreTime() {
		return preTime;
	}
	public void setPreTime(Date preTime) {
		this.preTime = preTime;
	}
	
	@Column(name = "status", length = 150)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "subTitle", length = 150)
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	@Column(name = "type", length = 50)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "imgUrl", length = 200)
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	@Column(name = "description", length = 150)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "title", length = 150)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "imgWidth", length = 50)
	public String getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}
	@Column(name = "imgHeight", length = 50)
	public String getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}
	@Column(name = "getUrl", length = 200)
	public String getGetUrl() {
		return getUrl;
	}
	public void setGetUrl(String getUrl) {
		this.getUrl = getUrl;
	}
	@Column(name = "isTop", length = 5)
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	
	
}
