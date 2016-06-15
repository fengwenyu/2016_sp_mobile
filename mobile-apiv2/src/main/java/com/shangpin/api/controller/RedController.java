package com.shangpin.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.Red;
import com.shangpin.biz.bo.RedResult;
import com.shangpin.biz.bo.base.ContentBuilder;
import com.shangpin.biz.service.ASPBizReturnGoodsService;
import com.shangpin.biz.service.RedBizService;
import com.shangpin.utils.JsonUtil;
/**
 *  红包口令业务
 * @author 李永桥
 *
 */
@Controller
public class RedController {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RedController.class);
	@Autowired
	private ASPBizReturnGoodsService returnGoodsService;
	@Autowired
	private RedBizService redBizService;
	/**
	 * 红包口令 主站红包是否存在 存在则返回搜索页  else 响应app
	 * @param request
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/returnRedList", method = { RequestMethod.POST, RequestMethod.GET })
	public String returnGoodList(HttpServletRequest request,String keywords){
    	Boolean b=redBizService.checkisred(keywords);
    	Red red=new Red();
    	if(b){
	    	red=redBizService.findRedList(keywords);
	    	ContentBuilder builder = new ContentBuilder();
	    	return JsonUtil.toJson(builder.buildDefSuccess("成功", red));
    	}else{
	    	ContentBuilder builder = new ContentBuilder();
	    	RedResult result =new RedResult();
	    	result.setType("0");
	    	result.setCommenBean(new Red());
	    	builder.setMsg("成功");
	    	return JsonUtil.toJson(builder.buildDefSuccess("成功",result));
    	}
	}
}
