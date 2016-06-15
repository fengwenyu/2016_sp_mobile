package com.shangpin.pay.service;

import com.shangpin.pay.bo.CommonBackBo;
/**
 * 支付请求接口
 * User: liling
 * Date: 14-11-10
 * Time: 下午3:23
 */
public interface BaseService {
	/**
     * 查询申请限额
     * @param versign 验签结果
     * @param orderId 订单号
     * @param totalFee 订单金额
     * @return 支付相关信息  
     * 
     */
	CommonBackBo getCommonBackBo(boolean versign,String orderId,String totalFee,String tradeNo);
	/**
     * 查询申请限额
     * @param versign 验签结果
     * @param orderId 订单号
     * @param totalFee 订单金额
     * @return 支付相关信息  
     * 
     */
	CommonBackBo getCommonBackBo(boolean versign,String orderId,String totalFee);
}
