package com.shangpin.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.BrandShop;
import com.shangpin.biz.bo.Brands;
import com.shangpin.biz.bo.SearchConditions;
import com.shangpin.biz.bo.SearchProductResult;
import com.shangpin.biz.bo.SearchResult;
import com.shangpin.biz.bo.SearchType;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.ASPBizBrandShopService;
import com.shangpin.biz.service.SPBizBrandService;
import com.shangpin.core.entity.HotBrands;
import com.shangpin.utils.JedisUtilFactory;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.MD5Util;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.SearchCache;
import com.shangpin.web.utils.SearchUtil;

/** 
* @ClassName: BrandController 
* @Description:品牌相关方法控制层
* @author qinyingchun
* @date 2014年10月24日
* @version 1.0 
*/
@Controller
@RequestMapping("/brand")
public class BrandController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
	
	private static final String LIST = "brand/list";
//	private static final String BRAND_PRODUCT = "brand/brand_product";

	private static final String FLAGSHOP_BRAND_PRODUCT = "flagshop/brandProductList";

	private static final String BRAND_PRODUCT_NEW = "brand/brand_product";
	
	@Autowired
	private SPBizBrandService brandService;
	@Autowired
	private ASPBizBrandShopService aspBizBrandShopService;
	/**
	 * 
	* @Title: list 
	* @Description: 品牌列表
	* @param @param gender
	* @param @param model
	* @param @return
	* @return String
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年10月25日
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model){
		logger.debug("BrandController list start!");
		Brands brands = brandService.brandList();
		List<HotBrands> hotBrands = brandService.hotBrands();
		model.addAttribute("brands", brands);
		model.addAttribute("hotBrands", hotBrands);
		logger.debug("BrandController list end!");
		return LIST;
	}
	

	
	@RequestMapping(value = "/product/list/more", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMore(SearchConditions searchConditions, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		String start=searchConditions.getStart();
		SearchProductResult searchResult = brandService.brandProductList(null, start, Constants.PRODUCT_LIST_END, searchConditions.getBrandNo(), searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(), searchConditions.getOrder(), userLv,SearchType.PRODUCT, searchConditions.getPostArea());
		
		int hasMore = SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()),Integer.parseInt(start), Integer.parseInt(Constants.PRODUCT_LIST_END));
		map.put("searchResult", searchResult);
		map.put("hasMore", hasMore);
		map.put("userLv", userLv);
		return map;
	}
	
	
	@RequestMapping(value = "/flagshopProduct/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String flagShopBrandProduct(SearchConditions searchConditions, Model model, HttpServletRequest request,String title,String imgUrl){
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		String start = ( StringUtils.isEmpty(searchConditions.getStart() )) ? "1" : searchConditions.getStart();
		SearchProductResult searchResult = brandService.brandProductList(null, start, Constants.PRODUCT_LIST_END, searchConditions.getBrandNo(), searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(), searchConditions.getOrder(), userLv,SearchType.PRODUCT, searchConditions.getPostArea());
		SearchProductResult filterCondition = brandService.brandProductList(null, null,null, searchConditions.getBrandNo(), null, null, null, null,null, userLv,SearchType.ALL_FILTER, searchConditions.getPostArea());
		String queryCondition = SearchUtil.initQueryConditions(searchConditions);
		int hasMore = SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()), Integer.parseInt(start),Integer.parseInt(Constants.PRODUCT_LIST_END));

		model.addAttribute("searchResult", searchResult);
		model.addAttribute("brandNo", searchConditions.getBrandNo());
		model.addAttribute("start", searchConditions.getStart());
		model.addAttribute("queryCondition", queryCondition);
		model.addAttribute("searchConditions", searchConditions);
		model.addAttribute("filterCondition", filterCondition);
		
		model.addAttribute("hasMore", hasMore);
		model.addAttribute("userLv", userLv);
		model.addAttribute("title",title);
		model.addAttribute("imgUrl", imgUrl);
		return FLAGSHOP_BRAND_PRODUCT;
	}
	@RequestMapping(value = "/flagshopProduct/list/more", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getflagShopProductMore(SearchConditions searchConditions, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		String start=searchConditions.getStart();
		SearchProductResult searchResult = brandService.brandProductList(null, start, Constants.PRODUCT_LIST_END, searchConditions.getBrandNo(), searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(), searchConditions.getOrder(), userLv,SearchType.PRODUCT, searchConditions.getPostArea());
		int hasMore = SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()),Integer.parseInt(start), Integer.parseInt(Constants.PRODUCT_LIST_END));
		map.put("hasMore", hasMore);
		map.put("searchResult", searchResult);
		map.put("userLv", userLv);
		return map;
	}
	/**
	 * 
	 * @param searchConditions
	 * @param fromFirst 这个参数代表了是否是第一次，不要从url中传值
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/list",  method = { RequestMethod.GET, RequestMethod.POST })
	public String brandProduct(SearchConditions searchConditions,Model model, HttpServletRequest request){
		BrandShop brandShop=null;
		SearchResult searchResult =null;
		try {
			//SearchUtil.SystemConditions(searchConditions);
			User user = getSessionUser(request);
			String userId = getUserId(request);
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			String start = (StringUtils.isEmpty(searchConditions.getStart())) ? "1" : searchConditions.getStart();
			//筛选项
			//redis   之前   520微信分享广告推广 
			String conditions=JsonUtil.toJson(searchConditions);
			String brandNo=searchConditions.getBrandNo();
			String key="searchResult_key"+brandNo+"brandShop";
			String keyresult="searchResult_key"+brandNo+MD5Util.MD5(conditions);
			String jedisVal = JedisUtilFactory.getDefaultJedisUtil().get(keyresult);
			if(jedisVal == null){
				brandShop = aspBizBrandShopService.queryBrandShop(userId, searchConditions.getBrandNo(), null, null, null, null, null, null, null,null, null, searchConditions.getPostArea(),"");
				//String userId, String brandId, String pageIndex, String pageSize, String userLv,String price,String size,String colorId,String tagId,String categoryId,String order
				searchResult = aspBizBrandShopService.queryBrandShopProduct(searchConditions.getBrandNo(), start, Constants.PRODUCT_LIST_END, userLv,searchConditions.getPrice(), searchConditions.getSize(), searchConditions.getColor(), searchConditions.getTagId(),  searchConditions.getCategoryNo(), searchConditions.getOrder(), searchConditions.getPostArea(),"");
				if("B1885".equals(brandNo)||"B02850".equals(brandNo)){
					SearchCache search=new SearchCache();
					search.cache(searchResult, keyresult);
					search.cache(brandShop, key);
				}
			}else{
				logger.info("--------get brand from 缓存"+key);
				String json=JedisUtilFactory.getDefaultJedisUtil().get(key);
				if(json==null){
					brandShop = aspBizBrandShopService.queryBrandShop(userId, searchConditions.getBrandNo(), null, null, null, null, null, null, null,null, null, searchConditions.getPostArea(),"");
					SearchCache search=new SearchCache();
					search.cache(brandShop, key);
				}else{
					brandShop=JsonUtil.fromJson(json, BrandShop.class);
				}
				//
//				String resultjson=JedisUtilFactory.getDefaultJedisUtil().get(keyresult);
				searchResult=JsonUtil.fromJson(jedisVal, SearchResult.class);
			}
			
			
			int hasPageNum = SearchUtil.hasPageNum(Integer.parseInt(searchResult.getCount()),Integer.parseInt(Constants.PRODUCT_LIST_END));            
			model.addAttribute("brandShop", brandShop);
			model.addAttribute("searchConditions", searchConditions);
			model.addAttribute("searchResult", searchResult);
			model.addAttribute("searchResult", searchResult);
			model.addAttribute("userLv", userLv);
			model.addAttribute("hasPageNum", hasPageNum);
			if(StringUtils.isEmpty(searchConditions.getOrder())){
				model.addAttribute("isHot", "1");
			}else{
				if(searchConditions.getOrder().equals("5")){
					model.addAttribute("isHot", "0");
				}else{
					model.addAttribute("isHot", "1");
				}
			}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return BRAND_PRODUCT_NEW;
	}
	

	/**
	 * 
	* @Title: getNewMore 
	* @Description:品牌商品列表加载更多
	* @param @return
	* @return String
	* @throws 
	* @Create By liling
	* @Create Date 2015年3月6日
	 */
	@ResponseBody
	@RequestMapping(value = "/new/more",  method = {RequestMethod.GET,RequestMethod.POST})
	public  Map<String, Object> getNewMore(SearchConditions searchConditions, HttpServletRequest request, String gender, Model model) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			
			User user = getSessionUser(request);
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			String start=String.valueOf(Integer.valueOf(searchConditions.getStart())+1);
			SearchResult searchResult = aspBizBrandShopService.queryBrandShopProduct(searchConditions.getBrandNo(), start, Constants.PRODUCT_LIST_END, userLv,searchConditions.getPrice(), searchConditions.getSize(), searchConditions.getColor(), searchConditions.getTagId(),  searchConditions.getCategoryNo(), searchConditions.getOrder(), searchConditions.getPostArea(),"");
						map.put("searchResult", searchResult);
			map.put("start", start);
			map.put("userLv", userLv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 
	* @Title: getSearchConditions 
	* @Description:品牌商品列表筛选条件
	* @param @return
	* @return String
	* @throws 
	* @Create By liling
	* @Create Date 2015年3月6日
	 */
	@ResponseBody
	@RequestMapping(value = "/search/conditions",  method = {RequestMethod.GET,RequestMethod.POST})
	public  Map<String, Object> getSearchConditions(SearchConditions searchConditions, HttpServletRequest request, String gender, Model model) {
		//SearchUtil.SystemConditions(searchConditions);
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			
			SearchResult searchResult = aspBizBrandShopService.queryBrandShopProduct(searchConditions.getBrandNo(), null, null, null,searchConditions.getPrice(), searchConditions.getSize(), searchConditions.getColor(), searchConditions.getTagId(),  searchConditions.getCategoryNo(), null, searchConditions.getPostArea(),"");
			map.put("searchResult", searchResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
