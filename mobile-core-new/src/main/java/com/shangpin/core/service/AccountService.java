package com.shangpin.core.service;

import com.shangpin.core.entity.main.Account;

public interface AccountService {
    
    public Account findByLoginName(String loginName);

}
