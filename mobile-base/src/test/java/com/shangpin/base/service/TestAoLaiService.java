package com.shangpin.base.service;

import org.junit.Assert;
import org.junit.Test;

import com.shangpin.base.service.impl.AoLaiServiceImpl;
import com.shangpin.base.vo.ListOfGoods;

public class TestAoLaiService {

    /**
     * 测试奥莱按起止时间获取活动展示
     */
    @Test
    public void testFindSubjectListByPeriod() {
        AoLaiService service = new AoLaiServiceImpl();
        String result = service.findSubjectListByPeriod("1", "2012-09-08", "2012-11-12", "100", "80");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试奥莱按起止时间获取活动展示
     */
    @Test
    public void testFindTopicListForiPhone() {
        AoLaiService service = new AoLaiServiceImpl();
        String result = service.findTopicListForiPhone("1", "100", "80");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试奥莱专题商品列表页面
     */
    @Test
    public void testFindTopicLists() {
        AoLaiService service = new AoLaiServiceImpl();
        String result = service.findTopicList("20120521171", "100", "80", null, null);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试奥莱活动商品列表页
     */
    @Test
    public void testFindSubjectProductList() {
        AoLaiService service = new AoLaiServiceImpl();
        String result = service.findSubjectProductList("20120810328", "100", "80", null, null);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试奥莱商品详情页面
     */
    @Test
    public void testFindProductDetail() {
        AoLaiService service = new AoLaiServiceImpl();
        String result = service.findProductDetail("02225940", "0", "2012112233445", "100", "80");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试奥莱获取所有未结束和今天即将开始的活动
     */
    @Test
    public void testFindTodaySubjectList() {
        AoLaiService service = new AoLaiServiceImpl();
        String result = service.findTodaySubjectList("0", "100", "50");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试奥莱获取所有未结束和今天即将开始的活动
     */
    @Test
    public void testFindEndingSubjectList() {
        AoLaiService service = new AoLaiServiceImpl();
        String result = service.findEndingSubjectList("0", "100", "50");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 奥莱获取频道
     */
    @Test
    public void testFindChannel() {
        AoLaiService service = new AoLaiServiceImpl();
        String result = service.findChannel();
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 奥莱获取频道中活动列表
     */
    @Test
    public void testFindChannelActivity() {
        AoLaiService service = new AoLaiServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setChId("1");
        listOfGoodsVO.setPageIndex("1");
        listOfGoodsVO.setPageSize("10");
        listOfGoodsVO.setPicw("20");
        listOfGoodsVO.setPich("30");
        String result = service.findChannelActivity(listOfGoodsVO);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 奥莱获取频道中活动列表
     */
    @Test
    public void testMobileFindTopicList() {
        AoLaiService service = new AoLaiServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setGender("1");
        listOfGoodsVO.setPicw("20");
        listOfGoodsVO.setPich("30");
        String result = service.findMobileTopicList(listOfGoodsVO);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

}
