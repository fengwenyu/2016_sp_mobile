package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.api.domain.PushconfigAolai;
import com.shangpin.wireless.api.domain.WeixinLottery;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 微信用户信息连接
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-08-20
 */
public interface WeixinLotteryService {

	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.WeixinLotteryServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @Return:
	 */
	public void save(WeixinLottery model) throws Exception;

	public WeixinLottery getById(Long id) throws Exception;
	/**
	 *根据userId查询pushconf_aolai
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param hqlHelper
	 *            查询对象
	 * @Return:
	 */
	public WeixinLottery getByCondition(HqlHelper hqlHelper) throws Exception;
	
	public List<WeixinLottery> getListByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @Return:
	 */
	void update(WeixinLottery model) throws Exception;
}
