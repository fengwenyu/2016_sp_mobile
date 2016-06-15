package com.shangpin.biz.bo;

import java.io.Serializable;

public class FashionDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String pubTime;
	private String source;
	private String article;
	private Share share;
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

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

}
