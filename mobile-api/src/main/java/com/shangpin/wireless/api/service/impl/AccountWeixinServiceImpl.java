package com.shangpin.wireless.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.AccountWeixinDao;
import com.shangpin.wireless.api.domain.AccountWeixin;
import com.shangpin.wireless.api.service.AccountWeixinService;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 微信用户信息连接
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-08-19
 */
@Service(AccountWeixinService.SERVICE_NAME)
public class AccountWeixinServiceImpl implements AccountWeixinService {
	@Resource(name = AccountWeixinDao.DAO_NAME)
	private AccountWeixinDao accountDao;

	/**
	 * 保存实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @throws Exception
	 * @Return:
	 */

	public void save(AccountWeixin model) throws Exception {
		accountDao.save(model, "");
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param id
	 * @throws Exception
	 * @Return:
	 */
	public AccountWeixin getById(Long id) throws Exception {
		return accountDao.getById(id, "");
	}

	/**
	 * 根据条件查询
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param hqlHelper
	 *            查询对象
	 * @Return:
	 */
	public AccountWeixin getByCondition(HqlHelper hqlHelper) throws Exception {
		return accountDao.getByCondition(hqlHelper, "");
	}

	/**
	 * 更新实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @Return:
	 */
	public void update(AccountWeixin model) throws Exception {
		accountDao.update(model, "");
	}

}
