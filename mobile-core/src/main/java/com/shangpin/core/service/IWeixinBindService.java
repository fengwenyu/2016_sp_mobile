package com.shangpin.core.service;

import com.shangpin.core.entity.AccountWeixinBind;

public interface IWeixinBindService {

	AccountWeixinBind findByWXId(String wxId);

	void addOrModifyAccountWeixinBind(AccountWeixinBind accountWeixinBind);
	
	boolean isBind(String wxId, String userId);


}
