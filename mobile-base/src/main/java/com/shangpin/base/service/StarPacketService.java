package com.shangpin.base.service;


/**明星红包调用主站接口
 * @author qinyingchun
 *
 */
public interface StarPacketService {
	
	/**
	 * 领取红包的历史记录
	 * @param batchId 批次号
	 * @param pageNo 页码
	 * @param pageSize 一页显示多少条
	 * @return
	 */
	public String packetHistory(String batchNo, String pageNo, String pageSize);
	
	/**
	 * 激活优惠券信息
	 * @param userId 用户ID
	 * @param batchNo 批次号
	 * @param activeCode 激活码
	 * @param couponCode 优惠券编号
	 * @return
	 */
	public String activeCouponInfo(String userId, String batchNo, String activeCode, String couponCode);
	
	/**
	 * 判断手机号是否领取过优惠券
	 * @param batchNo 批次号
	 * @param phoneNum 手机号
	 * @param couponCode 优惠券编号
	 * @return
	 */
	public String phoneHadCoupon(String batchNo, String phoneNum, String couponCode);
	
	/**
	 * 随机获取一张优惠券
	 * @param batchNo 批次号
	 * @return
	 */
	public String randomCoupon(String batchNo);
	
	/**
	 * 明星红包专用短信下发通道
	 * @param userId
	 * @param phoneNum
	 * @param msgTmpl
	 * @return
	 */
	public String sendPhoneNotic(String userId, String phoneNum, String msgTmpl);
	 
    /***
     * 获取用户红包接口
     * @param userId
     * @param phoneNum
     */
    public String getPacket(String userId, String phoneNum);
    
	/**
	 * 根据手机号激活现金红包接口(activeCashPacket)
	 * @param type 类型 1现金
	 * @param phone 手机号
	 * @return
	 */
	public String activeCashPacket(String type, String phone);
	
	/**
	 * 根据手机号获取用户现金红包接口(getCashPacket)
	 * @param phone 手机号
	 * @return
	 */
	public String getCashPacket(String phone);
	
	/**
     * 判断用户是否存在
     * @param phone 手机号
     * @return
     */
    public String isUserExist(String userId, String phoneNum);
}
