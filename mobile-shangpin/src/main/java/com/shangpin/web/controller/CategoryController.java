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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.CategoryItem;
import com.shangpin.biz.bo.CategoryOperationItem;
import com.shangpin.biz.bo.CustomBrandItem;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.SearchCategory;
import com.shangpin.biz.bo.SearchConditions;
import com.shangpin.biz.bo.SearchResult;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizBrandService;
import com.shangpin.biz.service.SPBizCategoryService;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.SearchUtil;

/**
 * @ClassName: CategoryController
 * @Description:品类相关控制层
 * @author qinyingchun
 * @date 2014年11月4日
 * @version 1.0
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private static Map<String, Object> searchFacet = new HashMap<String, Object>();
    private static String defaultCategoryNo;
    private static String defaultCategoryName;
    private static final String CATEGORY_PRODUCT_LIST = "category/category_product_list";
    private static final String NEWINDEX_CATEGORY = "category/index";
    private static final String NAVIGATION = "category/navigation";
    private static final String OPERATION = "category/operation";
    private static final String NEWINDEX = "category/new_index";
    private static final String BRAND = "category/brand";
    @Autowired
    private SPBizCategoryService bizCategoryService;
    @Autowired
    SPBizBrandService spBizBrandService;
    
    
    
    /***
     * 分类初始化页
     * @param model
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request,String categoryId) {
        CategoryItem categoryItem = bizCategoryService.queryCategory("0","1");
        CategoryOperationItem categoryOperationItem =null;
        if (StringUtils.isEmpty(categoryId) && categoryItem!=null && categoryItem.getCategoryList().size() !=0) {
            categoryId = categoryItem.getCategoryList().get(0).getId();
        }
        User user = getSessionUser(request);
        String userId = user != null ? user.getUserid() : "";
        categoryOperationItem =bizCategoryService.queryCategoryOperation(categoryId,userId);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("navigation", categoryItem);
        model.addAttribute("categoryOperationItem", categoryOperationItem);
        return NEWINDEX_CATEGORY;
    }
    
    /***
     * 根据导航获得数据
     * @param model
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/operations", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> operations(Model model, HttpServletRequest request, String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = getSessionUser(request);
        String userId = user != null ? user.getUserid() : "";
        CategoryOperationItem categoryOperationItem =bizCategoryService.queryCategoryOperation(id, userId);
        map.put("categoryItem", categoryOperationItem);
        return map;
    }
    
    
    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    public String product(String categoryNo, String postArea, String categoryName, @RequestParam(value = "gender", defaultValue = Constants.WOMAN, required = false) String gender,Model model, HttpServletRequest request) {
        logger.info("postArea======================{}", postArea);
        try {
            if(StringUtils.isEmpty(postArea)){
                postArea = "0";
            }
            SearchConditions searchConditions = new SearchConditions();
            searchConditions.setGender(gender);
            String userLv = this.getSessionUser(request) == null ? null : this.getSessionUser(request).getLv();
            searchConditions.setUserLv(userLv);
            searchConditions.setCategoryNo(categoryNo==null?"":categoryNo);
            String start = Constants.PRODUCT_LIST_START;
            searchConditions.setStart(start);
            searchConditions.setNum(Constants.PRODUCT_LIST_END);
            searchConditions.setPostArea(postArea);
            logger.info("biz method start============================");
            SearchResult searchResult = bizCategoryService.searchCategoryProduct(searchConditions);
            logger.info("biz method end============================");
            List<SearchCategory> categoryList = searchResult.getCategoryList();
            if(categoryList!=null&&categoryList.size()>0){
                defaultCategoryNo = categoryList.get(0).getId();
                defaultCategoryName = categoryList.get(0).getName();
            }
            searchFacet.put("color", searchResult.getColorList());
            searchFacet.put("size", searchResult.getSizeList());
            searchFacet.put("brand", searchResult.getBrandList());
            searchFacet.put("category", searchResult.getCategoryList());
            //判断商品显示的价格
            List<Product> products = searchResult.getProductList();
            for(Product product : products){
                if("0002".equals(userLv)){
                    product.setStrongPrice(Integer.parseInt(product.getGoldPrice()));
                }else if("0003".equals(userLv)){
                    product.setStrongPrice(Integer.parseInt(product.getPlatinumPrice()));
                }else if("0004".equals(userLv)){
                    product.setStrongPrice(Integer.parseInt(product.getDiamondPrice()));
                }else {
                    product.setStrongPrice(Integer.parseInt(product.getLimitedPrice()));
                }
                product.setDelPrice(Integer.parseInt(product.getMarketPrice()));
            }
            model.addAttribute("searchResult", searchResult);
            model.addAttribute(
                    "hasMore",
                    SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()),
                            Integer.parseInt(Constants.PRODUCT_LIST_END)));
            model.addAttribute("totalPage", SearchUtil.totalPage(Integer.parseInt(searchResult.getCount()), Integer.parseInt(Constants.PRODUCT_LIST_END)));
            model.addAttribute("start", start);
            model.addAttribute("searchFacet", searchFacet);
            model.addAttribute("userLv", userLv);
            model.addAttribute("categoryNo", categoryNo);
            model.addAttribute("categoryName",categoryName);
            model.addAttribute("gender",gender);
            logger.info("set postArea start===================={}", postArea);
            model.addAttribute("postArea",postArea);
            logger.info("set postArea end===================={}", postArea);
            if(searchResult.getParentCategory() != null){
                model.addAttribute("parentCategoryNo", searchResult.getParentCategory().getId());
                model.addAttribute("parentCategoryName",searchResult.getParentCategory().getName());
            }
            model.addAttribute("defaultCategoryNo", defaultCategoryNo);
            model.addAttribute("defaultCategoryName", defaultCategoryName);
            model.addAttribute("searchConditions", searchConditions);
            return CATEGORY_PRODUCT_LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return CATEGORY_PRODUCT_LIST;
        }
    }

    @RequestMapping(value = "/product/list", method = RequestMethod.POST)
    public String product(SearchConditions searchConditions, String parentCategoryNo,String parentCategoryName, Model model,
            HttpServletRequest request) {
        //SearchUtil.SystemConditions(searchConditions);
        String userLv = this.getSessionUser(request) == null ? null : this.getSessionUser(request).getLv();
        logger.debug("user level is :" + userLv);
        searchConditions.setUserLv(userLv);
        searchConditions.setNum(Constants.PRODUCT_LIST_END);
        SearchResult searchResult = bizCategoryService.searchCategoryProduct(searchConditions);
        //判断商品显示的价格
        List<Product> products = searchResult.getProductList();
        if(null != products){
            for(Product product : products){
                if("0002".equals(userLv)){
                    product.setStrongPrice(Integer.parseInt(product.getGoldPrice()));
                }else if("0003".equals(userLv)){
                    product.setStrongPrice(Integer.parseInt(product.getPlatinumPrice()));
                }else if("0004".equals(userLv)){
                    product.setStrongPrice(Integer.parseInt(product.getDiamondPrice()));
                }else {
                    product.setStrongPrice(Integer.parseInt(product.getLimitedPrice()));
                }
                product.setDelPrice(Integer.parseInt(product.getMarketPrice()));
            }
        }
        //List<Category> categoryList = searchResult.getCategoryList();
        model.addAttribute("searchResult", searchResult);
        model.addAttribute(
                "hasMore",
                SearchUtil.hasMore(Integer.parseInt(searchResult.getCount()),
                        Integer.parseInt(Constants.PRODUCT_LIST_END)));
        model.addAttribute("totalPage", SearchUtil.totalPage(Integer.parseInt(searchResult.getCount()), Integer.parseInt(Constants.PRODUCT_LIST_END)));
        model.addAttribute("start", searchConditions.getStart());
        model.addAttribute("searchFacet", searchFacet);
        model.addAttribute("searchConditions", searchConditions);
        model.addAttribute("categoryNo", searchConditions.getCategoryNo());
        model.addAttribute("categoryName",searchConditions.getCategoryName());
        model.addAttribute("userLv", userLv);
        model.addAttribute("gender", searchConditions.getGender());
        model.addAttribute("parentCategoryNo", parentCategoryNo);
        model.addAttribute("parentCategoryName", parentCategoryName);
        model.addAttribute("queryConditions", SearchUtil.initQueryConditions(searchConditions));
        model.addAttribute("defaultCategoryNo",defaultCategoryNo);
        model.addAttribute("defaultCategoryName",defaultCategoryName);
        if("5".equals(searchConditions.getOrder())){
            model.addAttribute("isHot", "0");
        }else {
            model.addAttribute("isHot", "1");
        }
        return CATEGORY_PRODUCT_LIST;
    }

    @RequestMapping(value = "/product/list/more", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getMore(SearchConditions searchConditions, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = getSessionUser(request);
        String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
        logger.debug("user level is :" + userLv);
        searchConditions.setUserLv(userLv);
        searchConditions.setNum(Constants.PRODUCT_LIST_END);
        SearchResult searchResult = bizCategoryService.searchCategoryProduct(searchConditions);
        int totalPage = SearchUtil.totalPage(Integer.parseInt(searchResult.getCount()),
                Integer.parseInt(Constants.PRODUCT_LIST_END));
        if (totalPage > searchConditions.getPageNo()) {
            map.put("hasMore", 1);
        } else {
            map.put("hasMore", 0);
        }
        map.put("searchResult", searchResult);
        map.put("userLv", userLv);
        return map;
    }

    /**
     * 
     * @Title: index
     * @Description: 新版品类首页
     * @param
     * @return String
     * @throws
     * @Create By liling
     * @Create Date 2015年4月20日
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return NEWINDEX;
    }
    /**
     * 
     * @Title: navigation
     * @Description: 新版一级品类品类
     * @param
     * @return String
     * @throws
     * @Create By liling
     * @Create Date 2015年4月20日
     */
    @RequestMapping(value = "/navigation", method = RequestMethod.GET)
    public String navigation(Model model) {
        CategoryItem categoryItem =bizCategoryService.queryCategory(Constants.QUERY_CATEGORY_INDEX_PARENT_ID, Constants.QUERY_CATEGORY_INDEX_TYPE);
        model.addAttribute("categoryItem", categoryItem);
        return NAVIGATION;

    }
    /**
     * 
     * @Title: navigation
     * @Description: 新版一级品类品类
     * @param
     * @return String
     * @throws
     * @Create By liling
     * @Create Date 2015年4月20日
     */
    @RequestMapping(value = "/operation", method = RequestMethod.GET)
    public String operation(Model model, HttpServletRequest request,String id) {
        User user = getSessionUser(request);
        String userId = user != null ? user.getUserid() : "";
        CategoryOperationItem categoryOperationItem =bizCategoryService.queryCategoryOperation(id, userId);
        model.addAttribute("categoryOperationItem", categoryOperationItem);
        return OPERATION;

    }
    /**
     * 
     * @Title: brand
     * @Description: 新版一级品类品类
     * @param
     * @return String
     * @throws
     * @Create By liling
     * @Create Date 2015年4月20日
     */
    @RequestMapping(value = "/brand", method = RequestMethod.GET)
    public String brand(Model model, HttpServletRequest request) {
        User user = getSessionUser(request);
        String userId = user != null ? user.getUserid() : "";
//      Cookie cookie =CookieUtil.getCookieByName(request, Constants.UVID_COOKIE_NAME);
//      String vuId= StringUtils.isEmpty(cookie)?null:cookie.getValue();
//      if(!StringUtil.isEmpty(vuId)){
//          vuId=vuId.split("[|]")[0];
//      }
        CustomBrandItem customBrandItem=spBizBrandService.queryCustomBrand(userId, null, Constants.CATEGORY_CUSTOM_BRAND_NUM);
        model.addAttribute("customBrandItem", customBrandItem);
    
        return BRAND;

    }
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public SearchResult getConditions(SearchConditions searchConditions, HttpServletRequest request){
        //SearchUtil.SystemConditions(searchConditions);
        SearchResult searchResult = bizCategoryService.getCategoryChildList(searchConditions);
        return searchResult;
    }
}
