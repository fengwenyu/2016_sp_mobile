package com.shangpin.biz.service.basic;

import com.shangpin.biz.bo.GiftCardBuy;
import com.shangpin.biz.bo.GiftCardCommit;
import com.shangpin.biz.bo.GiftCardProductList;
import com.shangpin.biz.bo.GiftCardRechargePasswd;
import com.shangpin.biz.bo.GiftCardRecordList;
import com.shangpin.biz.bo.GiftCardStatus;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMap;
import com.shangpin.biz.bo.base.ResultObjOne;

/**
 * 礼品卡基础对象
 * 
 * @author zghw
 *
 */
public interface IBizGiftCardService {
	/**
	 * 礼品卡列表
	 * 
	 * @param categoryNO
	 * @param productNo
	 * @param start
	 * @param end
	 * @param userID
	 * @param userIP
	 * @param encode
	 * @return
	 * @author zghw
	 */
	public String fromGiftCardList(String categoryNO, String productNo, String start, String end, String userID, String userIP, String encode);

	/**
	 * 礼品卡商品列表(6.1)
	 * 
	 * @param categoryNO
	 * @param productNo
	 * @param start
	 * @param end
	 * @param userID
	 * @param userIP
	 * @param encode
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<GiftCardProductList> beList(String categoryNO, String productNo, String start, String end, String userID, String userIP, String encode);

	/**
	 * 礼品卡商品列表(6.1)
	 * 
	 * @param userId
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<GiftCardProductList> beList(String userId, String type, String pageIndex, String pageSize);

	public String fromRecordList(String userId, String recordType, String pageIndex, String pageSize);

	/**
	 * 礼品卡记录列表
	 * 
	 * @param userId
	 *            用户ID
	 * @param recordType
	 *            礼品卡订单记录状态1.购买记录 2.充值记录 3.消费记录
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            每页条数
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<GiftCardRecordList> beRecordList(String userId, String recordType, String pageIndex, String pageSize);

	public String fromElectronicRecharge(String userId, String orderId, String cardPasswd);

	/**
	 * 礼品卡电子卡充值接口
	 * 
	 * @param userId
	 *            用户ID
	 * @param cardPasswd
	 * @param orderId
	 *            订单号 礼品卡号
	 * @return
	 * @author zghw
	 */
	public ResultObjMap<String> beElectronicRecharge(String userId, String orderId, String cardPasswd);

	public String fromGiftCardBuy(String userId, String skuId, String productId, String quantity, String categoryNo, String type, String eGiftCardAmount);

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
	public ResultObjOne<GiftCardBuy> beGiftCardBuy(String userId, String skuId, String productId, String quantity, String categoryNo, String type, String eGiftCardAmount);

	public String fromGiftCardCommit(String userId, String addressId, String invoiceFlag, String invoiceAddressId, String invoiceType, String invoiceTitle, String invoiceContent,
			String express, String orderFrom, String buysIds, String payTypeId, String payTypeChildId, String orderType, String type,
									 String invoiceEmail,String invoiceTel);

	/**
	 * 提交订单去结算
	 * 
	 * @param userId用户ID
	 * @param addressId收货地址ID
	 * @param invoiceFlag是否开发票
	 * @param invoiceAddressId发票包含的收货地址
	 * @param invoiceType发票类型0
	 *            ：个人 1公司
	 * @param invoiceTitle发票抬头
	 * @param invoiceContent发票内容
	 * @param express配送方式
	 * @param orderFrom订单来源
	 * @param buysIds购物车ID
	 * @param payTypeId主支付方式编号
	 * @param payTypeChildId子支付方式编号
	 * @param orderType订单来源站点
	 * @param type
	 *            1实物卡2电子卡
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<GiftCardCommit> beGiftCardCommit(String userId, String addressId, String invoiceFlag, String invoiceAddressId, String invoiceType, String invoiceTitle,
			String invoiceContent, String express, String orderFrom, String buysIds, String payTypeId, String payTypeChildId, String orderType, String type,
														 String invoiceEmail,String invoiceTel);

	public String fromGiftCardRechargePasswd(String userId, String orderId);

	/**
	 * 礼品卡--获取电子卡充值密码
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单号
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<GiftCardRechargePasswd> beGiftCardRechargePasswd(String userId, String orderId);

	/**
	 * 查询账户礼品卡状态
	 * 
	 * @param userId
	 * @return
	 * @author zghw
	 */
	public String fromGiftCardStatus(String userId);

	public ResultObjOne<GiftCardStatus> beGiftCardStatus(String userId);

	/**
	 * 礼品卡设置支付密码
	 * 
	 * @param userId
	 * @return
	 * @author zghw
	 */
	public String fromSetGiftCardPassword(String userId, String password);

	public ResultBase beSetGiftCardPassword(String userId, String password);

	/**
	 * 礼品卡实物卡充值
	 * 
	 * @param userId用户ID
	 * @param cardNo
	 *            卡号
	 * @param cardPwd卡密
	 * @return
	 * @author zghw
	 */
	public String fromGiftCardEntityRecharge(String userId, String cardNo, String cardPwd);

	public ResultObjMap<String> beGiftCardEntityRecharge(String userId, String cardNo, String cardPwd);

    public ResultObjOne<GiftCardStatus> findGetGiftCardStatus(String cardNo);}
