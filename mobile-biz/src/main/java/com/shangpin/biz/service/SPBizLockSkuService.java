package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.SupplierSkuNoInfo;
import com.shangpin.biz.bo.base.ResponseResult;
import com.shangpin.biz.bo.base.ResultContent;

public interface SPBizLockSkuService {
	public ResultContent lockSku(String supplierNo,String orderNo,String orderItemList);
	public List<SupplierSkuNoInfo> getOrderLockSupplierSkuList(String orderNo);
	public ResponseResult stockAbnormalSyncZero(String warehouseNo,String supplierNo ,String formNo, String operateUser,String skuDetailDtos);   
}
