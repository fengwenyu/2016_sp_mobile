package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IWeiXinPacketCouponDao;
import com.shangpin.core.entity.WeiXinPacketCoupon;
import com.shangpin.core.service.IWeiXinPacketCouponService;
/**
 * 
 * @author tongwenli
 *微信红包兑换券业务逻辑接口实现类
 */



@Service
@Transactional
public class WeiXinPacketCouponServiceImpl implements IWeiXinPacketCouponService {
	
	@Autowired
	private IWeiXinPacketCouponDao WeiXinPacketCouponDao;



	@Override
	public void delete(long id) {
	}



	@Override
	public WeiXinPacketCoupon save(WeiXinPacketCoupon coupon) {
		
		return  WeiXinPacketCouponDao.save(coupon);
	}



	@Override
	public List<WeiXinPacketCoupon> findByAccountName(String accountName) {
		return WeiXinPacketCouponDao.findByOpenId(accountName);
	}



	@Override
	public List<WeiXinPacketCoupon> queryNumber(int number, String date) {
		
		return WeiXinPacketCouponDao.findByCouponValueAndCreateTime(number,date);
	}



	@Override
	public List<WeiXinPacketCoupon> queryCoupon(String openId, String todayStr) {
		
		return WeiXinPacketCouponDao.findByOpenIdAndCreateTime(openId,todayStr);
	}


}
