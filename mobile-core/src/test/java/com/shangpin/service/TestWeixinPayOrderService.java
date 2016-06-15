package com.shangpin.service;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.WeixinPayOrder;
import com.shangpin.core.service.IWeixinPayOrderService;

public class TestWeixinPayOrderService extends BaseTest {

    @Autowired
    IWeixinPayOrderService service;

    /**
     * 测试添加
     * 
     * @author zhanghongwei
     */
    @Test
    public void testAdd() {
        WeixinPayOrder bean = new WeixinPayOrder();
        bean.setBankBillNo("aaaa");
        bean.setBankType("bbb");
        bean.setBuyerAlias("ccc");
        bean.setDiscount(null);
        bean.setFeeType("ddd");
        bean.setNotifyId("ffff");
        bean.setOpenId("eeee");
        bean.setOrderNo("ggggg");
        bean.setPayInfo("hhhhh");
        bean.setProductFee(null);
        bean.setTimeEnd(new Date());
        bean.setTotalFee(null);
        bean.setTradeMode("iiii");
        bean.setTradeState("llll");
        bean.setTransId("mmmmm");
        bean.setTransportFee(null);

        WeixinPayOrder entity = service.add(bean);
        Assert.notNull(entity.getId());
    }

    /**
     * 测试修改
     * 
     * @author zhanghongwei
     */
    @Test
    public void testModify() {
        WeixinPayOrder bean = service.findById(25l);
        BigDecimal discount = new BigDecimal(10002.22);
        BigDecimal productFee = new BigDecimal(333333.22);
        BigDecimal totalFee = new BigDecimal(44444.22);
        bean.setDiscount(discount);
        bean.setProductFee(productFee);
        bean.setTimeEnd(new Date());
        bean.setTotalFee(totalFee);
        bean.setOrderNo("ooooo");
        WeixinPayOrder entity = service.modify(bean);
        boolean ist = !("ggggg".equals(entity.getOrderNo()));
        Assert.isTrue(ist);
    }

    /**
     * 测试删除
     * 
     * @author zhanghongwei
     */
    @Test
    public void testDeleteById() {
        service.deleteById(24l);
        WeixinPayOrder entity = service.findById(24l);
        Assert.isNull(entity);
    }

    /**
     * 测试通过ID查询
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindById() {
        WeixinPayOrder bean = service.findById(1l);
        Assert.notNull(bean);
    }

    /**
     * 测试根据订单编号查询实体对象
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindByOrderNo() {
        WeixinPayOrder bean = service.findByOrderNo("20140317022320");
        Assert.notNull(bean);
        System.out.println(bean.getId());
    }

    /**
     * 测试根据交易ID查询实体对象
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindByTransId() {
        WeixinPayOrder bean = service.findByTransId("1217974201201402268235344720");
        Assert.notNull(bean);
        System.out.println(bean.getId());
    }

}