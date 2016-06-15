package com.shangpin.core.service;

import com.shangpin.core.entity.AccountWeixinBind;

/**
 * 微信用户信息连接
 * 
 * @author cuibinqiang
 * 
 */
public interface IAccountWeixinBindedService {

    // 保存实体
    public AccountWeixinBind save(AccountWeixinBind accountWeixinBind);

    // 根据ID删除实体
    public void delete(Long id);

    // 更新实体
    public AccountWeixinBind modify(AccountWeixinBind accountWeixinBind);

    // 根据ID查询实体
    public AccountWeixinBind findById(Long id);

    // 根据userId查询
    public AccountWeixinBind findByUserId(String userId);

}
