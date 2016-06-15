package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.ActivityHead;
import com.shangpin.biz.bo.ActivityIndex;
import com.shangpin.biz.bo.ActivityIndexNew;
import com.shangpin.biz.bo.BrandActivityCoupon;
import com.shangpin.biz.bo.BrandActivityHead;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.HeadInfo;
import com.shangpin.biz.bo.OperatHeader;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.ProductAtt;
import com.shangpin.biz.bo.SearchParam;
import com.shangpin.biz.bo.SearchResult;
import com.shangpin.biz.bo.SearchResultApp;
import com.shangpin.biz.service.ASPBizActivityService;
import com.shangpin.biz.service.ASPBizBrandActivityService;
import com.shangpin.biz.service.ASPBizSerchService;
import com.shangpin.biz.service.ASPBizUserService;
import com.shangpin.biz.service.abstraction.AbstractBizActivityService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.HtmlUtil;
import com.shangpin.biz.utils.PicCdnHash;
import com.shangpin.biz.utils.SearchParamUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.JsonUtil;

@Service
public class ASPBizActivityServiceImpl  extends AbstractBizActivityService implements ASPBizActivityService {

    private static final Logger logger = LoggerFactory.getLogger(ASPBizActivityServiceImpl.class);

    @Autowired
    ASPBizBrandActivityService aspBizBrandActivityService;
    @Autowired
    ASPBizSerchService aspBizSerchService;
    
    @Autowired
    ASPBizUserService  aSPBizUserService;

