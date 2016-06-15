package com.shangpin.biz.service.abstraction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.DailyCouponsService;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.dailyCoupons.CouponStatus;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizDailyCouponsService {
	public static Logger logger = LoggerFactory
			.getLogger(AbstractBizDailyCouponsService.class);
	
	@Autowired
	protected DailyCouponsService dailyCouponsService;
	
	/**
	 * 天天抢券判断用户是否领取该券
	 * @param activeCode
	 * @param userId
	 * @return
	 */
	protected String doBaseDailyCouponsGet(String activeCode,String userId) {
		return dailyCouponsService.dailyCouponsGet(activeCode, userId);
	}
	
	public List<CouponStatus> getCouponStatus(String activeCode, String userId){
		String json = this.doBaseDailyCouponsGet(activeCode, userId);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultObjMapList<CouponStatus> resultObjMapList = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<CouponStatus>>(){});
		if(null != resultObjMapList && Constants.SUCCESS.equals(resultObjMapList.getCode()) && null != resultObjMapList.getContent()){
			List<CouponStatus> couponStatus = resultObjMapList.getContent().get("list");
			return couponStatus;
		}
		return null;
	}
	
}
