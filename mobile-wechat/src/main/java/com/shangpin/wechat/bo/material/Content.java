package com.shangpin.wechat.bo.material;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cuibinqiang on 2015/12/14.
 */
public class Content implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<NewsItem> news_item;

    public List<NewsItem> getNews_item() {
        return news_item;
    }

    public void setNews_item(List<NewsItem> news_item) {
        this.news_item = news_item;
    }
}
