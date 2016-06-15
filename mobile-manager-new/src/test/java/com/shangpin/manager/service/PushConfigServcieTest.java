package com.shangpin.manager.service;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shangpin.core.entity.main.Push;
import com.shangpin.core.entity.main.PushConfig;
import com.shangpin.core.service.PushConfigService;
import com.shangpin.core.service.PushService;
import com.shangpin.core.util.spring.SpringUtil;


public class PushConfigServcieTest extends BaseServiceTest{
    
    @Autowired
    private PushConfigService pushConfigService;
    
    @Test
    public void testFindAll(){
        List<PushConfig> list = pushConfigService.findAll();
        System.out.println(list.size());
    }
    
    @Test
    public void findByTypeAndUserid(){
        PushConfig pushConfig = pushConfigService.findByTypeAndUserid(2, "2F47EB5C726AECEDB08607488707DB34");
        System.out.println(pushConfig.getToken());
    }
    
    @Test
    public void findPushTest(){
        System.out.println("------------------------------------------");
        PushService pushService = (PushService)SpringUtil.getBean("pushService");
        Push push = pushService.get((long)133);
        System.out.println(push.getId() + " " + push.getActionParam());
    }
    
    

}
