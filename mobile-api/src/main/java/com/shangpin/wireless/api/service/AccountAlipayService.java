package com.shangpin.wireless.api.service;

import com.shangpin.wireless.api.domain.AccountAlipay;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 支付宝钱包登录连接
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-08-01
 */
public interface AccountAlipayService {

	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.AccountAlipayServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @Return:
	 */
	public void save(AccountAlipay model) throws Exception;

	/**
	 *根据userId查询pushconf_aolai
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param hqlHelper
	 *            查询对象
	 * @Return:
	 */
	public AccountAlipay getByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @Return:
	 */
	void update(AccountAlipay model) throws Exception;
}
