package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.StarPacketService;
import com.shangpin.biz.bo.CouponHistory;
import com.shangpin.biz.bo.CouponInfo;
import com.shangpin.biz.bo.RedActivity;
import com.shangpin.biz.bo.RedIsHave;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizStarPacketService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JedisUtilFactory;
import com.shangpin.utils.JsonUtil;

@Service
public class SPBizStarPacketServiceImpl implements SPBizStarPacketService{
	
	@Autowired
	private StarPacketService starPacketService;
	
	private static final Logger logger = LoggerFactory.getLogger(SPBizStarPacketServiceImpl.class);

	@Override
	public List<CouponHistory> packetRecordList(String batchNo, String pageNo, String pageSize) {
		String json = starPacketService.packetHistory(batchNo, pageNo, pageSize);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultObjMapList<CouponHistory> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<CouponHistory>>(){});
		if(null != obj && Constants.SUCCESS.equals(obj.getCode())){
			obj.getContent().get("list");
		}
		return null;
	}

	@Override
	public ResultBase activeCouponInfo(String userId, String batchNo, String activeCode, String couponCode) {
		String json = starPacketService.activeCouponInfo(userId, batchNo, activeCode, couponCode);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		return JsonUtil.fromJson(json, ResultBase.class);
	}

	@Override
	public CouponInfo phoneHadCoupon(String phoneNum, String batchNo, String couponCode) {
		String json = starPacketService.phoneHadCoupon(batchNo, phoneNum, couponCode);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultObjOne<CouponInfo> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CouponInfo>>(){});
		if(null != obj && Constants.SUCCESS.equals(obj.getCode())){
			return obj.getContent();
		}
		return null;
	}

	@Override
	public CouponInfo randomCouponInfo(String batchNo) {
		String json = starPacketService.randomCoupon(batchNo);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultObjOne<CouponInfo> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CouponInfo>>(){});
		if(null != obj && Constants.SUCCESS.equals(obj.getCode())){
			return obj.getContent();
		}
		return null;
	}

	@Override
	public String randomObtainCoupon(String batchNo) {
		String key = "WeixinWalletCoupon" + batchNo;
		boolean flag = JedisUtilFactory.getCouponJedisUtil().exists(key);
		if(!flag){
			return null;
		}
		String value = JedisUtilFactory.getCouponJedisUtil().new Sets().spop(key);
		return value;
	}

	@Override
	public boolean isCouponOut(String key) {
		Long count = Long.parseLong(JedisUtilFactory.getCouponJedisUtil().get(key));
		return count > 0 ? false : true;
	}

	@Override
	public Long bindPhoneUser(String batchNo, String userId, String activeCode, String couponCode) {
		String key = "WeixinWalletBind" + batchNo;
		String value = userId + "|" + activeCode + "|" + couponCode;
		Long num = JedisUtilFactory.getServiceJedisUtil().new Sets().sadd(key, value);
		return num;
	}

	@Override
	public Long addPhoneToCache(String batchNo, String phoneNum, String amount, String type) {
		String key = "WeixinWalletHasTaked" + batchNo + phoneNum;
		String value = amount + "|" + type;
		Long num = JedisUtilFactory.getCouponJedisUtil().new Lists().rpush(key, value);
		return num;
	}

	@Override
	public long decr(String key) {
		return JedisUtilFactory.getCouponJedisUtil().decr(key);
	}

	@Override
	public long incr(String key) {
		return JedisUtilFactory.getCouponJedisUtil().incr(key);
	}

	@Override
	public long phoneCouponCount(String batchNo, String phone) {
		String key = "WeixinWalletHasTaked" + batchNo + phone;
		boolean flag = JedisUtilFactory.getCouponJedisUtil().exists(key);
		if(!flag){
			return -1;
		}
		return JedisUtilFactory.getCouponJedisUtil().new Lists().llen(key);
	}

	@Override
	public List<CouponInfo> findByKey(String batchNo, String phone) {
		List<CouponInfo> couponInfos = new ArrayList<CouponInfo>();
		String key = "WeixinWalletHasTaked" + batchNo + phone;
		List<String> strs = JedisUtilFactory.getCouponJedisUtil().new Lists().lrange(key, 0, - 1);
		for(String str : strs){
			String[] sarry = str.split("\\|");
			CouponInfo coupon = new CouponInfo();
			coupon.setAmount(sarry[0].split("\\.")[0]);
			coupon.setType(sarry[1]);
			couponInfos.add(coupon);
		}
		return couponInfos;
	}

	@Override
	public ResultBase sendPhoneNotic(String userId, String phoneNum, String msgTmpl) {
		String json = starPacketService.sendPhoneNotic(userId, phoneNum, msgTmpl);
		try {
			if(StringUtils.isEmpty(json)){
				return null;
			}
			return JsonUtil.fromJson(json, ResultBase.class);
		} catch (Exception e) {
			logger.error("send msg occur, return data:{}", json);
			e.printStackTrace();
			return null;
		}
	}

    @Override
    public RedActivity getGiftPacket(String userId, String phoneNum) {
        String json = starPacketService.getPacket(userId, phoneNum);
        try {
            if (StringUtils.isEmpty(json)) {
                return null;
            }
            ResultObjOne<RedActivity> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<RedActivity>>(){}) ;
            return obj.getContent();
        } catch (Exception e) {
            logger.error("send msg occur, return data:{}", json);
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public RedIsHave isUserExist(String userId, String phoneNum) {
        String json = starPacketService.isUserExist(userId, phoneNum);
        try {
            if (StringUtils.isEmpty(json)) {
                return null;
            }
            ResultObjOne<RedIsHave> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<RedIsHave>>(){}) ;
            return obj.getContent();
        } catch (Exception e) {
            logger.error("send msg occur, return data:{}", json);
            e.printStackTrace();
            return null;
        }
    }
    
	@Override
	public ResultObjOne<Map<String,Object>> activeCashPacket(String type, String phone) {
		String json = starPacketService.activeCashPacket(type, phone);
		logger.info("激活(标记为已领取)用户["+phone+"]现金红包 resultJson={}",json);
		try {
			if(StringUtils.isEmpty(json)){
				return null;
			}
			return JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Map<String,Object>>>(){});
		} catch (Exception e) {
			logger.error("send msg occur, return data:{}", json);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultObjOne<RedActivity> getCashPacket(String phone) {
		String json = starPacketService.getCashPacket(phone);
		logger.info("获取用户["+phone+"]现金红包 resultJson={}",json);
		try {
			if(StringUtils.isEmpty(json)){
				return null;
			}
			return JsonUtil.fromJson(json, new TypeToken<ResultObjOne<RedActivity>>(){}) ;
		} catch (Exception e) {
			logger.error("send msg occur, return data:{}", json);
			e.printStackTrace();
			return null;
		}
	}

}
