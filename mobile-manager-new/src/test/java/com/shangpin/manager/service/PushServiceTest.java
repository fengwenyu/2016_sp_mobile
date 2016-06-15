package com.shangpin.manager.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shangpin.core.entity.main.Push;
import com.shangpin.core.service.PushService;
import com.shangpin.core.util.dwz.Page;

public class PushServiceTest extends BaseServiceTest{
    
    @Autowired
    private PushService pushService;
    
    @Test
    public void testSave(){
        for(int i = 0; i < 100; i++){
            Push push = new Push();
            push.setPlatformType(0);
            push.setProductCode(22);
            push.setChannelCode(22);
            push.setTitle("测试");
            push.setContent("测试内容");
            push.setNotice("公告内容");
            push.setActionType("直接进入专题");
            push.setActionParam("http://wap.baidu.cn/");
            push.setActionDetailParam("{'goodsid':'01235725','type':'1','categoryno':'40605348'}");
            push.setSex(0);
            push.setCreateTime(new Date());
            push.setStartTime(new Date());
            push.setEndTime(new Date());
            pushService.save(push);
        }
        
    }
    
    @Test
    public void findAll(){
        Page page = new Page();
        List<Push> pushs = pushService.findAll(page);
        System.out.println(pushs.size());
    }
    
    @Test
    public void findSendPushs(){
        System.out.println("11111111111111111111111111111111111111111111");
        List<Push> pushs = pushService.findSendPushs(22, 1,1);
        for(Push push : pushs){
            System.out.println(push.getProductCode() + " " + push.getUserId() + " " + push.getUsername());
        }
    }
    
    @Test
    public void tokens(){
        List<String> tokens = pushService.tokens(2, 1, 1, 20);
        for(String token : tokens){
            System.out.println("token:" + token);
        }
    }

}
