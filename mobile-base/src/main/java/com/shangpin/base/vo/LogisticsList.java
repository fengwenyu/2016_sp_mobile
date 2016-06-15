package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 物流包含的物品及物流信息
 * 
 * @author zghw
 *
 */
public class LogisticsList  implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 货品集合 */
    private List<Merchandise> product;
    /** 物流信息 */
    private List<Logistics> logistics;

    public List<Merchandise> getProduct() {
        return product;
    }

    public void setProduct(List<Merchandise> product) {
        this.product = product;
    }

    public List<Logistics> getLogistics() {
        return logistics;
    }

    public void setLogistics(List<Logistics> logistics) {
        this.logistics = logistics;
    }

}
