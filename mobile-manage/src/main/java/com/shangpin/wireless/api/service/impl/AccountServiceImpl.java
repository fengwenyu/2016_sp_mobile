package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.service.AccountService;
import com.shangpin.wireless.dao.AccountDao;
import com.shangpin.wireless.domain.Account;
import com.shangpin.wireless.util.HqlHelper;

/**
 * push设置
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-09-16
 */
@Service(AccountService.SERVICE_NAME)
public class AccountServiceImpl implements AccountService {
	@Resource(name = AccountDao.DAO_NAME)
	private AccountDao accountDao;
	public List executeHql(String hql) throws Exception {
		return accountDao.executeHql(hql);
	}
	/**
	 * 保存实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-16
	 * @param entity
	 * @throws Exception
	 * @Return:
	 */

	public void save(Account model) throws Exception {
		accountDao.save(model);
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-16
	 * @param id
	 * @throws Exception
	 * @Return:
	 */
	public Account getById(Long id) throws Exception {
		return accountDao.getById(id);
	}

	/**
	 *根据userId查询pushconf_aolai
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-16
	 * @param hqlHelper
	 *            查询对象
	 * @Return:
	 */
	public Account getByCondition(HqlHelper hqlHelper) throws Exception {
		return accountDao.getByCondition(hqlHelper);
	}

	/**
	 * 更新实体
	 * 
	 * @Author: zhouyu
	 * 
	 * @CreateDate: 2012-09-16
	 * @param entity
	 *            实体
	 * @Return:
	 */
	public void update(Account model) throws Exception {
		accountDao.update(model);
	}

}
