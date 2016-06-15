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
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.Category;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.RunBrandsHotList;
import com.shangpin.biz.bo.RunGalleryList;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.UserBuyInfo;
import com.shangpin.biz.service.SPBizRunningService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.web.utils.Constants;


@Controller
@RequestMapping("/run")
public class RunController  extends BaseController{
	public static final Logger logger = LoggerFactory.getLogger(RunController.class);
	private static final String INDEX = "run_pavilion/index";
	private static final String FOCUS = "run_pavilion/slider";
	private static final String HOT = "run_pavilion/hot";
	private static final String CHANNEL = "run_pavilion/channel";
	@Autowired
	private SPBizRunningService spBizRunningService;
	@Autowired
	private SPBizUserService bizUserService;
	/**
	 * 
	 * @Title: index
	 * @Description:加载首页时执行,直接跳转到首页面
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年2月7日
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {

		return INDEX;
	}
	@RequestMapping(value = "/focus", method = RequestMethod.GET)
	public String focus(Model model) {
		RunGalleryList gallerys=spBizRunningService.getRunGalleryList(Constants.RUN_GALLEY_TYPE, "6");
		model.addAttribute("gallerys", gallerys);
		return FOCUS;
	}
	@RequestMapping(value = "/hot", method = RequestMethod.GET)
	public String hot(Model model) {
		RunBrandsHotList hotList=spBizRunningService.getRunBrandsHost(Constants.RUN_BRAND_HOT_TYPE, "3");
		model.addAttribute("hotList", hotList);
		return HOT;
	}
	@RequestMapping(value = "/channel", method = RequestMethod.GET)
	public String channel(HttpServletRequest request,Model model) {
	
		User user = getSessionUser(request);
		String userId = StringUtils.isEmpty(user) ? null : user.getUserid();
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
	/*	if (ClientUtil.isNotlessThanVer(ua.toLowerCase(),Constants.RUN_APP_VER)) {
			model.addAttribute("isNative", "1");
			logger.debug("isNative");
		}*/
		
		List<Category> secondfirstCategory=new ArrayList<Category>();
		List<Product> productList=new ArrayList<Product>();
		List<Category> firstCategory=spBizRunningService.runCategorys(userId, null,Constants.RUN_CHANNEL,"1",Constants.RUN_INDEX_CHANNEL_END);
		if(firstCategory!=null&&firstCategory.size()>0){
			 secondfirstCategory=spBizRunningService.runCategorys(userId, firstCategory.get(0).getChannelCategoryNo(),Constants.RUN_CHANNEL,null,null);
			 productList=spBizRunningService.runProducts(userId,Constants.RUN_CHANNEL, firstCategory.get(0).getChannelCategoryNo(), "1", Constants.RUN_PRODUCT_LIST_END);
		}
		
	/*	List<Category> firstCategory=new ArrayList<Category>();
		Category category=new Category();
		category.setChannelCategoryName("跑步馆");
		category.setChannelCategoryNo("123");
		category.setChannelCategoryPic("http://pic3.shangpin.com/e/s/15/05/12/20150512194712181936-0-0.jpg");
		category.setId("1");
		category.setName("跑步馆");
		firstCategory.add(category);
		firstCategory.add(category);
		
		List<Category> secondfirstCategory=new ArrayList<Category>();
		Category secondCategory=new Category();
		secondCategory.setChannelCategoryName("跑步馆相关");
		secondCategory.setChannelCategoryNo("123");
		secondCategory.setChannelCategoryPic("http://pic3.shangpin.com/e/s/15/05/12/20150512194712181936-0-0.jpg");
		secondCategory.setId("2");
		secondCategory.setName("跑步馆相关");
		secondfirstCategory.add(secondCategory);
		secondfirstCategory.add(secondCategory);
		
		List<Product> productList=new ArrayList<Product>();
		Product product=new Product();
		product.setBrandEnName("TOSHOP");
		product.setProductId("1122");
		product.setPic("http://pic1.shangpin.com/f/p/15/05/21/20150521173719721739-304-404.jpg");
		product.setProductNo("1122");
		product.setLimitedPrice("188.00");
		product.setProductName("字扣高跟凉鞋经典大牌女士鱼嘴高贵");
		product.setPrefix("女士鱼嘴高贵");
		product.setSuffix("字扣高跟凉鞋经典大牌");
		productList.add(product);
		productList.add(product);
		productList.add(product);
		productList.add(product);*/
		
		String hasMore="1";
		if(productList==null||productList.size()<Integer.valueOf(Constants.RUN_PRODUCT_LIST_END)){
				hasMore="0";
		}
		
		model.addAttribute("firstCategory", firstCategory);
		model.addAttribute("secondfirstCategory", secondfirstCategory);
		model.addAttribute("productList", productList);
		
