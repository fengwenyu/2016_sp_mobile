package com.shangpin.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.service.ASPBizSettlementService;
/**
 * 
 * @author lyq
 *
 */
@Controller
@RequestMapping("/")
public class AddressController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    @Autowired
    private ASPBizSettlementService spBizSettlementService;
	
    
    public String str(){
    	return "";
    }
    
    
	/**
	 * 
	 * list:<br/>
	 * 地址查询列表
	 * @param request
	 * @param userId
	 * @param buyId
	 * @param receiveId
	 * @return
	 */
    @ResponseBody
	@RequestMapping(value="/beDeliverAddress",method={RequestMethod.POST, RequestMethod.GET })
	public String list(HttpServletRequest request,HttpServletResponse response,@RequestHeader("userid") String userId,
			@RequestParam("buyId") String buyId, 
			@RequestParam("receiveId") String receiveId,@RequestParam("ordersourceId") String ordersourceId,
			@RequestParam("buyType") String buyType){

		String json=spBizSettlementService.addresslist(userId,buyId,receiveId,ordersourceId,buyType);
		return json;
	}
}

