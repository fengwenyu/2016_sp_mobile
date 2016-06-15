package com.shangpin.wireless.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.AccountAlipayDao;
import com.shangpin.wireless.api.domain.AccountAlipay;
import com.shangpin.wireless.api.service.AccountAlipayService;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 支付宝钱包登录连接
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-08-01
 */
@Service(AccountAlipayService.SERVICE_NAME)
public class AccountAlipayServiceImpl implements AccountAlipayService {
	@Resource(name = AccountAlipayDao.DAO_NAME)
	private AccountAlipayDao accountDao;

	/**
	 * 保存实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @throws Exception
	 * @Return:
	 */

	public void save(AccountAlipay model) throws Exception {
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
	public AccountAlipay getById(Long id) throws Exception {
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
	public AccountAlipay getByCondition(HqlHelper hqlHelper) throws Exception {
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
	public void update(AccountAlipay model) throws Exception {
		accountDao.update(model, "");
	}

}
