package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author qinyingchun
 *
 */
public class MerchandiseFirstprops  implements Serializable{
	private static final long serialVersionUID = 1L;
	/*** 颜色图标 */
    private String icon;
    /*** 颜色名称 */
    private String firstprop;
    /*** 可变属性 */
    private List<MerchandiseSecondprops> secondprops;
    private String thumbnail;
    private List<String> pics = new ArrayList<String>();

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFirstprop() {
        return firstprop;
    }

    public void setFirstprop(String firstprop) {
        this.firstprop = firstprop;
    }

    public List<MerchandiseSecondprops> getSecondprops() {
        return secondprops;
    }

    public void setSecondprops(List<MerchandiseSecondprops> secondprops) {
        this.secondprops = secondprops;
    }

    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail
     *            the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return the pics
     */
    public List<String> getPics() {
        return pics;
    }

    /**
     * @param pics
     *            the pics to set
     */
    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MerchandiseFirstprops [icon=" + icon + ", firstprop=" + firstprop + ", secondprops=" + secondprops + ", thumbnail=" + thumbnail + ", pics=" + pics + "]";
    }

}
