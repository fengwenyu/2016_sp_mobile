package com.shangpin.base.service;

import java.util.Map;

/**
 * 结算页<br/>
 * Date: 2016/4/23<br/>
 *
 * @author ZRS
 * @since JDK7
 */
public interface SettlementService {

    /**
     * 购物车结算
     */
    String cartSettlement(String userid, String buyId, String isDefaultUseCoupon);

    /**
     * 立即购买结算
     */
    String buyNowSettlement(Map<String,String> map);

    /**
     * 刷新结算页
     */
    String refreshSettlement(Map<String,String> map);
    
    
    String doaddresslist(Map<String,String> map);
}
