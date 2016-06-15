package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IAccountDAO;
import com.shangpin.core.entity.Account;
import com.shangpin.core.service.IAccountService;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDAO accountDAO;

    @Override
    public Account save(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public Account findByUserId(String userId) {
        Account account = accountDAO.findByUserId(userId);
        return account;
    }

    @Override
    public void delete(Long id) {
        accountDAO.delete(id);
    }

    @Override
    public Account modify(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public Account findById(Long id) {
        return accountDAO.findOne(id);
    }

	@Override
	public Account findByLoginName(String longinName) {
		return accountDAO.findAccountByName(longinName);
	}

}
