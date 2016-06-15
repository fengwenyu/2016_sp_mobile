package com.shangpin.core.service;

import com.shangpin.core.entity.AccountWeixin;

/**
 * 微信用户信息连接
 * 
 * @author cuibinqiang
 * 
 */
public interface IAccountWeixinService {

    // 保存实体
    public AccountWeixin save(AccountWeixin accountWeixin);

    // 删除实体
    public void delete(Long userId);

    // 更新实体
    public AccountWeixin modify(AccountWeixin accountWeixin);
    
    //根据ID查询实体
    public AccountWeixin findById(Long id);

    // 根据weixinId查询实体
    public AccountWeixin findByWeixinId(String weixinId);

}
