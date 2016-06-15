package com.shangpin.service;

import java.util.Date;
import java.util.Iterator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.PageableTest;
import com.shangpin.core.entity.ErrorLog;
import com.shangpin.core.service.IErrorLogService;


public class TestErrorLog  extends BaseTest  {

    @Autowired
    IErrorLogService errorLogService;
    
    @Test
    public void testFindSqlToMap(){
        Pageable pt=new PageableTest();
        Page<ErrorLog> errorPage=this.errorLogService.findByConditins(pt);
        Iterator<ErrorLog> it=errorPage.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    
    @Test
    public void testFindCount(){
        Long conut=errorLogService.findCount();
        System.out.println(conut+"-------------------------");
    }
    
    @Test
    public void testAddErrorLog(){
        ErrorLog bean= new ErrorLog();
        bean.setApn("1111111111");
        bean.setChannelNum(222L);
        bean.setCity("1111111111");
        bean.setCreateTime(new Date());
        bean.setId(1111L);
        bean.setIp("12");
        ErrorLog errorLog=errorLogService.addErrorLog(bean);
        Assert.notNull(errorLog.getId());
    }
    
    @Test
    public void testFindErrorLogById(){
        ErrorLog errorLog=errorLogService.findErrorLogById(1111L);
        Assert.notNull(errorLog);
    }
}
