package com.aolai.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aolai.web.utils.Constants;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.OrderResult;
import com.shangpin.biz.service.ALBizOrderService;

/**
 * 订单相关的Controller
 * 
 * @author zghw
 *
 */
@Controller
public class OrderController extends BaseController {

    public static Logger logger = LoggerFactory.getLogger(OrderController.class);
    /** 订单列表 */
    public static final String ORDER_LIST = "order/list";
    /** 订单详情页 */
    public static final String ORDER_DETAIL = "order/detail";
    @Autowired
    private ALBizOrderService orderService;

    /**
     * 订单列表
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/order/list", method = RequestMethod.GET)
    public String orderList(@RequestParam(value = "statusType", defaultValue = Constants.STATUS_WAIT_PAY, required = false) String statusType, String callbackUrl,
            @RequestParam(value = "pageNo", defaultValue = Constants.PAGE_NO_1_STR, required = false) String pageNo, Map<String, Object> map, HttpServletRequest request) {
        String userId = getUserId(request);
        OrderResult orderResult = orderService.findOrderListObj(userId, statusType, pageNo, String.valueOf(Constants.PAGE_SIZE));
        //处理待收货
        OrderResult orderResult2=null;
        if("4".equals(statusType)){
        	 orderResult2 = orderService.findOrderListObj(userId, "3", pageNo, String.valueOf(Constants.PAGE_SIZE));
        }
        // 是否存在订单
        boolean haveOrder = false;
        if (orderResult != null && !StringUtils.isEmpty(orderResult.getTotalItems()) && !("0".equals(orderResult.getTotalItems()))) {
            haveOrder = true;
        }
        // 是否存在加载更多
        boolean isHaveMore = false;
        if (haveOrder && orderResult.isMore()) {
            isHaveMore = true;
        }
        map.put("orderResult", orderResult);
        map.put("orderResult2", orderResult2);
        map.put("statusType", statusType);
        map.put("haveOrder", haveOrder);
        map.put("isHaveMore", isHaveMore);
        map.put("pageNo", pageNo);
        if (!StringUtils.isEmpty(callbackUrl)) {
            return "order/" + callbackUrl;
        }
        return ORDER_LIST;
    }

    /**
     * 订单详情
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/order/detail", method = RequestMethod.GET)
    public String orderDetail(String orderId, Map<String, Object> map, HttpServletRequest request) {
        String userId = getUserId(request);
        OrderItem orderItem = orderService.findOrderDetailObj(userId, orderId);
        map.put("orderItem", orderItem);
        map.put("orderId", orderId);
        return ORDER_DETAIL;
    }

    /**
     * 取消订单
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/order/cancel")
    @ResponseBody
    public String cancel(String orderId, HttpServletRequest request) {
        String userId = getUserId(request);
        String result = orderService.cancelOrder(userId, orderId);
        return result;
    }
}
