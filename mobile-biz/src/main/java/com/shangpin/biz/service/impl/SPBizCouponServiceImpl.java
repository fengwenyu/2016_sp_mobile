package com.shangpin.biz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.ActivityUserDb;
import com.shangpin.biz.bo.Coupon;
import com.shangpin.biz.bo.CouponCount;
import com.shangpin.biz.bo.CouponPeriod;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.abstraction.AbstractBizCouponService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.DateTimeUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.JsonUtil;

@Service
public class SPBizCouponServiceImpl extends AbstractBizCouponService implements SPBizCouponService {

	private static final Logger logger = LoggerFactory.getLogger(SPBizCouponServiceImpl.class);

	@Override
	public List<Coupon> findCouponsList(String userId, String shopType, String couponType, String pageIndex, String pageSize) {
		try {
			ResultObjOne<CouponCount> obj = beCouponsCount(userId, pageIndex, pageSize, shopType, couponType);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess() && obj.getContent() != null) {
				return obj.getContent().getList();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			ResultBase resultBase = beSendCoupon(userId, shopType, type, couponType);
			if (resultBase!=null && Constants.SUCCESS.equals(resultBase.getCode())) {
				map.put("code", Constants.SUCCESS);
			} else {
				map.put("code", Constants.FAILE);
				String msg = resultBase.getMsg();
				if (StringUtil.isNotEmpty(msg)) {
					map.put("msg", resultBase.getMsg());
				} else {
					map.put("msg", "领取失败");
				}

			}
		} catch (Exception e) {
			logger.error("base interface return data error!");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> sendCoupon(String userId, String shopType, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ResultBase resultBase = beSendActivation(userId, shopType, type);
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

	@Override
	public Map<String, Object> activeCoupon(String userid, String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Object> searchUseCouponProductList(String payAmount,
			String start, String end, String minPrice, String maxPrice,
			String postArea, String includeBrandNo, String excludeBrandNo,
			String includeCategoryNo, String excludeCategoryNo,
			String includeProductNo, String excludeProductNo, String userLv) {
		return doBaseSearchUseCouponProductList( payAmount,start, end, minPrice,maxPrice,postArea,includeBrandNo,excludeBrandNo,includeCategoryNo,excludeCategoryNo,includeProductNo,excludeProductNo,  userLv);
	}

    @Override
    public Coupon findCouponsInfo(String batchNo)throws Exception {
        return findCouponInfo(batchNo);
    }

    @Override
    public String saveActivityUserDetail(ActivityUserDb activityUserDb) {
         String json=saveActivityUserDetails(activityUserDb);
         ResultObjOne<ActivityUserDb> aOne = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ActivityUserDb>>() {
         });
         if (Constants.SUCCESS.equals(aOne.getCode())) {
            return aOne.getContent().getUserCode();
         }
         return null;
    }
  }
