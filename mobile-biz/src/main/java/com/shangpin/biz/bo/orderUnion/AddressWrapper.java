package com.shangpin.biz.bo.orderUnion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shangpin.biz.bo.Receive;

import java.io.Serializable;

/**
 * @author liushaoqing
 * @version 1.0
 *
 * 国内海外合并 结算接口消息返回
 * 地址包装实体封装
 * wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279841
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AddressWrapper  implements Serializable {

    private String valid;//1 是 0 是否
    private String prompt; //"当valid为0是显示：电子卡订单不需要收获地址"
    private Receive lastReceived; //
    private DeliveryWrapper delivery;

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Receive getLastReceived() {
		return lastReceived;
	}

	public void setLastReceived(Receive lastReceived) {
		this.lastReceived = lastReceived;
	}

	public DeliveryWrapper getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryWrapper delivery) {
        this.delivery = delivery;
    }
}
