package com.shangpin.biz.service;

import java.util.List;
import com.shangpin.base.vo.Look;
import com.shangpin.base.vo.LookForProduct;

/**
 * @ClassName: StyleBizService
 * @Description:style搭配service
 * @author liling
 * @date 2014年11月17日
 * @version 1.0
 */
public interface SPBizStyleService {

	/**
	 * 获取style商品类表
	 * 
	 * @param id
	 *            look活动编号
	 * @return LookForProduct
	 * @author liling
	 */
	public LookForProduct getLookProducts(String id);

	/**
	 * 获取style列表
	 * 
	 * @param pageIndex起始页
	 * @param pageSize条数
	 * @return List<Look>用户获取topshop活动look列表
	 * @author liling
	 */
	public List<Look> getLooks(String pageIndex);

}
