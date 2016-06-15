package com.aolai.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shangpin.biz.bo.Meet;
import com.shangpin.biz.bo.SiteType;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizMeetPlaceService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.utils.ClientUtil;

/**
 * 会场开发
 * 
 * @author zghw
 *
 */
@Controller
public class MeetController extends BaseController {

	public static final Logger logger = LoggerFactory.getLogger(MeetController.class);
	private static final String INDEX = "meet/common/index";
	/** 单页面前缀 */
	// private static final String SINGLE_PREFIX = "meet/single/";
	@Autowired
	SPBizMeetPlaceService meetPlaceService;
	@Autowired
	private SPBizCouponService bizCouponService;
	@Autowired
	private SPBizUserService userService;
	
	/**
	 * 兼容旧URL会场
	 * @author zghw
	 */
	@RequestMapping(value="/meet!index",method=RequestMethod.GET)
	public String oldIndex(HttpServletRequest request, HttpServletResponse response, Model model, String id, String type, String isChange){
		return  this.index(request, response, model, id, type, isChange);
	}

	/**
	 * 会场主通用方法
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @param type
	 * @param isChange
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/meet/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Model model, String id, String type, String isChange) {

		try {
			if (StringUtils.isNotEmpty(id)) {
				SiteType siteType =ClientUtil.getSiteType(request);
				Meet meet = meetPlaceService.getMeetPlaceList(id, siteType, isChange);
				model.addAttribute("meet", meet);
				/***
				 * 如果数据出错或者为空则不缓存
				 */
				if (meet == null || (meet != null && StringUtils.isEmpty(meet.getHtml()))) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MeetController index e={}", e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return INDEX;

	}

}
