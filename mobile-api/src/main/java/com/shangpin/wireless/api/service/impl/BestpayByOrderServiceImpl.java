package com.shangpin.wireless.api.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shangpin.wireless.api.dao.BestpayByOrderDao;
import com.shangpin.wireless.api.domain.BestpayorderShangpin;
import com.shangpin.wireless.api.service.BestpayByOrderService;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 *  翼支付生成订单实现类
 * 
 * @Author: wangfeng
 * @CreatDate: 2013-09-11
 */
@Service(BestpayByOrderService.SERVICE_NAME)
public class BestpayByOrderServiceImpl implements BestpayByOrderService {
	@Resource(name = BestpayByOrderDao.DAO_NAME)
	private BestpayByOrderDao bestpayByOrderDao;

	/**
	 * 保存实体
	 * 
	 * @Author: wangfeng
	 * @CreateDate: 2013-09-11
	 * @param entity
	 * @param dbType
	 *            数据库类型
	 * @throws Exception
	 * @Return:
	 */
	@Override
	public void save(BestpayorderShangpin bestpayorderShangpin,String dbType)
			throws Exception {
		bestpayByOrderDao.save(bestpayorderShangpin,dbType);
		
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
	public BestpayorderShangpin getByCondition(HqlHelper hqlHelper, String dbType) throws Exception {
		return bestpayByOrderDao.getByCondition(hqlHelper, "");
	}
	
}
