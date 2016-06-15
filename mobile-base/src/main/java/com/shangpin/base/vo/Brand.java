package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 品牌
 * 
 * @author zhanghongwei
 *
 */
public class Brand   implements Serializable{
	private static final long serialVersionUID = 1L;
	// 品牌id
    private String id;
    // 品牌名称
    private String name;
    //Add by cuibinqiang
    private String imgurl;
    private String chname;//品牌中文名称
    
    //这个是搜索需要用到的品牌VO 保留历史字段
	private String nameEN;//品牌英文名
	private String nameCN;//品牌中文名
	private String pic;//品牌图片
	private String count;//条数

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getChname() {
        return chname;
    }

    public void setChname(String chname) {
        this.chname = chname;
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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
    
    
}
