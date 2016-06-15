package com.shangpin.wireless.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.AccountDao;
import com.shangpin.wireless.api.domain.Account;
import com.shangpin.wireless.api.service.AccountService;
import com.shangpin.wireless.api.util.HqlHelper;

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

	/**
	 * 保存实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-16
	 * @param entity
	 * @param dbType
	 *            数据库类型
	 * @throws Exception
	 * @Return:
	 */

	public void save(Account model, String dbType) throws Exception {
//		 accountDao.save(model, dbType);
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-16
	 * @param id
	 * @param dbType
	 *            数据库类型
	 * @throws Exception
	 * @Return:
	 */
	public Account getById(Long id, String dbType) throws Exception {
		return accountDao.getById(id, dbType);
	}

	/**
	 *根据userId查询pushconf_aolai
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-16
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public Account getByCondition(HqlHelper hqlHelper, String dbType) throws Exception {
		return accountDao.getByCondition(hqlHelper, dbType);
	}

	/**
	 * 更新实体
	 * 
	 * @Author: zhouyu
	 * 
	 * @CreateDate: 2012-09-16
	 * @param entity
	 *            实体
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public void update(Account model, String dbType) throws Exception {
//		 accountDao.update(model, dbType);
	}

}
