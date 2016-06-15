package com.shangpin.biz.bo.orderUnion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shangpin.biz.bo.Pay;

import java.io.Serializable;

/**
 * @author liushaoqing
 * @version 1.0
 *
 * 国内海外合并 结算接口消息返回
 * 支付方式实体封装
 * wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279841
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Payment extends Pay implements Serializable {


    private String isSelected;
    private String subpayCode;



    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public String getSubpayCode() {
        return subpayCode;
    }

    public void setSubpayCode(String subpayCode) {
        this.subpayCode = subpayCode;
    }
}
