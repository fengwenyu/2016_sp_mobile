package com.shangpin.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.service.IPushManageService;

public class TestPushManageService extends BaseTest {

    @Autowired
    IPushManageService service;

    /**
     * 测试 根据username，获取个人的push信息列表，及广播push信息列表（Android平台）
     * 
     * @author zhanghongwei
     */
    @Test
    public void testModifyAndGetAndroidPushInfo() {
        // gener=0 productNum=101
        String pushMsg01 = service.modifyAndGetAndroidPushInfo("zghw", "0", 101l);
        System.out.println("pushMsg01" + pushMsg01);
        Assert.isTrue(!("[]".equals(pushMsg01)));
    }

    /**
     * 测试 获取24小时之内及定时发送时间大于当前时间的push信息(Android平台广播)
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindAndroidPushInfo() {
        String pushMsg01 = service.findAndroidPushInfo(1, 101L);
        System.out.println("pushMsg01 = " + pushMsg01);
        Assert.notNull(pushMsg01);
    }

}