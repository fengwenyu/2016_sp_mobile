package com.shangpin.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.utils.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.AolaiActivity;
import com.shangpin.core.service.IAolaiActivityService;

public class TestAolaiActivityService extends BaseTest {

    @Autowired
    IAolaiActivityService aolaiActivityService;

    // 查找列表
    @Test
    public void testFindAolaiActivity() {
        Date date = new Date();
        List<AolaiActivity> list = aolaiActivityService.findAolaiActivity(date, 0);
        Iterator<AolaiActivity> it = list.iterator();
        while (it.hasNext()) {
            AolaiActivity myData = it.next();
            System.out.println(myData.getId());// 预期是1
        }
        Assert.notNull(list);
    }

    // 保存
    @Test
    public void testSave() {
        AolaiActivity aolaiActivity = new AolaiActivity();
        aolaiActivity.setStartTime(DateUtils.parseDate("2014-07-14 17:16:08"));
        aolaiActivity.setEndTime(DateUtils.parseDate("2014-07-24 17:16:08"));
        aolaiActivity.setDisplay(0);
        aolaiActivity.setGetUrl("http://mail.qiye.163.com/jy3/main.jsp?sid=WADfzvRnVBKRUrnbhdnnmKIIfaIioRil&hl=zh_CN");
        aolaiActivity.setCreateDate(new Date());
        aolaiActivityService.save(aolaiActivity);
        Assert.notNull(aolaiActivity.getId());
    }

    // 删除
    @Test
    public void testDelete() {
        aolaiActivityService.delete(3L);
        AolaiActivity aolaiActivity = aolaiActivityService.findById(3L);
        Assert.isNull(aolaiActivity);
    }

    // 通过ID查找
    @Test
    public void testFindById() {
        AolaiActivity aolaiActivity = aolaiActivityService.findById(2L);
        Assert.notNull(aolaiActivity);
    }

}
