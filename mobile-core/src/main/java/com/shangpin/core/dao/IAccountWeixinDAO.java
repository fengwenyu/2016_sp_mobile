package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.AccountWeixin;

public interface IAccountWeixinDAO extends JpaRepository<AccountWeixin, Long>, JpaSpecificationExecutor<AccountWeixin> {

    @Query("select a from AccountWeixin a where a.weixinId = ?1")
    public AccountWeixin findByWeixinId(String weixinId);
}
