package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class MerchandiseFirstprops implements Serializable {
	private static final long serialVersionUID = 3125032867334994452L;
	/*** 颜色图标 */
	private String icon;
	/*** 颜色名称 */
	private String firstprop;
	/*** 可变属性 */
	private List<MerchandiseSecondprops> secondprops;

	private List<String> pics;
	private Object thumbnail;

	public Object getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Object thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

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

	@Override
	public String toString() {
		return "MerchandiseFirstprops [icon=" + icon + ", firstprop="
				+ firstprop + ", secondprops=" + secondprops + "]";
	}
}
