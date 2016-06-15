package com.shangpin.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.OrderService;
import com.shangpin.base.service.ShoppingCartService;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.OrderResult;
import com.shangpin.biz.bo.base.ResultObjOneResult;
import com.shangpin.biz.service.ALBizOrderService;
import com.shangpin.biz.service.abstraction.AbstractBizOrderService;
import com.shangpin.utils.JsonUtil;

@Service
public class ALBizOrderServiceImpl extends AbstractBizOrderService implements ALBizOrderService {

    public static Logger logger = LoggerFactory.getLogger(ALBizOrderServiceImpl.class);

    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private OrderService orderService;

    @Override
    public String findOrderList(String userId, String statusType, String pageIndex, String pageSize) {
        String json = orderService.getOrderlist(userId, statusType, pageIndex, pageSize,null);
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;
    }

    @Override
    public OrderResult findOrderListObj(String userId, String statusType, String pageIndex, String pageSize) {
        try {
            String json = findOrderList(userId, statusType, pageIndex, pageSize);
            if (!StringUtils.isEmpty(json)) {
                ResultObjOneResult<OrderResult> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOneResult<OrderResult>>() {
                });
                if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
                    return obj.getResult();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String findOrderDetail(String userId, String orderId) {
        String json = orderService.findOrderNew(userId, orderId);
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;

    }

    @Override
    public OrderItem findOrderDetailObj(String userId, String orderId) {
        try {
            String json = findOrderDetail(userId, orderId);
            if (!StringUtils.isEmpty(json)) {
                ResultObjOneResult<OrderItem> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOneResult<OrderItem>>() {
                });
                if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
                    return obj.getResult();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String cancelOrder(String userId, String orderId) {
        String json = orderService.cancelOrder(userId, orderId);
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;
    }
}
