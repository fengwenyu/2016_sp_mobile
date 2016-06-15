package com.shangpin.wireless.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信潮流资讯
 */
public class WXFashionInformation implements Serializable {
	private static final long serialVersionUID = 9178195457793082549L;
	//主键
	private Long id;
	// 标题
	private String title;
	// 作者
	private String author;
	// 摘要
	private String digest;
	// 发布时间
	private Date releaseDate;
	// 创建时间
	private Date createDate;
	// 封面图(链接)
	private String coverImg;

	// 资讯内容
	private String content;
	//排序字段，如果相同以发布日期排序，发布时间倒序
	private Integer sort;
	//封面图原图的宽度
	private String coverImgWidth;
	//封面图原图的高度
	private String coverImgheight;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCoverImgWidth() {
		return coverImgWidth;
	}

	public void setCoverImgWidth(String coverImgWidth) {
		this.coverImgWidth = coverImgWidth;
	}

	public String getCoverImgheight() {
		return coverImgheight;
	}

	public void setCoverImgheight(String coverImgheight) {
		this.coverImgheight = coverImgheight;
	}

	
	
}
