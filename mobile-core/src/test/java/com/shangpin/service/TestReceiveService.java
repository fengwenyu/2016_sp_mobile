package com.shangpin.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shangpin.BaseTest;
import com.shangpin.core.service.IReceiveService;

public class TestReceiveService extends BaseTest {

    @Autowired
    IReceiveService receiveservice;

    // 查询
    @Test
    public void testFindByUserId() throws Exception {
     Long a = receiveservice.countrecord("lyq", "001","spu001");
 	System.out.println(">>>>>>>>>>>>>>>>"+a);
    }

    // 保存
  /*  @Test
    public void testSave() {
        Receive entiy = new Receive();
        entiy.setFuserid("lyq");
        entiy.setAddressId("北京市朝阳区三间房东路");
        entiy.setCreateTime(new Date());
        entiy.setForderId("001");
        entiy.setFspuId("spu001");
        entiy.setLuserId("lyq001");
        entiy.setPhone("18610721003");
        entiy.setSkuId("skuid001");
        receiveservice.save(entiy);
        Assert.notNull(entiy.getId());
    }
*/
   /* // 修改
    @Test
    public void testModify() {
        AccountAlipay accountAlipay = accountAlipayService.findById(31L);
        accountAlipay.setUserId("6FA7F7D5276E1ECDF4EA6BF02ACA86F9");
        accountAlipay.setNickname("cui");
        accountAlipay = accountAlipayService.modify(accountAlipay);
        Assert.isTrue("cui".equals(accountAlipay.getNickname()));
    }
    
    //删除
    @Test
    public void testDelete() {
        accountAlipayService.delete(37L);
        AccountAlipay accountAlipay = accountAlipayService.findById(37L);
        Assert.isNull(accountAlipay);
    }
*/
}
