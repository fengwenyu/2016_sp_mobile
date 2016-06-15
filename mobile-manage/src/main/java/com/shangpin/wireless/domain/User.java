package com.shangpin.wireless.domain;

import java.util.Date;

/**
 * @Author zhouyu
 * @CreatDate 2012-07-12 后台系统用户
 */
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;// 主键
	private String loginName;// 用户名 50
	private String pwd;// 用户密码 30
	private String phoneNum;// 手机号码
	private String nickname;// 姓名 40
	private Integer rank;// 级别
	private Date createTime;// 创建日期
	private Role role;// 角色
	private String reserve0;// 备用字段
	private String reserve1;// 备用字段
	private String reserve2;// 备用字段
	private String reserve3;// 备用字段
	private String reserve4;// 备用字段
	private String reserve5;// 备用字段

	/**
	 * 判断当前用户是否有指定名称的权限
	 * 
	 * @param name
	 * @Return
	 */
	public boolean hasPrivilegeByName(String name) {
		// 超级管理员有所有的权限
		if (isAdmin()) {
			return true;
		}

		// 对于其他用户，就要判断是否有权限了
		for (Privilege privilege : role.getPrivileges()) {
			if (privilege.getModulename().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断当前用户是否有指定URL的权限
	 * 
	 * @param url
	 *            权限的URL（真正使用的是actionName，也是在数据库中privilege的url字段值 ）
	 * @Return
	 */
	public boolean hasPrivilegeByUrl(String url) {
		// 超级管理员有所有的权限
		if (isAdmin()) {
			return true;
		}

		// 对于其他用户，就要判断是否有权限了
		for (Privilege privilege : role.getPrivileges()) {
			if (url.equals(privilege.getUrl())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断当前用户是否是超级管理员
	 * 
	 * @Return
	 */
	public boolean isAdmin() {
		// return "admin".equals(loginName);
		return rank == 100;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReserve0() {
		return reserve0;
	}

	public void setReserve0(String reserve0) {
		this.reserve0 = reserve0;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
