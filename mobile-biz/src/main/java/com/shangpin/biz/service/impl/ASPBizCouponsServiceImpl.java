package com.shangpin.biz.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.CouponsService;
import com.shangpin.base.service.OrderService;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.RecProductFor;
import com.shangpin.biz.service.ASPBizCouponsService;
import com.shangpin.biz.service.abstraction.AbstractBizCouponService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.Constants;
@Service
public class ASPBizCouponsServiceImpl extends AbstractBizCouponService implements ASPBizCouponsService{
	@Autowired
	private CouponsService couponsService;
	
	@Autowired
    private OrderService orderService;

	@Override
	public String useCoupons(String userId,String type,String totalAmount,String promoAmount,String ticketAmount,String carriageAmount,String discountCode,String giftCardAmount,String postArea,String buyIds,String orderSource) {
		String data = couponsService.useCoupons(userId, type, totalAmount,promoAmount,ticketAmount,carriageAmount,discountCode,giftCardAmount,postArea, buyIds, orderSource);
		return data;
	}

	@Override
	public String giveCoupons(String userId, String orderId) {
		String data = couponsService.giveCoupons(userId, orderId);
		return data;
	}

	@Override
	public String searchUseCouponProductList(String payAmount,
			String start, String end, String minPrice, String maxPrice,
			String postArea, String includeBrandNo, String excludeBrandNo,
			String includeCategoryNo, String excludeCategoryNo,
			String includeProductNo, String excludeProductNo,String userLv) {
		try{
			Map<String,Object> map=doBaseSearchUseCouponProductList( payAmount,start, end, null,null,postArea,null,null,null,null,null,null,userLv);
		    if(map==null){
		    	return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
		    }
		    if(!map.get("code").equals(Constants.SUCCESS)){
		    	return ApiBizData.spliceData(map.get("code").toString() ,map.get("msg").toString());
		    }
		    RecProductFor recProductFor= (RecProductFor) map.get("recProductFor");
			if(recProductFor==null){
				return ApiBizData.spliceData(CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
			}
				
			return ApiBizData.spliceData(recProductFor, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
			
		}catch(Exception e){
			return null;
		}
		  
	}

    @Override
    public String getCouponsInfo(String bId) {
        return couponsService.getCouponsInfo(bId);
    }

    @Override
    public String activateCoupon(String userId, String shoptype, String cardNo, String type) {
        String json = orderService.sendCoupon(userId, shoptype, cardNo, type);
        return json;
    }

	
	
}
