package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author qinyingchun
 * 商品详细尺寸
 *
 */
public class ProductDetailSize implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private List<String> table = new ArrayList<String>(); 
    private String pic;
    private List<String> sizepiclist = new ArrayList<String>();
    /**
     * @return the table
     */
    public List<String> getTable() {
        return table;
    }
    /**
     * @param table the table to set
     */
    public void setTable(List<String> table) {
        this.table = table;
    }
    /**
     * @return the pic
     */
    public String getPic() {
        return pic;
    }
    /**
     * @param pic the pic to set
     */
    public void setPic(String pic) {
        this.pic = pic;
    }
    /**
     * @return the sizepiclist
     */
    public List<String> getSizepiclist() {
        return sizepiclist;
    }
    /**
     * @param sizepiclist the sizepiclist to set
     */
    public void setSizepiclist(List<String> sizepiclist) {
        this.sizepiclist = sizepiclist;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProductDetailSize [table=" + table + ", pic=" + pic + ", sizepiclist=" + sizepiclist + "]";
    }

}
