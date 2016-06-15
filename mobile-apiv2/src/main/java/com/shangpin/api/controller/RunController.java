package com.shangpin.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.RunVo;
import com.shangpin.biz.bo.base.ContentBuilder;
import com.shangpin.biz.service.ASPBizRunningService;

@Controller
@RequestMapping("/")
public class RunController extends BaseController{
	
	@Autowired
	private ASPBizRunningService bizRunningService;
	
	@ResponseBody
	@RequestMapping(value = "/channelCategoryProductList", method = { RequestMethod.GET, RequestMethod.POST})
	public ContentBuilder<RunVo> categoryProductList(String pageIndex, String pageSize, String userLv, String price, String size, String color, String channelCategoryNo, String categoryId, String brandId, String order, String postArea, HttpServletRequest request){
		ContentBuilder<RunVo> builder = new ContentBuilder<RunVo>();
		RunVo runVo = bizRunningService.categoryProductListVo(pageIndex, pageSize, userLv, price, size, color, channelCategoryNo, categoryId, brandId, order, postArea);
		return null == runVo ? builder.buildDefError() : builder.buildDefSuccess(runVo);
	}

}