		model.addAttribute("userLv", userLv);
		model.addAttribute("hasMore", hasMore);//1代表有，0代表没有
		return CHANNEL;
	}
	@ResponseBody
	@RequestMapping(value = "/get/channel", method = RequestMethod.GET)
	public Map<String, Object> getChannel(HttpServletRequest request,String channelCategoryNO,String start,Model model) {
		Map<String, Object> map=new HashMap<String, Object>();
		User user = getSessionUser(request);
		String userId = StringUtils.isEmpty(user) ? null : user.getUserid();
		/*String userLv = StringUtils.isEmpty(user) ? null : user.getLv();*/
		start = (StringUtils.isEmpty(start)) ? "1" : start;
		
		List<Category> secondfirstCategory=spBizRunningService.runCategorys(userId, channelCategoryNO,Constants.RUN_CHANNEL,null,null);
		List<Product> productList=spBizRunningService.runProducts(userId,Constants.RUN_CHANNEL, channelCategoryNO, start, Constants.RUN_PRODUCT_LIST_END);
	/*	Category category=new Category();
		category.setChannelCategoryName("跑步馆");
		category.setChannelCategoryNo("123");
		category.setChannelCategoryPic("http://pic3.shangpin.com/e/s/15/05/12/20150512194712181936-0-0.jpg");
		category.setId("1");
		category.setName("跑步馆");
		firstCategory.add(category);
		firstCategory.add(category);
		
		List<Category> secondfirstCategory=new ArrayList<Category>();
		Category secondCategory=new Category();
		secondCategory.setChannelCategoryName("跑步馆相关");
		secondCategory.setChannelCategoryNo("123");
		secondCategory.setChannelCategoryPic("http://pic3.shangpin.com/e/s/15/05/12/20150512194712181936-0-0.jpg");
		secondCategory.setId("2");
		secondCategory.setName("足球鞋");
		secondfirstCategory.add(secondCategory);
		secondfirstCategory.add(secondCategory);
		
		List<Product> productList=new ArrayList<Product>();
		Product product=new Product();
		product.setBrandEnName("NEW BALANCE");
		product.setProductId("1122");
		product.setPic("http://pic1.shangpin.com/f/p/15/05/21/20150521173719721739-304-404.jpg");
		product.setProductNo("1122");
		product.setLimitedPrice("188.00");
		product.setProductName("字扣高跟凉鞋经典大牌女士鱼嘴高贵");
		product.setPrefix("女士鱼嘴高贵");
		product.setSuffix("字扣高跟凉鞋经典大牌");
		productList.add(product);
		productList.add(product);
		productList.add(product);
		productList.add(product);
		*/
		String hasMore="1";
		if(productList==null||productList.size()<Integer.valueOf(Constants.RUN_PRODUCT_LIST_END)){
			hasMore="0";
		}
		map.put("secondfirstCategory", secondfirstCategory);
		map.put("productList", productList);
		
		/*map.put("userLv", userLv);*/
		map.put("hasMore", hasMore);//1代表有，0代表没有
		return map;
	}
	
	
	
	/**
	 * 
	* @Title: getNewMore 
	* @Description:商品列表加载更多
	* @param @return
	* @return String
	* @throws 
	* @Create By liling
	* @Create Date 2015年3月6日
	 */
	@ResponseBody
	@RequestMapping(value = "/product/more",  method = {RequestMethod.GET,RequestMethod.POST})
	public  Map<String, Object> getNewMore(String channelCategoryNO, String start,HttpServletRequest request, String gender, Model model) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			start = (StringUtils.isEmpty(start)) ? "1" : start;
			User user = getSessionUser(request);
			String userId = StringUtils.isEmpty(user) ? null : user.getUserid();
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			start=String.valueOf(Integer.valueOf(start)+1);
			List<Product> productList=spBizRunningService.runProducts(userId, Constants.RUN_CHANNEL,channelCategoryNO, start, Constants.RUN_PRODUCT_LIST_END);
			
		/*	List<Product> productList=new ArrayList<Product>();
			Product product=new Product();
			product.setBrandEnName("TOSHOP");
			product.setProductId("1122");
			product.setPic("http://pic1.shangpin.com/f/p/15/05/21/20150521173719721739-304-404.jpg");
			product.setProductNo("1122");
			product.setLimitedPrice("188.00");
			product.setProductName("字扣高跟凉鞋经典大牌女士鱼嘴高贵");
			product.setPrefix("女士鱼嘴高贵");
			product.setSuffix("字扣高跟凉鞋经典大牌");
			productList.add(product);
			productList.add(product);
			productList.add(product);
			productList.add(product);*/
			
			String hasMore="1";
			if(productList==null||productList.size()<Integer.valueOf(Constants.RUN_PRODUCT_LIST_END)){
				hasMore="0";
			}
			
			map.put("productList", productList);
			map.put("start", start);
			map.put("userLv", userLv);
			map.put("hasMore", hasMore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
