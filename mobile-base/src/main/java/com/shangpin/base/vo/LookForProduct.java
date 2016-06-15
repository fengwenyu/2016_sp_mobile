package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

public class LookForProduct  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String pic;
	private List<String> style;	
	private List<Product> list;
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public List<String> getStyle() {
        return style;
    }
    public void setStyle(List<String> style) {
        this.style = style;
    }
    public List<Product> getList() {
        return list;
    }
    public void setList(List<Product> list) {
        this.list = list;
    }
 
	
}
