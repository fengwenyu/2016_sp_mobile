package com.shangpin.wireless.api.service;
import com.shangpin.wireless.api.domain.BestpayorderShangpin;
import com.shangpin.wireless.api.util.HqlHelper;

/**
 * 翼支付生成订单service
 * 
 * @Author: wangfeng
 * @CreatDate: 2013-09-11
 */
public interface BestpayByOrderService {

	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.BestpayByOrderServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author: wangfeng
	 * @CreateDate: 2013-09-11
	 * @param model
	 * @Return:
	 */
	public void save(BestpayorderShangpin bestpayorderShangpin,String dbtype) throws Exception;
	/**
	 *根据orderid查询订单详情
	 * 
	 * @Author: wangfeng
	 * @CreateDate: 2013-09-11
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return:
	 */
	public BestpayorderShangpin getByCondition(HqlHelper hqlHelper, String dbType) throws Exception;
	

	
}
