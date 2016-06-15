package com.shangpin.mobileShangpin.platform.service;

import com.shangpin.mobileShangpin.platform.vo.AccountVO;

public interface MinshengBindService {
	
	/**
	 * 如果已绑定则自动登陆
	 * 
	 * @param Account
	 *            账户信息
	 * @return boolean 是否自动登录成功
	 */
	public boolean autologin(AccountVO accountVo) throws Exception;

	
}
