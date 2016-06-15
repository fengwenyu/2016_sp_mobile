package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.WeiXinPacketCoupon;

/**
 * @author tongwenli
 *微信红包现金兑换券数据处理接口，访问数据库数据
 */
public interface IWeiXinPacketCouponDao  extends JpaRepository<WeiXinPacketCoupon, Long>, JpaSpecificationExecutor<WeiXinPacketCoupon> {

	public List<WeiXinPacketCoupon> findByOpenId(String OpenId);
	
//	@Query("select count(*) from WeiXinPacketCoupon coupon where coupon.couponValue = ?1 and coupon.createTime = ?2")
//	public int countValue(int value, String date);
	
	public List<WeiXinPacketCoupon> findByCouponValueAndCreateTime(int value, String date);

	public List<WeiXinPacketCoupon> findByOpenIdAndCreateTime(String openId, String todayStr);
	
	
	
}
