package com.shangpin.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.service.SPBizCommonHanderService;


/**
 * 通用的规则处理，和业务逻辑完全没有关系
 * @author qinyingchun
 *
 */
@Controller
@RequestMapping("/common")
public class CommonHanderController {
	
	@Autowired
	private SPBizCommonHanderService bizCommonHanderService;
	
	@RequestMapping(value = "/combinePayList", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> useCombinePay(){
		return bizCommonHanderService.useCombinePay();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(String version, String plateForm){
		String member = plateForm + "|" + version;
		bizCommonHanderService.add(member);
		return "redirect:/common/combinePayList";
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String remove(String version, String plateForm){
		String member = plateForm + "|" + version;
		bizCommonHanderService.remove(member);
		return "redirect:/common/combinePayList";
	}

}
