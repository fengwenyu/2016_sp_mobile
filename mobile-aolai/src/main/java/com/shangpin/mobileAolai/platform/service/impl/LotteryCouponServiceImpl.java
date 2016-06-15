package com.shangpin.mobileAolai.platform.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.mobileAolai.base.model.Coupon;
import com.shangpin.mobileAolai.platform.dao.LotteryCouponDAO;
import com.shangpin.mobileAolai.platform.service.LotteryCouponService;

@Service("lotteryCouponService")
public class LotteryCouponServiceImpl implements LotteryCouponService {
	
	@Autowired
	private LotteryCouponDAO lotteryCouponDAO;

	@SuppressWarnings("unchecked")
	@Override
	public List<Coupon> findLotteryCoupon(String couponType) {
		String del = "0";
		DetachedCriteria criteria = DetachedCriteria.forClass(Coupon.class).add(Restrictions.and(Restrictions.eq("couponType", couponType), Restrictions.eq("del", del)));
		List<Coupon> coupons = lotteryCouponDAO.findByCriteria(criteria, 1, 1);
		return coupons;
	}

	@Override
	public void upate(Coupon coupon) {
		
		lotteryCouponDAO.saveOrUpdate(coupon);
		
	}

}
