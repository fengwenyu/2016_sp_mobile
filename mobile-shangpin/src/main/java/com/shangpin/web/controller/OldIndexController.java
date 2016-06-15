/**
 * <pre>
 * Copyright:		Copyright(C) 2010-2014, shangpin.com
 * Filename:		com.shangpin.controller.UserController.java
 * Class:			UserController
 * Date:			2014-07-11
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.0
 * Description:		
 *
 * </pre>
 **/

package com.shangpin.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shangpin.base.utils.BrandUtil;
import com.shangpin.biz.bo.Category;
import com.shangpin.biz.bo.CategoryList;
import com.shangpin.biz.bo.Gallery;
import com.shangpin.biz.bo.GalleryList;
import com.shangpin.biz.bo.GalleryResult;
import com.shangpin.biz.bo.IndexBrands;
import com.shangpin.biz.bo.IndexHotBrands;
import com.shangpin.biz.bo.Promotion;
import com.shangpin.biz.bo.Promotions;
import com.shangpin.biz.service.SPBizIndexService;
import com.shangpin.web.utils.Constants;

/**
 * 用户管理的controller
 * 
 * @author sunweiwei
 */
@Controller
@RequestMapping("/oldindex")
public class OldIndexController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(OldIndexController.class);
    
    private static final String INDEX = "index/index";
    private static final String FOCUS = "index/focus";
    private static final String CATEGORY = "index/category";
    private static final String HOT = "index/hot";
    private static final String PROMOTION = "index/special";
    
    @Autowired
    private SPBizIndexService indexService;

    /**
     * 加载首页时执行,直接跳转到首页面
     * 
     * @author sunweiwei
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
    	List<Gallery> galleryList = gallerys.getGallery();
    	List<GalleryResult> galleryResultList=new ArrayList<GalleryResult>();
    	//针对尚品m站页面展示处理，so,写在此处
		if(galleryList!=null){
			for(int i=0;i<galleryList.size();i++){
				Gallery gallery=galleryList.get(i);
				GalleryResult galleryResult=new GalleryResult();
				galleryResult.setName(gallery.getName());
				String refContent=gallery.getRefContent();
				if(gallery.getType().equals("3")){
					String[] brandContent=refContent.split("[|]");
					String brandId=brandContent[0];
					String brandEN=brandContent[1];
					if(BrandUtil.isFlagship(brandEN)){
						galleryResult.setUrl(BrandUtil.getFlagShipUrl(brandEN));
						galleryResult.setType("5");
						galleryResultList.add(galleryResult);
						continue;
					}
					String url=Constants.BRANDURL+"?brandNo="+brandId+"&brandName="+brandEN;
					galleryResult.setUrl(url);
					galleryResult.setType("1");
				}
				if(gallery.getType().equals("5")){
					galleryResult.setUrl(refContent);
					galleryResult.setType("5");
				}
				
				if(gallery.getType().equals("1")){
					String url=Constants.SUBJECTURL+"?topicId="+refContent;
					galleryResult.setUrl(url);
					galleryResult.setType("1");
				}
				galleryResult.setPic(gallery.getPic());
				galleryResultList.add(galleryResult);
			}
		}
    	
    	model.addAttribute("galleries", galleryResultList);
    	logger.debug("IndexController focus end!");
    	return FOCUS;
    }
    
    /** 
    * @Title: category 
    * @Description: 商城首页品类列表
    * @param @param model
    * @param @return
    * @return String
    * @throws 
    * @Create By qinyingchun
    * @Create Date 2014年10月22日
    */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String category(Model model){
    	logger.debug("IndexController category start!");
    	List<Integer> groups = new ArrayList<Integer>();
    	CategoryList list = indexService.categoryList();
    	if(null == list){
    		return CATEGORY;
    	}
    	List<Category> categories = list.getCategoryList();
    	String categoryImage = list.getCategoryImage();
    	int size = categories.size();
    	int group = size % 3 == 0 ? size / 3 : size / 3 + 1;
    	for(int i = 0; i < group; i++){
    		groups.add(i);
    	}
    	model.addAttribute("categories", categories);
    	model.addAttribute("groups", groups);
    	model.addAttribute("categoryImage", categoryImage);
    	logger.debug("IndexController category end!");
    	return CATEGORY;
    }
    
    /**
     * 
    * @Title: hot 
    * @Description:商城首页品品牌列表
    * @param @param model
    * @param @return
    * @return String
    * @throws 
    * @Create By qinyingchun
    * @Create Date 2014年10月23日
     */
    @RequestMapping(value = "/hot", method = RequestMethod.GET)
    public String hot(Model model){
    	logger.debug("IndexController hot start!");
    	IndexHotBrands brandList = indexService.brandLists(Constants.INDEX_BRAND_START, Constants.INDEX_BRAND_END);
    	if(null == brandList){
    		return HOT;
    	}
    	IndexBrands brands = brandList.getBrandList().get(0);
    	model.addAttribute("brands", brands);
    	logger.debug("IndexController hot end!");
    	return HOT;
    }
    
    /**
     * 
    * @Title: promotion 
    * @Description:商城首页品推广位
    * @param 
    * @param @return
    * @return String
    * @throws 
    * @Create By qinyingchun
    * @Create Date 2014年10月23日
     */
    @RequestMapping(value = "/promotion", method = RequestMethod.GET)
    public String promotion(Model model){
    	logger.debug("IndexController promotion start!");
    	Promotions promotion = indexService.promotions();
    	if(null == promotion){
    		return PROMOTION;
    	}
    	List<Promotion> promotions = promotion.getPromotion();
    	model.addAttribute("promotions", promotions);
    	logger.debug("IndexController promotion end!");
    	return PROMOTION;
    }
    

}
