package com.shangpin.biz.bo.submitOrder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * <br/>
 * Date: 2016/4/26<br/>
 *
 * @author ZRS
 * @since JDK7
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SelectedPayment implements Serializable{

    private String mainpaymode;
    private String subpaymode;

    public String getMainpaymode() {
        return mainpaymode;
    }

    public void setMainpaymode(String mainpaymode) {
        this.mainpaymode = mainpaymode;
    }

    public String getSubpaymode() {
        return subpaymode;
    }

    public void setSubpaymode(String subpaymode) {
        this.subpaymode = subpaymode;
    }
}
