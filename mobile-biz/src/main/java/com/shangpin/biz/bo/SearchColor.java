package com.shangpin.biz.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/** 
* @ClassName: SearchColor 
* @Description:搜索颜色实体类 
* @author qinyingchun
* @date 2014年10月28日
* @version 1.0 
*/
//将该标记放在属性上，如果该属性为NULL则不参与序列化  
//如果放在类上边,那对这个类的全部属性起作用  
//Include.Include.ALWAYS 默认  
//Include.NON_DEFAULT 属性为默认值不序列化  
//Include.NON_EMPTY 属性为 空（“”）  或者为 NULL 都不序列化  
//Include.NON_NULL 属性为NULL 不序列化  
@JsonInclude(Include.NON_NULL)  
public class SearchColor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;//颜色id
	private String name;//颜色名称
	private String rgb;//颜色rbg值
	private String count;//条数
	private String colorId;//颜色id
	private String colorName;//颜色名称
	private String colorRgb;//颜色rbg值
    public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getColorRgb() {
		return colorRgb;
	}
	public void setColorRgb(String colorRgb) {
		this.colorRgb = colorRgb;
	}
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRgb() {
        return rgb;
    }
    public void setRgb(String rgb) {
        this.rgb = rgb;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
	
	

}
