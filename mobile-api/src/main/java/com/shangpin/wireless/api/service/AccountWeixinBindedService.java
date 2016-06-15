package com.shangpin.wireless.api.service;

import com.shangpin.wireless.api.domain.AccountWeixinBinded;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 微信用户信息连接
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-08-20
 */
public interface AccountWeixinBindedService {

	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.AccountWeixinBindedServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @Return:
	 */
	public void save(AccountWeixinBinded model) throws Exception;

	/**
	 *根据userId查询pushconf_aolai
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param hqlHelper
	 *            查询对象
	 * @Return:
	 */
	public AccountWeixinBinded getByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @Return:
	 */
	void update(AccountWeixinBinded model) throws Exception;
}
