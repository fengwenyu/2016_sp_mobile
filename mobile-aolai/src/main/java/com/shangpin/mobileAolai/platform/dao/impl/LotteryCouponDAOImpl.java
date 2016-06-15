package com.shangpin.mobileAolai.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.mobileAolai.base.model.Coupon;
import com.shangpin.mobileAolai.common.persistence.GenericDAOHibernateImpl;
import com.shangpin.mobileAolai.platform.dao.LotteryCouponDAO;

@Repository("lotteryCouponDAO")
public class LotteryCouponDAOImpl  extends GenericDAOHibernateImpl<Coupon, Long> implements LotteryCouponDAO {
	
	private static final long serialVersionUID = 8706772920292517438L;
	
}
