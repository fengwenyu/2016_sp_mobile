package com.shangpin.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.PushconfShangpin;
import com.shangpin.core.service.IPushconfShangpinService;

public class TestPushconfShangpinService extends BaseTest {

    @Autowired
    IPushconfShangpinService service;

    /**
     * 测试添加
     * 
     * @author zhanghongwei
     */
    @Test
    public void testAdd() {
        PushconfShangpin bean = new PushconfShangpin();
        bean.setCreateTime(new Date());
        bean.setGender("1");
        bean.setImei("iiii");
        bean.setIsOpen(1);
        bean.setMsgType(3);
        bean.setReserve0("sdfsdfsd");
        bean.setReserve1("sdfsdfsd");
        bean.setUserId("zghw");
        bean.setToken("sdfds");
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        PushconfShangpin entity = service.add(bean);
        Assert.notNull(entity.getId());
    }

    /**
     * 测试修改
     * 
     * @author zhanghongwei
     */
    @Test
    public void testModify() {
        PushconfShangpin bean = service.findById(130524l);
        bean.setReserve0("aaaa");
        bean.setReserve1("bbbb");
        bean.setReserve4("xxxx");
        bean.setUserId("zhanghongwei");
        PushconfShangpin entity = service.modify(bean);
        Assert.isTrue(!("zghw".equals(entity.getUserId())));
    }

    /**
     * 测试删除
     * 
     * @author zhanghongwei
     */
    @Test
    public void testDeleteById() {
        service.deleteById(130524l);
        PushconfShangpin entity = service.findById(130524l);
        Assert.isNull(entity);
    }

    /**
     * 测试通过ID查询
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindById() {
        PushconfShangpin bean = service.findById(130524l);
        Assert.notNull(bean);
    }

    /**
     * 测试根据用户ID查询实体对象
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindByUserId() {
        PushconfShangpin bean = service.findByUserId("zghw");
        Assert.notNull(bean.getId());
        System.out.println(bean.getId());
    }

    /**
     * 测试根据token查询实体对象集合
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindByToken() {
        List<PushconfShangpin> list = service.findByToken("sdfds");
        Assert.isTrue(list != null && list.size() > 0);
        System.out.println(list.toString());
    }

    /**
     * 测试根据推送消息类型查询token集合
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindByMsgType() {
        List<String> list = service.findByMsgType(1);
        Assert.isTrue(list != null && list.size() > 0);
        System.out.println(list.toString());
        System.out.println(list.size());
    }

    /**
     * 测试根据推送消息类型查询token集合
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindByMsgTypeContain() {
        List<String> list = service.findByMsgTypeContain(1);
        Assert.isTrue(list != null && list.size() > 0);
        System.out.println(list.toString());
        System.out.println(list.size());
    }

    /**
     * 测试保存push设置
     * 
     * @author zhanghongwei
     */
    @Test
    public void testSavePushConfig() {
        // 当根据用户ID查询到实体对象时
        /*
         * service.savePushConfig("zghw", "1", "xxxx", "xxxxx", 3, 3);
         * PushconfShangpin bean = service.findByUserId("zghw");
         * Assert.isTrue(!("sdfds".equals(bean.getToken())));
         */

        // 当根据用户ID查询到实体对象时,通过token查出实体对象集合
        service.savePushConfig("newmodiry", "0", "sdfds", "士大夫似的", 0, 0);
        List<PushconfShangpin> list = service.findByToken("sdfds");
        Assert.isTrue(list != null && list.size() > 0);
        System.out.println(list.toString());

        /*
         * //当根据用户ID查询到实体对象时,并且token也查不出实体 service.savePushConfig("ssdfsd", "0",
         * "nnnnn", "ddddd", 1, 1); List<PushconfShangpin> list =
         * service.findByToken("nnnnn"); Assert.isTrue(list!=null
         * &&list.size()>0); System.out.println(list.toString());
         */

    }

    /**
     * 测试保存Token
     * 
     * @author zhanghongwei
     */
    @Test
    public void testSaveToken() {
        // // userId为空，查询token是否存在，如果存在则不insert，否则保存。

        /*
         * service.savePushConfig("", "1", "rrrrr", "xxxxx", 3, 3);
         * List<PushconfShangpin> list = service.findByToken("rrrrr");
         * Assert.isTrue(list!=null &&list.size()>0);
         * System.out.println(list.toString());
         */

        // userId不为空，查找该userId是否存在，如果存在update
        // token，如果不存在先判断是否存在token，如果token存在补全该token记录，如果token也不存在则save
        service.savePushConfig("ssdfsd", "0", "sdfds", "士大夫似的", 0, 0);
        PushconfShangpin bean = service.findByUserId("ssdfsd");
        Assert.isTrue(!("nnnnn".equals(bean.getToken())));

    }

}