package com.shangpin.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.AppNavigation;
import com.shangpin.core.service.IAppNavigationService;

public class TestAppNavigationService extends BaseTest {

    @Autowired
    IAppNavigationService appNavigationService;

    // 查找列表
    @Test
    public void testFindAolaiActivity() {
        List<AppNavigation> list = appNavigationService.findAll();
        Iterator<AppNavigation> it = list.iterator();
        while (it.hasNext()) {
            AppNavigation myData = it.next();
            System.out.println(myData.getId());// 预期是1
        }
        Assert.notNull(list);
    }

    // 保存
    @Test
    public void testSave() {
        AppNavigation appNavigation = new AppNavigation();
        appNavigation.setCreateDate(new Date());
        appNavigation.setShowType(1);
        appNavigation.setLink("tabId=6");
        appNavigationService.save(appNavigation);
        Assert.notNull(appNavigation);
    }

    // 通过ID查找
    @Test
    public void testFindById() {
        AppNavigation appNavigation = appNavigationService.findById(2L);
        System.out.println(appNavigation.getName());
    }

    // 删除
    @Test
    public void testDelete() {
        appNavigationService.delete(2L);
        AppNavigation appNavigation = appNavigationService.findById(2L);
        Assert.isNull(appNavigation);
    }

}
