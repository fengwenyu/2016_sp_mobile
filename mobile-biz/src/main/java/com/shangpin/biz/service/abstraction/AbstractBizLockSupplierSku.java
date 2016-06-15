package com.shangpin.biz.service.abstraction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.SupplierSkuNoInfo;
import com.shangpin.biz.bo.base.ResponseResult;
import com.shangpin.biz.bo.base.ResultContent;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizLockSupplierSku {
	public static Logger logger = LoggerFactory.getLogger(AbstractBizEntranceService.class);
	@Autowired
	ShangPinService shangPinService;
	public String doBaseLockSku(String supplierNo,String orderNo,String orderItemList) throws Exception{
		String json = shangPinService.LockSku(supplierNo,orderNo,orderItemList);
		logger.debug("调用base接口返回数据:" + json);
		return json;
	}
	public String doBaseGetOrderLockSupplierSkuList(String orderNo) throws Exception{
		String json = shangPinService.getOrderLockSupplierInfo(orderNo);
		logger.debug("调用base接口返回数据:" + json);
		return json;
	}
	public String doBaseStockAbnormalSyncZero(String warehouseNo,
			String supplierNo, String formNo, String operateUser,
			String skuDetailDtos) throws Exception{
		String json = shangPinService.stockAbnormalSyncZero(warehouseNo,supplierNo, formNo, operateUser,
				skuDetailDtos);
		logger.debug("调用base接口返回数据:" + json);
		return json;
		
	}
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
