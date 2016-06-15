package com.shangpin.biz.service;

import com.shangpin.biz.bo.Receive;
import com.shangpin.biz.bo.orderUnion.CouponsWraper;
import com.shangpin.biz.bo.orderUnion.OrderSubmitParam;
import com.shangpin.biz.bo.orderUnion.OrderSubmitResult;
import com.shangpin.biz.bo.orderUnion.SettlementUnion;
import com.shangpin.biz.domain.settlement.BuyNowDo;
import com.shangpin.biz.domain.settlement.RefreshDo;

import java.util.List;
import java.util.Map;

/**
 * 结算页面<br/>
 * Date: 2016/4/22<br/>
 *
 * @author liushaoqing
 * @since JDK7
 * 结算页面立即购买，结算，刷新结算数据，订单提交接口
 */
public interface SPBizSettlementService {

    /**
     * 购物车结算
     * @param userid 用户id
     * @param buyId 购物车id
     * @param isDefaultUseCoupon 1使用优惠券，0 不使用
     * @return
     */
    SettlementUnion cartSettlement(String userid, String buyId, String isDefaultUseCoupon);

    /**
     * 立即购买结算
     * @param buyNowDo BuyNowDo
     * @return
     */
    SettlementUnion buyNowSettlement(BuyNowDo buyNowDo);

    /**
     * 刷新结算页
     * @param refreshDo RefreshDo
     * @return
     */
    Map<String,Object> refreshSettlement(RefreshDo refreshDo);

    /**
     * 可用优惠券
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @param buyId
     * @param orderSource
     * @param couponNo
     * @param useCouponNo
     * @return
     */
    CouponsWraper accessCoupons(String userId, String pageIndex, String pageSize, String buyId, String orderSource, String couponNo, String useCouponNo);

    /**
     * 可用送货地址
     * @param userId
     * @param shopCartDetailIds
     * @param s
     * @return
     */
    List<Receive> getAccessAddress(String userId, String shopCartDetailIds, String s,String orderSourceId,String buyType);

    /**
     * 结算订单提交
     * @param orderSubmitParam
     */
    Map<String,Object> submitOrder(OrderSubmitParam orderSubmitParam);
}
