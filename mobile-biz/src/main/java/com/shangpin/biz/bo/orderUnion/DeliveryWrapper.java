package com.shangpin.biz.bo.orderUnion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shangpin.biz.bo.DeliveryVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author liushaoqing
 * @version 1.0
 *
 * 国内海外合并 结算接口消息返回
 * 收货地址包装实体
 * wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279841
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DeliveryWrapper  implements Serializable {

    private DeliveryVo lastDelivery;
    private List<DeliveryVo> list;

    public DeliveryVo getLastDelivery() {
        return lastDelivery;
    }

    public void setLastDelivery(DeliveryVo lastDelivery) {
        this.lastDelivery = lastDelivery;
    }

    public List<DeliveryVo> getList() {
        return list;
    }

    public void setList(List<DeliveryVo> list) {
        this.list = list;
    }
}
