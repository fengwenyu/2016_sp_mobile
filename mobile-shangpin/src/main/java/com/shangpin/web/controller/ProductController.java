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

import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.ProductComment;
import com.shangpin.biz.bo.ProductDetail;
import com.shangpin.biz.bo.ProductDetailSizeTable;
import com.shangpin.biz.bo.ProductDetailSizes;
import com.shangpin.biz.bo.ProductSize;
import com.shangpin.biz.bo.ProductTemplate;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.RecProduct;
import com.shangpin.biz.bo.RefContent;
import com.shangpin.biz.bo.SearchConditions;
import com.shangpin.biz.bo.SearchProductResult;
import com.shangpin.biz.bo.SearchType;
import com.shangpin.biz.bo.SiteType;
import com.shangpin.biz.bo.SizeInfo;
import com.shangpin.biz.bo.Tag;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBiz520SellService;
import com.shangpin.biz.service.SPBizProductCommentService;
import com.shangpin.biz.service.SPBizProductService;
import com.shangpin.biz.service.SPBizProductSizeService;
import com.shangpin.biz.service.SPBizProductTemplateService;
import com.shangpin.biz.service.SPBizRecProductService;
import com.shangpin.biz.service.SPBizReceiveService;
import com.shangpin.biz.service.SPBizSearchService;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.service.impl.FreebieService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.biz.utils.FreeBie520Util;
import com.shangpin.web.utils.ActivifyUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.SearchUtil;
import com.shangpin.wechat.service.WeChatPublicService;


