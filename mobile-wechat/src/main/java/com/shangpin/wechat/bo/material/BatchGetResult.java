package com.shangpin.wechat.bo.material;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cuibinqiang on 2015/12/14.
 */
public class BatchGetResult  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String total_count;
    private String item_count;
    private List<Media> item;

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getItem_count() {
        return item_count;
    }

    public void setItem_count(String item_count) {
        this.item_count = item_count;
    }

    public List<Media> getItem() {
        return item;
    }

    public void setItem(List<Media> item) {
        this.item = item;
    }
}
