package com.shangpin.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.WeixinMessage;
import com.shangpin.core.service.IWeixinMessageService;

public class TestWeixinMessageService extends BaseTest {

    @Autowired
    IWeixinMessageService service;

    /**
     * 测试添加
     * 
     * @author zhanghongwei
     */
    @Test
    public void testAdd() {
        WeixinMessage bean = new WeixinMessage();
        bean.setContent("aaaaa");
        bean.setCreateTime(new Date());
        bean.setCustom("bbbb");
        bean.setEventType("34");
        bean.setFromUser("zghw");
        bean.setMediaId("lsdfsd");
        bean.setMsgId("7878");
        bean.setMsgType("战功");
        bean.setParams("sdfsd");
        bean.setReserve0("ads");
        WeixinMessage entity = service.add(bean);
        Assert.notNull(entity.getId());
    }

    /**
     * 测试修改
     * 
     * @author zhanghongwei
     */
    @Test
    public void testModify() {
        WeixinMessage bean = service.findById(1l);
        bean.setContent("士大夫似的");
        bean.setCreateTime(new Date());
        bean.setCustom("戏弄是德国发射点");
        WeixinMessage entity = service.modify(bean);
        Assert.isTrue(!("aaaaa".equals(entity.getContent())));
    }

    /**
     * 测试删除
     * 
     * @author zhanghongwei
     */
    @Test
    public void testDeleteById() {
        service.deleteById(3l);
        WeixinMessage entity = service.findById(3l);
        Assert.isNull(entity);
    }

    /**
     * 测试通过ID查询
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindById() {
        WeixinMessage bean = service.findById(1l);
        Assert.notNull(bean);
    }

}