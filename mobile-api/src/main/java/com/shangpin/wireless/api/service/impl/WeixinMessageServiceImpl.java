package com.shangpin.wireless.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.WeixinMessageDao;
import com.shangpin.wireless.api.domain.WeixinMessage;
import com.shangpin.wireless.api.service.WeixinMessageService;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 微信用户信息连接
 * 
 * @Author: wangwenguan
 * @CreatDate: 2013-11-28
 */
@Service(WeixinMessageService.SERVICE_NAME)
public class WeixinMessageServiceImpl implements WeixinMessageService {
	@Resource(name = WeixinMessageDao.DAO_NAME)
	private WeixinMessageDao accountDao;

	/**
	 * 保存实体
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-08-01
	 * @param model
	 * @throws Exception
	 * @Return:
	 */

	public void save(WeixinMessage model) throws Exception {
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
	public WeixinMessage getById(Long id) throws Exception {
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
	public WeixinMessage getByCondition(HqlHelper hqlHelper) throws Exception {
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
	public void update(WeixinMessage model) throws Exception {
		accountDao.update(model, "");
	}

}
