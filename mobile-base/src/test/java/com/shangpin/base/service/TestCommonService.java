package com.shangpin.base.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.shangpin.base.service.impl.CommonServiceImpl;
import com.shangpin.base.vo.Area;
import com.shangpin.base.vo.City;
import com.shangpin.base.vo.ConsigneeAddress;
import com.shangpin.base.vo.Province;

public class TestCommonService {

    /**
     * 测试登录
     */
    @Test
    public void testLogin() {
        CommonService service = new CommonServiceImpl();
        String result = service.login("hehuan@shangpin.com", "111111");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试注册
     */
    @Test
    public void testRegister() {
        CommonService service = new CommonServiceImpl();
        String result = service.register("hehuanxbbx@aolai.com", null,"222222", "1", "","0",null);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试第三方登陆
     */
    @Test
    public void testThirdLogin() {
        CommonService service = new CommonServiceImpl();
        String result = service.thirdLogin("weibo", "111111", "1453", null, null, null);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试找回密码
     */
    @Test
    public void testForgotPassword() {
        CommonService service = new CommonServiceImpl();
        String result = service.forgotPassword("hehuan2@aolai.com");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试收藏商品接口
     */
    @Test
    public void testAddProductToCollect() {
        CommonService service = new CommonServiceImpl();
        String result = service.addProductToCollect("916E897CFD298AB703E45EC3A425FE8c", "1", "03231869001", "1", null, null, null, null);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试查询收藏商品接口
     */
    @Test
    public void testSelectCollectList() {
        CommonService service = new CommonServiceImpl();
        String result = service.findCollectList("916E897CFD298AB703E45EC3A425FE8c", "1", "172", "186", "420", "560");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 新增发票地址
     */
    @Test
    public void testAddInvoiceAddress() {
        CommonService service = new CommonServiceImpl();
        ConsigneeAddress consiqneeAddressVO = new ConsigneeAddress();
        consiqneeAddressVO.setUserId("cdc5c0625d8483a89ea0c157fee2335");
        consiqneeAddressVO.setAddress("通惠河北路郎家园2号楼A座");
        consiqneeAddressVO.setArea("3");
        consiqneeAddressVO.setCity("1");
        consiqneeAddressVO.setConsigneeName("hehuan");
        consiqneeAddressVO.setPostcode("1");
        consiqneeAddressVO.setProvince("2");
        consiqneeAddressVO.setTel("15901325759");
        String result = service.addInvoiceAddress(consiqneeAddressVO);
        Assert.assertNotNull(result);
        System.out.println(result);
        // 
    }

    /**
     * 删除发票地址
     */
    @Test
    public void testDeleteInvoiceAddress() {
        CommonService service = new CommonServiceImpl();
        String result = service.deleteInvoiceAddress("dcdc5c0625d8483a89ea0c157fee2335", "83829");
        Assert.assertNotNull(result);
        System.out.println(result);
        // 
    }

    /**
     * 编辑发票地址
     */
    @Test
    public void testModifyInvoiceAddress() {
        CommonService service = new CommonServiceImpl();
        ConsigneeAddress consiqneeAddressVO = new ConsigneeAddress();
        consiqneeAddressVO.setUserId("dcdc5c0625d8483a89ea0c157fee2335");
        consiqneeAddressVO.setAddrId("83819");
        consiqneeAddressVO.setAddress("通惠河北路郎家园2号楼");
        consiqneeAddressVO.setArea("3");
        consiqneeAddressVO.setCity("1");
        consiqneeAddressVO.setConsigneeName("hehuan");
        consiqneeAddressVO.setPostcode("1");
        consiqneeAddressVO.setProvince("2");
        consiqneeAddressVO.setTel("15901325759");
        String result = service.modifyInvoiceAddress(consiqneeAddressVO);
        Assert.assertNotNull(result);
        System.out.println(result);
        // 
    }

    // 获取省级行政区信息
    @Test
    public void testFindProvinceList() {
        CommonService service = new CommonServiceImpl();
        String result = service.findProvinceList();
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 获取城市行政区信息
    @Test
    public void testFindCityList() {
        CommonService service = new CommonServiceImpl();
        String result = service.findCityList("3");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 获取地区行政区信息
    @Test
    public void testFindAreaList() {
        CommonService service = new CommonServiceImpl();
        String result = service.findAreaList("1");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 获取会场内容数据URL
    @Test
    public void testfindHallContent() {
        CommonService service = new CommonServiceImpl();
        String result = service.findHallContent("97","0","");
        // String result = service.findHallContent("98");
        // String result = service.findHallContent("");//为空时默认为主会场
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 二级分类
    @Test
    public void testfindSecondLevel() {
        CommonService service = new CommonServiceImpl();
        String result = service.findCategoryList("A01", "1", "10");

        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试获得省份列表
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindProvinceListObj() {
        CommonService service = new CommonServiceImpl();
        List<Province> plist = service.findProvinceListObj();
        Assert.assertNotNull(plist);
        System.out.println(plist.get(0).getName());
        System.out.println(plist.get(0).getId());
        System.out.println(plist.get(0).getParentid());
    }
    
    /**
     * 测试获得市列表
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindCityListObj() {
        CommonService service = new CommonServiceImpl();
        List<City> clist = service.findCityListObj("3");
        Assert.assertNotNull(clist);
        System.out.println(clist.get(0).getName());
        System.out.println(clist.get(0).getId());
        System.out.println(clist.get(0).getParentid());
    }
    
    /**
     * 测试获得区域列表
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindAreaListObj() {
        CommonService service = new CommonServiceImpl();
        List<Area> alist = service.findAreaListObj("1");
        Assert.assertNotNull(alist);
        System.out.println(alist.get(0).getName());
        System.out.println(alist.get(0).getId());
        System.out.println(alist.get(0).getParentid());
    }
    
}
