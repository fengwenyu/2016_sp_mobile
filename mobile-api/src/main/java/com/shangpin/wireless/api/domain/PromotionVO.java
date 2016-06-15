package com.shangpin.wireless.api.domain;

/**
 * 购物车列促销信息
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-04-24
 */
public class PromotionVO {

	private String ispromotion;//是否使用促销 1：用2：未用[未使用有2种方式：1：不参加促销，下列信息都为空 2：对于还未满足条件的商品推荐其可能满足的促销活动,下列部分信息为空，比如：优惠金额]
	private String promotionno; //促销编号
	private String promotiontype;//促销类型
	private String promotioncontent;  //优惠内容[折扣、直减金额]
	private String promotiondesc;//促销描述
	private String promotionurl;//促销活动url
	private String couponprice; //总优惠金额
	public String getIspromotion() {
		return ispromotion;
	}
	public void setIspromotion(String ispromotion) {
		this.ispromotion = ispromotion;
	}
	public String getPromotionno() {
		return promotionno;
	}
	public void setPromotionno(String promotionno) {
		this.promotionno = promotionno;
	}
	public String getPromotiontype() {
		return promotiontype;
	}
	public void setPromotiontype(String promotiontype) {
		this.promotiontype = promotiontype;
	}
	public String getPromotioncontent() {
		return promotioncontent;
	}
	public void setPromotioncontent(String promotioncontent) {
		this.promotioncontent = promotioncontent;
	}
	public String getPromotiondesc() {
		return promotiondesc;
	}
	public void setPromotiondesc(String promotiondesc) {
		this.promotiondesc = promotiondesc;
	}
	public String getPromotionurl() {
		return promotionurl;
	}
	public void setPromotionurl(String promotionurl) {
		this.promotionurl = promotionurl;
	}
	public String getCouponprice() {
		return couponprice;
	}
	public void setCouponprice(String couponprice) {
		this.couponprice = couponprice;
	}
	
}
