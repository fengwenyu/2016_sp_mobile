package com.shangpin.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.FindManage;
import com.shangpin.core.service.IFindManageService;

public class TestFindManage extends BaseTest {

    @Autowired
    IFindManageService findManageService;

    @Test
    public void testFindManageData() {
        List<FindManage> list = findManageService.findManageData(new Date());
        Assert.notNull(list);
    }

    @Test
    public void testAddFindManageData() {
        FindManage bean = new FindManage();
        bean.setActivityId("战鼓");
        bean.setDescription("dsafaaa");
        bean.setDisplay(1111);
        bean.setGetUrl("11aaaaa");
        bean.setEndDate(new Date());
        FindManage list = findManageService.addFindManage(bean);
        Assert.notNull(list.getId());
    }

    @Test
    public void testFindManageById() {
        FindManage list = findManageService.findManageById(44L);
        Assert.notNull(list);
    }

    @Test
    public void testDeleteFindManageData() {
        findManageService.deleteFindManage(44L);
        FindManage list = findManageService.findManageById(44L);
        Assert.isNull(list);
    }

    @Test
    public void testFindByActivityManage() {
        List<FindManage> list = findManageService.findByActivityManage(new Date(), 0, "ACTIVITY");
        System.out.println(list);
        Assert.notNull(list);
    }

    @Test
    public void testFindStaticActivity() {
        List<FindManage> list = findManageService.findStaticActivity(new Date(), 0, "ACTIVITY");
        Assert.notNull(list.get(0).getId());
    }

    @Test
    public void testFindByConditions() {
        List<FindManage> list = findManageService.findByConditions(new Date(), "IMAGETEXT");
        Assert.notNull(list);
    }

    /**
     * 测试根据时间得到正在进行显示信息活动信息
     * @author zhanghongwei
     */
    @Test
    public void testFindFocus(){
        List<FindManage> list=findManageService.findFocus(new Date(), 1);
        Assert.notNull(list);
    }
    /**
     * 测试根据时间得到正在进行显示信息活动信息
     * @author zhanghongwei
     */
    @Test
    public void testFindDefaultSlider(){
        List<FindManage> list=findManageService.findDefaultSlider(new Date());
        Assert.notNull(list);
    }
}
