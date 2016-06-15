package com.shangpin.core.entity.main;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shangpin.core.entity.Idable;

/**
 * 微信潮流资讯实体
 * 
 * @author cuibinqiang
 * 
 */
@Entity
@Table(name = "api_fashion_info")
public class ApiFashionInfo implements Idable<Long> {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", length = 100)
    private String title;// 标题

    @Column(name = "author", length = 50)
    private String author;// 作者

    @Column(name = "digest", length = 150)
    private String digest;// 摘要

    @Column(name = "release_time", length = 19)
    private Date releaseTime;// 发布时间

    @Column(name = "create_time", length = 19)
    private Date createTime;// 创建时间

    @Column(name = "content")
    private String content;// 资讯内容

    @Column(name = "sort")
    private Integer sort;// 排序字段

    @Column(name = "img_url")
    private String imgUrl;// 封面图URL

    @Column(name = "img_width", length = 20)
    private String imgWidth;// 封面图宽度

    @Column(name = "img_height", length = 20)
    private String imgHeight;// 封面图高度

    public ApiFashionInfo() {
    }

    public ApiFashionInfo(String title, String author, String digest, Date releaseTime, Date createTime, String content, Integer sort, String imgUrl, String imgWidth,
            String imgHeight) {
        this.title = title;
        this.author = author;
        this.digest = digest;
        this.releaseTime = releaseTime;
        this.createTime = createTime;
        this.content = content;
        this.sort = sort;
        this.imgUrl = imgUrl;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return this.digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Date getReleaseTime() {
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgWidth() {
        return this.imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    public String getImgHeight() {
        return this.imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

}