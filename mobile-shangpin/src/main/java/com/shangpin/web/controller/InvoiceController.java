package com.shangpin.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.service.SPBizInvoiceService;
import com.shangpin.web.utils.Constants;

@Controller
@RequestMapping("/invoice")
public class InvoiceController extends BaseController {

	@Autowired
	private SPBizInvoiceService bizInvoiceService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Address address, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = getUserId(request);
		ResultObjMapList<Address> obj = bizInvoiceService.beAddInvoiceAddress(userId, address);
		String code = obj.getCode();
		if (Constants.SUCCESS.equals(code)) {
			map.put("code", Constants.SUCCESS);
			map.put("invoices", obj.getList("list"));
		} else {
			map.put("code", Constants.FAILE);
			map.put("msg", obj.getMsg());
		}
		return map;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	@ResponseBody
	public String modify(Address address, HttpServletRequest request) {
		String userId = getUserId(request);
		boolean flag = bizInvoiceService.isModify(address.getId(), userId, address);
		if (flag) {
			return Constants.SUCCESS;
		}
		return Constants.FAILE;
	}
	
	/**
	 * 删除发票地址
	 * @param invoinceId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/del", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBase del(String invoiceId, HttpServletRequest request){
		String userId = getUserId(request);
		ResultBase resultBase = bizInvoiceService.beDeleteInvoiceAddress(userId, invoiceId);
		return resultBase;
	}

	/**
	 * 添加发票地址
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/ajax/add", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxAdd(Address address, HttpServletRequest request) {
		String userId = getUserId(request);
		String result = bizInvoiceService.fromAddInvoiceAddress(userId, address);
		return result;
	}

	/**
	 * ajax请求更新地址的信息
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/ajax/update", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Address ajaxUpdate(String addressId, HttpServletRequest request) {
		String userId = getUserId(request);
		Address address = bizInvoiceService.findAddressByListObj(userId, addressId);
		return address;
	}
	/**
	 * ajax请求更新地址的信息
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/ajax/edit", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxEdit(Address address, HttpServletRequest request) {
		String userId = getUserId(request);
		String result = bizInvoiceService.fromModifyInvoiceAddress(address.getId(), userId, address);
		return result;
	}
}
