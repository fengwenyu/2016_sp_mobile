package com.shangpin.manager.service;

import org.junit.Test;

import com.shangpin.core.entity.main.Push;
import com.shangpin.core.service.PushService;
import com.shangpin.core.util.spring.SpringUtil;

public class SpringUtilTest extends BaseServiceTest{
    
    @Test
    public void test1(){
        System.out.println("获取实例bean.....................");
        PushService pushService = (PushService)SpringUtil.getBean("pushService");
        Push push = pushService.get((long)133);
        System.out.println(push.getId() + " " + push.getActionParam());
    }

}
