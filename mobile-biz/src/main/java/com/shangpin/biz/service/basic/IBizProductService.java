package com.shangpin.biz.service.basic;

import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.biz.bo.SPmerchandise;
import com.shangpin.biz.bo.base.ResultObjOne;

/**
 * 商品基础接口
 * 
 * @author zghw
 *
 */
public interface IBizProductService {
	/**
	 * 商品详情
	 * 
	 * @param listOfGoods
	 * @return
	 * @author zghw
	 */
	public String fromProductDetail(ListOfGoods listOfGoods);

	/**
	 * 商品详情
	 * 
	 * @param listOfGoods
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<SPmerchandise> beProductDetail(ListOfGoods listOfGoods);

}
