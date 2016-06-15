package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @ClassName: ProductOverseasInfo
 * @Description:海外商品实体类（支持sku不同价格体系）
 * @author wangfeng
 * @date 2015年3月11日
 * @version 1.0
 */
public class ProductOverseasInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2346494790958332439L;
    private String formula;// 海外计算公式
    private String sourceDesc;// 物流信息

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getSourceDesc() {
        return sourceDesc;
    }

    public void setSourceDesc(String sourceDesc) {
        this.sourceDesc = sourceDesc;
    }

}
