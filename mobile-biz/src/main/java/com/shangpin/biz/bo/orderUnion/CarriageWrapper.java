package com.shangpin.biz.bo.orderUnion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @author liushaoqing
 * @version 1.0
 *
 * 国内海外合并 结算接口消息返回
 * 关税说明包装实体封装
 * wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279841
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CarriageWrapper  implements Serializable {

    private String amount;
    private Carriage desc;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Carriage getDesc() {
        return desc;
    }

    public void setDesc(Carriage desc) {
        this.desc = desc;
    }
}
