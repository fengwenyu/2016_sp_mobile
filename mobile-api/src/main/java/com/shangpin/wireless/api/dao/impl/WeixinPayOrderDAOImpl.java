package com.shangpin.wireless.api.dao.impl;




import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.WeixinPayOrderDAO;
import com.shangpin.wireless.api.domain.NewProductBrand;
import com.shangpin.wireless.api.domain.WeixinPayOrder;


@Repository(WeixinPayOrderDAO.DAO_NAME)
public class WeixinPayOrderDAOImpl extends BaseDaoImpl<WeixinPayOrder> implements WeixinPayOrderDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8049187799638945099L;

	@Override
	public WeixinPayOrder findByTransId(String transId) {
		Query query = getSession(null).createQuery("select t from WeixinPayOrder t where t.transId=?");
		query.setString(0, transId);
		List<WeixinPayOrder> list=query.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	
	}

	@Override
	public WeixinPayOrder findByOrderId(String orderId) {
		Query query = getSession(null).createQuery("select t from WeixinPayOrder t where t.orderNo=?");
		query.setString(0, orderId);
		List<WeixinPayOrder> list=query.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
