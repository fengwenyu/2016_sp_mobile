package com.shangpin.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.Account;
import com.shangpin.core.service.IAccountService;

public class TestAccountService extends BaseTest {

    @Autowired
    IAccountService accountService;

    // 根据用户ID查询实体
    @Test
    public void testFindByUserId() {
        Account account = accountService.findByUserId("D918DA24704ABC535F7F9E1769271736");
        Assert.notNull(account.getChannel());
    }

    // 保存
    @Test
    public void testSave() {
        Account account = new Account();
        account.setUserId("23423333333333333");
        account.setLoginName("cuibinqiang@163.com");
        accountService.save(account);
        Assert.notNull(account.getId());
        System.out.println("ID为：" + account.getId());
    }

    // 删除
    @Test
    public void testDelete() {
        accountService.delete(1L);
        Account account = accountService.findById(1L);
        Assert.isNull(account);
    }
}
