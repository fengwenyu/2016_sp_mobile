package com.shangpin.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.Token;
import com.shangpin.core.service.ITokenService;

public class TestTokenService extends BaseTest {

    @Autowired
    ITokenService service;

    /**
     * 测试添加
     * 
     * @author zhanghongwei
     */
    @Test
    public void testAdd() {
        Token bean = new Token();
        bean.setCode("33333");
        bean.setEfftime(new Date());
        bean.setLastUpdated(new Date());
        bean.setServerId("23");
        bean.setStatus("custom");
        Token entity = service.add(bean);
        Assert.notNull(entity.getId());
    }

    /**
     * 测试修改
     * 
     * @author zhanghongwei
     */
    @Test
    public void testModify() {
        Token bean = service.findById(1l);
        bean.setCode("ssssss");
        bean.setEfftime(new Date());
        bean.setLastUpdated(new Date());
        bean.setServerId("ddd");
        bean.setStatus("normal");
        Token entity = service.modify(bean);
        Assert.isTrue(!("23".equals(entity.getServerId())));
    }

    /**
     * 测试删除
     * 
     * @author zhanghongwei
     */
    @Test
    public void testDeleteById() {
        service.deleteById(2l);
        Token entity = service.findById(2l);
        Assert.isNull(entity);
    }

    /**
     * 测试通过ID查询
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindById() {
        Token bean = service.findById(1l);
        Assert.notNull(bean);
    }

    /**
     * 测试根据token状态得到查询结果token集合中的第一个token对象
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindByStatus() {
        Token bean = service.findByStatus("csustom");
        Assert.notNull(bean);
        System.out.println(bean.getId());
    }

    /**
     * 测试重置数据库token状态
     * 
     * @author zhanghongwei
     */
    @Test
    public void testResetTokenStatus() {
        service.resetTokenStatus();
        Token bean = service.findById(3l);
        Assert.notNull(bean);
        Assert.isTrue(!"custom".equals(bean.getStatus()));
        System.out.println(bean.getId());
    }

    /**
     * 测试 验证token是否失效
     * 
     * @author zhanghongwei
     */
    @Test
    public void testIsInvalidCode() {
        Boolean isInvalid = service.isInvalidCode("1111111");
        Assert.isTrue(isInvalid == true);
    }

}