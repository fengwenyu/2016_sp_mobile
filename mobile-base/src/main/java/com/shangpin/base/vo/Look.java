package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class Look  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String pic;
	private ArrayList<String> style;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public ArrayList<String> getStyle() {
        return style;
    }
    public void setStyle(ArrayList<String> style) {
        this.style = style;
    }
	
	
}
