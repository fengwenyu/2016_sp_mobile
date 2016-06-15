package com.shangpin.biz.bo.orderUnion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @author liushaoqing
 * @version 1.0
 *
 * 国内海外合并 结算接口消息返回
 * 礼品卡包装实体
 * wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279841
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GiftCardWrapper  implements Serializable {

    private String giftCardButtonStatus;
    private String valid;
    private String title;
    private String prompt;
    private String balance;
    private String canUse;

    public String getGiftCardButtonStatus() {
        return giftCardButtonStatus;
    }

    public void setGiftCardButtonStatus(String giftCardButtonStatus) {
        this.giftCardButtonStatus = giftCardButtonStatus;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCanUse() {
        return canUse;
    }

    public void setCanUse(String canUse) {
        this.canUse = canUse;
    }
}
