package com.shangpin.base.service;

import org.junit.Assert;
import org.junit.Test;

import com.shangpin.base.service.impl.CustomerServiceImpl;
import com.shangpin.base.vo.ConsigneeAddress;

public class TestUserService {

    // 获取个人信息
    @Test
    public void testGetUserInfo() {
        CustomerService service = new CustomerServiceImpl();
        String result = service.findUserInfo("916E897CFD298AB703E45EC3A425FE8c");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 新增收货地址
    @Test
    public void testAddConsigneeAddress() {
        CustomerService service = new CustomerServiceImpl();
        ConsigneeAddress addConsigneeAddressVO = new ConsigneeAddress();
        addConsigneeAddressVO.setUserId("916E897CFD298AB703E45EC3A425FE8C");
        addConsigneeAddressVO.setConsigneeName("hehuan");
        addConsigneeAddressVO.setAddress("通惠河北路郎家园6号院2号楼通惠河北路郎家 园6号院2号");
        addConsigneeAddressVO.setProvince("1");
        addConsigneeAddressVO.setCity("1");
        addConsigneeAddressVO.setArea("1");
        addConsigneeAddressVO.setPostcode("1");
        addConsigneeAddressVO.setTel("15901325759");
        addConsigneeAddressVO.setSetd("true");

        String result = service.addConsigneeAddress(addConsigneeAddressVO);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 删除收货地址
    @Test
    public void testDelConsigneeAddress() {
        CustomerService service = new CustomerServiceImpl();
        String result = service.deleteConsigneeAddress("916E897CFD298AB703E45EC3A425FE8", "83760");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 设定默认收货地址（尚品、奥莱完全相同）
    @Test
    public void testModifyDefaultConsigneeAddress() {
        CustomerService service = new CustomerServiceImpl();
        String result = service.modifyDefaultConsigneeAddress("16E897CFD298AB703E45EC3A425FE8", "83760");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 设定根据地址id获取收货地址信息（尚品、奥莱完全相同）
    @Test
    public void testFindConsigneeAddressById() {
        CustomerService service = new CustomerServiceImpl();
        String result = service.findConsigneeAddressById("916E897CFD298AB703E45EC3A425FE8", "83760");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 编辑收货地址
    @Test
    public void testEditConsigneeAddress() {
        CustomerService service = new CustomerServiceImpl();
        ConsigneeAddress editConsigneeAddressVO = new ConsigneeAddress();
        editConsigneeAddressVO.setUserId("916E897CFD298AB703E45EC3A425FE8c");
        editConsigneeAddressVO.setAddrId("83760");
        editConsigneeAddressVO.setConsigneeName("hehuan2222");
        editConsigneeAddressVO.setAddress("通惠河北路郎家园6号院2号楼通惠河北路郎家园6号院2号");
        editConsigneeAddressVO.setProvince("1");
        editConsigneeAddressVO.setCity("1");
        editConsigneeAddressVO.setArea("1");
        editConsigneeAddressVO.setPostcode("1");
        editConsigneeAddressVO.setTel("15901325759");
        editConsigneeAddressVO.setSetd("true");

        String result = service.modifyConsigneeAddress(editConsigneeAddressVO);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 查询收货地址
    @Test
    public void testGetconsigneeaddress() {
        CustomerService service = new CustomerServiceImpl();
        String result = service.findConsigneeAddress("a8d464335069420b88a2ff1b2e77f1f6");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 发送手机验证码
    @Test
    public void testSendVerifyCode() {
        CustomerService service = new CustomerServiceImpl();
        String result = service.sendVerifyCode("916E897CFD298AB703E45EC3A425FE8C", "13812345678", "您的验证码是：{$verifyCode$}，请及时输入验证");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 验证手机号
    @Test
    public void testVerifyPhoneCode() {
        CustomerService service = new CustomerServiceImpl();
        String result = service.verifyPhoneCode("916E897CFD298AB703E45EC3A425FE8C", "13812345678", "743938");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 用户手机号与验证码匹配成功后的操作
    @Test
    public void testVerifySuccess() {
        CustomerService service = new CustomerServiceImpl();
        // StringBuilder builder = new
        // StringBuilder("bind:13819900999+coupon:1234");

        // type=bind:13819900999+coupon:1234
        // String type = builder.toString();
        // 绑定手机号
        // String result =
        // service.verifySuccess("916E897CFD298AB703E45EC3A425FE8C","bindPhone","13819900999");
        // 优惠劵领取
        String result = service.verifySuccess("916E897CFD298AB703E45EC3A425FE8C", "couponReceive", "1234");
        Assert.assertNotNull(result);
        System.out.println("主站返回参数：\n" + result);
    }

    /**
     * 测试根据用户名或手机号查找用户
     */
    @Test
    public void testFindUserByUserName() {
        CustomerService service = new CustomerServiceImpl();
        String result = service.findUserByUserName("hehuan@shangpin.com");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    // 确认收货
    @Test
    public void testConfirmOrderGoods() {
        CustomerService service = new CustomerServiceImpl();
        String result = service.confirmOrderGoods("916E897CFD298AB703E45EC3A425FE8c", "2012090504218");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

}
