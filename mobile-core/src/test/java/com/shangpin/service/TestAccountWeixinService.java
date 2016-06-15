package com.shangpin.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.AccountWeixin;
import com.shangpin.core.service.IAccountWeixinService;

public class TestAccountWeixinService extends BaseTest {

    @Autowired
    IAccountWeixinService accountWeixinService;

    // 根据微信ID查找
    @Test
    public void testFindByWeixinId() {
        AccountWeixin accountWeixin = accountWeixinService.findByWeixinId("001");
        System.out.println(accountWeixin.getId());// 预期为3
    }

  /*  // 保存
    @Test
    public void testSave() {
        AccountWeixin accountWeixin = new AccountWeixin();
        accountWeixin.setWeixinId("002");
        accountWeixin.setSex("man2");
        accountWeixinService.save(accountWeixin);
        Assert.notNull(accountWeixin.getId());
    }

    // 删除
    @Test
    public void testDelete() {
        accountWeixinService.delete((long) 1);
        AccountWeixin accountWeixin = accountWeixinService.findById(1L);
        Assert.isNull(accountWeixin);
    }

    // 根据ID查找
    @Test
    public void testFindById() {
        AccountWeixin accountWeixin = accountWeixinService.findById(2L);
        Assert.notNull(accountWeixin);
    }*/
}
