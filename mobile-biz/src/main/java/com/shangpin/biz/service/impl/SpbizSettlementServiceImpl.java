package com.shangpin.biz.service.impl;

import com.shangpin.base.service.CouponsService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.biz.bo.Receive;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.orderUnion.CouponsWraper;
import com.shangpin.biz.bo.orderUnion.OrderSubmitParam;
import com.shangpin.biz.bo.orderUnion.OrderSubmitResult;
import com.shangpin.biz.bo.orderUnion.SettlementUnion;
import com.shangpin.biz.domain.settlement.BuyNowDo;
import com.shangpin.biz.domain.settlement.RefreshDo;
import com.shangpin.biz.service.SPBizSettlementService;
import com.shangpin.biz.service.abstraction.AbstractBizSettlementService;
import com.shangpin.biz.utils.ObjectUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JSONUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liushaoqing
 * 结算页，结算，立即购买，刷新结算，订单提交，业务逻辑封装
 */
@Service
public class SpbizSettlementServiceImpl extends AbstractBizSettlementService implements SPBizSettlementService{

    @Autowired
    private CouponsService couponsService;

    private StringBuilder submitOrderURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/ConfirmOrderV2");
    /**
     * 购物车结算
     *
     * @param userid             用户id
     * @param buyId              购物车id
     * @param isDefaultUseCoupon 1使用优惠券，0 不使用
     * @return
     */
    @Override
    public SettlementUnion cartSettlement(String userid, String buyId, String isDefaultUseCoupon) {
        try {
            ResultObjOne<SettlementUnion> obj= this.doCartSettlement(userid, buyId, isDefaultUseCoupon);
            if (obj != null && obj.isSuccess()) {
                return obj.getObj();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 立即购买结算
     *
     * @param buyNowDo BuyNowDo
     * @return
     */
    @Override
    public SettlementUnion buyNowSettlement(BuyNowDo buyNowDo) {

        try{
            ResultObjOne<SettlementUnion> obj = this.doBuyNowSettlement(buyNowDo);
            if (obj != null && obj.isSuccess()) {
                return obj.getObj();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 刷新结算页
     *
     * @param refreshDo@return
     */
    @Override
    public Map<String,Object> refreshSettlement(RefreshDo refreshDo) {

        Map<String,Object> map = new HashMap<>();
        try{
            ResultObjOne<SettlementUnion> obj = this.doRefreshSettlement(refreshDo);
            if (obj != null && obj.isSuccess()) {
                map.put("cartUnion",obj.getContent());
            }
                map.put("code",obj.getCode());
            map.put("msg",obj.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public CouponsWraper accessCoupons(String userId, String pageIndex, String pageSize, String buyId, String orderSource, String couponNo, String useCouponNo) {

        String json = couponsService.canUseCoupons(userId, pageIndex, pageSize, buyId, orderSource, couponNo, useCouponNo);
        logger.debug("调用base接口返回数据:" + json);
        if(!StringUtil.isNotEmpty(json)){
            return null;
        }else{
            ResultObjOne<CouponsWraper> obj = null;
            try {
                obj = JSONUtils.toGenericsCollection(json, ResultObjOne.class, CouponsWraper.class);
                if (obj != null && obj.isSuccess()) {
                    return obj.getObj();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public List<Receive> getAccessAddress(String userId, String buyId, String receiveId,String orderSourceId,String buyType) {

        String json = super.doaddresslist(userId,buyId,receiveId,orderSourceId,buyType);
        logger.debug("调用base接口返回数据:" + json);
        if(!StringUtil.isNotEmpty(json)){
            return null;
        }
        ResultObjMapList<Receive> obj = null;
        try {
            obj = JSONUtils.toGenericsCollection(json, ResultObjMapList.class,Receive.class);
            if (obj != null && obj.isSuccess()) {
                return obj.getList("list");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String,Object> submitOrder(OrderSubmitParam orderSubmitParam) {

        Map<String,String> map_pa = new HashMap<>();
        ObjectUtil.beanToMap(orderSubmitParam, map_pa);
        String result = "";
        try {
            result = HttpClientUtil.doPost(submitOrderURL.toString(), map_pa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(StringUtils.isBlank(result)){
            return null;
        }
        logger.debug("调用base接口返回数据:" + result);
        Map<String,Object> map = new HashMap<>();
        ResultObjOne<OrderSubmitResult> obj = null;
        try {
            obj = JSONUtils.toGenericsCollection(result, ResultObjOne.class, OrderSubmitResult.class);
            if (obj!=null && obj.isSuccess()) {
                map.put("code", "0");
                map.put("orderInfo", obj.getObj());
                return map;
            }else {
                map.put("code", obj.getCode());
                map.put("msg", obj.getMsg());
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
