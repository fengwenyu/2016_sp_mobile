package com.shangpin.base.service;

import java.util.List;

import com.shangpin.base.vo.CartOrder;
import com.shangpin.base.vo.Logistics;
import com.shangpin.base.vo.LogisticsInfo;
import com.shangpin.base.vo.QuickSubmitVO;
import com.shangpin.base.vo.SubmitOrderParam;

/**
 * 订单相关接口的Service
 * 
 * @author sunweiwei
 * 
 */
public interface OrderService {
	/**
	 * 确认订单信息（尚品 奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param detailPicw
	 *            商品快照图片宽度
	 * @param detailPich
	 *            商品快照图片高度
	 * @param picw
	 *            列表图片宽度
	 * @param pich
	 *            列表图片高度
	 * @param shopType
	 *            订单来自 1代表尚品 2代表奥莱 参数不传或空时，表示奥莱
	 * @return
	 * @author zhanghongwei
	 */
	public String confirmOrderInfo(String userId, String detailPicw, String detailPich, String picw, String pich, String shopType);

	/**
	 * 获取某一订单简要信息（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户ID：用户的唯一标识
	 * @param orderId
	 *            订单ID
	 * @return
	 * 
	 * @author cuibinqiang
	 */
	public String findOrder(String userId, String orderId);

	/**
	 * 根据用户ID、订单ID，取消订单
	 * 
	 * @param userid
	 *            用户ID
	 * @param orderid
	 *            订单ID
	 * 
	 * @return 操作结果
	 */
	public String cancelOrder(String userid, String mainorderno);

	/**
	 * 获取某一订单详细信息（尚品）
	 * 
	 * @param userId
	 *            用户ID：用户的唯一标识
	 * @param orderId
	 *            订单ID
	 * @return
	 * 
	 * @author zhongchao
	 */
	public String findOrderNew(String userId, String mainOrderNo);

	/**
	 * 获取某一订单详细信息（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户ID：用户的唯一标识
	 * @param orderId
	 *            订单ID
	 * @param picW
	 *            图片宽度
	 * @param picH
	 *            图片高度
	 * @return
	 * 
	 * @author cuibinqiang
	 */
	public String findOrderDetail(String userId, String orderId, String picW, String picH);

	/**
	 * 获取订单列表详细信息(尚品)
	 * 
	 * @param userId
	 *            用户ID
	 * @param status
	 *            订单状态
	 * @param page
	 *            订单pageIndex
	 * @param pagesize
	 *            订单每页显示默认条数:10
	 * @author zhongchao
	 */
	public String getOrderlist(String userId, String status, String page, String pagesize, String isShowOverseas);

	/**
	 * 获取用户购物车、优惠券、物流、订单数量（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户主键ID
	 * @param checkTime
	 *            最后一次查看物流列表时间(本地获取)，不传、为空时logisticsstatus=0
	 * @param shopType
	 *            来自尚品1，奥莱2
	 * @return
	 * @author liujie
	 */
	public String findUserBuyInfo(String userId, String checkTime, String shopType);

	/**
	 * 查询某订单的物流信息（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param orderId
	 *            订单id
	 * @param flag
	 *            参数为0时，获取所有物流信息；为1时，logistics中仅包含最新的一条物流信息
	 * @param picw
	 *            列表图片宽度
	 * @param pich
	 *            列表图片高度
	 * @return
	 * @author liujie
	 */
	public String findOrderLogistic(String userId, String orderId, String flag, String pich, String picw);

	/**
	 * 物流详情（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param orderId
	 *            订单id
	 * @param ticketno
	 *            物流单号
	 * @return
	 * @author liujie
	 */
	public String findLogisticsDetail(String userId, String orderId, String ticketNo);

	/**
	 * 我的优惠券列表（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param pageIndex
	 *            当前页数
	 * @param pageSize
	 *            每页显示条数
	 * @param shopType
	 *            表示来自尚品1，奥莱2
	 * @param couponType
	 *            优惠券类型；0未使用；1已使用； 3已过期；-1全部
	 * @return
	 * @author liujie
	 */
	public String findCoupons(String userId, String pageIndex, String pageSize, String shopType, String couponType);

