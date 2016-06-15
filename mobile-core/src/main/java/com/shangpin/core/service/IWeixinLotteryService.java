package com.shangpin.core.service;

import com.shangpin.core.entity.WeixinLottery;

public interface IWeixinLotteryService {

    /**
     * 增加实体对象
     * 
     * @param weixinLottery
     * @return
     * @author zhanghongwei
     */
    public WeixinLottery add(WeixinLottery weixinLottery);

    /**
     * 更新实体对象
     * 
     * @param weixinLottery
     * @return
     * @author zhanghongwei
     */
    public WeixinLottery modify(WeixinLottery weixinLottery);

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
    public WeixinLottery findById(Long id);

}
