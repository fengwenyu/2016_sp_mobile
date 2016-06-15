package com.shangpin.core.service;

import com.shangpin.core.entity.Account;

/**
 * 
 * @author cuibinqiang
 * 
 */
public interface IAccountService {

    // 保存实体
    public Account save(Account account);

    // 根据ID删除实体
    public void delete(Long id);

    // 修改实体
    public Account modify(Account account);

    // 根据ID查询实体
    public Account findById(Long id);

    // 根据userId查询实体
    public Account findByUserId(String userId);
    
    //根据loginName查询实体
    public Account findByLoginName(String longinName);

}
