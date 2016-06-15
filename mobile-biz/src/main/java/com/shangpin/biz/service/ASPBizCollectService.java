package com.shangpin.biz.service;

import com.shangpin.biz.bo.CollectProductList;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.service.basic.IBizCollectService;

/**
 * 收藏接口
 * @author zghw
 *
 */
public interface ASPBizCollectService extends IBizCollectService{
	public ResultObjMapList<CollectProductList> doCollectProductList(String userId, String shopType, String pageIndex, String pageSize, String postArea);

}
