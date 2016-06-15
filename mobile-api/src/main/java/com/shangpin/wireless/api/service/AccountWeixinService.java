package com.shangpin.wireless.api.service;

import com.shangpin.wireless.api.domain.AccountWeixin;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 微信用户信息连接
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-08-19
 */
public interface AccountWeixinService {

	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.AccountWeixinServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @Return:
	 */
	public void save(AccountWeixin model) throws Exception;

	/**
	 *根据userId查询pushconf_aolai
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param hqlHelper
	 *            查询对象
	 * @Return:
	 */
	public AccountWeixin getByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @Return:
	 */
	void update(AccountWeixin model) throws Exception;
}
