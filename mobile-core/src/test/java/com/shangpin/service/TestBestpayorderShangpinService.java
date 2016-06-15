package com.shangpin.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.BestpayorderShangpin;
import com.shangpin.core.service.IBestpayByOrderService;


public class TestBestpayorderShangpinService  extends BaseTest {
    @Autowired
    IBestpayByOrderService bestpayByOrderService;
    
    @Test
    public void testAddBestpayorderShangpin(){
        BestpayorderShangpin bestpayorderShangpin =new BestpayorderShangpin();
        bestpayorderShangpin.setId(43L);
        bestpayorderShangpin.setPartnerid("231");
        bestpayorderShangpin.setPartnername("123");
        bestpayorderShangpin.setSupplyorgcode1("123");
        bestpayorderShangpin.setSig("123123");
        bestpayorderShangpin.setSupplyorgcode2("123");
        bestpayorderShangpin.setSupplyorgcode3("12312321");
        BestpayorderShangpin bean=bestpayByOrderService.addBestpayorderShangpin(bestpayorderShangpin);
        Assert.notNull(bean.getId());
    }
    
    @Test
    public void testFindBestpayorderShangpinById(){
        BestpayorderShangpin bean=bestpayByOrderService.findBestpayorderShangpinById(43L);
        Assert.notNull(bean.getId());
    }
    
    @Test
    public void deleteBestpayorderShangpinById(){
        bestpayByOrderService.deleteBestpayorderShangpinById(43L);
    }
    
}
