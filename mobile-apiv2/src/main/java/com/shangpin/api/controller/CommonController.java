package com.shangpin.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.service.ASPBizCommonHanderService;
import com.shangpin.biz.utils.ClientUtil;


/**
 * 通用的规则处理，和业务逻辑完全没有关系
 * @author qinyingchun
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private ASPBizCommonHanderService bizCommonHanderService;
	
	@ResponseBody
	@RequestMapping(value = "/isUseCombinePay", method = {RequestMethod.GET, RequestMethod.POST})
	public String isUseCombinePay(HttpServletRequest request){
		String version = request.getHeader("ver");
		String useragent = request.getHeader("User-Agent");
    	String plateForm = ClientUtil.isIOS(useragent) ? "IOS" : "Android";
    	String member = plateForm + "|" + version;
    	boolean flag = bizCommonHanderService.IsUseCombinePay(member);
    	if(flag){
    		return "{\"code\":\"0\",\"msg\":\"" + version + "\",\"content\":{\"enable\":\"0\"}}";
    	}
		return "{\"code\":\"0\",\"msg\":\"" + version + "\",\"content\":{\"enable\":\"1\"}}";
	}

}
