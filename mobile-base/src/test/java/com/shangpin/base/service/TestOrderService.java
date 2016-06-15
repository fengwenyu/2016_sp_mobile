package com.shangpin.base.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.shangpin.base.service.impl.OrderServiceImpl;
import com.shangpin.base.vo.Logistics;
import com.shangpin.base.vo.Merchandise;
import com.shangpin.base.vo.LogisticsInfo;
import com.shangpin.base.vo.LogisticsList;

public class TestOrderService {

    /**
     * 获取用户购物车、优惠券、物流、订单数量（尚品、奥莱共用）
     */
    @Test
    public void testFindUserBuyInfo() {
        OrderService service = new OrderServiceImpl();
        String result = service.findUserBuyInfo("916E897CFD298AB703E45EC3A425FE8c", "2012-12-13 12:20:55", "2");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 获取用户购物车、优惠券、物流、订单数量（尚品、奥莱共用）
     */
    @Test
    public void testFindOrderLogistic() {
        OrderService service = new OrderServiceImpl();
        String result = service.findOrderLogistic("916E897CFD298AB703E45EC3A425FE8c", "123123", "1", "50", "30");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 获取用户购物车、优惠券、物流、订单数量（尚品、奥莱共用）
     */
    @Test
    public void testFindLogisticsDetail() {
        OrderService service = new OrderServiceImpl();
        String result = service.findLogisticsDetail("916E897CFD298AB703E45EC3A425FE8c", "02225940", "2012112233445");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 我的优惠券列表（尚品、奥莱共用）
     */
    @Test
    public void testFindCoupons() {
        OrderService service = new OrderServiceImpl();
        String result = service.findCoupons("916E897CFD298AB703E45EC3A425FE8c", "1", "10", "1", "-1");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 订单优惠券列表（尚品、奥莱共用）
     */
    @Test
    public void testFindSelectCoupon() {
        OrderService service = new OrderServiceImpl();
        String result = service.findSelectCoupon("916E897CFD298AB703E45EC3A425FE8c", "1", "10", "1");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 更改订单支付方式（尚品、奥莱共用）
     */
    @Test
    public void testModifyChagePayMode() {
        OrderService service = new OrderServiceImpl();
        String result = service.modifyChagePayMode("916E897CFD298AB703E45EC3A425FE8C", "2012091204415", "30", "20");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 获取礼品卡列表（尚品、奥莱共用）
     */
    @Test
    public void testFindGiftCards() {
        OrderService service = new OrderServiceImpl();
        String result = service.findGiftCards("6ED12A52740E269ACF51840ABA5B91EE", "2", "0", "2", "1", "10");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 选择礼品卡（尚品、奥莱共用）
     */
    @Test
    public void testFindSelectGiftCards() {
        OrderService service = new OrderServiceImpl();
        String result = service.findSelectGiftCards("916E897CFD298AB703E45EC3A425FE8C", "123456", "201361801", "1");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 礼品卡支付（尚品、奥莱共用）
     */
    @Test
    public void testModifyPayGiftCards() {
        OrderService service = new OrderServiceImpl();
        String result = service.modifyPayGiftCards("916E897CFD298AB703E45EC3A425FE8C", "123123", "123456|123457", "201361801", "shangpin001@qqc.om", "1", null);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试确认订单信息
     */
    @Test
    public void testConfirmOrderInfo() {
        OrderService service = new OrderServiceImpl();
        String result = service.confirmOrderInfo("458A84BA51339E072AC75A66AD612CF0", "492", "600", "120", "160", "1");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试确认订单信息时更改地址和优惠码/券内容
     */
    @Test
    public void testModifyConfirmOrderInfo() {
        OrderService service = new OrderServiceImpl();
        String result = service.modifyConfirmOrderInfo("916E897CFD298AB703E45EC3A425FE8C", "2", "123456", "=123456|123457|123458", "8849", "2012121","");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试 获取优惠码信息
     */
    @Test
    public void testFindCouponCodeInfo() {
        OrderService service = new OrderServiceImpl();
        String result = service.findCouponCodeInfo("916E897CFD298AB703E45EC3A425FE8C", "123456", "123456|123457|123458", "2122");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试获取用户礼品卡数量
     */
    @Test
    public void testFindGiftCardsByUser() {
        OrderService service = new OrderServiceImpl();
        String result = service.findGiftCardsByUser("916E897CFD298AB703E45EC3A425FE8C", "2");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试获取24小时内未被激活的礼品卡密钥
     */
    @Test
    public void testFindGiftCardNewSecret() {
        OrderService service = new OrderServiceImpl();
        String result = service.findGiftCardNewSecret("916E897CFD298AB703E45EC3A425FE8C");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试获取礼品卡列表
     */
    @Test
    public void testFindGiftCardList() {
        OrderService service = new OrderServiceImpl();
        String result = service.findGiftCardList("6ED12A52740E269ACF51840ABA5B91EE", "2", "0", "2", "0", "10");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 测试获取某张礼品卡消费记录
     */
    @Test
    public void testFindGiftCardConsumption() {
        OrderService service = new OrderServiceImpl();
        String result = service.findGiftCardConsumption("916E897CFD298AB703E45EC3A425FE8C", "LPGK200000000201");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    // 获取某一订单简要信息
    @Test
    public void testFindOrder() {
        OrderService service = new OrderServiceImpl();
        String result = service.findOrder("916E897CFD298AB703E45EC3A425FE8C", "2012091204415");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    // 获取某一订单详细信息
    @Test
    public void testFindOrderDetail() {
        OrderService service = new OrderServiceImpl();
        String result = service.findOrderDetail("916E897CFD298AB703E45EC3A425FE8C", "2012091204415", "132", "146");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试 查询用户所有物流列表（分页）（尚品、奥莱共用）
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindLogisticsList() {
        OrderService service = new OrderServiceImpl();
        String result = service.findLogisticsList("916E897CFD298AB703E45EC3A425FE8c", "1", "30", "50", "1", "10", "1", "1");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试 查询用户所有物流列表（分页）（尚品、奥莱共用）
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindLogisticsListObj() {
        OrderService service = new OrderServiceImpl();
        List<LogisticsInfo> orderList = service.findOrderListObj("916E897CFD298AB703E45EC3A425FE8c", "1", "30", "50", "1", "10", "1", "1");
        Assert.assertNotNull(orderList);
        for (LogisticsInfo order : orderList) {
            System.out.println(order.getOrderid() + "&&&&&&&&&&&&");
            List<LogisticsList> plist = order.getList();
            if (plist != null && plist.size() > 0) {
                for (LogisticsList p : plist) {
                    List<Merchandise> mlist = p.getProduct();
                    List<Logistics> llist = p.getLogistics();
                    System.out.println(mlist + "  ====");
                    System.out.println(llist + "  *****");
                    if (mlist != null && mlist.size() > 0) {
                        for (Merchandise m : mlist) {
                            System.out.println(m.getSku());
                            System.out.println(m.getBrand());
                            System.out.println(m.getProductname());
                            System.out.println(m.getCate());
                        }
                    }

                    if (llist != null && llist.size() > 0) {
                        for (Logistics l : llist) {
                            System.out.println(l.getAddress());
                            System.out.println(l.getExpress());
                            System.out.println(l.getOrderId());
                            System.out.println(l.getStatus());
                            System.out.println(l.getStatusdesc());
                        }
                    }

                }
            }
        }
    }

    /**
     * 测试物流详情（尚品、奥莱共用）
     * 
     * @author zhanghongwei
     */
    @Test
    public void testFindLogisticsDetailObj() {
        OrderService service = new OrderServiceImpl();
        Logistics logis = service.findLogisticsDetailObj("566537929BB692F41C445544EAD8F0E8", "2013012807654", "120684667103");
        Assert.assertNotNull(logis);
        System.out.println(logis.getExpress());
        System.out.println(logis.getStatus());
    }
    /**
     * 测试订单列表
     * 
     * @author zhanghongwei
     */
    @Test
    public void testGetOrderlist() {
        OrderService service = new OrderServiceImpl();
        String result = service.getOrderlist("566537929BB692F41C445544EAD8F0E8", "1", "1", "10","0");
        Assert.assertNotNull(result);
        System.out.println(result);
    }
}
