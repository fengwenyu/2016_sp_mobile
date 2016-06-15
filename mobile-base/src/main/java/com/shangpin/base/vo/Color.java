package com.shangpin.base.vo;

import java.io.Serializable;

public class Color implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String pic;
	private String rgb;
	private String count;// 数量

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getRgb() {
		return rgb;
	}

	public void setRgb(String rgb) {
		this.rgb = rgb;
	}

}
