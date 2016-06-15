package com.shangpin.wechat.service;


import com.shangpin.wechat.bo.base.*;
import com.shangpin.wechat.bo.reply.CustomReply;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 微信公众平台的service
 * 
 * @author huangxiaoliang
 *
 */
public interface WeChatPublicService {

	/**
	 * 获取微信的token,默认从cache里取值
	 * 
	 * @param fromCache
	 * @return
	 */
	public String getToken();

	/**
	 * 获取jsapi的ticket
	 * 
	 * @return
	 */
	public String getTicket();
	
	/**
	 * 通过code获取网页授权的access_token
	 * @param code
	 * @return
	 */
	public String getAccessToken(String code);
	
	public AccessToken getAccessTokenObj(String code);
	
	/**
	 * 根据网页授权的token获取用户的信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public String userInfo(String accessToken, String openId);
	
	public UserInfo userInfoObj(String accessToken, String openId);
	
	/**
	 * 微信发送消息
	 * @param access_token
	 * @param msg
	 */
	public String sendMsg(String access_token, String msg);

	/**
	 * 微信发送客服回复消息
	 * @param customReply 发送信息实体
	 */
	public String sendMsg(CustomReply customReply);

	/**
	 * 常规获取微信用户基本信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public String baseUser(String accessToken, String openId);
	
	public UserInfo baseUserObj(String accessToken, String openId);
	
	/**
	 * 创建二维码
	 * @param accessToken
	 * @param content
	 * @return
	 */
	public String qrcodeCreate(String accessToken, String content);
	
	/**
	 * 调取二维码
	 * @param ticket
	 * @return
	 */
	public String showQRCode(String ticket);
	
	/**
	 * 保存二维码
	 * @param openId
	 * @param savePath
	 * @return
	 */
	public void saveQRCode(String openId, String savePath, String ticket);
	
	/**
	 * 上传媒体文件
	 * @param accessToken
	 * @param type
	 * @param path
	 * @return
	 */
	public String upload(String accessToken, String type, String path);
	
	/**
	 * 创建卡券
	 * @param accessToken
	 * @param postJson
	 * @return
	 */
	public String createCard(String accessToken, String postJson);
	
	/**
	 * 卡券查询
	 * @param accessToken
	 * @param code
	 * @param cardId 可为空
	 * @return
	 */
	public QueryCardCodeResult queryCardCode(String accessToken, String code, String cardId);

	/**
	 * 卡券核销
	 * @param accessToken
	 * @param code
	 * @param cardId 可为空
	 * @return
	 */
	public Map<String, Object> consumeCardCode(String accessToken, String code, String cardId);

	
	/**
	 * 上传logo
	 * @param accessToken
	 * @param logo 图片文件
	 * @return 上传后的连接
	 */
	public String uploadLogo(String accessToken, File logo);
	
	/**
	 * 查询门店地址列表
	 * @param accessToken
	 * @param pageNo 页数
	 * @param pageSize 每页数量
	 * @return json
	 */
	public String queryPoiListWithJson(String accessToken, int pageNo, int pageSize);
	/**
	 * 查询门店地址列表
	 * @param accessToken
	 * @param pageNo 页数
	 * @param pageSize 每页数量
	 * @return QueryPoiListResult
	 */
	public QueryPoiListResult queryPoiList(String accessToken, int pageNo, int pageSize);
	
	/**
	 * 获取用户已领卡券
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public QueryUserCardResult queryUserCard(String accessToken, String openid);
	/**
	 * 获取用户已领卡券
	 * @param accessToken
	 * @param openid
	 * @param cardId
	 * @return
	 */
	public QueryUserCardResult queryUserCard(String accessToken, String openid, String cardId);
	
	/**
	 * 批量查询卡信息
	 * @param accessToken
	 * @param pageNo 页数
	 * @param pageSize 每页数量
	 * @return QueryBatchCardResult
	 */
	public QueryBatchCardResult queryBatchCard(String accessToken, int pageNo, int pageSize);
	/**
	 * 批量查询卡信息
	 * @param accessToken
	 * @param pageNo 页数
	 * @param pageSize 每页数量
	 * @param statusList 状态列表 	“CARD_STATUS_NOT_VERIFY”,待审核；“CARD_STATUS_VERIFY_FAIL”,审核失败；“CARD_STATUS_VERIFY_OK”，通过审核；“CARD_STATUS_USER_DELETE”，卡券被商户删除；“CARD_STATUS_USER_DISPATCH”，在公众平台投放过的卡券 
	 * @return QueryBatchCardResult
	 */
	public QueryBatchCardResult queryBatchCard(String accessToken, int pageNo, int pageSize, List<String> statusList);
	
