package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.main.Account;

public interface AccountDao extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account>{

    public Account findByLoginName(String loginName);
}
