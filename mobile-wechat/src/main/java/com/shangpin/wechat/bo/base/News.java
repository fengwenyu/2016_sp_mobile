package com.shangpin.wechat.bo.base;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Articles> articles;

	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}
	

}
