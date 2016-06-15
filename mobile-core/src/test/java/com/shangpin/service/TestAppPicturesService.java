package com.shangpin.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.AppPictures;
import com.shangpin.core.service.IAppPicturesService;

public class TestAppPicturesService extends BaseTest {

    @Autowired
    IAppPicturesService appPicturesService;

    // 保存实体
    @Test
    public void testSave() {
        AppPictures appPictures = new AppPictures();
        appPictures.setImgUrl("http://192.168.9.108/scrumworks/taskboard?productKey=wxjg");
        appPictures.setImgWidth("500");
        appPictures.setImgHeight("500");
        appPictures.setCreateDate(new Date());
        appPictures.setProductType(0);
        appPicturesService.save(appPictures);
        Assert.notNull(appPictures.getId());
    }

    // 修改
    @Test
    public void testModify() {
        AppPictures appPictures = appPicturesService.findById(19L);
        appPictures.setId((long) 17);
        appPictures.setImgHeight("501");
        appPictures.setCreateDate(new Date());
        appPictures.setProductType(0);
        appPictures = appPicturesService.modify(appPictures);
        Assert.isTrue("501".equals(appPictures.getImgHeight()));
    }

    // 删除
    @Test
    public void testDelete() {
        appPicturesService.delete(17L);
        AppPictures appPictures = appPicturesService.findById(17L);
        Assert.isNull(appPictures);
    }

    // 通过ID查找
    @Test
    public void testFindById() {
        AppPictures appPictures = appPicturesService.findById(19L);
        Assert.notNull(appPictures);
    }

    //查询列表
    @Test
    public void testFindAll() {
        List<AppPictures> list = appPicturesService.findAll();
        Iterator<AppPictures> it = list.iterator();
        while (it.hasNext()) {
            AppPictures appPictures = it.next();
            System.out.println(appPictures.getId());
        }
    }

    //通过条件查询
    @Test
    public void testFindByCondition() {
        List<AppPictures> list = appPicturesService.findByCondition("iphoneFindShare", 0);
        Iterator<AppPictures> it = list.iterator();
        while (it.hasNext()) {
            AppPictures appPictures = it.next();
            System.out.println(appPictures.getId());
        }
    }

}
