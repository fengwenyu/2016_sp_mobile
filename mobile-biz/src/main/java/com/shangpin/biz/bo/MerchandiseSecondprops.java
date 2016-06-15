package com.shangpin.biz.bo;

import java.io.Serializable;

public class MerchandiseSecondprops  implements Serializable{
    private static final long serialVersionUID = -867705767359847052L;
    /*** 商品唯一标识 */
	private String sku;
	/*** 尺寸或者颜色 */
	private String secondprop;
	/*** 库存数量 */
	private String count;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSecondprop() {
		return secondprop;
	}

	public void setSecondprop(String secondprop) {
		this.secondprop = secondprop;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "MerchandiseSecondprops [sku=" + sku + ", secondprop=" + secondprop + ", count=" + count + "]";
	}
}
