package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.WeiXinPacketCoupon;





/**
 * @author tongwenli
 *微信红包兑换券业务逻辑接口
 */
public interface IWeiXinPacketCouponService {
	
    public WeiXinPacketCoupon save(WeiXinPacketCoupon account);
	
	public List<WeiXinPacketCoupon> findByAccountName(String accountName);
	
	public void delete(long id);

	public List<WeiXinPacketCoupon> queryNumber(int number, String date);

	public List<WeiXinPacketCoupon> queryCoupon(String openId, String todayStr);
}
