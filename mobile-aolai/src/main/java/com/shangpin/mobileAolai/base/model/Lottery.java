package com.shangpin.mobileAolai.base.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 抽奖信息表
 * 
 * @author: wangfeng
 * @date:2013-12-10
 */

@Entity
@Table(name = "lottery")
public class Lottery implements Serializable {

	/** 序列化ID **/
	private static final long serialVersionUID = 1L;
	
	/** 主键ID **/
	private long id;
	/** 用户ID **/
	private String userId;
	/** 活动id **/
	private String activeId;
	/**中奖等级 **/
	private String winningLevel;
	/** 中奖等级名称 **/
	private String winningName;
	/** 登录时间 **/
	private Date createTime;
	/** 活动名称**/
	private String activeName;
	/** 用户名**/
	private String userName;
	private String reserve0;
	private String reserve1;
	private String reserve2;
	private String reserve3;
	private String reserve4;
	private String reserve5;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "userId", length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "activeId", length = 10)
	public String getActiveId() {
		return activeId;
	}

	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}

	@Column(name = "userName", length = 20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	@Column(name = "winningLevel", length = 10)
	public String getWinningLevel() {
		return winningLevel;
	}
	

	public void setWinningLevel(String winningLevel) {
		this.winningLevel = winningLevel;
	}


	@Column(name = "winningName" ,length=20)
	public String getWinningName() {
		return winningName;
	}

	public void setWinningName(String winningName) {
		this.winningName = winningName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "activeName" ,length=50)
	
	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	@Column(name = "reserve0", length = 255)
	public String getReserve0() {
		return reserve0;
	}

	public void setReserve0(String reserve0) {
		this.reserve0 = reserve0;
	}

	@Column(name = "reserve1", length = 255)
	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	@Column(name = "reserve2", length = 255)
	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	@Column(name = "reserve3", length = 255)
	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	@Column(name = "reserve4", length = 255)
	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	@Column(name = "reserve5", length = 255)
	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}
}
