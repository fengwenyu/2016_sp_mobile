package com.shangpin.biz.service.impl;


import org.springframework.stereotype.Service;

import com.shangpin.biz.service.ASPBizLockSkuService;
import com.shangpin.biz.service.abstraction.AbstractBizLockSupplierSku;
@Service
public class ASPBizLockSkuServiceImpl extends AbstractBizLockSupplierSku implements ASPBizLockSkuService {
/*	@Override
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
					ResultObjMapList<Address> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>() {
					});
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
	*/

}