	/**
	 * 订单优惠券列表（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param pageIndex
	 *            当前页数
	 * @param pageSize
	 *            每页显示条数
	 * @param shopType
	 *            表示来自尚品1，奥莱2
	 * @return
	 * @author liujie
	 */
	public String findSelectCoupon(String userId, String pageIndex, String pageSize, String shopType);

	/**
	 * 更改订单支付方式（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param orderId
	 *            订单ID
	 * @param mainPaymode
	 *            主支付方式
	 * @param subPaymode
	 *            子支付方式
	 * @return
	 * @author liujie
	 */
	public String modifyChagePayMode(String userId, String orderId, String mainPaymode, String subPaymode);

	/**
	 * 获取礼品卡列表（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param type
	 *            礼品卡类型，1表示与帐号相关；2表示支付相关；注意：当类型为2时，状态值为空字符串
	 * @param status
	 *            礼品卡状态，0：全部；6：已激活（实物、电子）；7：已使用（实物、电子）；8：已冻结（实物、电子）；9：已失效（实物、电子）
	 *            ；10：已作废（实物、电子）。
	 * @param shopType
	 *            表示来自尚品1，奥莱2
	 * @param pageIndex
	 *            当前页数（为0时显示全部不分页）
	 * @param pageSize
	 *            每页显示条数
	 * @return
	 * @author liujie
	 */
	public String findGiftCards(String userId, String type, String status, String shopType, String pageIndex, String pageSize);

	/**
	 * 选择礼品卡（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param giftCardnNo
	 *            礼品卡字符串（多个用“|”分割）
	 * @param orderId
	 *            订单编号。
	 * @param shopType
	 *            表示来自尚品1，奥莱2
	 * @return
	 * @author liujie
	 */
	public String findSelectGiftCards(String userId, String giftCardnNo, String orderId, String shopType);

	/**
	 * 礼品卡支付（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param password
	 *            礼品卡密码
	 * @param giftCardNo
	 *            礼品卡字符串（多个用“|”分割）
	 * @param orderId
	 *            订单编号。
	 * @param email
	 *            电子邮箱（用户登录名）
	 * @param shopType
	 *            表示来自尚品1，奥莱2
	 * @param payType
	 *            支付类型，无线默认为空字符串，暂无其他值
	 * @return
	 * @author liujie
	 */
	public String modifyPayGiftCards(String userId, String password, String giftCardnNo, String orderId, String email, String shopType, String payType);

	/**
	 * 确认订单信息时更改地址和优惠码/券内容（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户ID
	 * @param couponFlag
	 *            1使用优惠券； 2使用优惠码；未使用传空字符串；
	 * @param coupon
	 *            flag为优惠券时，coupon为优惠券id；flag为优惠码时，coupon为优惠码串
	 * @param buyGids
	 *            购买商品的gid串，多个用“|”分割
	 * @param addressId
	 *            使用的收货地址id（兼容以后运费与地址有关的情况）
	 * @param topicNo
	 *            专题编号
	 * @return
	 * @author zhanghongwei
	 */
	public String modifyConfirmOrderInfo(String userId, String couponFlag, String coupon, String buyGids, String addressId, String topicNo, String orderSource);

	/**
	 * 获取优惠码信息（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户唯一ID
	 * @param couponCode
	 *            优惠码
	 * @param buyGids
	 *            购买商品的gid串，多个用“|”分割
	 * @param topicNo
	 *            专题或活动编号，多个”，”分割
	 * @return
	 * @author zhanghongwei
	 */
	public String findCouponCodeInfo(String userId, String couponCode, String buyGids, String topicNo);

	/**
	 * 获取用户礼品卡数量（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户唯一ID
	 * @param shopType
	 *            来自尚品1，奥莱2
	 * @return
	 * @author zhanghongwei
	 */
	public String findGiftCardsByUser(String userId, String shopType);

	/**
	 * 获取24小时内未被激活的礼品卡密钥
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 * @author zhanghongwei
	 */
	public String findGiftCardNewSecret(String userId);

