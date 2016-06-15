package com.shangpin.biz.bo;
/**
 * 买赠分享信息,订单支付成功后用户可以分享该件商品，其他用户可以领取它
 * @author 陈小峰
 * @date 2016-04-29
 */
public class Freebie extends Share {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String shareName;//按钮上的文本"
	private String isMorethan;//是否多于1件， 0:本地分享, 1多件,跳wapurl"
	private String wapurl;//m站分享列表的url
	private String wapTitle;//m站列表页的标题

	public String getShareName() {
		return shareName;
	}
	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
	public String getIsMorethan() {
		return isMorethan;
	}
	public void setIsMorethan(String isMorethan) {
		this.isMorethan = isMorethan;
	}
	public String getWapurl() {
		return wapurl;
	}
	public void setWapurl(String wapurl) {
		this.wapurl = wapurl;
	}
	public String getWapTitle() {
		return wapTitle;
	}
	public void setWapTitle(String wapTitle) {
		this.wapTitle = wapTitle;
	}



}
