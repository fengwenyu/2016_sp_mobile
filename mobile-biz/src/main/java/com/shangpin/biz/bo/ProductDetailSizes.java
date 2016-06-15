package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;
/**
 * @ClassName: ProductDetailSizes
 * @Description:商品尺码详情实体类
 * @author liling
 * @date 2015年3月16日
 * @version 1.0
 */
public class ProductDetailSizes implements Serializable{
	private static final long serialVersionUID = 6309467615877856215L;
	private List<ProductDetailSizeTable> table;
	private List<String> sizePicList;
	public List<ProductDetailSizeTable> getTable() {
		return table;
	}
	public void setTable(List<ProductDetailSizeTable> table) {
		this.table = table;
	}
	public List<String> getSizePicList() {
		return sizePicList;
	}
	public void setSizePicList(List<String> sizePicList) {
		this.sizePicList = sizePicList;
	}
	
}