	/**
	 * 获取礼品卡列表（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户ID
	 * @param type
	 *            礼品卡类型，1表示与帐号相关；2表示支付相关；注意：当类型为2时，状态值为空字符串
	 * @param status
	 *            礼品卡状态，0：全部；6：已激活（实物、电子）；7：已使用（实物、电子）；8：已冻结（实物、电子）；9：已失效（实物、电子）
	 *            ；10：已作废（实物、电子）。
	 * @param shopType
	 *            表示来自尚品1，奥莱2
	 * @param pageNo
	 *            当前页码（为0时显示全部不分页）
	 * @param pageSize
	 *            每页显示条数
	 * @return
	 * @author zhanghongwei
	 */
	public String findGiftCardList(String userId, String type, String status, String shopType, String pageNo, String pageSize);

	/**
	 * 获取某张礼品卡消费记录
	 * 
	 * @param userId
	 *            用户ID
	 * @param cardNo
	 *            礼品卡卡号
	 * @return
	 * @author zhanghongwei
	 */
	public String findGiftCardConsumption(String userId, String cardNo);

	/**
	 * 查询用户所有物流列表（分页）（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户ID
	 * @param flag
	 *            参数为0时，获取所有物流信息；为1时，logistics中仅包含最新的一条物流信息
	 * @param picw
	 *            图片宽度
	 * @param pich
	 *            图片高度
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @param orderType
	 *            表示订单来自尚品1，奥莱2
	 * @param isAll
	 *            1为包括已完成的订单；0、空或无参数，只获取正在进行的物流
	 * @return
	 * @author zhanghongwei
	 */
	public String findLogisticsList(String userId, String flag, String picw, String pich, String pageNo, String pageSize, String orderType, String isAll);

	/**
	 * 查询用户所有物流列表（分页）（尚品、奥莱共用）参数同上
	 * 
	 * @author zhanghongwei
	 */
	public List<LogisticsInfo> findOrderListObj(String userId, String flag, String picw, String pich, String pageNo, String pageSize, String orderType, String isAll);

	/**
	 * 物流详情（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param orderId
	 *            订单id
	 * @param ticketno
	 *            物流单号
	 * @return
	 * @author zhanghongwei
	 */
	public Logistics findLogisticsDetailObj(String userId, String orderId, String ticketNo);

	/**
	 * 物流详情新（尚品）
	 * 
	 * @param userId
	 *            用户Id
	 * @param orderId
	 *            订单id
	 * @return
	 * @author zhongchao
	 */
	public String getNewLogistice(String orderId, String userId);

	/**
	 * 一件购物提交订单
	 * 
	 * @param userId
	 *            用户Id
	 * @param productId
	 *            商品编号
	 * @param skuId
	 *            sku编号
	 * @param payTypeId
	 *            主支付方式
	 * @param payTypeChildId
	 *            子支付方式
	 * @param orderOrigin
	 *            订单来源
	 * @param shopType
	 *            表示订单来自尚品1，奥莱2
	 * @param consigneeName
	 *            收货人姓名
	 * @param province
	 *            省id
	 * @param provinceName
	 *            省份名称
	 * @param city
	 *            城市id
	 * @param cityName
	 *            城市名称
	 * @param area
	 *            区id
	 * @param areaName
	 *            区名称
	 * @param town
	 *            镇id
	 * @param townName
	 *            镇名
	 * @param address
	 *            详细地址
	 * @param consigneeName
	 *            收货人姓名
	 * @param zip
	 *            邮编
	 * @param tel
	 *            手机
	 * @return
	 * @author wangfeng
	 */
	public String quickOrder(QuickSubmitVO quickSubmitVO);

	/**
	 * 
	 * @Title: sendActivation
	 * @Description:发送优惠券激活码
	 * @param userid
	 *            用户唯一标识
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * @param type
	 *            操作类型(可组合，bind绑定手机号；coupon领取优惠券)；不同的类型用“+”相连接；如果是绑定手机，要指定手机号；
	 *            如果是领优惠券类型，还要指定激活码。如：bind:13819900999+coupon:661472359377
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月11日
	 */
	public String sendActivation(String userid, String shoptype, String type);

