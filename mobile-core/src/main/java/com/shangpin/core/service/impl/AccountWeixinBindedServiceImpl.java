package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IAccountWeixinBindedDAO;
import com.shangpin.core.entity.AccountWeixinBind;
import com.shangpin.core.service.IAccountWeixinBindedService;

@Service
@Transactional
public class AccountWeixinBindedServiceImpl implements IAccountWeixinBindedService {

    @Autowired
    private IAccountWeixinBindedDAO accountWeixinBindedDAO;

    @Override
    public AccountWeixinBind save(AccountWeixinBind accountWeixinBind) {
		
       return accountWeixinBindedDAO.save(accountWeixinBind);
    }

    @Override
    public AccountWeixinBind modify(AccountWeixinBind accountWeixinBind) {
       return accountWeixinBindedDAO.save(accountWeixinBind);
    }

    @Override
    public void delete(Long id) {
        accountWeixinBindedDAO.delete(id);
    }

    @Override
    public AccountWeixinBind findById(Long id) {
        return accountWeixinBindedDAO.findOne(id);
    }

    @Override
    public AccountWeixinBind findByUserId(String userId) {
        return accountWeixinBindedDAO.findByUserId(userId);
    }

}
