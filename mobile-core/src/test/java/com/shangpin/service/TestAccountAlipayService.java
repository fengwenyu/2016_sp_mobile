package com.shangpin.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.AccountAlipay;
import com.shangpin.core.service.IAccountAlipayService;

public class TestAccountAlipayService extends BaseTest {

    @Autowired
    IAccountAlipayService accountAlipayService;

    // 查询
    @Test
    public void testFindByUserId() throws Exception {
        AccountAlipay accountAlipay = accountAlipayService.findByUserId("BE3C036CC29F175CB6408118AB90397D");
        Assert.notNull(accountAlipay.getNickname());
        System.out.println(accountAlipay.getNickname());
    }

    // 保存
    @Test
    public void testSave() {
        AccountAlipay accountAlipay = new AccountAlipay();
        accountAlipay.setUserId("6FA7F7D5276E1ECDF4EA6BF02ACA86F4");
        accountAlipay.setNickname("cui");
        accountAlipayService.save(accountAlipay);
        Assert.notNull(accountAlipay.getId());
    }

    // 修改
    @Test
    public void testModify() {
        AccountAlipay accountAlipay = accountAlipayService.findById(31L);
        accountAlipay.setUserId("6FA7F7D5276E1ECDF4EA6BF02ACA86F9");
        accountAlipay.setNickname("cui");
        accountAlipay = accountAlipayService.modify(accountAlipay);
        Assert.isTrue("cui".equals(accountAlipay.getNickname()));
    }
    
    //删除
    @Test
    public void testDelete() {
        accountAlipayService.delete(37L);
        AccountAlipay accountAlipay = accountAlipayService.findById(37L);
        Assert.isNull(accountAlipay);
    }

}
