package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 品牌
 * 
 * @author zhanghongwei
 *
 */
public class Gallery  implements Serializable{
	private static final long serialVersionUID = 1L;
	// 品牌id
    private String id;
    // 品牌名称
    private String name;
    //Add by cuibinqiang
    private String imgurl;
    private String chname;//品牌中文名称
    

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
}
