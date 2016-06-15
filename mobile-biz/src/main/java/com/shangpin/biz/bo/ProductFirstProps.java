package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ProductFirstProps
 * @Description:商品详情第一属性实体类（支持sku不同价格体系）
 * @author wangfeng
 * @date 2015年3月11日
 * @version 1.0
 */
public class ProductFirstProps implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7171399291420395450L;
    private String icon;//sku属性图片
    private String firstProp;//sku第一属性值
    private String isSecondProp;//是否有第二属性 0没有 1有
    private List<String> pics;//第一属性图片
    private List<ProductSecondProps> secondProps; //sku第二属性对象
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getFirstProp() {
        return firstProp;
    }
    public void setFirstProp(String firstProp) {
        this.firstProp = firstProp;
    }
    
    public String getIsSecondProp() {
		return isSecondProp;
	}
	public void setIsSecondProp(String isSecondProp) {
		this.isSecondProp = isSecondProp;
	}
	
	public List<String> getPics() {
		return pics;
	}
	public void setPics(List<String> pics) {
		this.pics = pics;
	}
	public List<ProductSecondProps> getSecondProps() {
		return secondProps;
	}
	public void setSecondProps(List<ProductSecondProps> secondProps) {
		this.secondProps = secondProps;
	}

}
