package com.shangpin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.PushManageAndroid;
import com.shangpin.core.service.IPushManageAndroidService;

public class TestPushManageAndroidService extends BaseTest {

    @Autowired
    IPushManageAndroidService service;

    /**
     * 测试添加
     * 
     * @author zhanghongwei
     */
    @Test
    public void testAdd() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            PushManageAndroid bean = new PushManageAndroid();
            bean.setPlatform("android");
            bean.setProductNum(101l);
            bean.setPushType(0);
            bean.setMsgType(2);
            bean.setNotice("14:00 -14:58 安卓尚品公告");
            bean.setCreateTime(sdf.parse("2014-07-02 12:45:30"));
            bean.setShowTime(sdf.parse("2014-07-02 12:51:30"));
            bean.setEndTime(sdf.parse("2014-07-02 14:45:30"));
            PushManageAndroid entity = service.add(bean);
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
        PushManageAndroid bean = service.findById(44l);
        bean.setPlatform("android");
        bean.setProductNum(102l);
        bean.setPushType(1);
        bean.setMsgType(1);
        bean.setNotice(" 安卓尚品 测试");
        PushManageAndroid entity = service.modify(bean);
        Assert.isTrue(!(bean.equals(entity)));
    }

    /**
     * 测试删除
     * 
     * @author zhanghongwei
     */
    @Test
    public void testDeleteById() {
        service.deleteById(35l);
        PushManageAndroid entity = service.findById(35l);
        Assert.isNull(entity);
    }

    /**
     * 测试通过ID查询
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindById() {
        PushManageAndroid bean = service.findById(44l);
        Assert.notNull(bean);
    }

    /**
     * 测试通过用户ID查询
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindByUserId() {
        List<PushManageAndroid> list = service.findByUserId("zghw", 101l);
        Assert.isTrue(list != null && list.size() > 0);
        System.out.println(list.toString());
    }

    /**
     * 测试根据id，更新push状态为已发送
     * 
     * @author zhanghongwei
     */
    @Test
    public void testModifyTypeById() {
        PushManageAndroid bean = service.modifyTypeById(44l);
        Assert.isTrue(bean.getPushType() == 0);
    }

    /**
     * 测试获取24小时之内及定时发送时间大于当前时间的实体对象集合(Android平台广播)
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindByGender() {
        List<PushManageAndroid> list = service.findByGender(1, 101l);
        Assert.isTrue(list != null && list.size() > 0);
        System.out.println(list.toString());
        System.out.println(list.size());
        for (PushManageAndroid bean : list) {
            System.out.print(bean.getCreateTime());
        }
    }

}