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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.shangpin.core.entity.Idable;

/**
 * app上传的图片信息实体
 * 
 * @author zhanghongwei
 *
 */
@Entity
@Table(name = "manage_app_pic")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.shangpin.core.entity.main.AppPic")
public class AppPic implements Idable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "image_url", length = 200)
    private String imageUrl;// 图片链接地址

    @Column(name = "image_width")
    private Integer imageWidth;// 图片宽度

    @Column(name = "image_height")
    private Integer imageHeight;// 图片高度

    @Column(name = "share_url", length = 200)
    private String shareUrl;// 分享链接

    @Column(name = "share_title", length = 100)
    private String shareTitle;// 分享标题

    @Column(name = "description", length = 255)
    private String description;// 描述

    @Column(name = "os_type", length = 50)
    private String osType;// 移动操作系统类型

    @Column(name = "uses", length = 50)
    private String uses;// 图片用途 如启动图 分享图

    @Column(name = "shop_type")
    private Integer shopType;// 类型 尚品或者奥莱：1尚品2奥莱 默认是1

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;// 创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
