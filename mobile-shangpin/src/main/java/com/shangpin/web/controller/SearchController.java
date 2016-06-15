package com.shangpin.web.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.SearchConditions;
import com.shangpin.biz.bo.SearchProductResult;
import com.shangpin.biz.bo.SearchType;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizSearchService;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.SearchUtil;

@Controller
@RequestMapping("/search")
public class SearchController extends BaseController{
	private static final String FLAGSHOP_SEARCH = "flagshop/search";
	@Autowired
	SPBizSearchService searchService;
	@RequestMapping(value = "/flagshopProduct/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String flagshopBrandProduct(SearchConditions searchConditions, Model model, HttpServletRequest request){
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		String start = ( StringUtils.isEmpty(searchConditions.getStart() )) ? "1" : searchConditions.getStart();
		SearchProductResult searchResult = searchService.searchProductList(searchConditions.getKeyword(),null, start, Constants.PRODUCT_LIST_END, searchConditions.getTagId(),searchConditions.getBrandNo(), searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(), searchConditions.getOrder(), userLv,SearchType.PRODUCT, searchConditions.getPostArea());
		int hasMore = SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()),Integer.parseInt(start), Integer.parseInt(Constants.PRODUCT_LIST_END));
		model.addAttribute("keyword", searchConditions.getKeyword());
		model.addAttribute("searchResult", searchResult);
		model.addAttribute("start", start);
		
		model.addAttribute("hasMore", hasMore);
		model.addAttribute("userLv", userLv);
		return FLAGSHOP_SEARCH;
	}
	@RequestMapping(value = "/flagshopProduct/list/more", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getflagshopProductMore(SearchConditions searchConditions, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		String start = searchConditions.getStart() ;
		SearchProductResult searchResult = searchService.searchProductList(searchConditions.getKeyword(),null, start, Constants.PRODUCT_LIST_END,searchConditions.getTagId(), searchConditions.getBrandNo(), searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(), searchConditions.getOrder(), userLv,SearchType.PRODUCT, searchConditions.getPostArea());
		int hasMore = SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()),Integer.parseInt(start), Integer.parseInt(Constants.PRODUCT_LIST_END));
		
		map.put("hasMore", hasMore);
		map.put("searchResult", searchResult);
		map.put("userLv", userLv);
		return map;
	}
}
