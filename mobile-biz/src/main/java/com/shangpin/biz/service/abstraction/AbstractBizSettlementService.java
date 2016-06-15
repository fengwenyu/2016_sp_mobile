package com.shangpin.biz.service.abstraction;

import com.shangpin.base.service.SettlementService;
import com.shangpin.base.vo.Address;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.orderUnion.SettlementUnion;
import com.shangpin.biz.domain.settlement.BuyNowDo;
import com.shangpin.biz.domain.settlement.RefreshDo;
import com.shangpin.biz.utils.ObjectUtil;
import com.shangpin.utils.JSONUtils;

import com.shangpin.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * <br/>
 * Date: 2016/4/23<br/>
 *
 * @author ZRS
 * @since JDK7
 */
public abstract class AbstractBizSettlementService {

    public static Logger logger = org.slf4j.LoggerFactory.getLogger(AbstractBizSettlementService.class);

    @Autowired
    private SettlementService settlementService;

    /**
     * 购物车结算
     *
     * @param userid             用户id
     * @param buyId              购物车id
     * @param isDefaultUseCoupon 1使用优惠券，0 不使用
     * @return
     */
    public ResultObjOne<SettlementUnion> doCartSettlement(String userid, String buyId, String isDefaultUseCoupon) {
        String json = settlementService.cartSettlement(userid,buyId,isDefaultUseCoupon);

        logger.debug("调用base接口返回数据:" + json);
        if(StringUtils.isBlank(json)){
            return null;
        }

        ResultObjOne<SettlementUnion> obj = null;
        try {
            obj = JSONUtils.toGenericsCollection(json, ResultObjOne.class, SettlementUnion.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;

    }

    /**
     * 立即购买结算
     *
     * @param buyNowDo BuyNowDo
     * @return
     */
    public ResultObjOne<SettlementUnion> doBuyNowSettlement(BuyNowDo buyNowDo) {

        Map<String,String> map = new HashMap<>();

        ObjectUtil.beanToMap(buyNowDo, map);
        //如果是null,去掉参数
        if(map.containsKey("channelId") && map.containsKey("channelNo")){
            if(StringUtil.isEmpty(map.get("channelId"),map.get("channelNo"))){
                map.remove("channelId");
                map.remove("channelNo");
            }
        }
        String json = settlementService.buyNowSettlement(map);
        if(StringUtils.isBlank(json)){
            return null;
        }

        logger.debug("调用base接口返回数据:" + json);
        ResultObjOne<SettlementUnion> obj = null;
        try {
            obj = JSONUtils.toGenericsCollection(json, ResultObjOne.class, SettlementUnion.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * 刷新结算页
     *
     * @param refreshDo
     * @return
     */
    public ResultObjOne<SettlementUnion> doRefreshSettlement(RefreshDo refreshDo) {

        Map<String,String> map = new HashMap<>();

        ObjectUtil.beanToMap(refreshDo, map);

        String json = settlementService.refreshSettlement(map);
        if(StringUtils.isBlank(json)){
            return null;
        }

        logger.debug("调用base接口返回数据:" + json);
        ResultObjOne<SettlementUnion> obj = null;
        try {
            obj = JSONUtils.toGenericsCollection(json, ResultObjOne.class, SettlementUnion.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
    
    
    
    public String doaddresslist(String userId,String buyId,String receiveId,String ordersourceId,String buyType) {


        Map<String,String> map = new HashMap<>();

        map.put("userId", userId);
        map.put("buyId", buyId);
        map.put("receiveId", receiveId);
        map.put("ordersourceId", ordersourceId);
        map.put("buyType", buyType);
        String json = settlementService.doaddresslist(map);
        if(StringUtils.isBlank(json)){
            return null;
        }

        logger.debug("调用base接口返回数据:" + json);

        return json;
    
	}
    
    
    
    
    
}
