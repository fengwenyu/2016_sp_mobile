package com.shangpin.biz.service.abstraction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.CouponsService;
import com.shangpin.base.service.OrderService;
import com.shangpin.biz.bo.ActivityUserDb;
import com.shangpin.biz.bo.Coupon;
import com.shangpin.biz.bo.CouponCount;
import com.shangpin.biz.bo.CouponFilterProductResult;
import com.shangpin.biz.bo.CouponFilterProductResultModel;
import com.shangpin.biz.bo.PriceShowVo;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.RecProductFor;
import com.shangpin.biz.bo.Tag;
import com.shangpin.biz.bo.base.CommonObj;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultListContent;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.PicCdnHash;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizCouponService {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CouponsService couponsService;
	@Autowired
	private CommonService commonService;

	public String fromCoupons(String userId, String shopType, String couponType, String pageIndex, String pageSize) {
        String json = orderService.findCoupons(userId, pageIndex, pageSize, shopType, couponType);
        return json;
    }


	public ResultObjOne<CouponCount> beCouponsCount(String userId, String shopType, String couponType, String pageIndex,
			String pageSize) {
		String json = fromCoupons(userId, pageIndex, pageSize, shopType, couponType);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<CouponCount> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CouponCount>>() {
			});
			return result;
		}
		return null;
	}
	

	public String fromSendCoupon(String userId, String shopType, String type, String couponType) {
		String json = orderService.sendCoupon(userId, shopType, type, couponType);
		return json;
	}

	public ResultBase beSendCoupon(String userId, String shopType, String type, String couponType) {
		String json = fromSendCoupon(userId, shopType, type, couponType);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	public Coupon findCouponInfo(String batchNo) throws Exception{
	    String json = couponsService.getCouponsInfo(batchNo);
        if(!StringUtils.isEmpty(json)){
           CommonObj obj = JSONUtils.toGenericsCollection(json, CommonObj.class, Coupon.class);
           return (Coupon) obj.getContent();
        }
        return null;
    }
	public String fromSendActivation(String userid, String shoptype, String type) {
		String json = orderService.sendActivation(userid, shoptype, type);
		return json;
	}

	public ResultBase beSendActivation(String userid, String shoptype, String type) {
		String json = fromSendActivation(userid, shoptype, type);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}
	
	public List<Coupon> couponList(String userId, String orderSource, String buyId){
		String json = orderService.couponList(userId, orderSource, buyId);
		if(!StringUtils.isEmpty(json)){
			ResultListContent<Coupon> resultListContent = JsonUtil.fromJson(json, new TypeToken<ResultListContent<Coupon>>() {});
			if(Constants.SUCCESS.equals(resultListContent.getCode())){
				return resultListContent.getContent();
			}
		}
		return null;
	}
	
	public String useCoupon(String userId,String type,String totalAmount,String promoAmount,
			String ticketAmount,String carriageAmount,String discountCode,String giftCardAmount,String postArea,String buyIds,String orderSource){
		String json = couponsService.useCoupons(userId, type, totalAmount,promoAmount,ticketAmount,carriageAmount,discountCode,giftCardAmount,postArea, buyIds, orderSource);
		return json;
	}
	
	public List<PriceShowVo> useCouponObj(String userId,String type,String totalAmount,String promoAmount,String ticketAmount,String carriageAmount,String discountCode,String giftCardAmount,String postArea,String buyIds,String orderSource){
		String json = useCoupon(userId, type, totalAmount,promoAmount,ticketAmount,carriageAmount,discountCode,giftCardAmount,postArea, buyIds, orderSource);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultObjMapList<PriceShowVo> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<PriceShowVo>>() {});
		if(Constants.SUCCESS.equals(obj.getCode())){
			return obj.getList("priceShow");
		}
		return null;
	}
	
	public ResultBase checkActiveCode(String phoneNum, String activeCode){
		String json = couponsService.checkActiveCode(phoneNum, activeCode);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultBase resultBase = JsonUtil.fromJson(json, ResultBase.class);
		return resultBase;
	}
	public String checkActiveUserId(String userId){
        String json = couponsService.checkActiveUserId(userId);
        ResultObjOne<ActivityUserDb> aOne = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ActivityUserDb>>() {
        });
        if ((Constants.SUCCESS.equals(aOne.getCode())) && (!aOne.getContent().getUserCode().equals("000000"))) {
           return aOne.getContent().getUserCode();
        } 
        return null;
        
    }
	
	public ResultBase checkPhoneActivity(String phoneNum, String activityNo){
		String json = commonService.checkPhoneActivity(phoneNum, activityNo);
		if(org.springframework.util.StringUtils.isEmpty(json)){
			return null;
		}
		return JsonUtil.fromJson(json, ResultBase.class);
	}
	
	public ResultBase isCheckPhoneActivity(String phoneNum, String activityNo){
		String json = commonService.isCheckPhoneActivity(phoneNum, activityNo);
		if(org.springframework.util.StringUtils.isEmpty(json)){
			return null;
		}
		return JsonUtil.fromJson(json, ResultBase.class);
	}
	
	public RecProductFor useCouponProductList(String payAmount,String start, String end,
			String minPrice, String maxPrice, String postArea,
			String includeBrandNo, String excludeBrandNo,
			String includeCategoryNo, String excludeCategoryNo,
			String includeProductNo, String excludeProductNo, String userLv) {
		BigDecimal  amount=new BigDecimal(payAmount);
		if(new BigDecimal(0).compareTo(amount)==-1&&amount.compareTo(new BigDecimal(500))!=1){
			minPrice="400";
			maxPrice="700";
		}
		if(new BigDecimal(500).compareTo(amount)==-1&&amount.compareTo(new BigDecimal(1000))!=1){
			minPrice="700";
			maxPrice="1300";
		}
		if(new BigDecimal(1000).compareTo(amount)==-1&&amount.compareTo(new BigDecimal(2000))!=1){
			minPrice="1300";
			maxPrice="2200";
		}
		if(amount.compareTo(new BigDecimal(2000))==1){
			minPrice="2200";
			maxPrice="10000";
		}
		int pageStart=0;
		int pageEnd=0;
		if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
			return null;
		}
		if(Integer.parseInt(end)>50){
			end="50";
		}
		try {
			pageStart = (Integer.parseInt(start) - 1) * Integer.parseInt(end) + 1;
			pageEnd = pageStart + Integer.parseInt(end) - 1;
		
		} catch (Exception e) {
		}
		String json = couponsService.useCouponProductList(String.valueOf(pageStart), String.valueOf(pageEnd), minPrice, maxPrice, postArea,includeBrandNo, excludeBrandNo,includeCategoryNo,  excludeCategoryNo,includeProductNo,  excludeProductNo,userLv);
		CouponFilterProductResultModel couponFilterProductResult=JsonUtil.fromJson(json, CouponFilterProductResultModel.class);
		
		return  getCouponProductList(couponFilterProductResult,payAmount);
	}
	
	public Map<String,Object> doBaseSearchUseCouponProductList(String payAmount,String start, String end,
			String minPrice, String maxPrice, String postArea,
			String includeBrandNo, String excludeBrandNo,
			String includeCategoryNo, String excludeCategoryNo,
			String includeProductNo, String excludeProductNo,String userLv) {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			Pattern patternIndexOrSize = Pattern.compile("^[0-9]*[1-9][0-9]*$");
			Matcher isPIntIndex = patternIndexOrSize.matcher(start);
			Matcher isPIntSize = patternIndexOrSize.matcher(end);
			Pattern pattern = Pattern.compile("^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$");
			Matcher isNum = pattern.matcher(payAmount);
			if( !isPIntIndex.matches()|| !isPIntSize.matches()||!isNum.matches()) {
				map.put("code",Constants.ERROR_VERIFY_CODE);
				map.put("msg",Constants.ERROR_VERIFY_MSG);
				return map;
			}
			
			BigDecimal  amount=new BigDecimal(payAmount);
			if(new BigDecimal(0).compareTo(amount)!=1&&amount.compareTo(new BigDecimal(500))==-1){
				minPrice="400";
				maxPrice="700";
			}
			if(new BigDecimal(500).compareTo(amount)!=1&&amount.compareTo(new BigDecimal(1000))==-1){
				minPrice="700";
				maxPrice="1300";
			}
			if(new BigDecimal(1000).compareTo(amount)!=1&&amount.compareTo(new BigDecimal(2000))==-1){
				minPrice="1300";
				maxPrice="2200";
			}
			if(amount.compareTo(new BigDecimal(2000))!=-1){
				minPrice="2200";
				maxPrice="10000";
			}
			int pageStart=0;
			int pageEnd=0;
			if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
				return null;
			}
			if(Integer.parseInt(end)>50){
				end="50";
			}
		
			pageStart = (Integer.parseInt(start) - 1) * Integer.parseInt(end) + 1;
			pageEnd = pageStart + Integer.parseInt(end) - 1;
			String json = couponsService.useCouponProductList(String.valueOf(pageStart), String.valueOf(pageEnd), minPrice, maxPrice, postArea,includeBrandNo, excludeBrandNo,includeCategoryNo,  excludeCategoryNo,includeProductNo,  excludeProductNo,userLv);
			CouponFilterProductResultModel couponFilterProductResult=JsonUtil.fromJson(json, CouponFilterProductResultModel.class);
			map.put("code", Constants.SUCCESS);
			map.put("recProductFor", getCouponProductList(couponFilterProductResult,payAmount));
			return map ;
		} catch (Exception e) {
			return null;
		}
	
	}
	
	
	private RecProductFor getCouponProductList(CouponFilterProductResultModel couponFilterProductResultModel,String payAmount){
		
		if(couponFilterProductResultModel==null){
			return null;
		}
		CouponFilterProductResult couponFilterProductResult =couponFilterProductResultModel.getCouponFilterProductResultModel();
		if(couponFilterProductResult==null||couponFilterProductResult.getDocs()==null||couponFilterProductResult.getDocs().size()<=0){
			return null;
		}
		List<Product> list=new ArrayList<Product>();
		RecProductFor  recProductFor=new RecProductFor();
		
		recProductFor.setRecommendNum(couponFilterProductResult.getTotal());
		recProductFor.setSystime("");
		String titles="0";

		BigDecimal amount=new BigDecimal(payAmount);
		if(amount.compareTo(new BigDecimal(0))!=-1&&amount.compareTo(new BigDecimal(500))==-1){
			titles="50";
		}
		if(amount.compareTo(new BigDecimal(500))!=-1&&amount.compareTo(new BigDecimal(1000))==-1){
			titles="100";
		}
		if(amount.compareTo(new BigDecimal(1000))!=-1&&amount.compareTo(new BigDecimal(2000))==-1){
			titles="200";
		}
		if(amount.compareTo(new BigDecimal(2000))!=-1){
			titles="400";
		}
		recProductFor.setTitles("该优惠券可购买");
		for(int i=0;i<couponFilterProductResult.getDocs().size();i++){
			Product productDocs=couponFilterProductResult.getDocs().get(i);
			Product product=new Product();
			product.setPrefix(productDocs.getPrefix()==null?"":productDocs.getPrefix());
			product.setSuffix(productDocs.getSuffix()==null?"":productDocs.getSuffix());
			product.setProductId(productDocs.getProductNo()==null?"":productDocs.getProductNo());
			product.setBrandNameEN(productDocs.getBrandEnName()==null?"":productDocs.getBrandEnName());
			product.setBrandNameCN(productDocs.getBrandCnName()==null?"":productDocs.getBrandCnName());
			product.setProductName(productDocs.getProductName()==null?"":productDocs.getProductName());
			product.setBrandNo(productDocs.getBrandNo()==null?"":productDocs.getBrandNo());
			product.setAdvertWord("");
			product.setName(productDocs.getProductName()==null?"":productDocs.getProductName());
			product.setMarketPrice(productDocs.getMarketPrice()==null?"":productDocs.getMarketPrice());
			product.setLimitedPrice(productDocs.getLimitedPrice()==null?"":productDocs.getLimitedPrice());
			product.setGoldPrice(productDocs.getSellPrice()==null?"":productDocs.getSellPrice());
			product.setPlatinumPrice(productDocs.getPlatinumPrice()==null?"":productDocs.getPlatinumPrice());
			product.setDiamondPrice(productDocs.getDiamondPrice()==null?"":productDocs.getDiamondPrice());
			product.setIsSupportDiscount(productDocs.getIsSupportDiscount()==null?"":productDocs.getIsSupportDiscount());
			product.setLimitedVipPrice("");
			product.setPromotionPrice(productDocs.getPromotionPrice()==null?"":productDocs.getPromotionPrice());
			product.setPromotionDesc("可优惠"+titles+"元");
			product.setPromotionNotice(productDocs.getPromotionNotice()==null?"":productDocs.getPromotionNotice());
			product.setPostArea(productDocs.getMerchantType()==null?"":productDocs.getMerchantType());
			product.setPostAreaPic(productDocs.getPostAreaPic()==null?"":productDocs.getPostAreaPic());
			product.setCount(productDocs.getAvailableStock()==null?"":productDocs.getAvailableStock());
			product.setIsPromotion(productDocs.getIsPromotion()==null?"":productDocs.getIsPromotion());
			product.setComments("");
			product.setCollections("");
			String picFile=productDocs.getProductPicFile();
			if(!StringUtils.isEmpty(picFile)){
				product.setPic(PicCdnHash.getPicUrl(picFile, "1"));
			}
			
			product.setPicNo(picFile==null?"":picFile);
			product.setProductModelPicFile(productDocs.getProductModelPicFile()==null?"":productDocs.getProductModelPicFile());
			product.setCountryPic("");
			List<Tag> tag=new ArrayList<Tag>();
			product.setTag(tag);
			product.setWidth("");
			product.setHeight("");
			product.setStatus("10000");
			list.add(product);
		}
		recProductFor.setList(list);
		return recProductFor;
		
	}
	
	public String saveActivityUserDetails(ActivityUserDb activityUserDb){
	    return couponsService.saveActivityUserDetails(activityUserDb.getUserId(),activityUserDb.getName(),activityUserDb.getSex(),
	            activityUserDb.getSize(),activityUserDb.getPhone());
	}
	
    public String canUseCoupons(String userId, String pageIndex, String pageSize, String buyId, String orderSource, String couponNo, String excludeCouponNo){
    	String json = couponsService.canUseCoupons(userId, pageIndex, pageSize, buyId, orderSource, couponNo, excludeCouponNo);
    	//String json = "{\"code\": \"0\",\"msg\": \"\",\"content\": {\"count\":\"未使用的券数量\",\"list\": [{\"couponno\": \"939980387403\",\"name\": \"按商品\",\"amount\": \"200\",\"expirydate\": \"2015-05-04 00:00:00至2015-07-04 00:00:00\",\"rule\": \"按商品30001258\",\"statuscode\": \"0\",\"status\": \"未使用\",\"usedate\": \"\",\"orderid\": \"\",\"type\": \"1\",\"isSelected\":\"1\"}]}}";
    	return json;
    }
    
    public CouponCount canUseCouponsObj(String userId, String pageIndex, String pageSize, String buyId, String orderSource, String couponNo, String excludeCouponNo){
    	String json = canUseCoupons(userId, pageIndex, pageSize, buyId, orderSource, couponNo, excludeCouponNo);
    	if(StringUtils.isEmpty(json)){
    		return null;
    	}
    	ResultObjOne<CouponCount> resultObjOne = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CouponCount>>(){});
    	if(null != resultObjOne && Constants.SUCCESS.equals(resultObjOne.getCode()) && null != resultObjOne.getContent()){
    		return resultObjOne.getContent();
    	}
    	return null;
    }
}
