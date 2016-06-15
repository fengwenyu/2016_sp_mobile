package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IAccountWeixinDAO;
import com.shangpin.core.entity.AccountWeixin;
import com.shangpin.core.service.IAccountWeixinService;

@Service
@Transactional
public class AccountWeixinServiceImpl implements IAccountWeixinService {

    @Autowired
    private IAccountWeixinDAO accountWeixinDAO;

    @Override
    public AccountWeixin save(AccountWeixin accountWeixin) {
        return accountWeixinDAO.save(accountWeixin);
    }

    @Override
    public AccountWeixin findByWeixinId(String weixinId) {
        return accountWeixinDAO.findByWeixinId(weixinId);
    }

    @Override
    public AccountWeixin modify(AccountWeixin accountWeixin) {
        return accountWeixinDAO.save(accountWeixin);
    }

    @Override
    public void delete(Long id) {
         accountWeixinDAO.delete(id);
    }

    @Override
    public AccountWeixin findById(Long id) {
        return accountWeixinDAO.findOne(id);
    }

}
