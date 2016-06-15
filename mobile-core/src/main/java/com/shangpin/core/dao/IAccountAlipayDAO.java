package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.AccountAlipay;

public interface IAccountAlipayDAO extends JpaRepository<AccountAlipay, Long>, JpaSpecificationExecutor<AccountAlipay> {

    @Query("select a from AccountAlipay a where a.userId = ?1")
    public AccountAlipay findByUserId(String userId);

}
