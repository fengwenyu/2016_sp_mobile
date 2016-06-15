package com.shangpin.biz.service.abstraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.CustomerService;
import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizInvoiceService {

	@Autowired
	private CommonService commonService;
	@Autowired
	private CustomerService userService;
	
	public String fromAddInvoiceAddress(String userId, Address address) {
		String json = commonService.addInvoiceAddress(userId, address.getName(), address.getProvince(),
				address.getCity(), address.getArea(), address.getTown(), address.getAddr(), address.getPostcode(),
				address.getTel());
		return json;
	}

	public ResultObjMapList<Address> beAddInvoiceAddress(String userId, Address address) {
		String json = fromAddInvoiceAddress(userId, address);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<Address> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>() {
			});
			return result;
		}
		return null;
	}

	public String fromModifyInvoiceAddress(String invoiceId, String userId, Address address) {
		String json = commonService.modifyInvoiceAddress(invoiceId, userId, address.getName(), address.getProvince(),
				address.getCity(), address.getArea(), address.getTown(), address.getAddr(), address.getPostcode(),
				address.getTel());
		return json;
	}

	public ResultObjMapList<Address> beModifyInvoiceAddress(String invoiceId, String userId, Address address) {
		String json = fromModifyInvoiceAddress(invoiceId, userId, address);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<Address> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>() {
			});
			return result;
		}
		return null;
	}

	public String fromDeleteInvoiceAddress(String userId, String addrId) {
		String json = commonService.deleteInvoiceAddress(userId, addrId);
		return json;
	}

	public ResultBase beDeleteInvoiceAddress(String userId, String addrId) {
		String json = fromDeleteInvoiceAddress(userId, addrId);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}
	public String fromInvoiceAddress(String userId) {
		String json = userService.findConsigneeAddress(userId,"1");
		return json;
	}

	public ResultObjMapList<Address> beInvoiceAddress(String userId) {
		String json = fromInvoiceAddress(userId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<Address> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>() {
			});
			return result;
		}
		return null;
	}
}
