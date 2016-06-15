package com.shangpin.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.WeixinLottery;
import com.shangpin.core.service.IWeixinLotteryService;

public class TestWeixinLotteryService extends BaseTest {

    @Autowired
    IWeixinLotteryService service;

    /**
     * 测试添加
     * 
     * @author zhanghongwei
     */
    @Test
    public void testAdd() {
        WeixinLottery bean = new WeixinLottery();
        bean.setActivateCode("sssss");
        bean.setCreateTime(new Date());
        bean.setIssueId("234234");
        bean.setPrizeLevel("2");
        bean.setUserId("wwweee");
        bean.setWeixinId("ssddff");
        WeixinLottery entity = service.add(bean);
        Assert.notNull(entity.getId());
    }

    /**
     * 测试修改
     * 
     * @author zhanghongwei
     */
    @Test
    public void testModify() {
        WeixinLottery bean = service.findById(3l);
        bean.setUserId("zghw");
        bean.setWeixinId("pass");
        WeixinLottery entity = service.modify(bean);
        Assert.isTrue(!("wwweee".equals(entity.getUserId())));
    }

    /**
     * 测试删除
     * 
     * @author zhanghongwei
     */
    @Test
    public void testDeleteById() {
        service.deleteById(3l);
        WeixinLottery entity = service.findById(3l);
        Assert.isNull(entity);
    }

    /**
     * 测试通过ID查询
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindById() {
        WeixinLottery bean = service.findById(3l);
        Assert.notNull(bean);
    }

}