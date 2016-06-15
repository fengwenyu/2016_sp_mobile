package com.shangpin.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.AccountWeixinBind;
import com.shangpin.core.service.IAccountWeixinBindedService;

public class TestAccountWeixinBindedService extends BaseTest {

    @Autowired
    IAccountWeixinBindedService accountWeixinBindedService;

    // 保存实体
    @Test
    public void testSave() {
        AccountWeixinBind accountWeixinBind = new AccountWeixinBind();
        accountWeixinBind.setWeixinId("111444");
        accountWeixinBind.setUserId("222556");
        accountWeixinBind.setLoginName("cuibinqiang13@163.com");
        accountWeixinBind.setGender("0");
        accountWeixinBindedService.save(accountWeixinBind);
        Assert.notNull(accountWeixinBind.getId());
    }

    // 删除实体
    @Test
    public void testDelete() {
        accountWeixinBindedService.delete(80L);
        AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findById(80L);
        Assert.isNull(accountWeixinBind);
    }

    // 根据ID查找实体
    @Test
    public void testFindById() {
        AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findById(81L);
        System.out.println(accountWeixinBind.getLoginName());
    }

    // 根据用户ID查找实体
    @Test
    public void testFindByUserId() {
        AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId("2DCAD81CED6FC79BEDB5E05741D6E084");
        System.out.println(accountWeixinBind.getLoginName());
    }

}