/** 
* @ClassName: ProductController 
* @Description:商品详情页
* @author qinyingchun
* @date 2014年11月3日
* @version 1.0 
*/
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{
	
	@SuppressWarnings("unused")
	private static final Logger logger  = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private SPBizProductService bizProductService;
	@Autowired
	private SPBizSearchService searchService;
	@Autowired
	private WeChatPublicService weiXinService;
	@Autowired
	SPBizProductService spBizProductService;
	@Autowired
	SPBizProductSizeService spBizProductSizeService;
	@Autowired
	SPBizProductTemplateService spBizProductTemplateService;
	@Autowired
	SPBizProductCommentService spBizProductCommentService;
	@Autowired
	SPBizRecProductService spBizRecProductService;
	@Autowired
    private SPBiz520SellService freebieService;
	@Autowired
	private SPBiz520SellService sPBiz520SellService;
	@Autowired
	private SPBizUserService userService;
	@Autowired
    private SPBizSendInfoService bizSendInfoService;
	@Autowired
	private SPBizReceiveService sPBizReceiveService;
	
	
	private static final String PRODUCT_DETAIL = "product/index";
	private static final String PRODUCT_SIZE = "product/size";
	private static final String NEWLIST = "product/new_list";
	private static final String NEWINDEX = "product/new_index";
	private static final String PRODUCT_OVERSEAS_DETAIL = "ep_product/index";
	private static final String GUESSLIKE = "product/guessLike";
	private static final String SERVICE = "product/service";
	private static final String APPGUESSLIKE = "app_product/guessLike";
	private static final String APPINDEX = "app_product/index";
	private static final String APP_GIFTCARD_INDEX = "app_product/giftcard_index";
	private static final String PRODUCT_COMMENT = "comment/index";
	private static int HASPAGENUM =0;
	private static final String COMMENT_PAGE_INDEX ="20";
	private static Map<String, SizeInfo> sizeInfo = new HashMap<String, SizeInfo>();
	//买赠分享详情页
	private static final String PRODUCT_FREEBIEDETAIL = "product/freebie/index";
	
	private static final String PRODUCT_FREEBIEDETAIL1 = "product/freebie_index";
	
	/**
	 * 
	 * @Title: detail
	 * @Description:详情页数据
	 * @param productNo
	 * @param topicId
	 * @param model
	 * @param request
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@SuppressWarnings("unused")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam String productNo, String topicId,String picNo, String ch,Model model, HttpServletRequest request) {
		model.addAttribute("productNo", productNo);
		model.addAttribute("topicId", topicId);
		//解决cookie刷新不及时的问题，只有微信浦发银行时用到
		model.addAttribute("ch", ch);
		User user = getSessionUser(request);
		String userId = user != null ? user.getUserid() : "";
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		ProductDetail productDetail = spBizProductService.findProductDetail(topicId, productNo, userId,picNo);
		List<RecProduct> guessLikeList = spBizRecProductService.findRecProductObj(userId, Constants.GUESSLIKE_RECPRODUCT_TYPE, Constants.SHANGPIN_SHOPTYPE, productNo, Constants.GUESSLIKE_RECPRODUCT_START, Constants.GUESSLIKE_RECPRODUCT_END);
		ProductComment productComment = spBizProductCommentService.queryProductComment(productNo, "0", "2","0");
		String ua = request.getHeader("User-Agent").toLowerCase();
		SiteType siteType =ClientUtil.getSiteType(request);
		ProductTemplate productTemplate = spBizProductTemplateService.findProductTemplate("5", productNo,siteType);
		model.addAttribute("productTemplate", productTemplate);
		model.addAttribute("productDetail", productDetail);
		model.addAttribute("guessLikeList", guessLikeList);
		model.addAttribute("productComment", productComment);
		model.addAttribute("userLv", userLv);
	    if(StringUtils.isEmpty(productDetail)){
	        return PRODUCT_DETAIL;
	    }
		if (productDetail.getPostArea().equals(Constants.PRODUCT_OVERSEAS_TYPE)) {
			return PRODUCT_OVERSEAS_DETAIL;
		}
		return PRODUCT_DETAIL;
	}
    /***
     * init 到商品评论页 
     * @param productNo
     * @param topicId
     * @param picNo
     * @param ch
     * @param model
     * @param tagId
     * @param request
     * @return
     */
	@RequestMapping(value = "/prCommentList", method = RequestMethod.GET)
    public String prCommentList(@RequestParam String productNo, String topicId,String picNo, String ch,Model model, String tagId,HttpServletRequest request) {
        model.addAttribute("productNo", productNo);
        //解决cookie刷新不及时的问题，只有微信浦发银行时用到
        model.addAttribute("ch", ch);
        User user = getSessionUser(request);
        String userId = user != null ? user.getUserid() : "";
        String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
        ProductDetail productDetail = spBizProductService.findProductDetail(topicId, productNo, userId,picNo);
        ProductComment productComment = spBizProductCommentService.queryProductComment(productNo, "0", COMMENT_PAGE_INDEX,tagId);       
        model.addAttribute("seleTagId", tagId);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productComment", productComment);
        model.addAttribute("userLv", userLv);
        model.addAttribute("productDetail", productDetail);
        return PRODUCT_COMMENT;
    }
	/**
	 * 
	 * @Title: getSize
	 * @Description:详情页尺码数据
	 * @param productNo
	 * @param model
	 * @param request
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@RequestMapping(value = "/getSize", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getSize(String productNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		ProductSize productSize = spBizProductSizeService.findProductSize(productNo);
		int size=0;
		ProductDetailSizes productDetailSize=productSize.getProductDetailSize();
		if(productDetailSize!=null){
			List<ProductDetailSizeTable> table=productDetailSize.getTable();
			if(table!=null&&table.size()>0){
				List<String> value=table.get(0).getValue();
				if(value!=null){
					size=value.size();
				}
			}
		}
		List<Integer> groups = new ArrayList<Integer>();
		int group = size % 4 == 0 ? size / 4 : size / 4 + 1;
		for (int i = 0; i < group; i++) {
			groups.add(i);
		}
		map.put("groups", groups);
		map.put("productSize", productSize);
		return map;
	}
	/**
	 * 
	 * @Title: getTemplate
	 * @Description:详情页模板数据
	 * @param productNo
	 * @param model
	 * @param request
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@RequestMapping(value = "/getTemplate",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getTemplate(String type, String productNo, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		SiteType siteType =ClientUtil.getSiteType(request);
		ProductTemplate productTemplate = spBizProductTemplateService.findProductTemplate(type, productNo,siteType);
		map.put("productTemplate", productTemplate);
		return map;
	}
	/**
	 * 
	 * @Title: getComment
	 * @Description:详情页评论数据
	 * @param productNo
	 * @param model
	 * @param request
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@RequestMapping(value = "/getComment", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getComment(String productNo, String pageIndex, String pageSize,String tagId) {
		Map<String, Object> map = new HashMap<String, Object>();
		ProductComment productComment = spBizProductCommentService.queryProductComment(productNo, pageIndex, pageSize,tagId);
		for (Tag tag : productComment.getTag()) {
            if (tag.getId().equals(tagId)) {
                HASPAGENUM= SearchUtil.hasPageNum(Integer.parseInt(tag.getCount()),Integer.parseInt(Constants.PRODUCT_LIST_END));
                break;
            }
        }
		map.put("pageIndex", pageIndex);
		map.put("hasMore", "0");
		if(productComment!=null&&productComment.getList()!=null){
		    if(productComment.getList().size()>=Integer.valueOf(COMMENT_PAGE_INDEX)){
		        map.put("hasMore", "1");
		    }
		}
        map.put("hasPageNum", HASPAGENUM);
        map.put("productComment", productComment);
		return map;
	}
	/**
	 * 
	 * @Title: guessLike
	 * @Description:详情页猜您喜欢数据
	 * @param productNo
	 * @param model
	 * @param request
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@RequestMapping(value = "/guessLike", method = RequestMethod.GET)
	public String guessLike(String productNo,Model model, HttpServletRequest request) {
		User user = getSessionUser(request);
		String userId = user.getUserid();
		List<RecProduct> guessLikeList = spBizRecProductService.findRecProductObj(userId, Constants.GUESSLIKE_RECPRODUCT_TYPE, Constants.SHANGPIN_SHOPTYPE, productNo, Constants.GUESSLIKE_RECPRODUCT_START, Constants.GUESSLIKE_RECPRODUCT_END);
		model.addAttribute("guessLikeList", guessLikeList);
		return GUESSLIKE;
	}
	/**
	 * 
	 * @Title: template
	 * @Description:详情页模板数据
	 * @param productNo
	 * @param model
	 * @param request
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@RequestMapping(value = "/template", method = RequestMethod.GET)
	public String template(String type, String productNo,Model model, HttpServletRequest request) {
		SiteType siteType =ClientUtil.getSiteType(request);
		ProductTemplate productTemplate = spBizProductTemplateService.findProductTemplate(type, productNo,siteType);
		model.addAttribute("productTemplate", productTemplate);
		if(type.equals(Constants.PRODUCT_TEMPLATE_AFTERSALE)){
			return SERVICE;
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: appDetail
	 * @Description:app详情页数据
	 * @param productNo
	 * @param model
	 * @param request
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@RequestMapping(value = "/app/detail", method = RequestMethod.GET)
	public String appDetail(@RequestParam String type, String productNo,String topicId,String picNo,Model model, HttpServletRequest request) {

		User user = getSessionUser(request);
		String userId = user != null ? user.getUserid() : "";
		if(type.equals(Constants.APP_DETAIL_BASIC_TYPE)||type.equals(Constants.APP_DETAIL_SHOWPICS_TYPE)){
			ProductDetail productDetail = spBizProductService.findProductDetail(topicId, productNo, userId,picNo);
			model.addAttribute("productDetail", productDetail);
		}
		if(type.equals(Constants.APP_DETAIL_SIZE_TYPE)){
			ProductSize productSize = spBizProductSizeService.findProductSize(productNo);
			int size=0;
			ProductDetailSizes productDetailSize=productSize.getProductDetailSize();
			if(productDetailSize!=null){
				List<ProductDetailSizeTable> table=productDetailSize.getTable();
				if(table!=null&&table.size()>0){
					List<String> value=table.get(0).getValue();
					if(value!=null){
						size=value.size();
					}
				}
			}
			List<Integer> groups = new ArrayList<Integer>();
			int group = size % 4 == 0 ? size / 4 : size / 4 + 1;
			for (int i = 0; i < group; i++) {
				groups.add(i);
			}
			model.addAttribute("groups", groups);
			model.addAttribute("productSize", productSize);
		}
		if(type.equals(Constants.APP_DETAIL_TEMPLATE_TYPE)){
			SiteType siteType =ClientUtil.getSiteType(request);
			ProductTemplate productTemplate = spBizProductTemplateService.findProductTemplate(Constants.PRODUCT_DETAIL_TEMPLATE_TYPE, productNo,siteType);
			model.addAttribute("productTemplate", productTemplate);
		}
		model.addAttribute("type", type);
		model.addAttribute("productNo", productNo);
		  if(Constants.APP_DETAIL_GIFTCARD_TYPE.equals(type) || Constants.APP_DETAIL_GIFTCARD_E_TYPE.equals(type)){
			   return APP_GIFTCARD_INDEX;
			  }
		return APPINDEX;
	}
	/**
	 * 
	 * @Title: appGuessLike
	 * @Description:app详情页猜您喜欢数据
	 * @param productNo
	 * @param model
	 * @param request
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@RequestMapping(value = "app/guessLike", method = RequestMethod.GET)
	public String appGuessLike(String productNo,Model model, HttpServletRequest request) {
		User user = getSessionUser(request);
		String userId = user != null ? user.getUserid() : "";
		List<RecProduct> guessLikeList = spBizRecProductService.findRecProductObj(userId, Constants.GUESSLIKE_RECPRODUCT_TYPE, Constants.SHANGPIN_SHOPTYPE, productNo, Constants.GUESSLIKE_RECPRODUCT_START, Constants.GUESSLIKE_RECPRODUCT_END);
		model.addAttribute("guessLikeList", guessLikeList);
		return APPGUESSLIKE;
	}
	
	
	/**
	 * 
	* @Title: sizedesc 
	* @Description:商品尺码信息
	* @param 
	* @return String
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年11月18日
	 */
	@RequestMapping(value = "/sizedesc", method = RequestMethod.GET)
	public String sizedesc(String productNo, Model model){
		SizeInfo sizeInf = sizeInfo.get(productNo);
		model.addAttribute("sizeInfo", sizeInf);
		return PRODUCT_SIZE;
	}
	/**
	 * 
	* @Title: newIndex 
	* @Description:商品尺码信息
	* @param 
	* @return String
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年11月18日
	 */
	@RequestMapping(value = "/new/index", method = RequestMethod.GET)
	public String newIndex(){
		return NEWINDEX;
		
	}
	/**
	 * 
	* @Title: newList 
	* @Description:商品尺码信息
	* @param 
	* @return String
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年11月18日
	 */
	@RequestMapping(value = "/new/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String newList(SearchConditions searchConditions, Model model, HttpServletRequest request){
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
//		SearchResult searchResult = brandService.brandProducts(null, searchConditions.getStart(), Constants.PRODUCT_LIST_END, searchConditions.getBrandNo(), searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), searchConditions.getCategoryNo(), searchConditions.getOrder(), userLv);
//		String queryCondition = SearchUtil.initQueryConditions(searchConditions);
//		int hasMore = SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()), Integer.parseInt(Constants.PRODUCT_LIST_END));
//		model.addAttribute("categories", categories);
//		model.addAttribute("sizes", sizes);
//		model.addAttribute("colors", colors);
//		model.addAttribute("searchResult", searchResult);
//		model.addAttribute("brandNo", searchConditions.getBrandNo());
//		model.addAttribute("start", searchConditions.getStart());
//		model.addAttribute("queryCondition", queryCondition);
//		model.addAttribute("searchConditions", searchConditions);
//		model.addAttribute("hasMore", hasMore);
//		model.addAttribute("userLv", userLv);
		SearchProductResult brandFilter=new SearchProductResult();
		SearchProductResult categoryFilter=new SearchProductResult();
		SearchProductResult otherFilter=new SearchProductResult();
		String categoryNo=searchConditions.getCategoryNo();
		String brandNo=searchConditions.getBrandNo();
		String start = ( StringUtils.isEmpty(searchConditions.getStart() )) ? "1" : searchConditions.getStart();
		SearchProductResult searchResult = searchService.newProductList(null,null, start, Constants.PRODUCT_LIST_END, brandNo, searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), categoryNo, searchConditions.getOrder(),searchConditions.getGender(), userLv,SearchType.PRODUCT, searchConditions.getPostArea());
	
		//品牌由品类控制
		if(StringUtils.isEmpty(categoryNo)||"A01".equals(categoryNo)||"A02".equals(categoryNo)){
			categoryFilter= searchService.newProductList(null,null, null,null, null, null, null, null, null,null, searchConditions.getGender(),userLv,SearchType.ALL_FILTER, searchConditions.getPostArea());
			brandFilter=categoryFilter;
		}else{
			categoryFilter = searchService.newProductList(null,null, null,null, null, null, null, null, null,null, searchConditions.getGender(),userLv,SearchType.CATEGORY_FILTER, searchConditions.getPostArea());
			brandFilter = searchService.newProductList(null,null, null,null, null, null, null, null, searchConditions.getCategoryNo(),null, searchConditions.getGender(),userLv,SearchType.BRAND_FILTER, searchConditions.getPostArea());
		}
		
		//筛选条件由品类 和品牌控制
		if((StringUtils.isEmpty(categoryNo)||"A01".equals(categoryNo)||"A02".equals(categoryNo))&&StringUtils.isEmpty(brandNo)){
			otherFilter=categoryFilter;
		}else{
			otherFilter = searchService.newProductList(null,null, null,null, brandNo, null, null, null, categoryNo,null, searchConditions.getGender(),userLv,SearchType.OTHER_FILTER, searchConditions.getPostArea());
		}
		String queryCondition = SearchUtil.initQueryConditions(searchConditions);
		int hasMore = SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()), Integer.parseInt(start),Integer.parseInt(Constants.PRODUCT_LIST_END));

		model.addAttribute("searchResult", searchResult);
		model.addAttribute("brandNo", searchConditions.getBrandNo());
		model.addAttribute("start", searchConditions.getStart());
		model.addAttribute("queryCondition", queryCondition);
		model.addAttribute("searchConditions", searchConditions);
		model.addAttribute("categoryFilter", categoryFilter);
		model.addAttribute("brandFilter", brandFilter);
		model.addAttribute("otherFilter", otherFilter);
		
		model.addAttribute("hasMore", hasMore);
		model.addAttribute("userLv", userLv);
		
		
		return NEWLIST;
	}
	
	@RequestMapping(value = "/new/list/more", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMore(SearchConditions searchConditions, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = getSessionUser(request);
		String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
		String categoryNo=searchConditions.getCategoryNo();
		String brandNo=searchConditions.getBrandNo();
		String start = ( StringUtils.isEmpty(searchConditions.getStart() )) ? "1" : searchConditions.getStart();
		SearchProductResult searchResult =  searchService.newProductList(null,null, start, Constants.PRODUCT_LIST_END, brandNo, searchConditions.getPrice(), searchConditions.getColor(), searchConditions.getSize(), categoryNo, searchConditions.getOrder(),searchConditions.getGender(), userLv,SearchType.PRODUCT, searchConditions.getPostArea());
		int hasMore = SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()),Integer.parseInt(start), Integer.parseInt(Constants.PRODUCT_LIST_END));
		map.put("hasMore", hasMore);
		map.put("searchResult", searchResult);
		map.put("userLv", userLv);
		return map;
	}
	
	/////买赠分享专有详情页
	/**
	 * 分享详情页面
	 * @param p
	 * @param model
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/freebieDetail", method = RequestMethod.GET)
    public String freebieDetail(@RequestParam String p, String spu,Model model, HttpServletRequest request) {
        //1.验证参数
        Map<String,String> parameter= FreeBie520Util.decodeFreebieParam(p);
        if (parameter==null) {
            logger.info("参数有误!"+p+":"+spu);
            return null;
        }
        String productNo = parameter.get("spu");
        //2是否有这活
        Boolean isTopic=false;
        try {
        	isTopic= freebieService.checkSpuIsTopic(productNo);
		} catch (Exception e) {
			logger.error("调用isTopic方法发生异常.......");
			e.printStackTrace();
			throw new RuntimeException("调用isTopic方法发生异常....... ", e);
		}
        if (!isTopic) {
            logger.info("活动不存在:"+productNo);
            return null;
        }
        //换其他商品领取
        if (!StringUtils.isEmpty(spu)) {
        	if (isFreeSpu(productNo,spu)) {
        		productNo=spu;
			}else {
				logger.info("换赠品的spu错误:"+spu);
				return null;
			}
        }
        //3.分享链接呗领取是否查库(永桥)返回结束页面
        String re=null;
        try {
        	re= sPBizReceiveService.isPicked(parameter.get("userId"), parameter.get("orderId"), parameter.get("sku"), parameter.get("sortNo"));
		} catch (Exception e) {
			logger.error("调用SPBizReceiveService.isPicked方法发生异常.......");
			e.printStackTrace();
			throw new RuntimeException("调用SPBizReceiveService.isPicked方法发生异常....... ", e);
		}

		if(!ActivifyUtil.isfreeActivify()){
			re="活动已结束！";
		}
        if (!StringUtils.isEmpty(re) &&"0".equals(re)) {
            ProductDetail productDetail = sPBiz520SellService.getProductDetail(productNo);
            ProductComment productComment = spBizProductCommentService.queryProductComment(productNo, "0", "3","0");
            SiteType siteType =ClientUtil.getSiteType(request);
            ProductTemplate productTemplate = spBizProductTemplateService.findProductTemplate("5", productNo,siteType);
            model.addAttribute("productTemplate", productTemplate);
            model.addAttribute("productDetail", productDetail);
            model.addAttribute("productComment", productComment);
            model.addAttribute("pd", p);
            if("1".equals(productDetail.getBasic().getIsSoldOut())){
            	 List<Product> tjproducts= sPBiz520SellService.recommendProduct(productNo);
            	 if(tjproducts!=null &&tjproducts.size()>0){
            		 model.addAttribute("tj", tjproducts);
            	 }
            }
            return PRODUCT_FREEBIEDETAIL;
        }else {
            ProductDetail productDetail = sPBiz520SellService.getProductDetail(productNo);
            ProductComment productComment = spBizProductCommentService.queryProductComment(productNo, "0", "3","0");
            SiteType siteType =ClientUtil.getSiteType(request);
            ProductTemplate productTemplate = spBizProductTemplateService.findProductTemplate("5", productNo,siteType);
            model.addAttribute("productTemplate", productTemplate);
            model.addAttribute("productDetail", productDetail);
            model.addAttribute("productComment", productComment);
            model.addAttribute("codeSt", "0");
            model.addAttribute("msg", re);
            return PRODUCT_FREEBIEDETAIL;
        }
    }
    
    /**
     * 赠送人productNo,领取人spu
     * @param productNo
     * @param spu
     * @return
     */
    private boolean isFreeSpu(String productNo, String spu) {
		 List<Product> tjproducts= sPBiz520SellService.recommendProduct(productNo);
		 if(tjproducts!=null &&tjproducts.size()>0){
			for (Product product : tjproducts) {
				if (product.getProductId().equals(spu)) {
					return true;
				}
			}
		 }
		return false;
	}
    /////买赠分享推荐商品
    /**
     * 分享详情页面
     * @param p
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> recommend(@RequestParam String p,Model model, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //1.验证参数
        Map<String,String> parameter= FreeBie520Util.decodeFreebieParam(p);
        if (parameter==null) {
            return map;
        }
        String productNo = parameter.get("spu");
        List<Product> products= sPBiz520SellService.recommendProduct(productNo);
        map.put("products", products);
        map.put("p", p);
        return map;
    }
    
    @RequestMapping(value = "/sendcode", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> login(String phoneNum, Model model, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 一、点击获取验证码时判断该手机号是否已经注册尚品网
        QuickUser quickUser = userService.checkUser(phoneNum, Constants.CREATE_NEW_USER, String.valueOf("83"));
        String userId = quickUser.getUserId();
        User user = userService.findUserByUserId(userId);
        request.getSession().setAttribute(Constants.SESSION_USER, user);
        // 新用户下发验证码和注册信息
        if (Constants.IS_NEW_USER.equals(quickUser.getIsNewUser())) {
            String msgTemplate = "验证码:{$verifyCode$}，立即输入抢好友送出的同款撞衫！该号码已注册为尚品会员，密码为手机号后6位";
            map=bizSendInfoService.sendPhoneCode(phoneNum, phoneNum, msgTemplate);
            map.put("user", "1");
        } else {
            // 二、已注册的用户点击获取验证码时需判断该手机号是否已经抢过红包
            String msgTemplate = "验证码:{$verifyCode$}，立即输入抢好友送出的同款撞衫！";
            map=bizSendInfoService.sendPhoneCode(phoneNum, phoneNum, msgTemplate);
            map.put("user", "0");
           
        }
        return map;
    }
	
}
