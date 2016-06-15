package com.shangpin.biz.service;

import com.shangpin.biz.bo.GiftCard;
import com.shangpin.biz.bo.GiftCardBuy;
import com.shangpin.biz.bo.GiftCardKeyt;
import com.shangpin.biz.bo.GiftCardRecordList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.basic.IBizGiftCardService;
import com.shangpin.biz.bo.GiftCardProductList;

public interface SPBizGiftCardService extends IBizGiftCardService {
	/**
	 * 得到礼品卡记录列表
	 * @param userId
	 * @param recordType 1为购买记录 2为充值记录 3为消费记录
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author zghw
	 */
	public GiftCardRecordList getRecordList(String userId, String recordType, String pageIndex, String pageSize);
	/**
	 * 得到电子卡充值密码
	 * @param userId
	 * @param orderId
	 * @return
	 * @author zghw
	 */
	public String getGiftCardRechargePasswd(String userId, String orderId);
	
	/**
	 * 礼品卡产品列表
	 * @param userId 用户ID
	 * @param type  1为电子卡2为实物卡
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author zghw
	 */
	public GiftCardProductList getGiftCardProductList(String userId, String type, String pageIndex, String pageSize);
	/**
	 * 礼品卡立即购买
	 * 
	 * @param userId用户ID
	 * @param skuIdSKU
	 * @param productId产品ID
	 * @param amount购买数量
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<GiftCardBuy> doGiftCardBuy(String userId, String skuId, String productId, String quantity,String categoryNo,String type,String eGiftCardAmount);
	public GiftCardKeyt queryGiftCardSecretKey(String mainOrderId, String userId);

	
	/**
	 * 赠送礼品卡信息存储Service 
	 * @param giftCard 实体类
	 * @return
	 * @Description: 
	 * @author fengwenyu
	 * @Create 2015年12月23日
	 */
	public String saveGiftCardToDb(GiftCard giftCard);
    
	/**
	 * 获取礼品卡图片和祝福语
	 * @param giftCard 实体类
	 * @return
	 * @author fengwenyu
	 */
	public String getGiftCardPicAndMsg(GiftCard giftCard);
	
	/**
	 * 根据礼品卡卡号得到电子卡充值密码
	 * @param userId 用户id
	 * @param giftCardId 礼品卡卡号
	 * @return
	 * @author fengwenyu
	 */
	public String getGiftCardRechargePasswdByCardId(String userId, String giftCardId);
	
	/**
	 * 一键充值逻辑获取秘钥
	 * @param giftCard
	 * @return
	 */
	public String oneKeyRechargeGetPass(GiftCard giftCard);
	
	/**
	 * 更新数据库中的礼品卡信息
	 * @param userId 充值用户id
	 * @param giftCardId 礼品卡id
	 * @param recgargeTime 充值时间
	 * @return
	 */
	public String oneKeyRechargeUpdateDb(String userId,String sendPhoneNum, String giftCardId,String recgargeTime);
	
	/**
	 * 根据礼品卡id获取礼品卡状态  0为未充值  1为已充值
	 * @param giftCardId
	 * @return
	 */
	public String getGiftCardStatusByGiftCardId(String giftCardId);
}
