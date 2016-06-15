package com.shangpin.base.service.impl;

import com.shangpin.base.service.SettlementService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.utils.HttpClientUtil;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <br/>
 * Date: 2016/4/23<br/>
 *
 * @author ZRS
 * @since JDK7
 */
@Service
public class SettlementServiceImpl implements SettlementService {

    private static StringBuilder cartSettlementV2URL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/settlementOrderV2");
    private static StringBuilder settlementOrderRefreshURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/settlementOrderRefresh");
    private static StringBuilder settlementGiftCartOrderRefreshURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/settlementGiftOrderRefresh");
    private static StringBuilder buyNowV2V2URL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/buyNowV2");
    
    private static StringBuilder addressURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/mainDeliverAddress");
    
    /**
     * 购物车结算
     *
     * @param userid
     * @param buyId
     * @param isDefaultUseCoupon
     */
    @Override
    public String cartSettlement(String userid, String buyId, String isDefaultUseCoupon) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userid);
        map.put("buyId", buyId);
        map.put("isDefaultUseCoupon", isDefaultUseCoupon);
        String result = "";
        try {
            result = HttpClientUtil.doPost(cartSettlementV2URL.toString(), map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 立即购买结算
     *
     * @param map
     */
    @Override
    public String buyNowSettlement(Map<String, String> map) {
        String result = "";
        try {
            result = HttpClientUtil.doPost(buyNowV2V2URL.toString(), map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 刷新结算页
     *
     * @param map
     */
    @Override
    public String refreshSettlement(Map<String, String> map) {
        String result = "";
        try {

            //赶时间 暂时写  //突然要兼容app实物卡刷新
            if("2".equals(map.get("type")) ){

                result = HttpClientUtil.doPost(settlementGiftCartOrderRefreshURL.toString(), map);
            }
            else if("0".equals(map.get("type")) ){

                result = HttpClientUtil.doPost(settlementOrderRefreshURL.toString(), map);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

	@Override
	public String doaddresslist(Map<String, String> map) {
        String result = "";
        try {
            result = HttpClientUtil.doPost(addressURL.toString(), map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
