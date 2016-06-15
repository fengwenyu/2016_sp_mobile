package com.shangpin.wireless.api.weixinbean;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * 用于将更多品类XML文件转换为javabean实体类
 *
 */
@XObject(value = "Articles")
public class XmlArticles {
	/** 品牌列表 */
	@XNodeList(value = "item", type = ArrayList.class, componentType = XmlArticleItem.class)
	private List<XmlArticleItem> items;

	public List<XmlArticleItem> getItems() {
		return items;
	}

	public void setItems(List<XmlArticleItem> items) {
		this.items = items;
	}

}