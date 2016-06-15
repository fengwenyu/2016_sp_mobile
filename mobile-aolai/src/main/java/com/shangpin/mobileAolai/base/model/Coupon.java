package com.shangpin.mobileAolai.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 优惠券信息表
 * @author huangxiaoliang
 *
 */
@Entity
@Table(name = "coupon")
public class Coupon implements Serializable {
	
	/** 序列化ID **/
	private static final long serialVersionUID = 1L;
	
	/** 主键ID **/
	private Long id;
	/** 1:10元；2:30元 **/
	private String couponType;
	/** 活动id **/
	private String couponNum;
	/** 优惠券批次编码 **/
	private String couponBatNum;
	/** 是否使用过 **/
	private String del;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "couponType", length = 5)
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	
	@Column(name = "couponNum", length = 20)
	public String getCouponNum() {
		return couponNum;
	}
	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}
	
	@Column(name = "couponBatNum", length = 20)
	public String getCouponBatNum() {
		return couponBatNum;
	}
	public void setCouponBatNum(String couponBatNum) {
		this.couponBatNum = couponBatNum;
	}
	
	@Column(name = "del", length = 2)
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}

}
