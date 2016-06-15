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
 * WxFashionInformation
 */
@Entity
@Table(name = "wxFashionInformation")
public class WxFashionInformation implements java.io.Serializable {

    private static final long serialVersionUID = -4270722949516647592L;
    private Long id;
    private String title;
    private String author;
    private String digest;
    private Date releaseDate;
    private Date createDate;
    private String coverImg;
    private String content;
    private Integer sort;
    private String coverImgWidth;
    private String coverImgheight;
    private String smallCoverImg;

    public WxFashionInformation() {
    }

    public WxFashionInformation(String title, String author, String digest, Date releaseDate, Date createDate, String coverImg, String content, Integer sort, String coverImgWidth,
            String coverImgheight, String smallCoverImg) {
        this.title = title;
        this.author = author;
        this.digest = digest;
        this.releaseDate = releaseDate;
        this.createDate = createDate;
        this.coverImg = coverImg;
        this.content = content;
        this.sort = sort;
        this.coverImgWidth = coverImgWidth;
        this.coverImgheight = coverImgheight;
        this.smallCoverImg = smallCoverImg;
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

    @Column(name = "title", length = 100)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", length = 50)
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "digest", length = 150)
    public String getDigest() {
        return this.digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "releaseDate", length = 19)
    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "coverImg")
    public String getCoverImg() {
        return this.coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    @Column(name = "content")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "coverImgWidth", length = 20)
    public String getCoverImgWidth() {
        return this.coverImgWidth;
    }

    public void setCoverImgWidth(String coverImgWidth) {
        this.coverImgWidth = coverImgWidth;
    }

    @Column(name = "coverImgheight", length = 20)
    public String getCoverImgheight() {
        return this.coverImgheight;
    }

    public void setCoverImgheight(String coverImgheight) {
        this.coverImgheight = coverImgheight;
    }

    @Column(name = "smallCoverImg")
    public String getSmallCoverImg() {
        return this.smallCoverImg;
    }

    public void setSmallCoverImg(String smallCoverImg) {
        this.smallCoverImg = smallCoverImg;
    }

}
