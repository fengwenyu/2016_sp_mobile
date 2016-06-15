package com.shangpin.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.RecProductFor;
import com.shangpin.biz.service.ASPBizCMSService;
import com.shangpin.biz.service.ASPBizSerchService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.utils.StringUtil;

@Controller
@RequestMapping("/")
public class SearchController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ASPBizSerchService aspBizSerchService;
	
	@Autowired
	private ASPBizCMSService aspBizCMSService;

	/**
	 * 首页标签列表
	 * 
	 * @param labelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/searchLabel", method = { RequestMethod.POST, RequestMethod.GET })
	public String searchLabel(@RequestParam String pageIndex, @RequestParam String pageSize, @RequestParam String userLv, @RequestParam String tagId, @RequestParam String order,
			@RequestParam(required = false) String filters, @RequestParam(required = false) String originalFilters, @RequestParam(required = false) String dynamicFilters, @RequestParam String type) {
		final String imei = request.getHeader("imei");
		final String ver = request.getHeader("ver");
		if (!StringUtil.isNotEmpty(imei, pageIndex, pageSize, tagId, type, ver)) {
			return returnParamError();
		}

		try {
			return aspBizSerchService.queryLabels(pageIndex, pageSize, userLv, tagId, order, filters, originalFilters, dynamicFilters, type, imei, ver);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}
	
	@ResponseBody
	@RequestMapping(value = "/searchKeyword", method = { RequestMethod.POST, RequestMethod.GET })
	public Object searchKeyword() {
		final String imei = request.getHeader("imei");
		if (!StringUtil.isNotEmpty(imei)) {
			return returnParamError();
		}

		try {
//			return aspBizCMSService.querySearchKeyword();
			return aspBizCMSService.getSearchKeyword();
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}
	@ResponseBody
	@RequestMapping(value = "/searchProductNo", method = { RequestMethod.POST, RequestMethod.GET })
	public Object searchProductNo( @RequestParam String includeProductNo) {
		String end = "1";
		if(includeProductNo!=null&&includeProductNo.indexOf(",")>-1){
			String str [] = includeProductNo.split(",");
			end = str.length+"";
		} 
		try {
			RecProductFor recProductFor = aspBizSerchService.searchProductNos(null, end, "0", "20000", null, null, null, null, null, includeProductNo, null,null);
			if (recProductFor != null) {
				return ApiBizData.spliceData(recProductFor, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
			}
			return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}

	}
}
