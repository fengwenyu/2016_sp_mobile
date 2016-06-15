package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.AccountDao;
import com.shangpin.core.entity.main.Account;
import com.shangpin.core.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    
    @Autowired
    private AccountDao accountDao;

    @Override
    public Account findByLoginName(String loginName) {
        return accountDao.findByLoginName(loginName);
    }

}
