package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.RecProduct;

public interface SPBizRecProductService {
	public List<RecProduct> findRecProductObj(String userId, String type,String shopType,String productId, String pageIndex, String pageSize);
}
