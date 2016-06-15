package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.dailyCoupons.CouponStatus;
import com.shangpin.biz.bo.dailyCoupons.DailyCouponsList;

/**
 * 天天抢券业务层接口
 * @author zkj
 *
 */
public interface SPBizDailyCouponsService {

	/**
	 * 天天抢券列表显示
	 * @param userId
	 * @param order
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public DailyCouponsList getCouponsList(String userId,String order,String type,String pageIndex,String pageSize);
	
	/**
	 * 天天抢券判断用户是否领取该券
	 * @param activeCode
	 * @param userId
	 * @return
	 */
	public CouponStatus isGetCoupons(String activeCode,String userId);
	
	/**
	 * 判断用户领取券的状态
	 * @param activeCode 激活码编号，支持批量查询，多个激活码用“，”隔开
	 * @param userId
	 * @return
	 */
	public List<CouponStatus> getCouponStatus(String activeCode, String userId);
	
}
