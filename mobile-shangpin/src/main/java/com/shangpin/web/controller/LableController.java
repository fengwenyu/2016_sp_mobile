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

import com.shangpin.biz.bo.LableSearchConditions;
import com.shangpin.biz.bo.SearchResult;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizSearchService;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.SearchUtil;


@Controller
@RequestMapping("/lable")
public class LableController extends BaseController{
	private static final String LIST = "lable/product_list";
	@Autowired
	SPBizSearchService spBizSearchService;
	/**
	 * 
	 * @Title: searchProductList
	 * @Description:标签商品列表
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年10月30日
	 */
	@RequestMapping(value = "/product/list",  method = { RequestMethod.GET, RequestMethod.POST })
	public String searchProduct(LableSearchConditions searchConditions,Model model, HttpServletRequest request){
		try {
			User user = getSessionUser(request);
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			String start = (StringUtils.isEmpty(searchConditions.getStart())) ? "1" : searchConditions.getStart();
			//筛选项
			SearchResult searchResultFliter =spBizSearchService.queryLabels("1", "1", userLv, searchConditions.getTagId(), null,null, StringUtils.isEmpty(searchConditions.getType())?"1":searchConditions.getType());
			SearchResult searchResult =spBizSearchService.queryLabels(start, Constants.PRODUCT_LIST_END, userLv, searchConditions.getTagId(), searchConditions.getOrder(),searchConditions.getFilters(), StringUtils.isEmpty(searchConditions.getType())?"1":searchConditions.getType());
			int hasPageNum = SearchUtil.hasPageNum(Integer.parseInt(searchResult.getCount()),Integer.parseInt(Constants.PRODUCT_LIST_END));
			model.addAttribute("searchResult", searchResult );
			model.addAttribute("searchResultFliter", searchResultFliter );
			model.addAttribute("userLv", userLv);
			model.addAttribute("searchConditions", searchConditions);
			model.addAttribute("hasPageNum", hasPageNum);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return LIST;
	}
	
	/**
	 * 
	 * @Title: searchProductListMore
	 * @Description:商品列表加载更多
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年10月30日
	 */
	@ResponseBody
	@RequestMapping(value = "/product/list/more", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> searchProductListMore(LableSearchConditions searchConditions, HttpServletRequest request, String gender, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			User user = getSessionUser(request);
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			String start = String.valueOf(Integer.valueOf(searchConditions.getStart()) + 1);
			// 筛选项
			SearchResult searchResult = spBizSearchService.queryLabels(start, Constants.PRODUCT_LIST_END, userLv, searchConditions.getTagId(), searchConditions.getOrder(),searchConditions.getFilters(), StringUtils.isEmpty(searchConditions.getType())?"1":searchConditions.getType());
			map.put("searchResult",searchResult );
			map.put("userLv", userLv);
			map.put("searchConditions", searchConditions);
			map.put("hasPageNum", "1");
			map.put("start", start);
				
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 
	 * @Title: getSearchConditions
	 * @Description:活动商品列表筛选条件
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月6日
	 */

	@ResponseBody
	@RequestMapping(value = "/search/conditions", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getSearchConditions(LableSearchConditions searchConditions, HttpServletRequest request, String gender, Model model) {
		//SearchUtil.SystemConditions(searchConditions);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SearchResult searchResult = spBizSearchService.queryLabels("1", "1", null, searchConditions.getTagId(), null,searchConditions.getFilters(), StringUtils.isEmpty(searchConditions.getType())?"1":searchConditions.getType());
			map.put("searchResult",searchResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	
	
}
