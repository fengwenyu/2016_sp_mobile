package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IWeiXinPacketAccountDao;
import com.shangpin.core.entity.WeiXinPacketAccount;
import com.shangpin.core.service.IWeiXinPacketAccountService;

/**
 * @author qinyingchun
 *微信红包账户业务逻辑处理接口实现类
 */
@Service
@Transactional
public class WeiXinPacketAccountServiceImpl implements IWeiXinPacketAccountService{
	
	@Autowired
	private IWeiXinPacketAccountDao weiXinPacketAccountDao;

	@Override
	public WeiXinPacketAccount save(WeiXinPacketAccount account) {
		return weiXinPacketAccountDao.save(account);
	}

	@Override
	public WeiXinPacketAccount findById(long id) {
		return weiXinPacketAccountDao.findOne(id);
	}

	@Override
	public WeiXinPacketAccount findByLoginName(String loginName) {
		return weiXinPacketAccountDao.findByLoginName(loginName);
	}

	@Override
	public WeiXinPacketAccount modify(WeiXinPacketAccount account) {
		return weiXinPacketAccountDao.save(account);
	}

	@Override
	public void delete(long id) {
		weiXinPacketAccountDao.delete(id);
	}

	@Override
	public WeiXinPacketAccount findByOpenId(String openId) {
		return weiXinPacketAccountDao.findByOpenId(openId);
	}

}
