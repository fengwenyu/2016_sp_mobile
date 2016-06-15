package com.shangpin.service;

import java.util.Iterator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.PageableTest;
import com.shangpin.core.entity.OnlineManage;
import com.shangpin.core.service.IOnlineManageService;


public class TestOnlineManageService extends BaseTest{

    @Autowired
    IOnlineManageService onlineManageService;
    
    @Test
    public void testAddOnlineManage(){
        OnlineManage bean=new OnlineManage();
        bean.setChannelNum(3333L);
        bean.setDownloadPath("44444444");
        bean.setFileName("444444");
        bean.setInuse(112);
        bean.setPrompt("222222244");
        bean.setProductNum(433L);
        OnlineManage onlineManage=this.onlineManageService.addOnlineManage(bean); 
        Assert.notNull(onlineManage.getId());
    }
    
    @Test
    public void testModifyOnlineManage(){
        OnlineManage bean=new OnlineManage();
        bean.setChannelNum(3333L);
        bean.setDownloadPath("44444444");
        bean.setFileName("444444");
        bean.setInuse(112);
        bean.setPrompt("222222244");
        bean.setProductNum(433L);
        OnlineManage onlineManage=this.onlineManageService.modifyOnlineManage(bean);
        Assert.notNull(onlineManage.getId());
    }
    
    @Test
    public void testFindOnlineManageById(){
        OnlineManage onlineManage=this.onlineManageService.findOnlineManageById(57L);
        Assert.notNull(onlineManage);
    }
    
    @Test
    public void testDeleteOnlineManageById(){
        this.onlineManageService.deleteOnlineManageById(57L);
        OnlineManage onlineManage=this.onlineManageService.findOnlineManageById(57L);
        Assert.isNull(onlineManage);
    }
    
    @Test
    public void testFindByOnlineManage(){
        Pageable pt=new PageableTest();
        Page<OnlineManage> onlineManage=this.onlineManageService.findByOnlineManage(pt);
        Iterator<OnlineManage> it=onlineManage.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
