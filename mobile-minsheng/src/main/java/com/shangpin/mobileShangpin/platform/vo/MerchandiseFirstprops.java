package com.shangpin.mobileShangpin.platform.vo;

import java.util.List;

public class MerchandiseFirstprops {
	/*** 颜色图标 */
	private String icon;
	/*** 颜色名称 */
	private String firstprop;
	/*** 可变属性 */
	private List<MerchandiseSecondprops> secondprops;

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
		return "MerchandiseFirstprops [icon=" + icon + ", firstprop=" + firstprop + ", secondprops=" + secondprops + "]";
	}
}
