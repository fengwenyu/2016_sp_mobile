package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.dailyCoupons.CouponStatus;
import com.shangpin.biz.bo.dailyCoupons.DailyCoupons;
import com.shangpin.biz.bo.dailyCoupons.DailyCouponsList;
import com.shangpin.biz.service.SPBizDailyCouponsService;
import com.shangpin.biz.service.abstraction.AbstractBizDailyCouponsService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.PicCdnHash;
import com.shangpin.product.model.cbwfs.PromotionTicket;
import com.shangpin.product.model.common.ContentBuilder;
import com.shangpin.utils.DateUtils;
import com.shangpin.utils.JsonUtil;

@Service
public class SPBizDailyCouponsServiceImpl extends AbstractBizDailyCouponsService implements SPBizDailyCouponsService{

	private static final Logger logger = LoggerFactory.getLogger(SPBizDailyCouponsServiceImpl.class);
	
	private static final String pattern = "yyyy.MM.dd";
	
	 /**
	  * 获取天天抢券列表
	  */
	 @Override
	 public DailyCouponsList getCouponsList(String userId,String order,String type,String pageIndex,String pageSize){
		 ContentBuilder<Map<String, List<PromotionTicket>>> builder = dailyCouponsService.promotionTickets(order, type, pageIndex, pageSize);
		 DailyCouponsList dailyCouponsList = new DailyCouponsList();
		 List<DailyCoupons> dailyCoupons = new ArrayList<DailyCoupons>();
		 if(null != builder && Constants.SUCCESS.equals(builder.getCode())){
			 List<PromotionTicket> promotionTickets = builder.getContent().get("list");
			 if(null != promotionTickets && promotionTickets.size() > 0){
				 Map<String, String> map = getCouponStatusMap(promotionTickets, userId);
				 for(PromotionTicket promotionTicket : promotionTickets){
					 DailyCoupons dailyCoupon = new DailyCoupons();
					 dailyCoupon.setActiveCode(promotionTicket.getActiveCode());
					 dailyCoupon.setDesc(promotionTicket.getDescription());
					 dailyCoupon.setType(String.valueOf(promotionTicket.getPromotionType()));
					 dailyCoupon.setPic(PicCdnHash.getPicUrl(promotionTicket.getPictureFileNo(), "2"));
					 dailyCoupon.setAmount(String.valueOf(promotionTicket.getTicketMoney()));
					 dailyCoupon.setExpireDate(DateUtils.dateToStr(promotionTicket.getActiveCodeStartTime(), pattern) + "-" + DateUtils.dateToStr(promotionTicket.getActiveCodeEndTime(), pattern));
					 dailyCoupon.setUrl(promotionTicket.getLinkAddress());
					 dailyCoupon.setCondition(getCondition(promotionTicket.getPromotionType(), promotionTicket.getOrderTicketAmount()));
					 dailyCoupon.setStatusCode(map.get(promotionTicket.getActiveCode()));
					 dailyCoupons.add(dailyCoupon);
				 }
			 }
		 }
		 dailyCouponsList.setList(dailyCoupons);
		 return dailyCouponsList;
	 }
	 
	 /**
	  * 封装券的领取状态到map集合中
	  * @param promotionTickets
	  * @param userId
	  * @return
	  */
	 private Map<String, String> getCouponStatusMap(List<PromotionTicket> promotionTickets, String userId){
		Map<String, String> map = new HashMap<String, String>();
		StringBuilder builder = new StringBuilder();
		for(PromotionTicket promotionTicket : promotionTickets){
			builder.append(promotionTicket.getActiveCode()).append(",");
		}
		List<CouponStatus> couponStatus = this.getCouponStatus(builder.toString(), userId);
		if(null != couponStatus && couponStatus.size() > 0){
			for(CouponStatus status : couponStatus){
				map.put(status.getActiveCode(), status.getIsReceive());
			}
			return map;
		}
		return null;
	}
	 
	 /**
	  * 获取券的使用规则
	  * @param type 0优惠券，1现金券，2礼包
	  * @param orderAmount
	  * @return
	  */
	 private String getCondition(Integer type, long orderAmount){
		 switch (type) {
		case 1:
			return "无金额门槛";
		case 0:
			return "满" + orderAmount + "元可用";
		case 2:
			return "";
		default:
			return "";
		}
	 }
	 
	 /**
	  * 天天抢券获取券的领取状态
	  */
	 @Override
	 public CouponStatus isGetCoupons(String activeCode,String userId){
		 try {
			String resultJson=doBaseDailyCouponsGet(activeCode, userId);
            logger.debug("调用base天天抢券获取券的领取状态接口返回数据：" + resultJson);
			
			if(StringUtils.isEmpty(resultJson))
				return null;
			
			ResultObjOne<CouponStatus> objOne=JsonUtil.fromJson(resultJson, new TypeToken<ResultObjOne<CouponStatus>>(){});
			CouponStatus status=objOne.getObj();
			return status;
		} catch (Exception e) {
			logger.error("调用base天天抢券获取券的领取状态接口返回数据发生错误!", e);
            return null;
		}
	 }
}
