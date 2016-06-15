package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IAccountAlipayDAO;
import com.shangpin.core.entity.AccountAlipay;
import com.shangpin.core.service.IAccountAlipayService;

@Service
@Transactional
public class AccountAlipayServiceImpl implements IAccountAlipayService {

    @Autowired
    private IAccountAlipayDAO accountAlipayDAO;

    @Override
    public AccountAlipay save(AccountAlipay accountAlipay) {
        return accountAlipayDAO.save(accountAlipay);
    }

    @Override
    public AccountAlipay findByUserId(String userId) {
        AccountAlipay accountAlipay = accountAlipayDAO.findByUserId(userId);
        return accountAlipay;
    }

    @Override
    public AccountAlipay modify(AccountAlipay accountAlipay) {
        return accountAlipayDAO.save(accountAlipay);
    }

    @Override
    public void delete(Long id) {
        accountAlipayDAO.delete(id);
    }

    @Override
    public AccountAlipay findById(Long id) {
        return accountAlipayDAO.findOne(id);
    }

}
