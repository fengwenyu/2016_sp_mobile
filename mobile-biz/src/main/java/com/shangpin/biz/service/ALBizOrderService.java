package com.shangpin.biz.service;


import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.OrderResult;
import com.shangpin.biz.service.basic.IBizOrderService;

/**
 * 订单相关的Service
 * 
 * @author cuibinqiang
 * @date 2014-11-4
 *
 */
public interface ALBizOrderService extends IBizOrderService {
    /**
     * 查询订单列表
     * 
     * @param userId
     *            用户ID
     * @param statusType
     *            状态类型
     * @param pageIndex
     *            当前页
     * @param pageNo
     *            每页显示多少
     * @return
     * @author zghw
     */
    public String findOrderList(String userId, String statusType, String pageIndex, String pageSize);

    /**
     * 查询订单列表
     * 
     * @param userId
     *            用户ID
     * @param statusType
     *            状态类型
     * @param pageIndex
     *            当前页
     * @param pageNo
     *            每页显示多少
     * @return
     * @author zghw
     * 
     */
    public OrderResult findOrderListObj(String userId, String statusType, String pageIndex, String pageSize);

   
    /**
     * 获取某一订单详细信息
     * 
     * @param userId
     *            用户ID：用户的唯一标识
     * @param orderId
     *            订单ID
     * @return
     * 
     * @author zhanghongwei
     */
    public String findOrderDetail(String userId, String orderId);

    /**
     * 获取某一订单详细信息
     * 
     * @param userId
     *            用户ID：用户的唯一标识
     * @param orderId
     *            订单ID
     * @return
     * 
     * @author zhanghongwei
     */
    public OrderItem findOrderDetailObj(String userId, String orderId);
    /**
     * 根据用户ID、订单ID，取消订单
     * 
     * @param userId
     *            用户ID
     * @param orderId
     *            订单ID
     * 
     * @return 操作结果
     */
    public String cancelOrder(String userId, String orderId);

}
