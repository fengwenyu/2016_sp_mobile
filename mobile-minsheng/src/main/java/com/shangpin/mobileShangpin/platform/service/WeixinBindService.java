package com.shangpin.mobileShangpin.platform.service;

import com.shangpin.mobileShangpin.base.model.Account;
import com.shangpin.mobileShangpin.base.model.AccountWeixinBind;

public interface WeixinBindService {
	/**
	 * 根据微信id查询绑定信息
	 * 
	 * @param weixinid
	 *            微信id
	 * @return AccountWeixinBind 绑定信息
	 */
	public AccountWeixinBind findByWeixinId(String weixinid) throws Exception;

	/**
	 * 添加微信绑定
	 * 
	 * @param accountWeixinBind
	 *            绑定信息
	 */
	public void addAccountWeixinBind(AccountWeixinBind accountWeixinBind) throws Exception;

	/**
	 * 如果已绑定则自动登陆
	 * 
	 * @param Account
	 *            账户信息
	 * @return boolean 是否自动登录成功
	 */
	public boolean autologin(Account account) throws Exception;

	/**
	 * 查询微信id是否绑定
	 * 
	 * @param weixinid
	 *            微信id
	 * @return true为已经绑定
	 */
	public boolean isBind(String weixinid);
}
