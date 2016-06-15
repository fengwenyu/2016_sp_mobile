package com.shangpin.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.Collect;
import com.shangpin.biz.bo.CollectProduct;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizCollectService;
import com.shangpin.web.utils.Constants;

/**
 * @ClassName: CollectController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author qinyingchun
 * @date 2014年11月22日
 * @version 1.0
 */
@Controller
@RequestMapping("/collect")
public class CollectController extends BaseController {
	
	@Autowired
	private SPBizCollectService bizCollectService;
	
	private static final String COLLECT_LIST = "collect/list";
	
	private static final String COLLECT_PRODUCT_LIST = "collect/product_list";
	
	private static final String COLLECT_BRANDS_LIST = "collect/brands_list";
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request){
		String userId = getUserId(request);
		List<Collect> collects = bizCollectService.collectList(userId, Constants.COLLECT_PAGE_INDEX, Constants.COLLECT_PAGE_SIZE, Constants.SITE_NO_SP);
		model.addAttribute("collects", collects);
		return COLLECT_LIST;
	}
	
	/**
	 * 
	 * @Title: collectBrand
	 * @Description: 收藏品牌
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月9日
	 */
	@RequestMapping(value = "/brand", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> collectBrand(HttpServletRequest request, @RequestParam("brandId") String brandId) {
		Map<String, Object> map = new HashMap<String, Object>();
		 String code = "";
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			code = Constants.UNLOGIN;
			map.put("code", code);
			return map;
		}
		map = bizCollectService.collectBrand(user.getUserid(), brandId);
		return map;
	}
	/**
	 * 
	 * @Title: appCollectBrand
	 * @Description: 在app中收藏品牌
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月9日
	 */
	@RequestMapping(value = "app/brand", method = { RequestMethod.GET, RequestMethod.POST })
	public String appCollectBrand(HttpServletRequest request, @RequestParam("brandId") String brandId, String back) {
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			  return "redirect:" + back;
		}
		bizCollectService.collectBrand(user.getUserid(), brandId);
		return "redirect:" + back;
	}
	/**
	 * 
	 * @Title: getCoupon
	 * @Description: 领取优惠券
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月9日
	 */
	@RequestMapping(value = "/cancle/brand", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> cancleCollectBrand(HttpServletRequest request, @RequestParam("brandId") String brandId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "";
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			code = Constants.UNLOGIN;
			map.put("code", code);
			return map;
		}
		map = bizCollectService.cancleCollectBrand(user.getUserid(), brandId);
		return map;
	}
	
	/**
	 * 
	 * @Title: collectProduct
	 * @Description: 收藏商品
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月18日
	 */
	@RequestMapping(value = "/product", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> collectProduct(HttpServletRequest request, @RequestParam("skuId") String skuId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "";
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			code = Constants.UNLOGIN;
			map.put("code", code);
			return map;
		}
		map = bizCollectService.collectProduct(Constants.SHANGPIN_SHOPTYPE, skuId, user.getUserid(), null, null, null, null);
		return map;
	}
	
	/**
	 * 
	 * @Title: cancleCollectProduct
	 * @Description: 取消收藏商品
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月18日
	 */
	@RequestMapping(value = "/cancle/product", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> cancleCollectProduct(HttpServletRequest request, @RequestParam("collectId") String collectId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "";
		User user = getSessionUser(request);
		if (user == null || StringUtils.isEmpty(user.getUserid())) {
			code = Constants.UNLOGIN;
			map.put("code", code);
			return map;
		}
		map = bizCollectService.cancleCollectProduct(Constants.SHANGPIN_SHOPTYPE, collectId, user.getUserid());
		return map;
	}
	
	/**
	 * 
	 * @Title:collectProducts
	 * @Description:单品收藏列表
	 * @param pageIndex
	 * @param pageSize
	 * @param shopType
	 * @param model
	 * @param request
	 * @return
	 * @author qinyingchun
	 * @date 2015年3月26日
	 */
	@RequestMapping(value = "/product/list", method = RequestMethod.GET)
	public String collectProducts(String pageIndex, String pageSize, String shopType, String postArea, Model model, HttpServletRequest request){
		String userId = getUserId(request);
		List<CollectProduct> products = bizCollectService.collectProductList(userId, pageIndex, pageSize, shopType, "0");
		if(null == products){
			return COLLECT_PRODUCT_LIST;
		}
		String userLv = getSessionUser(request).getLv();
		for(CollectProduct product : products){
			if("0002".equals(userLv) && "1".equals(product.getIsSupportDiscount())){
				product.setStrongPrice(Integer.parseInt(product.getGoldPrice()));
			}else if("0003".equals(userLv) && "1".equals(product.getIsSupportDiscount())){
				product.setStrongPrice(Integer.parseInt(product.getPlatinumPrice()));
			}else if("0004".equals(userLv) && "1".equals(product.getIsSupportDiscount())){
				product.setStrongPrice(Integer.parseInt(product.getDiamondPrice()));
			}else {
				product.setStrongPrice(Integer.parseInt(product.getLimitedPrice()));
			}
			//促销商品
			if("000100".equals(product.getStatus())){
				product.setStrongPrice(Integer.parseInt(product.getPromotionPrice()));
			}
			product.setDelPrice(Integer.parseInt(product.getMarketPrice()));
		}
		List<CollectProduct> nextCollectProducts = bizCollectService.collectProductList(userId, String.valueOf(Integer.parseInt(pageIndex) + 1), pageSize, shopType, "0");
		String hasMore = "0";
		if(null != nextCollectProducts && nextCollectProducts.size() > 0){
			hasMore = "1";
		}
		model.addAttribute("hasMore", hasMore);
		model.addAttribute("products", products);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", pageSize);
		return COLLECT_PRODUCT_LIST;
	}
	
	@RequestMapping(value = "/product/more", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> collectProductList(String pageIndex, String pageSize, String shopType, Model model, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = getUserId(request);
		String userLv = getSessionUser(request).getLv();
		List<CollectProduct> products = bizCollectService.collectProductList(userId, pageIndex, pageSize, shopType, "0");
		List<CollectProduct> nextCollectProducts = bizCollectService.collectProductList(userId, String.valueOf(Integer.parseInt(pageIndex) + 1), pageSize, shopType, "0");
		String hasMore = "0";
		if(null != nextCollectProducts && nextCollectProducts.size() > 0){
			hasMore = "1";
		}
		map.put("products", products);
		map.put("hasMore", hasMore);
		map.put("userLv", userLv);
		return map;
	}
	
	@RequestMapping(value = "/brand/list", method = RequestMethod.GET)
	public String collectBrands(String pageIndex, String pageSize, Model model, HttpServletRequest request){
		String userId = getUserId(request);
		List<Collect> brands = bizCollectService.collectBrands(userId, pageIndex, pageSize);
		if(null == brands){
			return COLLECT_BRANDS_LIST;
		}
		List<Collect> nextCollectBrands = bizCollectService.collectBrands(userId, String.valueOf(Integer.parseInt(pageIndex) + 1), pageSize);
		String hasMore = "0";
		if(null != nextCollectBrands && nextCollectBrands.size() > 0){
			hasMore = "1";
		}
		model.addAttribute("hasMore", hasMore);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("brands", brands);
		return COLLECT_BRANDS_LIST;
	}
	
	@RequestMapping(value = "/brand/more", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> collectBrandsList(String pageIndex, String pageSize, Model model, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = getUserId(request);
		List<Collect> brands = bizCollectService.collectBrands(userId, pageIndex, pageSize);
		List<Collect> nextCollectBrands = bizCollectService.collectBrands(userId, String.valueOf(Integer.parseInt(pageIndex) + 1), pageSize);
		String hasMore = "0";
		if(null != nextCollectBrands && nextCollectBrands.size() > 0){
			hasMore = "1";
		}
		map.put("brands", brands);
		map.put("hasMore", hasMore);
		return map;
	}
}
