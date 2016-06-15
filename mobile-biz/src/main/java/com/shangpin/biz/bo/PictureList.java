package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;


public class PictureList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<Picture> list;
	public List<Picture> getList() {
		return list;
	}
	public void setList(List<Picture> list) {
		this.list = list;
	}
	

}
