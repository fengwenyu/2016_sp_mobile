package com.shangpin.mobileShangpin.platform.vo;

import java.util.List;

/**
 * 尚品专题传输数据对象，用于前台展示
 */
public class SPTopicVO {
	/*** 专题ID */
	private String topicid;
	/*** 专题描述 */
	private String topicdesc;
	/*** 专题的分享url */
	private String shareurl;
	/*** 专题的商品集合 */
	private List<SPMerchandiseVO> spMerchandiseList;

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getTopicdesc() {
		return topicdesc;
	}

	public void setTopicdesc(String topicdesc) {
		this.topicdesc = topicdesc;
	}

	public String getShareurl() {
		return shareurl;
	}

	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}

	public List<SPMerchandiseVO> getSpMerchandiseList() {
		return spMerchandiseList;
	}

	public void setSpMerchandiseList(List<SPMerchandiseVO> spMerchandiseList) {
		this.spMerchandiseList = spMerchandiseList;
	}
}
