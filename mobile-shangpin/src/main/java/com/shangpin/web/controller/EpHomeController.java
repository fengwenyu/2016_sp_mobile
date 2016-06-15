package com.shangpin.web.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.OverSeaCategory;
import com.shangpin.biz.bo.OverSeasGalleryList;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.SiteType;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.UserBuyInfo;
import com.shangpin.biz.service.SPBizOverSeasService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.product.model.custom.AdverList;
import com.shangpin.utils.StringUtil;
import com.shangpin.web.utils.Constants;

@Controller
@RequestMapping("/ephome")
public class EpHomeController extends BaseController{
	public static final Logger logger = LoggerFactory.getLogger(EpHomeController.class);
	private static final String INDEX = "epHome/index";
	private static final String FOCUS = "epHome/slider";
	private static final String ENTRANCE = "epHome/entrance";
	private static final String AD = "epHome/ad";//广告位
	private static final String MALL = "epHome/mall";
	private static final String PAVILION = "epHome/national_pavilion";
	private static final String INDEXMALL = "epHome/indexmall";
	private static final String COUNTRYPIC = "epHome/countrypic";
	@Autowired
	private SPBizOverSeasService spBizOverSeasService;
	@Autowired
	private SPBizUserService bizUserService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		
		return INDEX;
	}
	@RequestMapping(value = "/focus", method = RequestMethod.GET)
	public String focus(Model model,HttpServletRequest request) {
		OverSeasGalleryList gallerys=spBizOverSeasService.getOverSeasGalleryList(Constants.OVERSEAS_GALLEY_TYPE, "6");
		SiteType siteType =ClientUtil.getSiteType(request);
        model.addAttribute("isTag", "1");
        if(siteType.equals(SiteType.ANDRIOD_SHANGPIN)){
            String ver = request.getHeader("ver");
            logger.info(ver);
            if(StringUtil.compareVer(ver, "2.9.3")==-1){
                model.addAttribute("isTag", "0");
            }
        }
		model.addAttribute("gallerys", gallerys);
		return FOCUS;
	}
	@RequestMapping(value = "/entrance", method = RequestMethod.GET)
	public String pavilion(Model model,HttpServletRequest request) {

		return ENTRANCE;
	}
	
	@RequestMapping(value = "/ad", method = RequestMethod.GET)
	public String ad(HttpServletRequest request,Model model){
		List<AdverList> advers = spBizOverSeasService.adverLists();
		SiteType siteType =ClientUtil.getSiteType(request);
		model.addAttribute("isTag", "1");
		if(siteType.equals(SiteType.ANDRIOD_SHANGPIN)){
			String ver = request.getHeader("ver");
			logger.info(ver);
			if(StringUtil.compareVer(ver, "2.9.3")==-1){
				model.addAttribute("isTag", "0");
			}
		}
		model.addAttribute("advers", advers);
		return AD;
	}
	
	@RequestMapping(value = "/indexmall", method = RequestMethod.GET)
	public String indexmall(HttpServletRequest request,Model model) {
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		String ua = request.getHeader("User-Agent").toLowerCase();
		model.addAttribute("isNative", "0");
		logger.debug("ua={}",ua);
		if (ClientUtil.CheckApp(ua)){
			String appUserid = request.getHeader(Constants.APP_COOKIE_NAME_UID);
			if(!StringUtils.isEmpty(appUserid)&&!appUserid.equals("null")){
				   UserBuyInfo buyInfo = bizUserService.getUserBuyInfoObj(appUserid, Constants.SITE_NO_SP);
				   if(buyInfo!=null){
					   userLv=buyInfo.getCurlevel();
					   logger.debug("appuserLv={}",userLv);
				   }
				
			}
			logger.debug("appUserid={}",appUserid);
		}
		if (ClientUtil.isNotlessThanVer(ua.toLowerCase(),Constants.EP_APP_VER)) {
			model.addAttribute("isNative", "1");
			logger.debug("isNative");
		}
		List<Product> productList=new ArrayList<Product>();
		productList=spBizOverSeasService.productList("1", Constants.OVERS_PRODUCT_LIST_END, null, null, userLv);
		
		String hasMore="1";
		if(productList==null||productList.size()<Integer.valueOf(Constants.OVERS_PRODUCT_LIST_END)){
				hasMore="0";
		}
		
		model.addAttribute("productList", productList);
		
		model.addAttribute("userLv", userLv);
		model.addAttribute("hasMore", hasMore);
		return INDEXMALL;
		
	}
	@RequestMapping(value = "/mall", method = RequestMethod.GET)
	public String mall(HttpServletRequest request,Model model) {
		String postAreaNO = request.getParameter("postAreaNO");
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		String ua = request.getHeader("User-Agent").toLowerCase();
		model.addAttribute("isNative", "0");
		logger.debug("ua={}",ua);
		if (ClientUtil.CheckApp(ua)){
			String appUserid = request.getHeader(Constants.APP_COOKIE_NAME_UID);
			if(!StringUtils.isEmpty(appUserid)&&!appUserid.equals("null")){
				   UserBuyInfo buyInfo = bizUserService.getUserBuyInfoObj(appUserid, Constants.SITE_NO_SP);
				   userLv=buyInfo.getCurlevel();
				   logger.debug("appuserLv={}",userLv);
			}
			logger.debug("appUserid={}",appUserid);
		}
		
		List<OverSeaCategory> firstCategory=spBizOverSeasService.categories(null,postAreaNO);
		List<OverSeaCategory> secondCategory=new ArrayList<OverSeaCategory>();
		List<Product> productList=new ArrayList<Product>();
		if(firstCategory!=null&&firstCategory.size()>0)
		{
			secondCategory=spBizOverSeasService.categories(firstCategory.get(0).getShopCategoryNo(), postAreaNO);
			productList=spBizOverSeasService.productList("1", Constants.OVERS_PRODUCT_LIST_END, firstCategory.get(0).getShopCategoryNo(), postAreaNO, userLv);
		}
		
		String hasMore="1";
		if(productList==null||productList.size()<Integer.valueOf(Constants.OVERS_PRODUCT_LIST_END)){
				hasMore="0";
		}
		
		model.addAttribute("firstCategory", firstCategory);
		model.addAttribute("secondCategory", secondCategory);
		model.addAttribute("productList", productList);
		
		model.addAttribute("userLv", userLv);
		model.addAttribute("hasMore", hasMore);
		model.addAttribute("postAreaNO", postAreaNO);
		return MALL;
		
	}
	@RequestMapping(value = "/pavilion", method = RequestMethod.GET)
	public String pavilion(HttpServletRequest request,String postAreaNO,Model model) {
		String country=request.getParameter("country");
		String countryName=null;
		if(country.equals("America")){
			countryName="美国馆";
		}
		if(country.equals("Italy")){
			countryName="意大利馆";
		}
		if(country.equals("Hongkong")){
			countryName="香港馆";
		}
		if(country.equals("England")){
			countryName="英国馆";
			
		}
		model.addAttribute("country", country);
		model.addAttribute("countryname", countryName);
		model.addAttribute("postAreaNO", postAreaNO);
		return PAVILION;
	}
	@RequestMapping(value = "/countrypic", method = RequestMethod.GET)
	public String countrypic(Model model) {
		return COUNTRYPIC;
	}
	
	@ResponseBody
	@RequestMapping(value = "/get/mall", method = RequestMethod.POST)
	public Map<String, Object> getMall(HttpServletRequest request,String firstCategoryNO,@RequestParam("postAreaNO")String postAreaNO,Model model){
		Map<String, Object> map=new HashMap<String,Object>();
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		List<OverSeaCategory> secondCategory=spBizOverSeasService.categories(firstCategoryNO, null);
		List<Product> productList=spBizOverSeasService.productList("1", Constants.OVERS_PRODUCT_LIST_END, firstCategoryNO, postAreaNO, userLv);
		
		String hasMore="1";
		if(productList==null||productList.size()<Integer.valueOf(Constants.OVERS_PRODUCT_LIST_END)){
			hasMore="0";
		}
		
		map.put("secondCategory", secondCategory);
		map.put("productList", productList);
		map.put("hasMore", hasMore);
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "/get/product", method = RequestMethod.POST)
	public Map<String, Object> getMallProduct(HttpServletRequest request,String firstCategoryNO,String start,String postAreaNO,Model model){
		Map<String, Object> map=new HashMap<String,Object>();
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		start = (StringUtils.isEmpty(start)) ? "1" : start;
		start=String.valueOf(Integer.valueOf(start)+1);
		List<Product> productList=spBizOverSeasService.productList(start, Constants.OVERS_PRODUCT_LIST_END, firstCategoryNO, postAreaNO, userLv);
		
		String hasMore="1";
		if(productList==null||productList.size()<Integer.valueOf(Constants.OVERS_PRODUCT_LIST_END)){
			hasMore="0";
		}
		
		map.put("productList", productList);
		map.put("start", start);
		map.put("hasMore", hasMore);
		return map;
	}
}
