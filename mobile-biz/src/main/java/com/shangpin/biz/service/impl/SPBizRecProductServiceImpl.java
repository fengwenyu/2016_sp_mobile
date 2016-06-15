package com.shangpin.biz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.RecProduct;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.service.SPBizRecProductService;
import com.shangpin.biz.service.abstraction.AbstractBizCartService;
@Service
public class SPBizRecProductServiceImpl extends AbstractBizCartService implements SPBizRecProductService{

	@Override
	public List<RecProduct> findRecProductObj(String userId, String type,String shopType,String productId, String pageIndex, String pageSize) {
		try {
			ResultObjMapList<RecProduct> obj = fromRecProductList(userId, type, shopType,productId, pageIndex,
					pageSize);
			if (obj != null && obj.isSuccess()) {
				return obj.getList("productList");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}