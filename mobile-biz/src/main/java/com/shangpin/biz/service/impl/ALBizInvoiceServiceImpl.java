package com.shangpin.biz.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.service.ALBizInvoiceService;
import com.shangpin.biz.service.abstraction.AbstractBizInvoiceService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

/** 
* @ClassName: BizInvoiceServiceImpl 
* @Description:发票地址实现类 
* @author qinyingchun
* @date 2014年11月14日
* @version 1.0 
*/
@Service
public class ALBizInvoiceServiceImpl extends AbstractBizInvoiceService implements ALBizInvoiceService{
	
	private static final Logger logger = LoggerFactory.getLogger(ALBizInvoiceServiceImpl.class);
	
	@Autowired
	private CommonService commonService;

	@Override
	public boolean isAddInvoice(String userId, Address address) {
		try {
			String json = commonService.addInvoiceAddress(userId, address.getName(), address.getProvince(), address.getCity(), address.getArea(), address.getTown(), address.getAddr(), address.getPostcode(), address.getTel());
			logger.debug("invoice interface add return data :" + json);
			ResultObjMapList<Address> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>(){});
			if(Constants.SUCCESS.equals(obj.getCode())){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean isModify(String invoiceId, String userId, Address address) {
		try {
			String json = commonService.modifyInvoiceAddress(invoiceId, userId, address.getName(), address.getProvince(), address.getCity(), address.getArea(), address.getTown(), address.getAddr(), address.getPostcode(), address.getTel());
			logger.debug("invoice interface modify return data :" + json);
			ResultObjMapList<Address> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>(){});
			if(Constants.SUCCESS.equals(obj.getCode())){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public ResultObjMapList<Address> invoices(String userId, Address address) {
		try {
			String json = commonService.addInvoiceAddress(userId, address.getName(), address.getProvince(), address.getCity(), address.getArea(), address.getTown(), address.getAddr(), address.getPostcode(), address.getTel());
			logger.debug("invoice interface add return data :" + json);
			ResultObjMapList<Address> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>(){});
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
