package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shangpin.base.service.CouponsService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.utils.HttpClientUtil;
@Service
public class CouponsServiceImpl implements CouponsService {

	//使用优惠券，折扣码
	private StringBuilder useCouponsRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/useCoupons");
	//提交订单页选择优惠券列表
	private StringBuilder canUseCouponsRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/canUseCoupons");
	//判断手机号是否领取过优惠券
	private StringBuilder checkActiveCodeRUL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("checkActiveCode");

	//支付成功后，赠送优惠券
	private StringBuilder giveCouponsRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/giveCoupons");
	private StringBuilder useCouponProductListRUL= new StringBuilder(GlobalConstants.BASE_URL_SEARCH).append("shangpin/CouponFilterProduct");
    // 验证V码是否与活动匹配并且该V码是否和账户绑定
    private StringBuilder getGetTicketInfoByBatchNoURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("VCode/GetTicketInfoByBatchNo");
    //验证用户是否参与过领取NBD鞋活动
    private StringBuilder checkActiveUserIdRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/checkActiveUserId");
	//NBD鞋活动信息保存接口
    private StringBuilder saveActivityUserDetailRUL =new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/saveActiveUserInfo");
    @Override
	public String useCoupons(String userId,String type,String totalAmount,String promoAmount,
			String ticketAmount,String carriageAmount,String discountCode,String giftCardAmount,String postArea,String buyIds,String orderSource) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("type", type);
		params.put("totalAmount", totalAmount);
		params.put("promoAmount", promoAmount);
		params.put("ticketAmount", ticketAmount);
		params.put("carriageAmount", carriageAmount);
		params.put("discountCode", discountCode);
		params.put("giftCardAmount", giftCardAmount);
		params.put("postArea", postArea);
		params.put("buyIds", buyIds);
		params.put("orderSource", orderSource);
		return HttpClientUtil.doGet(useCouponsRUL.toString(), params);
	}

	@Override
	public String checkActiveCode(String phoneNum, String couponCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phone", phoneNum);
		params.put("activeCode", couponCode);
		return HttpClientUtil.doGet(checkActiveCodeRUL.toString(), params);
	}

	@Override
	public String giveCoupons(String userId, String orderId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("orderId", orderId);
		return HttpClientUtil.doGet(giveCouponsRUL.toString(), params);
	}
	

@Override
	public String useCouponProductList(String start, String end,
			String minPrice, String maxPrice, String postArea,
			String includeBrandNo, String excludeBrandNo,
			String includeCategoryNo, String excludeCategoryNo,
			String includeProductNo, String excludeProductNo,String userLv) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("start", start);
		params.put("end", end);
		params.put("minPrice", minPrice);
		params.put("maxPrice", maxPrice);
		params.put("includeBrandNo", includeBrandNo);
		params.put("excludeBrandNo", excludeBrandNo);
		params.put("includeCategoryNo", includeCategoryNo);
		params.put("excludeCategoryNo", excludeCategoryNo);
		params.put("includeProductNo", includeProductNo);
		params.put("excludeProductNo", excludeProductNo);
		params.put("postArea", postArea);
		params.put("userLv", userLv);
		return HttpClientUtil.doGet(useCouponProductListRUL.toString(), params);
	}
 @Override
   public String getCouponsInfo(String batchNo) {
       Map<String, String> params = new HashMap<String, String>();
       params.put("batchNo", batchNo); 
       return HttpClientUtil.doGet(getGetTicketInfoByBatchNoURL.toString(), params);
   }

    @Override
    public String checkActiveUserId(String userId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        return HttpClientUtil.doGet(checkActiveUserIdRUL.toString(), params);
    }

    @Override
    public String saveActivityUserDetails(String userId, String name, String sex, String size, String phone) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        params.put("name", name);
        params.put("sex", sex);
        params.put("size", size);
        params.put("Phone", phone);
        return HttpClientUtil.doGet(saveActivityUserDetailRUL.toString(), params);
    }

	@Override
	public String canUseCoupons(String userId, String pageIndex,
			String pageSize, String buyId, String orderSource, String couponNo,
			String excludeCouponNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		params.put("buyId", buyId);
		params.put("orderSource", orderSource);
		params.put("couponNo", couponNo);
		params.put("excludeCouponNo", excludeCouponNo);
		return HttpClientUtil.doGet(canUseCouponsRUL.toString(), params);
	}
    
    
     
}
