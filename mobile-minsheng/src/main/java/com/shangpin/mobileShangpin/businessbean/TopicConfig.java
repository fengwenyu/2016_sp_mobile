package com.shangpin.mobileShangpin.businessbean;

import java.util.Date;

import com.shangpin.mobileShangpin.common.util.DateTimeUtil;

import net.sf.json.JSONObject;

/**
 * 活动实体类，用于将配置文件文件转换为javabean
 */
public class TopicConfig {
	/** 活动ID */
	private String id;
	/** 活动名称 */
	private String name;
	/** 访问地址后缀名，如m.shangpin.com/bazaar */
	private String uriSuffix;
	/** 渠道号 */
	private String ch;
	/** 活动宣传图片 */
	private String img;
	/** 优惠券发放码 */
	private String coupon;
	/** 注册描述 ，如：新注册会员,即获赠500元优惠券 */
	private String registDesc;
	/** 邀请码 */
	private String invitecode;
	/** 活动截至日期 */
	private Date endDate;
	/** 活动状态，0为有效，1为失效 */
	private int status;

	/**
	 * 将json数据转换TopicConfig对象
	 * 
	 * @param jsonStr
	 *            json数据
	 * 
	 * @Return TopicConfig对象
	 */
	public TopicConfig json2Obj(String jsonStr) throws Exception {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setId(obj.getString("id"));
		this.setName(obj.getString("name"));
		this.setUriSuffix(obj.getString("urisuffix"));
		this.setCh(obj.getString("ch"));
		this.setImg(obj.getString("img"));
		this.setInvitecode(obj.getString("invitecode"));
		this.setCoupon(obj.getString("coupon"));
		this.setRegistDesc(obj.getString("registdesc"));
		this.setEndDate(DateTimeUtil.getShortDate(obj.getString("enddate")));
		this.setStatus(Integer.valueOf(obj.getString("status")));
		return this;
	}

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

	public String getUriSuffix() {
		return uriSuffix;
	}

	public void setUriSuffix(String uriSuffix) {
		this.uriSuffix = uriSuffix;
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRegistDesc() {
		return registDesc;
	}

	public void setRegistDesc(String registDesc) {
		this.registDesc = registDesc;
	}

	public String getInvitecode() {
		return invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
}