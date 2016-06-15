package com.shangpin.biz.service;

import com.shangpin.biz.bo.PointBuyProduct;
import com.shangpin.biz.bo.PointBuyTimesList;
import com.shangpin.biz.bo.base.ResultObjOne;

public interface ASPBizPointBuyService {

	public ResultObjOne<PointBuyTimesList> findTimesList();
	public ResultObjOne<PointBuyProduct> findProductList(String pharseId);
}
