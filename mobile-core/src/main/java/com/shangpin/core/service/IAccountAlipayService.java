package com.shangpin.core.service;

import com.shangpin.core.entity.AccountAlipay;

/**
 * 支付宝钱包登录连接
 * 
 * @author cuibinqiang
 * 
 */
public interface IAccountAlipayService {

    // 保存实体
    public AccountAlipay save(AccountAlipay accountAlipay);

    // 根据userId查询实体
    public AccountAlipay findByUserId(String userId);

    // 更新实体
    public AccountAlipay modify(AccountAlipay accountAlipay);

    // 根据ID删除实体
    public void delete(Long id);

    // 根据ID查询实体
    public AccountAlipay findById(Long id);
}
