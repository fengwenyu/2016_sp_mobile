package com.shangpin.wireless.api.weixinbean;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * 用于将更多品类XML文件转换为javabean实体类（品牌单一实体）
 * 
 * @author yumeng
 *
 */
@XObject
public class XmlArticleItem {
	/** 消息标题 */
	@XNode("Title")
	private String Title;
	/** 消息描述 */
	@XNode("Description")
	private String Description;
	/** 消息链接 */
	@XNode("Url")
	private String Url;
	/** 图片链接 */
	@XNode("PicUrl")
	private String PicUrl;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}