    @Autowired
    ShangPinService  shangPinService;
    
    
    @Override
    public String queryActivityIndex(String userid,String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
            String order, String gender, String userLv, String postArea, String imei) throws Exception {
        ActivityIndex activityIndex=new ActivityIndex();
        activityIndex.setSysTime(String.valueOf(System.currentTimeMillis()));
        OperatHeader operatHeader=new OperatHeader();
        BrandActivityHead brandActivityHead=new BrandActivityHead();
        brandActivityHead=aspBizBrandActivityService.headInfoObj(userid, subjectNo, "1");
        operatHeader.setHead(brandActivityHead.getHead());
        operatHeader.setActivity(brandActivityHead.getActivity());
        operatHeader.setCoupon(aspBizBrandActivityService.couponInfoObj(userid, subjectNo, "1"));
        activityIndex.setOperat(operatHeader);
        String isPre=isPre(brandActivityHead.getActivity().getDatepreTime(),brandActivityHead.getActivity().getStartTime());
        brandActivityHead.getActivity().setIsPre(isPre);
        SearchResult searchResult=aspBizSerchService.queryActivityProductList(subjectNo, start, end, tagId, brandId, price, colorId, size, categoyId, order, gender, userLv, postArea,isPre,brandActivityHead.getActivity(), imei);
        activityIndex.setResult(searchResult);
        String json;
        try {
            json = ApiBizData.spliceData(activityIndex, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
            logger.debug("调用api接口返回数据:" + json);
            return json;
        } catch (Exception e) {
            logger.error("调用api接口返回数据发生错误！");
            e.printStackTrace();
        }
        return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
    }
    @Override
    public ActivityIndex searchActivityIndex(String userId,String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
            String order, String gender, String userLv, String postArea, String imei) throws Exception {
        ActivityIndex activityIndex=getSearchActivityIndexWithoutSearch(userId, subjectNo);
        ActivityHead activity = activityIndex.getOperat().getActivity();
        SearchResult searchResult=aspBizSerchService.queryActivityProductList(subjectNo, start, end, tagId, brandId, price, colorId, size, categoyId, order, gender, userLv, postArea,activity.getIsPre(),activity, imei);
        activityIndex.setResult(searchResult);
        return activityIndex;
    }
	@Override
	public SearchResult searchActivityProduct(String subjectNO, String start, String end, String tagId, String brandNo, String price, String color, String size, String categoryNo, String order, String gender, String userLv, String postArea, String imei,ActivityHead activityHead) {
//		 SearchResult searchResult=aspBizSerchService.queryActivityProductList(subjectNO, start, end, tagId, brandNo, price, color, size, categoryNo, order, gender, userLv, postArea,null,null, imei);
		 SearchResult searchResult=aspBizSerchService.queryActivityProductList(subjectNO, start, end, tagId, brandNo, price, color, size, categoryNo, order, gender, userLv, postArea,activityHead.getIsPre(),activityHead, imei);
		return searchResult;
	}
	
	public ActivityIndex getSearchActivityIndexWithoutSearch(String userId,String subjectNo){
		ActivityIndex activityIndex=new ActivityIndex();
		activityIndex.setSysTime(String.valueOf(System.currentTimeMillis()));
		OperatHeader operatHeader=new OperatHeader();
		BrandActivityHead brandActivityHead = getBrandActivityHead(userId, subjectNo);
		operatHeader.setHead(brandActivityHead.getHead());
		operatHeader.setActivity(brandActivityHead.getActivity());
		operatHeader.setCoupon(aspBizBrandActivityService.couponInfoObj(userId, subjectNo, "1"));
		activityIndex.setOperat(operatHeader);
		return activityIndex;
	}
    /**
     * 是否预热
     * 
     * @param preTime 预热时间
     * @param startTime 活动开始时间
     * @return
     */
    public String isPre(String preTime, String startTime) {
        String isPre = "0";
        if(preTime!=null&&!"".equals(preTime)&&startTime!=null&&!"".equals(startTime)){
            Long pre = Long.valueOf(preTime);
            Long start = Long.valueOf(startTime);
            Long now = System.currentTimeMillis();
            if (now > pre && now < start) {
                isPre = "1";
            }
        }       
        return isPre;
    }
    @Override
    public String queryActivityIndexNew(String userid, String subjectNo, String start, String end, String tagId, String order, String userLv, String filters, String imei) throws Exception {
        SearchParam searchParam=SearchParamUtil.parse(filters,"1");
        ActivityIndexNew activityIndexNew=new ActivityIndexNew();
        activityIndexNew.setSysTime(String.valueOf(System.currentTimeMillis()));
        OperatHeader operatHeader=new OperatHeader();
        BrandActivityHead brandActivityHead=new BrandActivityHead();
        brandActivityHead=aspBizBrandActivityService.headInfoObj(userid, subjectNo, "1");
        operatHeader.setHead(brandActivityHead.getHead());
        operatHeader.setActivity(brandActivityHead.getActivity());
        operatHeader.setCoupon(aspBizBrandActivityService.couponInfoObj(userid, subjectNo, "1"));
        activityIndexNew.setOperat(operatHeader);
        String isPre=isPre(brandActivityHead.getActivity().getDatepreTime(),brandActivityHead.getActivity().getStartTime());
        brandActivityHead.getActivity().setIsPre(isPre);
        SearchResultApp searchResultApp=aspBizSerchService.queryActivityProductListNew(subjectNo, start, end, tagId, searchParam.getBrandId(), searchParam.getPriceId(), searchParam.getColorId(), searchParam.getSizeId(), searchParam.getCategoryId(), order,  userLv, searchParam.getPostArea(),isPre,brandActivityHead.getActivity(), imei);
        activityIndexNew.setResult(searchResultApp);
        String json;
        try {
            json = ApiBizData.spliceData(activityIndexNew, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
            logger.debug("调用api接口返回数据:" + json);
            return json;
        } catch (Exception e) {
            logger.error("调用api接口返回数据发生错误！");
            e.printStackTrace();
        }
        return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
    }
	@Override
	public String queryActivityIndexNew(String userid, String subjectNo, String start, String end, String tagId, String order, String userLv, String filters,
			String originalFilters, String dynamicFilters, String imei, String version) throws Exception {
		SearchParam searchParam= new SearchParam();
		if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.7", version) == 1) {
			searchParam=SearchParamUtil.parse(originalFilters, dynamicFilters, "1");
		} else {
			searchParam=SearchParamUtil.parse(filters,"1");
		}
		
        ActivityIndexNew activityIndexNew=new ActivityIndexNew();
        activityIndexNew.setSysTime(String.valueOf(System.currentTimeMillis()));
        OperatHeader operatHeader=new OperatHeader();
        ActivityHead actHead = new ActivityHead();
        HeadInfo headInfo = new HeadInfo();
		List<BrandActivityCoupon> coupon = new ArrayList<BrandActivityCoupon>();
        BrandActivityHead brandActivityHead=new BrandActivityHead();
        brandActivityHead=aspBizBrandActivityService.headInfoObj(userid, subjectNo, "1");
        operatHeader.setActivity(actHead);
		operatHeader.setHead(headInfo);
		operatHeader.setCoupon(coupon);
		String isPre = "0";
		if (brandActivityHead != null) {
			actHead = brandActivityHead.getActivity();
			headInfo = brandActivityHead.getHead();
			if (actHead != null) {
				operatHeader.setActivity(actHead);
				String reTime = actHead.getDatepreTime();
				String startTime = actHead.getStartTime();
				if (StringUtil.isNotEmpty(reTime, startTime)) {
					isPre = isPre(reTime, startTime);
					brandActivityHead.getActivity().setIsPre(isPre);
				}
				//520
		        activityIndexNew.setTitle(brandActivityHead.getHead().getName());//活动名称
		        activityIndexNew.setPicremind("http://pic6.shangpin.com/group1/M00/A4/F4/rBQKaVcfHTWAdFvxAAAGAVWc0Vg499.png");//提醒图标
		        // 是否提醒 根据当前系统时间 判定是否在活动开始和结束时间段范围之内
		        
		        Long systemtime=new Date().getTime();//系统时间
		        Long starttime=Long.parseLong(brandActivityHead.getActivity().getStartTime());//活动开始时间
		        if(systemtime<starttime){
		        	activityIndexNew.setEnableRemind(true);
		        }else{
		        	activityIndexNew.setEnableRemind(false);
		        }
		     
		        headInfo.setPageView(brandActivityHead.getHead().getPageView());//浏览量
		        
		        //wap 
		        activityIndexNew.setWaptitle(brandActivityHead.getHead().getName());
		        String domain=aSPBizUserService.getShangpinDomain(); 
		        
		        String actityId=brandActivityHead.getHead().getId();
		        activityIndexNew.setRemindUrl(domain+"acivity/tip?actityId="+actityId);
			}
			if (headInfo != null) {
				operatHeader.setHead(headInfo);
			}
		}
		coupon = aspBizBrandActivityService.couponInfoObj(userid, subjectNo, "1");
		if (coupon != null) {
			operatHeader.setCoupon(coupon);
		}
        activityIndexNew.setOperat(operatHeader);
       
        SearchResultApp searchResultApp=aspBizSerchService.queryActivityProductListNew(subjectNo, start, end, tagId, searchParam.getBrandId(), searchParam.getPriceId(), searchParam.getColorId(), searchParam.getSizeId(), searchParam.getCategoryId(), order,  userLv, searchParam.getPostArea(),isPre,brandActivityHead.getActivity(), originalFilters, dynamicFilters, imei, version);
        activityIndexNew.setResult(searchResultApp);
        String json;
        try {
            json = ApiBizData.spliceData(activityIndexNew, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
            logger.debug("调用api接口返回数据:" + json);
            return json;
        } catch (Exception e) {
            logger.error("调用api接口返回数据发生错误！");
            e.printStackTrace();
        }
        return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}
	
	public BrandActivityHead getBrandActivityHead(String userId,String subjectNo){
	    BrandActivityHead brandActivityHead=new BrandActivityHead();
        brandActivityHead=aspBizBrandActivityService.headInfoObj(userId, subjectNo, "1");
        String isPre=isPre(brandActivityHead.getActivity().getDatepreTime(),brandActivityHead.getActivity().getStartTime());
        brandActivityHead.getActivity().setIsPre(isPre);
        //logger.info("BrandActivityHead"+JsonUtil.toJson(brandActivityHead));
        return brandActivityHead;
	}
	
	@Override
	public Map<String ,Object> getSubjectFloorInfo(String topicId,ActivityHead activityHead) {
		String data = aspBizBrandActivityService.subjectFloorInfo(topicId);
		if(StringUtils.isNotBlank(data)){
			ResultObjOne objOne = JsonUtil.fromJson(data, ResultObjOne.class);
			Map<String ,Object> map = new HashMap<>();
			if(objOne==null || !"0".equals(objOne.getCode())){
				return map;
			}
			String content = JsonUtil.toJson(objOne.getContent());
			JSONObject obj = JSONObject.fromObject(content);
			String isFloor = obj.getString("isFloor");
			boolean flag=false;
			if(isFloor==null||!"1".equals(isFloor)){
				map.put("isFloor", "0");//0不是楼层
				flag=true;
			}else{
				map.put("isFloor", "1");//1 是楼层
			}
			JSONObject headObj = obj.getJSONObject("headPic");
			String headPichtml = headObj.getString("html");
			if(StringUtils.isNotBlank(headPichtml)){
				//headPichtml = HtmlUtil.modifyHref(headPichtml, "1"); 
				Map<String ,String> headPic = new HashMap<>();
				headPic.put("html",headPichtml);
				headPic.put("css",headObj.getString("css"));
				headPic.put("js",headObj.getString("js"));
				map.put("headPic", headPic);
			}
			if(flag){
				return map;
			}
			JSONArray topicFloors = obj.getJSONArray("topicFloors");
			List<Map<String, Object>> floorList = new ArrayList<>();
			for (int i = 0; i < topicFloors.size(); i++) {
				Map<String, Object> floorMap = new HashMap<>();
				JSONObject floorObj = (JSONObject) topicFloors.get(i);
				String title = floorObj.getString("title");
				String pic = floorObj.getString("pic");
				if(StringUtils.isBlank(pic)){
					String html = floorObj.getString("html");
					String css = floorObj.getString("css");
					String js = floorObj.getString("js");
					floorMap.put("html", html);
					floorMap.put("css", css);
					floorMap.put("js", js);
				}else{
					//floorMap.put("pic", PicCdnHash.getPicUrl(pic.trim(), "2"));//品牌图片 主站给的是图片编号
					floorMap.put("pic", pic);
				}
				floorMap.put("title", title);
				
				String prodateStr = floorObj.getString("productList");
				List<ProductAtt> productAttrs = JsonUtil.fromJson(prodateStr, new TypeToken<List<ProductAtt>>(){}.getType());
				Map<String, String> productAttMap = new HashMap<>();
				List<String> spus = new ArrayList<>();
				for (ProductAtt productAtt : productAttrs) {
					if(productAttMap.get(productAtt.getSpu())==null){//多sku只保留一个spu
						spus.add(productAtt.getSpu().trim());
						productAttMap.put(productAtt.getSpu(),productAtt.getOrder());
					}
				}
				//调取搜索
				List<Product> productList = aspBizSerchService.getProductListFromProductNo(spus, productAttMap,activityHead);
				floorMap.put("productList", productList);
				floorList.add(floorMap);
			}
			map.put("floorList", floorList);
			return map;
		}
		return null;
	}
	 @Override
	    public  Map<String, Object> activityStartRemind(String phoneNum, String actityId, String time) {
	     return aActivityStartRemind(phoneNum,actityId,time);
	    }
}



