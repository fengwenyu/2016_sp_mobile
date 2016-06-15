package com.shangpin.biz.bo.orderUnion;

import java.util.List;

/**
* @author liushaoqing
* 优惠券接口返回数据封装
* wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279954
*/
public class CouponsWraper {

    private String count;
    private List<Coupon> list;

    public List<Coupon> getList() {
        return list;
    }

    public void setList(List<Coupon> list) {
        this.list = list;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