	/**
	 * 查询卡券详情
	 * @param accessToken
	 * @param cardId 卡券id
	 * @return
	 */
	public String queryCardDetailByCardIdWithJson(String accessToken, String cardId);
	/**
	 * 查询卡券详情
	 * @param accessToken
	 * @param cardId 卡券id
	 * @return
	 */
	public QueryCardDetailResult queryCardDetailByCardId(String accessToken, String cardId);
	
	/**
	 * 微信发送模板消息
	 * @param access_token
	 * @param msg body
	 */
	public TemplateMsgResult sendTemplateMsg(String accessToken, String msg);
	
	/**
	 * 微信发送模板消息 通用
	 * @param openId 微信id
	 * @param templateId 模板id
	 * @param jumpUrl 调整详情地址
	 * @param map data数据map
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg(String openId,String templateId,
			String jumpUrl,  Map<String, String> map);
	
	/**
	 * 微信发送模板消息-支付成功
	 * 
	 * 	{{first.DATA}}
	 *
	 *	支付金额：{{orderMoneySum.DATA}}
	 *	商品信息：{{orderProductName.DATA}}
	 *	{{Remark.DATA}}
	 * 
	 * @param userId 用户Id
	 * @param openId 微信Id
	 * @param mainOrderId 主支付单号
	 * @param orderId 子订单号
	 * @param isSplitOrder 是否拆单
	 * @param orderMoneySum 订单金额
	 * @param orderProductName 商品名称
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg4PayOk(String userId,String openId,String mainOrderId, String orderId,String isSplitOrder, String orderMoneySum, String orderProductName);
	
	/**
	 * 快递签收通知
	 * 
	 *  {{first.DATA}}
	 *	签收人：{{keyword1.DATA}}
	 *	签收时间：{{keyword2.DATA}}
	 *	{{remark.DATA}}
	 * 
	 * @param userId 用户Id
	 * @param openId 微信Id
	 * @param mainOrderId 主支付单号
	 * @param orderId 子订单号
	 * @param isSplitOrder 是否拆单
	 * @param keyword1 签收人
	 * @param keyword2 签收时间
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg4SignForExpress(String userId,String openId,String mainOrderId, String orderId,
			String isSplitOrder, String keyword1, String keyword2);
	/**
	 * 优惠券到期提醒
	 * 
	 *  {{first.DATA}}
	 *	主题：{{theme.DATA}}
	 *	券代号：{{code.DATA}}
	 *	有效期:{{date.DATA}}
	 *	{{remark.DATA}}
	 *
	 * @param userId 用户Id
	 * @param openId 微信Id
	 * @param theme 主题
	 * @param code 券代号
	 * @param date 有效期
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg4CouponExpiryRemind(String userId,String openId, String theme, String code, String date);

	/**
	 * 退款通知
	 * {{first.DATA}}
	 *
	 *	退款原因：{{reason.DATA}}
	 *	退款金额：{{refund.DATA}}
	 *	{{remark.DATA}}
	 * 
	 * @param userId 用户Id
	 * @param openId 微信Id
	 * @param mainOrderId 主支付单号
	 * @param orderId 子订单号
	 * @param isSplitOrder 是否拆单
	 * @param reason 退款原因
	 * @param refund 退款金额
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg4RefundNotice(String userId,String openId,String mainOrderId, String orderId,
			String isSplitOrder, String reason, String refund);
	

	/**
	 * 订单发货通知
	 * 
	 * {{first.DATA}}
	 *	订单内容：{{keyword1.DATA}}
	 *	物流服务：{{keyword2.DATA}}
	 *	快递单号：{{keyword3.DATA}}
	 *	收货信息：{{keyword4.DATA}}
	 *	{{remark.DATA}}
	 * 
	 * @param userId 用户Id
	 * @param openId 微信Id
	 * @param mainOrderId 主支付单号
	 * @param orderId 子订单号
	 * @param isSplitOrder 是否拆单
	 * @param keyword1 订单内容
	 * @param keyword2 物流服务
	 * @param keyword3快递单号
	 * @param keyword4 收货信息
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg4OrderDeliver(String userId,String openId,String mainOrderId, String orderId,
			String isSplitOrder, String keyword1, String keyword2, String keyword3, String keyword4);
	
	/**
	 * 领取红包通知
	 * 
	 *	{{first.DATA}} (恭喜您获得{title}红包)
	 *	成功领取：{{keyword1.DATA}}
	 *	红包金额：{{keyword2.DATA}}
	 *	{{remark.DATA}} (有效期：expiry，红包使用条件：{limit}。)
	 *
	 * @param userId 用户Id
	 * @param openId 微信Id
	 * @param title 红包名称
	 * @param expiry 有效期
	 * @param limit 使用条件
	 * @param keyword1 领取的红包名称
	 * @param keyword2 红包金额
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg4ReceiveRedEnvelope(String userId,String openId, String title,
			String expiry, String limit, String keyword1, String keyword2);

	/**
	 * 领取红包通知
	 * 
	 *	{{first.DATA}} (恭喜您获得{title}红包)
	 *	成功领取：{{keyword1.DATA}}
	 *	红包金额：{{keyword2.DATA}}
	 *	{{remark.DATA}} (有效期：expiry，红包使用条件：{limit}。)
	 *
	 * @param userId 用户Id
	 * @param openId 微信Id
	 * @param first 
	 * @param remark 
	 * @param keyword1 领取的红包名称
	 * @param keyword2 红包金额
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg4ReceiveRedEnvelope(String openId, String first,
			String remark, String keyword1, String keyword2);
	/**
	 * 优惠券领取成功通知
	 * 
	 *	{{first.DATA}}
	 *	优惠券名称：{{keyword1.DATA}}
	 *	兑换码：{{keyword2.DATA}}
	 *	失效期：{{keyword3.DATA}}
	 *	{{remark.DATA}}
	 *
	 * @param userId 用户Id
	 * @param openId 微信Id
	 * @param first
	 * @param remark 
	 * @param keyword1 优惠券名称
	 * @param keyword2 兑换码
	 * @param keyword3 失效期
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg4GetCouponSuccess(String openId, String first,
			String remark, String keyword1, String keyword2, String keyword3);
	
	/**
	 * 到期提醒通知
	 * 
	 *	{{first.DATA}}
	 *
	 *	您的{{name.DATA}}有效期至{{expDate.DATA}}。
	 *	{{remark.DATA}}
	 *
	 * @param openId 微信Id
	 * @param first
	 * @param remark 
	 * @param name 名称
	 * @param expDate 有效期
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg4ExpiryRemind(String openId, String first,
			String remark, String name, String expDate);
	
/**
	 * 创建菜单
	 * @param accessToken 接口调用凭证token
	 * @param jsonMenu post提交的json数据
	 * @return
	 */
	public ErrorInfo createMenu(String accessToken, String jsonMenu);
	
	/**
	 * 删除菜单
	 * @param accessToken 接口调用凭证token
	 * @return
	 */
	public ErrorInfo delMenu(String accessToken); 
	
	/**
	 * 查新菜单
	 * @param accessToken 接口调用凭证token
	 * @return json字符串
	 */
	public String getMenu(String accessToken);
	
	/**
	 * 查新菜单
	 * @param accessToken accessToken 接口调用凭证token
	 * @return 实体
	 */
	public MenuButton getMenuObj(String accessToken);
	/**
	 * 订单提交成功
	 * 
		{{first.DATA}}
		
		订单号：{{orderID.DATA}}
		待付金额：{{orderMoneySum.DATA}}
		{{backupFieldName.DATA}}{{backupFieldData.DATA}}
		{{remark.DATA}}
	 *
	 * @param openId 微信Id
	 * @param first
	 * @param remark 
	 * @param orderID 
	 * @param orderMoneySum
	 * @param backupFieldName
	 * @param backupFieldData
	 * @param jumpUrl
	 * @return
	 */
	public TemplateMsgResult sendTemplateMsg4SubmitOrder(String openId, String first, String remark,
			String orderID, String orderMoneySum, String backupFieldName,String backupFieldData,String jumpUrl);
	}
