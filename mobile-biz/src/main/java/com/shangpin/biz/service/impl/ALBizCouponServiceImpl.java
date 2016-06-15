package com.shangpin.biz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.OrderService;
import com.shangpin.biz.bo.Coupon;
import com.shangpin.biz.bo.CouponCount;
import com.shangpin.biz.bo.CouponPeriod;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ALBizCouponService;
import com.shangpin.biz.service.abstraction.AbstractBizCouponService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.DateTimeUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.JsonUtil;

@Service
public class ALBizCouponServiceImpl extends AbstractBizCouponService implements ALBizCouponService {

	private static final Logger logger = LoggerFactory.getLogger(ALBizCouponServiceImpl.class);

	@Autowired
	private OrderService orderService;

	@Override
	public String findCoupons(String userId, String couponType, String pageIndex, String pageSize) {
		String json = orderService.findCoupons(userId, pageIndex, pageSize, Constants.SHOP_TYPE_AOLAI, couponType);
		if (!StringUtils.isEmpty(json)) {
			return json;
		}
		return null;
	}

	@Override
	public List<Coupon> findCouponsList(String userId, String couponType, String pageIndex, String pageSize) {

		try {
			String result = findCoupons(userId, couponType, pageIndex, pageSize);
			if (!StringUtils.isEmpty(result)) {
				ResultObjOne<CouponCount> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<CouponCount>>() {
				});
				if (!StringUtils.isEmpty(obj) && obj.isSuccess() && obj.getContent() != null) {
					return obj.getContent().getList();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Coupon> findCouponsList(String userId, String shopType, String couponType, String pageIndex, String pageSize) {
		try {
			String json = orderService.findCoupons(userId, pageIndex, pageSize, shopType, couponType);
			logger.debug("base interface return data:" + json);
			ResultObjMapList<Coupon> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Coupon>>() {
			});
			return obj.getList("list");
		} catch (Exception e) {
			logger.error("base interface return data error!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultBase sendActivation(String userid, String shoptype, String type) {
		try {
			String json = orderService.sendActivation(userid, shoptype, type);
			logger.debug("base interface send activation return data :" + json);
			return JsonUtil.fromJson(json, ResultBase.class);
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public boolean isSendCoupon(CouponPeriod couponPeriod) {
		if (couponPeriod != null) {
			Date start = DateTimeUtil.parse(couponPeriod.getStartDate());
			Date end = DateTimeUtil.parse(couponPeriod.getEndDate());
			Date now = new Date();
			return (now.after(start) && now.before(end));
		}
		return false;
	}

	@Override
	public Map<String, Object> sendCoupon(String userId, String shopType, String type, String couponType) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String json = orderService.sendCoupon(userId, shopType, type, couponType);
			logger.debug("base interface send activation return data :" + json);
			ResultBase resultBase = JsonUtil.fromJson(json, ResultBase.class);
			if (Constants.SUCCESS.equals(resultBase.getCode())) {
				map.put("code", Constants.SUCCESS);
			} else {
				map.put("code", Constants.FAILE);
				String msg = resultBase.getMsg();
				if (StringUtil.isNotEmpty(msg)) {
					map.put("mag", resultBase.getMsg());
				} else {
					map.put("mag", "领取失败");
				}

			}
		} catch (Exception e) {
			logger.error("base interface return data error!");
			e.printStackTrace();
		}
		return map;
	}

}
