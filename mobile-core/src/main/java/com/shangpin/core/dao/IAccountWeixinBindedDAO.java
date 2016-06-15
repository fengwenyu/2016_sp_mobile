package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.AccountWeixinBind;

public interface IAccountWeixinBindedDAO extends JpaRepository<AccountWeixinBind, Long>, JpaSpecificationExecutor<AccountWeixinBind> {

    @Query(value="SELECT * FROM accountweixinbind a WHERE userId= ?1 "
    		+ " AND a.bindTime IS NOT NULL AND a.unbindTime IS NULL ",nativeQuery=true)
    public AccountWeixinBind findByUserId(String userId);
}
