package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 运动馆轮播图
 * @author chenshouqin
 *
 */
public class RunGallery extends CommonRules implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String pic; //图片编号

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
