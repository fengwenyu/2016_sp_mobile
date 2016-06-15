package com.shangpin.biz.service;

import java.util.List;
import java.util.Map;

import com.shangpin.biz.bo.CouponHistory;
import com.shangpin.biz.bo.CouponInfo;
import com.shangpin.biz.bo.RedActivity;
import com.shangpin.biz.bo.RedIsHave;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;

public interface SPBizStarPacketService {
	
	/**
	 * 红包领取历史记录
	 * @param batchNo 批次号
	 * @param pageNo 页码
	 * @param pageSize 一页显示的条数
	 * @return
	 */
	public List<CouponHistory> packetRecordList(String batchNo, String pageNo, String pageSize);
	
	/**
	 * 激活优惠券信息
	 * @param userId 用户ID
	 * @param batchNo 批次号
	 * @param activeCode 激活码
	 * @param couponCode 优惠券编号
	 * @return
	 */
	public ResultBase activeCouponInfo(String userId, String batchNo, String activeCode, String couponCode);
	
	/**
	 * 判断手机号是否领取过优惠券
	 * @param phoneNum 手机号
	 * @param batchNo 批次号
	 * @param couponCode 优惠券编号
	 * @return
	 */
	public CouponInfo phoneHadCoupon(String phoneNum, String batchNo, String couponCode);
	
	/**
	 * 随机领取优惠券
	 * @param batchNo 批次号
	 * @return
	 */
	public CouponInfo randomCouponInfo(String batchNo);
	
	/**
	 * 从redis中随机获取一条优惠券信息
	 * @return
	 */
	public String randomObtainCoupon(String batchNo);
	
	/**
	 * 判断优惠券是否领取完
	 * @param key
	 * @return
	 */
	public boolean isCouponOut(String key);
	
	/**
	 * 绑定用户优惠券信息
	 * @param batchNo
	 * @param userId
	 * @param activeCode
	 * @param couponCode
	 * @return
	 */
	public Long bindPhoneUser(String batchNo, String userId, String activeCode, String couponCode);
	
	/**
	 * 向redis中添加手机号领取记录
	 * @param batchNo
	 * @param phoneNum
	 * @param amount
	 * @param type
	 * @return
	 */
	public Long addPhoneToCache(String batchNo, String phoneNum, String amount, String type);
	
	/**
	 * redis计数器递减
	 * @param key
	 * @return
	 */
	public long decr(String key);
	
	/**
	 * redis计数器递增
	 * @param key
	 * @return
	 */
	public long incr(String key);
	
	/**
	 * 手机号领取优惠券的次数
	 * @param batchNo
	 * @param phone
	 * @return
	 */
	public long phoneCouponCount(String batchNo, String phone);
	
	/**
	 * 查询手机号领取的记录
	 * @param batchNo
	 * @param phone
	 * @return
	 */
	public List<CouponInfo> findByKey(String batchNo, String phone);
	
	/**
	 * 明星红包下发短信通知
	 * @param userId
	 * @param phoneNum
	 * @param msgTmpl
	 * @return
	 */
	public ResultBase sendPhoneNotic(String userId, String phoneNum, String msgTmpl);
	
	//掉接口实现业务	
    /***
     * 获取用户红包接口
     * @param userId
     * @param phoneNum
     */
    public RedActivity getGiftPacket(String userId,String phoneNum);
    
	/**
	 * 根据手机号激活现金红包接口(activeCashPacket)
	 * @param type 类型 1现金
	 * @param phone 手机号
	 * @return
	 */
	public ResultObjOne<Map<String,Object>> activeCashPacket(String type, String phone);
	
	/**
	 * 根据手机号获取用户现金红包接口(getCashPacket)
	 * @param phone 手机号
	 * @return
	 */
	public ResultObjOne<RedActivity> getCashPacket(String phone);
	
	//掉接口实现业务  
    /***
     * 获取用户红包接口
     * @param userId
     * @param phoneNum
     */
    public RedIsHave isUserExist(String userId,String phoneNum);
}
