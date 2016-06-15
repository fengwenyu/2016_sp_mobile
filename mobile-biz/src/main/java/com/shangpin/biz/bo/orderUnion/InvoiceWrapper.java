package com.shangpin.biz.bo.orderUnion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @author liushaoqing
 * @version 1.0
 *
 * 国内海外合并 结算接口消息返回
 * 发票包装实体
 * wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279841
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceWrapper  implements Serializable {

    private String invoiceButtonStatus;
    private String valid;
    private String title;
    private String prompt;
    private Invoice lastInvoice;

    public String getInvoiceButtonStatus() {
        return invoiceButtonStatus;
    }

    public void setInvoiceButtonStatus(String invoiceButtonStatus) {
        this.invoiceButtonStatus = invoiceButtonStatus;
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

    public Invoice getLastInvoice() {
        return lastInvoice;
    }

    public void setLastInvoice(Invoice lastInvoice) {
        this.lastInvoice = lastInvoice;
    }
}
