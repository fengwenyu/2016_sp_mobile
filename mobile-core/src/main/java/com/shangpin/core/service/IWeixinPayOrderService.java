package com.shangpin.core.service;

import com.shangpin.core.entity.WeixinPayOrder;

public interface IWeixinPayOrderService {

    /**
     * 增加实体对象
     * 
     * @param weixinPayOrder
     * @return
     * @author zhanghongwei
     */
    public WeixinPayOrder add(WeixinPayOrder weixinPayOrder);

    /**
     * 更新实体对象
     * 
     * @param weixinPayOrder
     * @return
     * @author zhanghongwei
     */
    public WeixinPayOrder modify(WeixinPayOrder weixinPayOrder);

    /**
     * 删除实体对象
     * 
     * @param id
     * @author zhanghongwei
     */
    public void deleteById(Long id);

    /**
     * 根据ID查询实体对象
     * 
     * @param id
     * @return
     * @author zhanghongwei
     */
    public WeixinPayOrder findById(Long id);

    /**
     * 根据订单编号查询实体对象
     * 
     * @param orderNo
     *            订单编号
     * @return
     * @author zhanghongwei
     */
    public WeixinPayOrder findByOrderNo(String orderNo);

    /**
     * 根据交易ID查询实体对象
     * 
     * @param transId
     *            交易ID
     * @return
     * @author zhanghongwei
     */
    public WeixinPayOrder findByTransId(String transId);

}
