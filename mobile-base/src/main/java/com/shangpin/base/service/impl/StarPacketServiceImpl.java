package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shangpin.base.service.StarPacketService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.utils.HttpClientUtil;

@Service
public class StarPacketServiceImpl implements StarPacketService{
	
	//领取优惠券的历史记录
	private StringBuilder packetRecordListRUL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("packetRecordList");
	//激活优惠券信息
	private StringBuilder activeCouponInfoRUL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("activeCouponInfo");
	//判断手机号是否领取优惠券
	private StringBuilder phoneHadCouponRUL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("phoneHadCoupon");
	//随机领取一张优惠券
	private StringBuilder randomCouponRUL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("randomCoupon");
	//下发短信通知
	private StringBuilder sendVerifyCodeURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("sendRedBagMobileVerifyCode");
	//查询该手机号是否领券接口
    private StringBuilder getGiftPacketURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("RedPacket/getGiftPacket");
	//激活现金券
	private StringBuilder activeCashPacketURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("RedPacket/activeCashPacket");
	//判断用户是否存在
    private StringBuilder getIsUserExistURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("RedPacket/isUserExist");
	//获取现金券
	private StringBuilder getCashPacketURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("RedPacket/getCashPacket");
    
	@Override
	public String packetHistory(String batchNo, String pageNo, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("batchNo", batchNo);
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		return HttpClientUtil.doGet(packetRecordListRUL.toString(), params);
	}

	@Override
	public String activeCouponInfo(String userId, String batchNo, String activeCode, String couponCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("batchNo", batchNo);
		params.put("activeCode", activeCode);
		params.put("couponCode", couponCode);
		return HttpClientUtil.doGet(activeCouponInfoRUL.toString(), params);
	}

	@Override
	public String phoneHadCoupon(String batchNo, String phoneNum, String couponCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("batchNo", batchNo);
		params.put("phoneNum", phoneNum);
		params.put("couponCode", couponCode);
		return HttpClientUtil.doGet(phoneHadCouponRUL.toString(), params);
	}

	@Override
	public String randomCoupon(String batchNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("batchNo", batchNo);
		return HttpClientUtil.doGet(randomCouponRUL.toString(), params);
	}

	@Override
	public String sendPhoneNotic(String userId, String phoneNum, String msgTmpl) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("phonenum", phoneNum);
		params.put("msgtemplate", msgTmpl);
		return HttpClientUtil.doGet(sendVerifyCodeURL.toString(), params);
	}

    @Override
    public String getPacket(String userId, String phoneNum) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        params.put("phone", phoneNum);
        params.put("key", "key"+"getgiftpacket"+"keykey");
        return HttpClientUtil.doGet(getGiftPacketURL.toString(), params);
    }
    

	@Override
	public String activeCashPacket(String type, String phone) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("phone", phone);
		params.put("key", "key"+"activecashpacket"+"keykey");
		return HttpClientUtil.doGet(activeCashPacketURL.toString(), params);
	}

	@Override
	public String getCashPacket(String phone) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phone", phone);
		params.put("key", "key"+"getcashpacket"+"keykey");
		return HttpClientUtil.doGet(getCashPacketURL.toString(), params);
	}

    @Override
    public String isUserExist(String userId, String phoneNum) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        params.put("phone", phoneNum);
        params.put("key", "key"+"isuserexist"+"keykey");
        return HttpClientUtil.doGet(getIsUserExistURL.toString(), params);
    }
}
