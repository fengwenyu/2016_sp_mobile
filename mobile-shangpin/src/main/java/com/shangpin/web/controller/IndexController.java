package com.shangpin.web.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
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

import com.shangpin.biz.bo.AdvertTitle;
import com.shangpin.biz.bo.CategoryItem;
import com.shangpin.biz.bo.CustomBrandItem;
import com.shangpin.biz.bo.Entrance;
import com.shangpin.biz.bo.Fashion;
import com.shangpin.biz.bo.GalleryList;
import com.shangpin.biz.bo.ModelTitle;
import com.shangpin.biz.bo.NewGoodsTitle;
import com.shangpin.biz.bo.RecProductFor;
import com.shangpin.biz.bo.ReleasesSPActivity;
import com.shangpin.biz.bo.RevealLabel;
import com.shangpin.biz.bo.SPActivity;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.WorthTitle;
import com.shangpin.biz.service.ASPBizRecommendService;
import com.shangpin.biz.service.ASPBizSaleService;
import com.shangpin.biz.service.SPBizBrandService;
import com.shangpin.biz.service.SPBizCategoryService;
import com.shangpin.biz.service.SPBizEntranceService;
import com.shangpin.biz.service.SPBizExclusiveRecProductService;
import com.shangpin.biz.service.SPBizFashionService;
import com.shangpin.biz.service.SPBizIndexInfoService;
import com.shangpin.biz.service.SPBizIndexService;
import com.shangpin.biz.service.SPBizNewReleaseService;
import com.shangpin.biz.service.SPBizRecommendService;
import com.shangpin.biz.service.SPBizStyleThemeService;
import com.shangpin.utils.IpUtils;
import com.shangpin.utils.StringUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.CookieUtil;

