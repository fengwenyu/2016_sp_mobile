package com.shangpin.biz.bo;

import java.io.Serializable;


/** 
* @ClassName: SearchBrand 
* @Description:搜索品牌条件类 
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
//@JsonInclude(Include.NON_NULL)  
public class SearchBrand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;//品牌id
	private String nameEN;//品牌英文名称
	private String nameCN;//品牌中文名称
	private String name;//客户端英文name
	private String brandEnName;//品牌英文名称
	private String brandCnName;//品牌中文名称
	private String brandEnViewName;
	private String picNo;
	private String imgurl;
	private String count;//条数
	
	public String getBrandEnViewName() {
		return brandEnViewName;
	}
	public void setBrandEnViewName(String brandEnViewName) {
		this.brandEnViewName = brandEnViewName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPicNo() {
		return picNo;
	}
	public void setPicNo(String picNo) {
		this.picNo = picNo;
	}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNameEN() {
        return nameEN;
    }
    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }
    public String getNameCN() {
        return nameCN;
    }
    public void setNameCN(String nameCN) {
        this.nameCN = nameCN;
    }
    public String getImgurl() {
        return imgurl;
    }
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
	public String getBrandEnName() {
		return brandEnName;
	}
	public void setBrandEnName(String brandEnName) {
		this.brandEnName = brandEnName;
	}
	public String getBrandCnName() {
		return brandCnName;
	}
	public void setBrandCnName(String brandCnName) {
		this.brandCnName = brandCnName;
	}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
  
	
	

}