	/**
	 * 提交订单
	 * 
	 * @param orderVO
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public String submitOrder(CartOrder orderVO);

	/**
	 * 
	 * @Title: submitCommonOrder
	 * @Description:常规购物提交订单
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月3日
	 */
	public String submitCommonOrder(String... params);

	/**
	 * 常规购物提交订单
	 * 
	 * @param params
	 * @return
	 */
	public String submitCommonOrder(SubmitOrderParam submitOrderParam);
	
	/**
	 * 礼品卡、实物卡提交订单
	 * @param param
	 * @return
	 */
	public String submitGiftOrder(SubmitOrderParam param);

	/**
	 * 
	 * @Title: sendCoupon
	 * @Description:发送优惠券
	 * @param userid
	 *            用户唯一标识
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * @param type
	 *            操作类型(可组合，bind绑定手机号；coupon领取优惠券)；不同的类型用“+”相连接；如果是绑定手机，要指定手机号；
	 *            如果是领优惠券类型，还要指定激活码。如：bind:13819900999+coupon:661472359377
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月09日
	 */
	public String sendCoupon(String userId, String shopType, String type, String couponType);

	/**
	 * 
	 * @Title: payGiftCards
	 * @Description: 礼品卡支付
	 * @param userId
	 *            用户ID
	 * @param shopType
	 *            商品来源，1为尚品，2为奥莱
	 * @param payType
	 *            支付类型（暂时传空）
	 * @param orderId
	 *            订单ID
	 * @param password
	 *            礼品卡支付密码
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月5日
	 */
	public String payGiftCards(String userId, String shopType, String payType, String orderId, String password);

	/**
	 * 
	 * @Title: setGiftCardPassword
	 * @Description: 礼品卡修改密码
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月16日
	 */
	public String setGiftCardPassword(String userId, String password);

	/**
	 * 
	 * @Title:couponList
	 * @Description:优惠券激活后返回用户可用的优惠券列表
	 * @param userId
	 * @param orderSource
	 *            1表示从购物车来，2表示立即购买
	 * @return
	 * @author qinyingchun
	 * @date 2015年3月28日
	 */
	public String couponList(String userId, String orderSource, String buyId);

	/**
	 * 根据用户ID、子订单ID，确认收货
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            子订单ID
	 * @return 操作结果
	 * @date 2015年4月20日
	 */
	public String finishOrder(String userId, String orderId);

	/**
	 * 改变支付方式
	 * 
	 * @param userId
	 * @param mainOrderNo
	 * @param payType
	 * @return
	 */
	public String updatePayType(String userId, String mainOrderNo, String payType);

	/**
	 * 2.9.0 订单列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param status
	 * @return
	 */
	String orderList(String userId, String pageIndex, String pageSize, String status);

	/**
	 * 2.9.0 订单详情
	 * 
	 * @param userId
	 * @param mainOrderId
	 * @param orderId
	 * @return
	 */
	String orderDetail(String userId, String mainOrderId, String orderId,String isSplitOrder);

	public String cancelOrder(String userId, String orderId, String postArea);

	
	String queryGiftCardSecretKey(String mainOrderId, String userId);
	/**
	 * 更新待支付订单收货地址或者发票地址或者支付方式
	 * @param userId 用户ID
	 * @param addrId -1的时候不修改收货地址
	 * @param express 收货方式
	 * @param invoiceAddrId -1的时候不修改发票地址
	 * @param invoiceFlag 1开 0 不开
	 * @param invoiceType 发票类型
	 * @param invoiceTitle 发票抬头
	 * @param invoiceContent 发票内容
	 * @param isModifyInvoice 是否修改发票信息
	 * @return
	 * @author qinyingchun
	 */
	public String modifyOrder(String userId, String orderId, String addrId, String express, String invoiceAddrId, String invoiceFlag, String invoiceType, String invoiceTitle, String invoiceContent, String isModifyInvoice);
}
