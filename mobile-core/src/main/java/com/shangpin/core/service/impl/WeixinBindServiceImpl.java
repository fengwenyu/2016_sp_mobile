package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.core.dao.IWeixinBindDAO;
import com.shangpin.core.entity.AccountWeixinBind;
import com.shangpin.core.service.IWeixinBindService;

@Service
public class WeixinBindServiceImpl implements IWeixinBindService {
	@Autowired
	private IWeixinBindDAO weixinBindDAO;

	@Override
	public AccountWeixinBind findByWXId(String wxId) {
		List<AccountWeixinBind> list = weixinBindDAO.findByWXId(wxId);
		if (list.size() <= 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void addOrModifyAccountWeixinBind(AccountWeixinBind accountWeixinBind) {
		weixinBindDAO.saveAndFlush(accountWeixinBind);

	}

	@Override
	public boolean isBind(String wxId, String userId) {
		AccountWeixinBind account = weixinBindDAO.findByWXIdAndUserId(wxId, userId);
		return null == account ? false : true;
	}

}
