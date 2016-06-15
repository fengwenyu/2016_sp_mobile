package com.shangpin.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微信红包现金卷实体类
 * @author tongwenli
 */
@Entity
@Table(name="weixin_packet_coupon")
public class WeiXinPacketCoupon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String openId;
	private long id;
	private String  accountName;
	private int couponValue;
	private String identify;
	private String createTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="account_name")
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Column(name="coupon_value")
	public int getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(int couponValue) {
		this.couponValue = couponValue;
	}
	@Column(name="identify")
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="open_id")
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
