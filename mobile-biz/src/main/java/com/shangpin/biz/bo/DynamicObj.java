package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class DynamicObj<T> implements Serializable {

    private static final long serialVersionUID = -9185607734928327617L;

    private String title;

    private List<T> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
