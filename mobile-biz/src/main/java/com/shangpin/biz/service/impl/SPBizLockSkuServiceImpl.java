package com.shangpin.biz.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.SupplierSkuNoInfo;
import com.shangpin.biz.bo.base.ResponseResult;
import com.shangpin.biz.bo.base.ResultContent;
import com.shangpin.biz.service.SPBizLockSkuService;
import com.shangpin.biz.service.abstraction.AbstractBizLockSupplierSku;
import com.shangpin.utils.JsonUtil;
@Service
public class SPBizLockSkuServiceImpl extends AbstractBizLockSupplierSku implements SPBizLockSkuService {
	private static final Logger logger = LoggerFactory.getLogger(SPBizLockSkuServiceImpl.class);
	@Override
	public ResultContent lockSku(String supplierNo, String orderNo,
			String orderItemList) {
		try {
			String json =doBaseLockSku(supplierNo,orderNo,orderItemList);
			return JsonUtil.fromJson(json, ResultContent.class);
		} catch (Exception e) {
			logger.error("调用锁库存接口错误!");
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<SupplierSkuNoInfo> getOrderLockSupplierSkuList(String orderNo) {
		try {
			String json =doBaseGetOrderLockSupplierSkuList(orderNo);
			//List<SupplierSkuNoInfo> result = JsonUtil.fromJson(json, new ArrayList<SupplierSkuNoInfo>() );
				/*	ResultObjMapList<Address> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>() {
					});*/
			List<SupplierSkuNoInfo> result = JsonUtil.fromJson(json, new TypeToken<List<SupplierSkuNoInfo>>() {
	            }.getType());
			return result;
		} catch (Exception e) {
			logger.error("调用锁库存接口错误!");
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public ResponseResult stockAbnormalSyncZero(String warehouseNo,
			String supplierNo, String formNo, String operateUser,
			String skuDetailDtos) {
		try {
			String json =doBaseStockAbnormalSyncZero(warehouseNo,supplierNo,formNo,operateUser,skuDetailDtos);
			return JsonUtil.fromJson(json, ResponseResult.class);
		} catch (Exception e) {
			logger.error("调用库存清零接口错误!");
			e.printStackTrace();
		}
		return null;
	}
	

}
