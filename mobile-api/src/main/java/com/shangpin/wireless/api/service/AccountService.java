package com.shangpin.wireless.api.service;

import com.shangpin.wireless.api.domain.Account;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * Push设置
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-09-16
 */
public interface AccountService {

	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.AccountServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-16
	 * @param model
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public void save(Account model, String dbType) throws Exception;

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
	public Account getByCondition(HqlHelper hqlHelper, String dbType) throws Exception;

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
	void update(Account model, String dbType) throws Exception;
}
