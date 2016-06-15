package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.WeixinLotteryDao;
import com.shangpin.wireless.api.domain.WeixinLottery;
import com.shangpin.wireless.api.service.WeixinLotteryService;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 微信用户信息连接
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-08-19
 */
@Service(WeixinLotteryService.SERVICE_NAME)
public class WeixinLotteryServiceImpl implements WeixinLotteryService {
	@Resource(name = WeixinLotteryDao.DAO_NAME)
	private WeixinLotteryDao accountDao;

	/**
	 * 保存实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @throws Exception
	 * @Return:
	 */

	public void save(WeixinLottery model) throws Exception {
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
	public WeixinLottery getById(Long id) throws Exception {
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
	public WeixinLottery getByCondition(HqlHelper hqlHelper) throws Exception {
		return accountDao.getByCondition(hqlHelper, "");
	}
	
	public List<WeixinLottery> getListByCondition(HqlHelper hqlHelper) throws Exception {
		return accountDao.getListByCondition(hqlHelper, "");
	}

	/**
	 * 更新实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @Return:
	 */
	public void update(WeixinLottery model) throws Exception {
		accountDao.update(model, "");
	}

}
