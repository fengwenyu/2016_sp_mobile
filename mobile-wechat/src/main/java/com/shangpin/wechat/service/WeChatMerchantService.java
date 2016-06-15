package com.shangpin.wechat.service;

import com.shangpin.wechat.bo.base.CashBonusResult;
import com.shangpin.wechat.bo.base.CashCouponResult;

public interface WeChatMerchantService {

	/**
	 * 现金红包接口
	 * @param re_openid 微信id
	 * @param total_amount 红包金额
	 * @param total_num 红包发放总人数 1
	 * @param wishing 祝福语
	 * @param client_ip ip
	 * @param act_name 活动名称
	 * @param remark 备注
	 * @return
	 */
	public CashBonusResult cashBonus(String re_openid, String total_amount, String total_num, String wishing,
			String client_ip, String act_name, String remark);
	
	/**
	 * 代金券接口
	 * @param coupon_stock_id 代金券批次id
	 * @param openid 微信id
	 * @param userId 可为空 打印日志使用
	 * @return
	 */
	public CashCouponResult cashCoupon(String coupon_stock_id,String openid, String userId);
	
}





