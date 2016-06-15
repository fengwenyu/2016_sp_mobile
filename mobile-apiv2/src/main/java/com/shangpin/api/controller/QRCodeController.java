package com.shangpin.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/QRcode")
public class QRCodeController extends BaseController{

	@RequestMapping(value = "/activity", method = { RequestMethod.POST, RequestMethod.GET })
	public String activity(HttpServletRequest request,String activityId,String postArea) {
		//活动列表
		return "redirect:http://m.shangpin.com/subject/product/list?topicId="+activityId+"&postArea="+postArea;
	}
	@RequestMapping(value = "/category", method = { RequestMethod.POST, RequestMethod.GET })
	public String category(HttpServletRequest request,String categoryId,String postArea) {
		//品类列表
		return "redirect:http://m.shangpin.com/category/product/list?categoryNo="+categoryId+"&postArea="+postArea;
	}
	@RequestMapping(value = "/brand", method = { RequestMethod.POST, RequestMethod.GET })
	public String brand(HttpServletRequest request,String brandId,String postArea) {
		//品牌列表
		return "redirect:http://m.shangpin.com/brand/product/list?brandNo="+brandId+"&postArea="+postArea;
	}
	@RequestMapping(value = "/detail", method = { RequestMethod.POST, RequestMethod.GET })
	public String detail(HttpServletRequest request,String productId,String activityId) {
		//商品详情
		return "redirect:http://m.shangpin.com/product/detail?productNo="+productId+"&topicId="+activityId;
	}
}
