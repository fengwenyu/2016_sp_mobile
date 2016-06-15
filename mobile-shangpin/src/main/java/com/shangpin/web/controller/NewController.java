package com.shangpin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shangpin.biz.bo.NewGoods;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizBrandService;
import com.shangpin.biz.service.SPBizIndexInfoService;

@Controller
public class NewController extends BaseController {
	private static final String SUBJECT_LIST = "new/suject_list";
	private static final String NEW_GOODS = "new/new_goods";;
	@Autowired
	SPBizIndexInfoService spBizIndexInfoService;
	@Autowired
	SPBizBrandService spBizBrandService;
	/**
	 * 
	 * @Title: list
	 * @Description:最新专题
	 * @Create By liling
	 * @Create Date 2015年9月16日
	 */
	@RequestMapping(value = "/new/subject", method = RequestMethod.GET)
	public String subject(){
		//spBizIndexInfoService.
		return SUBJECT_LIST;
	}
	/**
	 * 
	 * @Title: newGoods
	 * @Description:新品到货
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年10月13日
	 */
	@RequestMapping(value = "/new/goods", method = RequestMethod.GET)
	public String newGoods(HttpServletRequest request, Model model){
		User user = getSessionUser(request);
		String userId = StringUtils.isEmpty(user) ? null : user.getUserid();
		try {
			 List<NewGoods> newGoods=spBizBrandService.getNewGoods(userId);
			model.addAttribute("newGoodsList", newGoods);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NEW_GOODS;
	}
}
