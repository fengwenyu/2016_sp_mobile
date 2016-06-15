package com.shangpin.biz.bo;

import java.io.Serializable;

public class HeadInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String type;
	private String logo;
	private String isCollected;
	private String about;
	private Share share;
	private String fansCount;
	private String goodsCount;
	private BrandAbout brandAbout;
	private LogoNew logoNew;
	//TODO 2016.5.20 促销需求
	 private String  pageView;//浏览量
     private String title;//活动名称,
     private String enableRemind;//"true"是否可以提醒,
    

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPageView() {
		return pageView;
	}
	public void setPageView(String pageView) {
		this.pageView = pageView;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEnableRemind() {
		return enableRemind;
	}
	public void setEnableRemind(String enableRemind) {
		this.enableRemind = enableRemind;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(String isCollected) {
		this.isCollected = isCollected;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Share getShare() {
		return share;
	}
	public void setShare(Share share) {
		this.share = share;
	}
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
	public String getFansCount() {
		return fansCount;
	}
	public void setFansCount(String fansCount) {
		this.fansCount = fansCount;
	}
	public String getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}
	public BrandAbout getBrandAbout() {
		return brandAbout;
	}
	public void setBrandAbout(BrandAbout brandAbout) {
		this.brandAbout = brandAbout;
	}
	public LogoNew getLogoNew() {
		return logoNew;
	}
	public void setLogoNew(LogoNew logoNew) {
		this.logoNew = logoNew;
	}
	

}
