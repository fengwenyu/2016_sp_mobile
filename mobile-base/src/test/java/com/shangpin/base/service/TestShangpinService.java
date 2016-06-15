package com.shangpin.base.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.shangpin.base.service.impl.ShangPinServiceImpl;
import com.shangpin.base.vo.CapitalBrand;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.base.vo.Merchandise;
import com.shangpin.base.vo.Topic;

public class TestShangpinService {

    /**
     * 获取CMS尚品专题商品列表
     */
    @Test
    public void testFindUserBuyInfo() {
        ShangPinService service = new ShangPinServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setUserId("916E897CFD298AB703E45EC3A425FE8c");
        listOfGoodsVO.setTopicId("20130201566");
        listOfGoodsVO.setGender("1");
        listOfGoodsVO.setPicw("30");
        listOfGoodsVO.setPich("50");
        listOfGoodsVO.setPageIndex("1");
        listOfGoodsVO.setPageSize("10");
        listOfGoodsVO.setFilterSold("1");
        String result = service.findTopicProducts(listOfGoodsVO);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 获取尚品男士9宫格活动
     */
    @Test
    public void testFindMan9Grids() {
        ShangPinService service = new ShangPinServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setGender("0");
        listOfGoodsVO.setPicw("100");
        listOfGoodsVO.setPich("50");
        String result = service.findMan9Grids(listOfGoodsVO);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 批量获取图片链接
     */
    @Test
    public void testFindPicUrls() {
        ShangPinService service = new ShangPinServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setPich("180");
        listOfGoodsVO.setPicw("120");
        listOfGoodsVO.setPicType("product");
        listOfGoodsVO.setPicNos("10031");
        String result = service.findPicUrls(listOfGoodsVO);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 根据开始结束时间获取尚品活动列表
     */
    @Test
    public void testFindSubjectList() {
        ShangPinService service = new ShangPinServiceImpl();
        String result = service.findSubjectList("","","","2013-08-3 00:00:00", "2013-09-10 00:00:00");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 获取品牌旗舰店数据
     */
    @Test
    public void testFindFlagShip() {
        ShangPinService service = new ShangPinServiceImpl();
        String result = service.findFlagShip("B007");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 根据活动名称或者活动编号获取符合条件的尚品活动列表
     */
    @Test
    public void testFindSearchSubject() {
        ShangPinService service = new ShangPinServiceImpl();
        String result = service.findSearchSubject("abc");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 尚品专题商品列表
    @Test
    public void testFindSPTopicProducts() {
        ShangPinService service = new ShangPinServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setGender("1");
        listOfGoodsVO.setPicw("30");
        listOfGoodsVO.setPich("50");
        listOfGoodsVO.setPageIndex("1");
        listOfGoodsVO.setPageSize("10");
        listOfGoodsVO.setUserId("916E897CFD298AB703E45EC3A425FE8c");
        listOfGoodsVO.setTopicId("20130201566");
        listOfGoodsVO.setFilterSold("1");
        String result = service.findSPTopicProducts(listOfGoodsVO);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 尚品专题商品列表
    @Test
    public void testFindSPNewProducts() {
        ShangPinService service = new ShangPinServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setGender("0");
        listOfGoodsVO.setPicw("30");
        listOfGoodsVO.setPich("50");
        listOfGoodsVO.setPageIndex("1");
        listOfGoodsVO.setPageSize("10");
        listOfGoodsVO.setUserId("916E897CFD298AB703E45EC3A425FE8c");
        listOfGoodsVO.setCategoryId("A01B01");
        listOfGoodsVO.setFilterSold("0");
        String result = service.findSPNewProducts(listOfGoodsVO);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 尚品获取品类品牌商品列表
    @Test
    public void testFindSPProductsAndBrand() {
        ShangPinService service = new ShangPinServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setGender("0");
        listOfGoodsVO.setPicw("30");
        listOfGoodsVO.setPich("50");
        listOfGoodsVO.setPageIndex("1");
        listOfGoodsVO.setPageSize("10");
        listOfGoodsVO.setUserId("916E897CFD298AB703E45EC3A425FE8c");
        listOfGoodsVO.setCategoryId("A01B01");
        listOfGoodsVO.setFilterSold("1");
        listOfGoodsVO.setBrandId("");
        String result = service.findSPProductsAndBrand(listOfGoodsVO);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 尚品商品详情
    @Test
    public void testFindSPProductDetail() {
        ShangPinService service = new ShangPinServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setPicw("600");
        listOfGoodsVO.setPich("450");
        listOfGoodsVO.setUserId("916E897CFD298AB703E45EC3A425FE8c");
        listOfGoodsVO.setProductId("01238451");
        listOfGoodsVO.setTopicId("2012112233445");
        String result = service.findSPProductDetail(listOfGoodsVO);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试根据性别查询品牌列表
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindCapitalBrandList() {
        ShangPinService service = new ShangPinServiceImpl();
        String result = service.findCapitalBrandList("1");
        Assert.assertNotNull(result);
    }

    /**
     * 测试查询以大写字母分类的品牌列表
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindCapitalBrandListObj() {
        ShangPinService service = new ShangPinServiceImpl();
        List<CapitalBrand> list = service.findCapitalBrandListObj("0");
        Assert.assertNotNull(list);
    }

    /**
     * 测试品牌对应的产品列表
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindMerchandiseListObj() {
        ShangPinService service = new ShangPinServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setGender("0");
        listOfGoodsVO.setPicw("30");
        listOfGoodsVO.setPich("50");
        listOfGoodsVO.setPageIndex("1");
        listOfGoodsVO.setPageSize("10");
        listOfGoodsVO.setUserId("916E897CFD298AB703E45EC3A425FE8c");
        listOfGoodsVO.setCategoryId("A01B01");
        listOfGoodsVO.setFilterSold("1");
        listOfGoodsVO.setBrandId("");
        List<Merchandise> list = service.findMerchandiseListObj(listOfGoodsVO);
        Assert.assertNotNull(list);
    }

    /**
     * 测试尚品品类新品商品列表
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindSPNewProductsObj() {
        ShangPinService service = new ShangPinServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setGender("0");
        listOfGoodsVO.setPicw("30");
        listOfGoodsVO.setPich("50");
        listOfGoodsVO.setPageIndex("1");
        listOfGoodsVO.setPageSize("10");
        listOfGoodsVO.setUserId("916E897CFD298AB703E45EC3A425FE8c");
        listOfGoodsVO.setCategoryId("A01B01");
        listOfGoodsVO.setFilterSold("0");
        List<Merchandise> list = service.findSPNewProductsObj(listOfGoodsVO);
        Assert.assertNotNull(list);
    }
    /**
     * 测试尚品品类新品商品列表
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindTopicProductsObj() {
        //?pagesize=20&topicid=31004041&picw=318&pich=422&filtersold=0&pageindex=1&userid=&gender=0
        ShangPinService service = new ShangPinServiceImpl();
        ListOfGoods listOfGoodsVO = new ListOfGoods();
        listOfGoodsVO.setGender("1");
        listOfGoodsVO.setPicw("318");
        listOfGoodsVO.setPich("422");
        listOfGoodsVO.setPageIndex("1");
        listOfGoodsVO.setPageSize("10");
        listOfGoodsVO.setUserId("");
        listOfGoodsVO.setTopicId("31004041");
        listOfGoodsVO.setFilterSold("0");
        Topic topic= service.findTopicProductsObj(listOfGoodsVO);
        Assert.assertNotNull(topic);
        
    }
    /**
     * 测试查询订单物流
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindOrderMoreLogistic(){
    	ShangPinService service =new ShangPinServiceImpl();
    	String result = service.findOrderMoreLogistic("566537929BB692F41C445544EAD8F0E8", "2013012807654","1","1");
    	System.out.println(result);
    	Assert.assertNotNull(result);
    }
}
