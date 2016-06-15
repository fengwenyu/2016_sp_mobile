package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.GiftCardBuy;
import com.shangpin.biz.bo.GiftCardCommit;
import com.shangpin.biz.bo.GiftCardKeytContent;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.basic.IBizGiftCardService;

public interface ASPBizGiftCardService extends IBizGiftCardService {
	
	
	
	/**
	 * 礼品卡订单提交接口
	 *
	 * 2.9.12 增加字段
	 * 		invoiceEmail,  发票邮箱
	 * 		invoiceTel     发票手机号
	 * 	 	invoiceStyle
	 * 
	 * @param userId用户ID
	 * @param type礼品卡类型1
	 *            .实物卡2电子卡
	 * @param pageIndex当前页
	 * @param pageSize每页条数
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<GiftCardCommit> doGiftCardCommit(String userId, String addressId, String invoiceFlag, String invoiceAddressId, String invoiceType, String invoiceTitle,
			String invoiceContent, String express, String orderFrom, String buysIds, String payTypeId, String payTypeChildId, String orderType,String type,
			String invoiceEmail,String invoiceTel);

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
	public ResultObjOne<GiftCardBuy> doGiftCardBuy(String userId, String skuId, String productId, String quantity,String categoryNo,String type,String eGiftCardAmount,String version);
	
	/**
	 * 得到电子卡充值密码
	 * @param userId
	 * @param orderId
	 * @return
	 * @author zghw
	 */
	public String  doGiftCardRechargePasswd(String userId, String orderId);
	

	List<GiftCardKeytContent> getGiftCardKeyt(String userId, String orderId);
	
	public String giftCardKeyt(String userId, String orderId);
	
	String getFromRecordList(String userId, String recordType, String pageIndex, String pageSize) throws Exception;

	List<GiftCardKeytContent> getGiftCardRechargePasswd(String userId, String orderId);

	public String getGiftCardRechargePasswdByCardId(String userId, String cardId);
	
	/**
	 * 将礼品卡购买的返回值转换为2.9.11版本以后的返回值
	 * @param giftCardBuy
	 * @return
	 */
	public String doGiftCardBuyToSettlementUnion(ResultObjOne<GiftCardBuy> giftCardBuy,String version);
	
}
