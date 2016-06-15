package com.shangpin.mobileAolai.platform.dao;

import java.util.List;

import com.shangpin.mobileAolai.base.model.Account;
import com.shangpin.mobileAolai.common.persistence.GenericDAO;

public interface AccountDAO extends GenericDAO<Account, Long> {
	/**
	 * 通过登录名获取用户信息
	 * 
	 * @param loginName
	 *            登录名
	 * @return 用户信息
	 * @throws Exception
	 */
	public List<Account> getAccountByLoginName(String loginName)
			throws Exception;
}
