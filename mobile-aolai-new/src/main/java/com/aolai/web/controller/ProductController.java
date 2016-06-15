package com.aolai.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aolai.web.utils.Constants;
import com.shangpin.biz.bo.ActivityLv2;
import com.shangpin.biz.bo.Merchandise;
import com.shangpin.biz.service.ALBizProductService;

/**
 * 商品相关的controller
 * 
 * @author cuibinqiang
 * 
 */
@Controller
public class ProductController extends BaseController {

    public static Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    /**二级商品活动列表页*/
    private static final String LEVEL2 = "product/activity_lv2_list";
    /**商品详情页*/
    private static final String DETAIL = "product/product_detail";
    /**APP商品详情页*/
    private static final String DETAIL_APP = "product/product_detail_app";
    /**尺码说明页*/
    private static final String SIZESPEC = "product/size_spec";
    /**二级活动列表模板*/
    private static final String MODEL = "product/level2_model";
    
    @Autowired
    private ALBizProductService productService;
    
    /**
     * 二级活动商品列表：最新热卖/限时特卖/预售日历
     * 
     * @param map
     * @return
     */
	@RequestMapping("/activity/lv2")
    public String getLv2List(Map<String, Object> map,String activityId,@RequestParam(value = "typeFlag", defaultValue = "1", required = false) String typeFlag,@RequestParam(value = "pageType", defaultValue = "1", required = false)String pageType){
    	//调用主站返回二级活动商品列表
    	ActivityLv2 activityLv2 = productService.getActivityLv2List(activityId, typeFlag, Constants.PAGE_INDEX, Constants.PAGE_SIZE);
    	Integer haveMore ;
    	List<Merchandise> merchandiseList = activityLv2.getMerchandiseList();
    	if(merchandiseList.size()<Constants.PAGE_SIZE){
    		haveMore = 0;
    	}else{
    		haveMore = 1;
    	}
    	//活动名称
    	String activityName = activityLv2.getName();
    	//活动是否开启
    	String openFlag = activityLv2.getOpenFlag();
    	//活动开始时间
    	String startTime = activityLv2.getStartTime();
    	//活动结束时间
    	String endTime = activityLv2.getEndTime();
    	//系统时间
    	String sysTime = sysTime();
    	
    	map.put("haveMore", haveMore);
    	map.put("activityLv2", activityLv2);
    	map.put("merchandiseList", merchandiseList);
    	map.put("openFlag", openFlag);
    	map.put("startTime", startTime);
    	map.put("endTime", endTime);
    	map.put("activityName", activityName);
    	map.put("sysTime", sysTime);
    	map.put("activityId", activityId);
    	map.put("typeFlag", typeFlag);
    	map.put("pageType", pageType);//标记显示头部连接
    	
    	return LEVEL2;
    }
	
	/**
	 * 加载更多
	 * 
	 * @param map
	 * @param activityId
	 * @param typeFlag 活动入口：1
	 * @return
	 *
	 * @author cuibinqiang
	 */
	@RequestMapping("/activity/more")
	public String getMore(Map<String,Object> map,String activityId, @RequestParam(value = "typeFlag", defaultValue = "1", required = false)String typeFlag,@RequestParam(value = "pageType", defaultValue = "1", required = false)String pageType,int pageIndex){
		//调用主站返回二级活动商品列表
    	ActivityLv2 activityLv2 = productService.getActivityLv2List(activityId, typeFlag, pageIndex, Constants.PAGE_SIZE);
    	Integer haveMore ;
    	List<Merchandise> merchandiseList = activityLv2.getMerchandiseList();
    	if(merchandiseList.size()<Constants.PAGE_SIZE){
    		haveMore = 0;
    	}else{
    		haveMore = 1;
    	}
    	//活动是否开启
    	String openFlag = activityLv2.getOpenFlag();
    	
    	map.put("haveMore", haveMore);
    	map.put("activityLv2", activityLv2);
    	map.put("merchandiseList", merchandiseList);
    	map.put("pageType", pageType);
    	map.put("typeFlag", typeFlag);
    	map.put("pageIndex", pageIndex);
    	map.put("openFlag", openFlag);
    	
		return MODEL;
	}
    
    /**
     * 活动商品详情
     * 
     * @param map
     * @return
     */
    @RequestMapping("/activity/detail")
    public String detail(Map<String, Object> map,String categoryNo,String goodsId,String activityId,
    		@RequestParam(value = "pageType", defaultValue = "1", required = false)String pageType,@RequestParam(value = "typeFlag", defaultValue = "1", required = false)String typeFlag){
    	Merchandise merchandise = productService.getDetail(categoryNo, goodsId, typeFlag);
    	
    	map.put("merchandise", merchandise);
    	map.put("typeFlag", typeFlag);
    	map.put("pageType", pageType);
    	map.put("activityId", activityId);
    	
    	return DETAIL;
    }
    /**
     * 商品详情App
     * 
     * @param map
     * @return
     */
    @RequestMapping("/product/detailApp")
    public String detailApp(Map<String, Object> map,String categoryNo,String goodsId,@RequestParam(value = "typeFlag", defaultValue = "1", required = false)String typeFlag){
    	Merchandise merchandise = productService.getDetail(categoryNo, goodsId, typeFlag);
    	map.put("merchandise", merchandise);
    	return DETAIL_APP;
    }
    
    /**
     * 尺码说明
     * 
     * @author cuibinqiang
     */
    @RequestMapping("/activity/sizespec")
    public String sizeSpec(String cate){
    	StringBuilder str = new StringBuilder(SIZESPEC);
    	String SIZE = str.append("_").append(cate).toString();
    	
    	return SIZE;
    }
    
    /**
     * 获取系统时间
     * 
     * @return
     */
    public String sysTime(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String sysTime = sdf.format(new Date(System.currentTimeMillis()));
    			
    	return sysTime;
    }

}
