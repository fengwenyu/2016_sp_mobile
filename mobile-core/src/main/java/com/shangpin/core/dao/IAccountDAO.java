package com.shangpin.core.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.Account;

public interface IAccountDAO extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    @Query("select a from Account a where a.userId = ?1") 
    public Account findByUserId(String userId);
    
    /**
	 * 根据登录名和产品号获取用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-12-04
	 * @param loginName
	 *            登录名
	 * @Return
	 */
    @Query("select a from Account a where a.loginName = ?1") 
	public Account findAccountByName(String loginName);

}
