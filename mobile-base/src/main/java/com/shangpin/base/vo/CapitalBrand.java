package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 根据大写字母品牌分类
 * 
 * @author zhanghongwei
 *
 */
public class CapitalBrand  implements Serializable{
	private static final long serialVersionUID = 1L;
	// 品牌的英文名称大写首字母，起索引作用
    private String capital;
    // 对应capital包含的品牌集合
    private List<Brand> brands;

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

}
