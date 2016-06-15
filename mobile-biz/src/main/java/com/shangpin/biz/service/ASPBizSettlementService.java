package com.shangpin.biz.service;

import com.shangpin.base.vo.Address;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.orderUnion.SettlementUnion;
import com.shangpin.biz.domain.settlement.BuyNowDo;
import com.shangpin.biz.domain.settlement.RefreshDo;

/**
 * 结算页面<br/>
 * Date: 2016/4/22<br/>
 *
 * @author ZRS
 * @since JDK7
 */
public interface ASPBizSettlementService {

    /**
     * 购物车结算
     * @param userid 用户id
     * @param buyId 购物车id
     * @param isDefaultUseCoupon 1使用优惠券，0 不使用
     * @return
     */
    ResultObjOne<SettlementUnion> cartSettlement(String userid, String buyId, String isDefaultUseCoupon);

    /**
     * 立即购买结算
     * @param buyNowDo BuyNowDo
     * @return
     */
    ResultObjOne<SettlementUnion> buyNowSettlement(BuyNowDo buyNowDo);

    /**
     * 刷新结算页
     * @param refreshDo RefreshDo
     * @return
     */
    ResultObjOne<SettlementUnion> refreshSettlement(RefreshDo refreshDo);

    /**
     * 地址列表
     * @param refreshDo
     * @return
     */
    
    
    
    String addresslist(String userid,String buyId,String receiveId,String ordersourceId,String buyType);
}
