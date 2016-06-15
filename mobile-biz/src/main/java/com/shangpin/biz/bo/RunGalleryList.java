package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 运动馆轮播图数据结构
 * @author chenshouqin
 *
 */
public class RunGalleryList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private List<RunGallery> gallery;  //轮播图列表
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<RunGallery> getGallery() {
        return gallery;
    }
    public void setGallery(List<RunGallery> gallery) {
        this.gallery = gallery;
    }
    
}
