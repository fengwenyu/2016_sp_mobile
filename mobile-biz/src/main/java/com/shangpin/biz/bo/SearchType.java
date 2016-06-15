package com.shangpin.biz.bo;

import java.io.Serializable;

public enum SearchType implements Serializable{
		PRODUCT,//搜索商品
		ALL_FILTER,//搜索所有筛选条件
		BRAND_FILTER,//搜索品牌筛选条件
		CATEGORY_FILTER,//搜索品类筛选条件
		OTHER_FILTER;	//搜索其他筛选条件	
}
