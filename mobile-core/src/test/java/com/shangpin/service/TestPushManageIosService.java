package com.shangpin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.PushManageIos;
import com.shangpin.core.service.IPushManageIosService;

public class TestPushManageIosService extends BaseTest {

    @Autowired
    IPushManageIosService service;

    /**
     * 测试添加
     * 
     * @author zhanghongwei
     */
    @Test
    public void testAdd() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            PushManageIos bean = new PushManageIos();
            bean.setPlatform("android");
            bean.setProductNum(101l);
            bean.setPushType(0);
            bean.setMsgType(2);
            bean.setNotice("14:00 -14:58 安卓尚品公告");
            bean.setCreateTime(sdf.parse("2014-07-02 12:45:30"));
            bean.setShowTime(sdf.parse("2014-07-02 12:51:30"));
            PushManageIos entity = service.add(bean);
            Assert.notNull(entity.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试修改
     * 
     * @author zhanghongwei
     */
    @Test
    public void testModify() {
        PushManageIos bean = service.findById(94l);
        bean.setPlatform("iso");
        bean.setProductNum(102l);
        bean.setPushType(1);
        bean.setMsgType(1);
        bean.setTitle("测试修改");
        PushManageIos entity = service.modify(bean);
        Assert.isTrue(!("android".equals(entity.getPlatform())));
    }

    /**
     * 测试删除
     * 
     * @author zhanghongwei
     */
    @Test
    public void testDeleteById() {
        service.deleteById(94l);
        PushManageIos entity = service.findById(94l);
        Assert.isNull(entity);
    }

    /**
     * 测试通过ID查询
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindById() {
        PushManageIos bean = service.findById(94l);
        Assert.notNull(bean);
    }

}