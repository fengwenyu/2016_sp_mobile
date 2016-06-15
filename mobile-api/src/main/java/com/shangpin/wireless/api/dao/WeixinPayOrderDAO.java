package com.shangpin.wireless.api.dao;


import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.WeixinPayOrder;

public interface WeixinPayOrderDAO extends BaseDao<WeixinPayOrder>  {
	public final static String DAO_NAME = "com.shangpin.wireless.api.dao.impl.WeixinPayOrderDaoImpl";

	public WeixinPayOrder findByTransId(String transId);

	public WeixinPayOrder findByOrderId(String orderId);
}