/**
 * 用户管理的controller
 * 
 * @author sunweiwei
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    
    private static final String INDEX = "index_20150203001/index";
    private static final String FOCUS = "index_20150203001/focus";
    private static final String NEW_RELEASE = "index_20150203001/new_release";
    private static final String RELEASE_LIST = "index_20150203001/release_list";
	private static final String CATEGORY = "index_20150203001/category";

	private static final String ENTRANCE = "index_20150203001/entrance";

	private static final String FASHION ="index_20150203001/fashion";

	private static final String EXCLUSIVERECOMMEND = "index_20150203001/exclusive_recommend";

	private static final String BRAND = "index_20150203001/custom_brands";

	private static final String WORTH = "index_20150203001/worth";

	private static final String REVEALLABEL = "index_20150203001/reveal_label";

	private static final String ADVERT = "index_20150203001/advert";


	private static final String ADVERT_FIRST = "index_20150203001/advert_first";
	
    @Autowired
    private SPBizNewReleaseService bizNewReleaseService;
    @Autowired
    private SPBizStyleThemeService bizStyleThemeService;
    @Autowired
	private ASPBizSaleService aspBizSaleService;
	@Autowired
	private ASPBizRecommendService recommendService;
	@Autowired
	private SPBizIndexService indexService;
	@Autowired
	private SPBizEntranceService spBizEntranceService;
	@Autowired
	SPBizFashionService spBizFashionService;
	@Autowired
	SPBizBrandService spBizBrandService;
	@Autowired
	SPBizExclusiveRecProductService spBizExclusiveRecProductService;
	@Autowired
	SPBizRecommendService spBizRecommendService;
	@Autowired
	SPBizIndexInfoService spIndexInfoService;
	@Autowired
    private SPBizCategoryService bizCategoryService;
    /**
     * 加载首页时执行,直接跳转到首页面
     * 
     * @author qinyingchun
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return INDEX;
    }
    
    /** 
     * @Title: focus 
     * @Description: 首页轮播图
     * @param type 轮播图类型
     * @return String
     * @throws 
     * @Create By qinyingchun
     * @Create Date 2014年10月22日
     */
     @RequestMapping(value = "/focus", method = RequestMethod.GET)
     public String focus(@RequestParam String type, Model model){
     	logger.debug("IndexController focus start!");
     	GalleryList gallerys = indexService.galleryList(type,"6");
     	if(null == gallerys){
     		return FOCUS;
     	}
     	model.addAttribute("gallerys", gallerys);
     	logger.debug("IndexController focus end!");
     	return FOCUS;
     }
    /**
     * 
     * @Description:首页最新上架
     * @return
     * @author qinyingchun
     * @date 2015年2月3日
     */
    @RequestMapping(value = "/release", method = RequestMethod.GET)
    public String release(String pageIndex, String pageSize, String origin, Model model){
    	List<SPActivity> activities = bizNewReleaseService.activities(pageIndex, pageSize, origin);
    	if(null == activities){
    		return NEW_RELEASE;
    	}
    	for(SPActivity activity : activities){
    		String startTime = activity.getStartTime();
    		SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd");
    		String str = dateFormat.format(new Date(new Long(startTime)));
    		activity.setStartTime(str);
    	}
    	model.addAttribute("activities", activities);
    	return NEW_RELEASE;
    }
    
    @RequestMapping(value = "/release/list", method = RequestMethod.GET)
    public String leaseList(String pageIndex, String pageSize, String origin, Model model){
    	List<SPActivity> activities = bizNewReleaseService.activities(pageIndex, pageSize, origin);
    	if(null == activities){
    		return RELEASE_LIST;
    	}
    	for(SPActivity activity : activities){
    		String startTime = activity.getStartTime();
    		SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd");
    		String str = dateFormat.format(new Date(new Long(startTime)));
    		activity.setStartTime(str);
    	}
    	model.addAttribute("activities", activities);
    	return RELEASE_LIST;
    }


	/**
	 * 
	 * @Title: customBrand
	 * @Description:可管理推荐品牌
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年4月23日
	 */
	@RequestMapping(value = "custom/brand", method = RequestMethod.GET)
	public String customBrand(Model model,HttpServletRequest request) {

		User user = getSessionUser(request);
		String userId = user != null ? user.getUserid() : "";
		Cookie cookie =CookieUtil.getCookieByName(request, Constants.UVID_COOKIE_NAME);
		String vuId= StringUtils.isEmpty(cookie)?null:cookie.getValue();
		if(!StringUtil.isEmpty(vuId)){
			vuId=vuId.split("[|]")[0];
		}
        CustomBrandItem customBrandItem=spBizBrandService.queryCustomBrand(userId, vuId,Constants.INDEX_CUSTOM_BRAND_NUM );
        model.addAttribute("customBrandItem", customBrandItem);
    
        return BRAND;
	}
	
	/**
	 * 
	 * @Title: entrance
	 * @Description:商城首页运营位入口
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By LILING
	 * @Create Date 2015年3月11日
	 */
	@RequestMapping(value = "/entrance", method = RequestMethod.GET)
	public String entrance(Model model) {
		logger.debug("IndexController entrance start!");
		List<Entrance> entrances = spBizEntranceService.queryEntranceList(Constants.ENTRANCE_TYPE, Constants.INDEX_ENTRANCE_START,Constants.INDEX_ENTRANCE_END);
		model.addAttribute("entrances", entrances);
		logger.debug("IndexController entrance end!");
		return ENTRANCE;
	}
	/**
	 * 
	 * @Title: fashion
	 * @Description: 尚潮流列表首页
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@RequestMapping(value = "/fashion", method = RequestMethod.GET)
	public String fashion(Model model,String type) {
		List<Fashion> fashionList = spBizFashionService.findFashionList(Constants.INDEX_FASHION_TYPE,Constants.INDEX_FASHION_START,Constants.INDEX_FASHION_END);
		model.addAttribute("fashionList", fashionList);
		return FASHION;
	}
	 /** 
     * @Title: category 
     * @Description: 分类
     * @param type 分类
     * @return String
     * @throws 
     * @Create By wanghua
     * @Create Date 2015年11月17日
     */
    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String fashion55(Model model,String type) {
        logger.debug("IndexController category start!");
        CategoryItem categoryItem = bizCategoryService.queryCategory("0","1");
        model.addAttribute("categoryItem", categoryItem);
        return CATEGORY;
    }

	/**
	 * @Title: exclusiveRecommend
	 * @Description:为您推荐
	 * @return
	 * @author liling
	 * @date 2015年4月22日
	 */
	@RequestMapping(value = "exclusive/recommend", method = RequestMethod.GET)
	public String exclusiveRecommend(HttpServletRequest request, Model model,String dynParam,String pageIndex) {
		User user = getSessionUser(request);
		String userId = StringUtils.isEmpty(user) ? null : user.getUserid();
//		String type, String userId,
//		String vuid, String coord, String ip, String pageIndex,
//		String pageSize)
		String ip = IpUtils.getIpAddr(request);
		if (ip.indexOf(",") > -1) {
			ip = ip.split(",")[0].trim();
		}
		Cookie cookie =CookieUtil.getCookieByName(request, Constants.UVID_COOKIE_NAME);
		String vuId= StringUtils.isEmpty(cookie)?null:cookie.getValue();
		
		if(!StringUtil.isEmpty(vuId)){
			vuId=vuId.split("[|]")[0];
		}
		RecProductFor recProductItem = spBizExclusiveRecProductService.queryRecProduct(Constants.INDEX_REC_PRODUCT_TYPE,userId, vuId, null, ip, Constants.EXCLUSIVE_RECOMMEND_PAGEINDEX,Constants.EXCLUSIVE_RECOMMEND_PAGESIZE);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		model.addAttribute("recProductItem", recProductItem);
		model.addAttribute("userLv", userLv);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		model.addAttribute("userLv", userLv);
		model.addAttribute("date",  calendar.get(Calendar.DATE));
		model.addAttribute("week",  calendar.get(Calendar.DAY_OF_WEEK));
		model.addAttribute("month",  calendar.get(Calendar.MONTH));
		int minute=calendar.get(Calendar.MINUTE);
		model.addAttribute("time", calendar.get(Calendar.HOUR_OF_DAY) + ":"+(minute<10 ? "0"+minute:minute));
		if (null == recProductItem) {
			return EXCLUSIVERECOMMEND;
		}
		return EXCLUSIVERECOMMEND;

	}
	
	/**
	 * @Title: getMore
	 * @Description:更多为您推荐
	 * @return
	 * @author liling
	 * @date 2015年4月22日
	 */
	@RequestMapping(value = "/exclusive/recommend/more",  method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getMore(HttpServletRequest request, Model model,String pageIndex){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = getSessionUser(request);
		String userId = StringUtils.isEmpty(user) ? null : user.getUserid();
		
		String ip = IpUtils.getIpAddr(request);
		if (ip.indexOf(",") > -1) {
			ip = ip.split(",")[0].trim();
		}
		Cookie cookie =CookieUtil.getCookieByName(request, Constants.UVID_COOKIE_NAME);
		String vuId= StringUtils.isEmpty(cookie)?null:cookie.getValue();
		
		if(!StringUtil.isEmpty(vuId)){
			vuId=vuId.split("[|]")[0];
		}
		String start=String.valueOf(Integer.valueOf(pageIndex)+1);
		RecProductFor recProductItem = spBizExclusiveRecProductService.queryRecProduct(Constants.INDEX_REC_PRODUCT_TYPE,userId, vuId, null, ip, start,Constants.EXCLUSIVE_RECOMMEND_PAGESIZE);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		map.put("recProductItem", recProductItem);
		map.put("userLv", userLv);
		map.put("pageIndex", start);
		map.put("hasMore", "0");
		if(recProductItem!=null&&recProductItem.getList()!=null){
			if(recProductItem.getList().size()>=Integer.valueOf(Constants.EXCLUSIVE_RECOMMEND_PAGESIZE)){
				map.put("hasMore", "1");
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: worth
	 * @Description:每日超值购
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年9月23日
	 */
	@RequestMapping(value = "/worth", method = RequestMethod.GET)
	public String worth(HttpServletRequest request,Model model) {
		logger.debug("IndexController worth start!");
		try{
			User user = getSessionUser(request);
			String userId = StringUtils.isEmpty(user) ? null : user.getUserid();
			WorthTitle worthTitle=spBizRecommendService.doRecProduct("2", userId, "1", "4");
			model.addAttribute("worthTitle", worthTitle);
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			String hasMore="1";
			if(worthTitle==null||worthTitle.getList()==null||worthTitle.getList().size()<Integer.valueOf(Constants.RUN_PRODUCT_LIST_END)){
				hasMore="0";
			}
			model.addAttribute("userLv", userLv);
			model.addAttribute("pageIndex", "1");
			model.addAttribute("hasMore", hasMore);
			logger.debug("IndexController worth end!");
			return WORTH;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @Title: worthMore
	 * @Description:更多每日超值购
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年9月23日
	 */
	@RequestMapping(value = "/worth/more", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> worthMore(HttpServletRequest request,String pageIndex){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			User user = getSessionUser(request);
			String userId = StringUtils.isEmpty(user) ? null : user.getUserid();
			
		
			String start=String.valueOf(Integer.valueOf(pageIndex)+1);
			WorthTitle worthTitle=spBizRecommendService.doRecProduct("2", userId, start, "4");
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			String hasMore="1";
			if(worthTitle==null||worthTitle.getList()==null||worthTitle.getList().size()<Integer.valueOf(Constants.RUN_PRODUCT_LIST_END)){
				hasMore="0";
			}
			map.put("worthTitle", worthTitle);
			map.put("userLv", userLv);
			map.put("pageIndex", start);
			map.put("hasMore", hasMore);
			return map;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @Title: 标签
	 * @Description:商城首页标签
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年10月12日
	 */
	
	@RequestMapping(value = "/revealLabel", method = { RequestMethod.POST, RequestMethod.GET })
	public String revealLabel( Model model) {
		try {
			RevealLabel revealLabel =spIndexInfoService.getRevealLabel("1", "12");
			model.addAttribute("revealLabel", revealLabel);
		} catch (Exception e) {
			logger.error("error:", e);
		}
		return REVEALLABEL;

	}
	/**
	 * 
	 * @Title: advertFirst
	 * @Description:轮播图下的广告位
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年10月13日
	 */
	@RequestMapping(value = "/advert/first", method = { RequestMethod.POST, RequestMethod.GET })
	public String advertFirst(HttpServletRequest request, Model model) {
		try {
			AdvertTitle advertTitle= spIndexInfoService.getAdvertTitle("2","1", "3");//轮播图下的广告位
			model.addAttribute("advertTitle", advertTitle);
		} catch (Exception e) {
			logger.error("error:", e);
		}
		return ADVERT_FIRST;
	}
	/**
	 * 
	 * @Title: advert
	 * @Description:广告位
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年10月13日
	 */
	@RequestMapping(value = "/advert", method = { RequestMethod.POST, RequestMethod.GET })
	public String advert(HttpServletRequest request, Model model) {
		try {
			User user = getSessionUser(request);
			String userId = StringUtils.isEmpty(user) ? null : user.getUserid();
		
			//AdvertNewTitle advertNewTitle=spIndexInfoService.getAdvertNewTitle();//广告模板
			ModelTitle modelOne=spIndexInfoService.findModelInfo();
			ReleasesSPActivity releasesSPActivity=spIndexInfoService.getReleaseTitle("1", "1", "");//最新专题首位第一张
			NewGoodsTitle newGoodsTitle=spIndexInfoService.getNewGoodsTitle();//新品到货
			WorthTitle worthTitle=spIndexInfoService.getWorthTitle("2", userId, "1", "6");//超值购
			AdvertTitle operation= spIndexInfoService.getAdvertTitle("1","1", "1");//最下面的广告
			
			model.addAttribute("modelOne", modelOne);
			model.addAttribute("releasesSPActivity", releasesSPActivity);
			model.addAttribute("newGoodsTitle", newGoodsTitle);
			model.addAttribute("worthTitle", worthTitle);
			model.addAttribute("operation", operation);
		} catch (Exception e) {
			logger.error("error:", e);
		}
		return ADVERT;

	}
	
}
