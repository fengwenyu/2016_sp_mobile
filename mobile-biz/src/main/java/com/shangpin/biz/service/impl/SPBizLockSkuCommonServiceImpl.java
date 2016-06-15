package com.shangpin.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.SupplierSkuNoInfo;
import com.shangpin.biz.bo.base.ResponseResult;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultContent;
import com.shangpin.biz.service.SPBizLockSkuCommonService;
import com.shangpin.biz.service.SPBizLockSkuService;
import com.shangpin.biz.service.SPBizOrderService;
import com.shangpin.biz.utils.Constants;
@Service
public class SPBizLockSkuCommonServiceImpl implements
		SPBizLockSkuCommonService {
	public static Logger log = LoggerFactory.getLogger(SPBizLockSkuCommonServiceImpl.class);
	@Autowired
	SPBizOrderService spBizOrderService;
	@Autowired
	SPBizLockSkuService spBizLockSkuService;
	@Override
	public Map<String, String> orderLockSku(String orderNo,String userid,String mainOrderid) {
		Map<String,String> returnMap =new HashMap<String,String>();
		returnMap.put("code", "0");
		returnMap.put("msg", "SUCCESS");
		return returnMap;
		/*//1:查询锁库存接口
		List<SupplierSkuNoInfo> list=spBizLockSkuService.getOrderLockSupplierSkuList(orderNo);
		returnMap= baseOrderLockSku(orderNo,userid, mainOrderid,list);
		return returnMap;*/
		
	}
	@Override
	public Map<String, String> orderLockSku(String orderNo,String userid,String mainOrderid,List<SupplierSkuNoInfo> list) {
		Map<String,String> returnMap =new HashMap<String,String>();
		returnMap.put("code", "0");
		returnMap.put("msg", "SUCCESS");
		return returnMap;
		/*returnMap= baseOrderLockSku(orderNo,userid, mainOrderid,list);
		return returnMap;*/
	}
	
	
	private Map<String, String> baseOrderLockSku(String orderNo,String userid,String mainOrderid,List<SupplierSkuNoInfo> list){
		Map<String,String> returnMap =new HashMap<String,String>();
		//1:查询锁库存接口
		Map<String,String> map =new HashMap<String,String>();
		
		if(list==null||list.size()<=0){
			returnMap.put("code",Constants.ORDER_LOCKSKU_ERROR);
			returnMap.put("msg",Constants.ORDER_LOCKSKU_ERROR_PROMPT);
			return returnMap;
		}
		if(list.size()>1){
			returnMap.put("code", "0");
			returnMap.put("msg", "SUCCESS");
			return returnMap;
		}
		map.put("code", "0");
		for(int i=0;i<list.size();i++){
			String orderItemList= new StringBuffer(list.get(i).getSkuNo()).append(":").append(list.get(i).getQuantity()).toString();
			ResultContent resultContent=spBizLockSkuService.lockSku(list.get(i).getSupplierNo(), orderNo, orderItemList);
			if(resultContent==null){
				continue;
			}
			//如果请求失败
			if(resultContent.getResponseCode().equals(Constants.ERROR_LOCKSKU_NETWORK)||resultContent.getResponseCode().equals(Constants.ERROR_LOCKSKU_PUSHFAIL)){
				log.info("lockSku back orderNo:{}, mainOrderid:{},errcode:{},errmsg:{}",orderNo,mainOrderid,resultContent.getResponseCode(),resultContent.getResopnseMsg());
				/*returnMap.put("code", Constants.ORDER_LOCKSKU_ERROR);
				returnMap.put("msg", Constants.ORDER_LOCKSKU_ERROR_PROMPT);
				return returnMap;*/
				continue;
			}
			if(!resultContent.getResponseCode().equals(Constants.ERROR_LOCKSKU_NOTSKU)&&!resultContent.getResponseCode().equals(Constants.ERROR_LOCKSKU_UNDERSTOCK)){
				continue;
			}
			
			//如果库存不足或者未找到对应sku
			if(resultContent.getResponseCode().equals(Constants.ERROR_LOCKSKU_NOTSKU)||resultContent.getResponseCode().equals(Constants.ERROR_LOCKSKU_UNDERSTOCK)){
				map.put("code", "1");
				String key=list.get(i).getWarehouseNo()+"|"+list.get(i).getSupplierNo();
				if(map.containsKey(key)){
					String value=map.get(key);
					value=value+","+orderItemList;
					map.put(key,value);
				}else{
					map.put(key,orderItemList);
				}
			
			}
			
			
		}
		if(map.get("code").equals("1")){
			ResultBase resultBase=spBizOrderService.beCancelOrder(userid, mainOrderid);
			if(!resultBase.getCode().equals("0")){
				returnMap.put("code", Constants.ORDER_LOCKSKU_ERROR);
				returnMap.put("msg", Constants.ORDER_LOCKSKU_ERROR_PROMPT);
				return returnMap;
			}
			map.remove("code");
			for (Map.Entry<String, String> entry : map.entrySet()) {   
				String[] keyArr=entry.getKey().split("[|]");
				ResponseResult responseResult=spBizLockSkuService.stockAbnormalSyncZero(keyArr[0], keyArr[1], orderNo, userid, entry.getValue());
				String resposeStatus=responseResult.getResposeStatus();
				if(!resposeStatus.equals(Constants.STOCKSYNCZERO_SUCCESS)){
					ResponseResult result=spBizLockSkuService.stockAbnormalSyncZero(keyArr[0], keyArr[1], orderNo, userid, entry.getValue());
					log.info("stockAbnormalSyncZero back errcode:{},errstatus:{},errmsg:{},",result.getResposeStatus(),result.getErrorCode(),result.getErrorMessage());
				}
	        }  
			returnMap.put("code", Constants.ORDER_LOCKSKU_FAIL);
			returnMap.put("msg", Constants.ORDER_LOCKSKU_FAIL_PROMPT);
			return returnMap;
		
		}
		returnMap.put("code", "0");
		returnMap.put("msg", "SUCCESS");
		return returnMap;
	}
}
