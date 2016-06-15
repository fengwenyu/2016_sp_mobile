package com.shangpin.wireless.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.AccountWeixinBindedDao;
import com.shangpin.wireless.api.domain.AccountWeixinBinded;
import com.shangpin.wireless.api.service.AccountWeixinBindedService;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 微信绑定用户信息连接
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-08-20
 */
@Service(AccountWeixinBindedService.SERVICE_NAME)
public class AccountWeixinBindedServiceImpl implements AccountWeixinBindedService {
	@Resource(name = AccountWeixinBindedDao.DAO_NAME)
	private AccountWeixinBindedDao accountDao;

	/**
	 * 保存实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @throws Exception
	 * @Return:
	 */

	public void save(AccountWeixinBinded model) throws Exception {
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
	public AccountWeixinBinded getById(Long id) throws Exception {
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
	public AccountWeixinBinded getByCondition(HqlHelper hqlHelper) throws Exception {
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
	public void update(AccountWeixinBinded model) throws Exception {
		accountDao.update(model, "");
	}

}
