package com.shangpin.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.utils.StringUtil;
/**
 *  推荐商品业务处理
 * @author 李永桥
 *
 */
@Controller
public class RecommendController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);
	@Autowired
	private ShangPinService shangPinService;
	
	/**
	 * 区分用户使用新老推荐接口，然后调用不同的接口返回响应推荐商品数据
	 * @param request
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/productList", method = { RequestMethod.POST, RequestMethod.GET })
	public String recommendproductList(HttpServletRequest request,String keywords){
		final String imei = request.getHeader("imei");
		final String userId = request.getHeader("userid");
		if (!StringUtil.isNotEmpty(imei, userId)) {
			return returnParamError();
		}
		//根据串码和userid调用推荐系统接口，获取productNo
		
		
		
		
		
		
		//TODO 推荐系统接口返回productNo 目前写死
	    String json = shangPinService.findProductDetailNew("", "30003644", "", "");
		return keywords;
    	
    	}
	}

