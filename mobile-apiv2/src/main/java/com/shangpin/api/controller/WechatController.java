package com.shangpin.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.base.ContentBuilder;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.core.entity.AccountWeixin;
import com.shangpin.core.entity.AccountWeixinBind;
import com.shangpin.core.service.IAccountWeixinBindedService;
import com.shangpin.core.service.IAccountWeixinService;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.StringUtil;
//import com.shangpin.wechat.bo.base.CashBonusResult;
import com.shangpin.wechat.bo.base.TemplateMsgResult;
import com.shangpin.wechat.service.WeChatMerchantService;
import com.shangpin.wechat.service.WeChatPublicService;
/**
 * 微信接口
 * @author zrs
 *
 */
@Controller
@RequestMapping(value="/wechat")
public class WechatController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(WechatController.class);
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private WeChatPublicService weChatPublicService;
	@Autowired
	private WeChatMerchantService weChatMerchantService;
	@Autowired
	private SPBizUserService userService;
    @Autowired
    private IAccountWeixinBindedService accountWeixinBindedService;
    @Autowired
    private IAccountWeixinService accountWeixinService;
	
	/**
	 * 卡券核销
	 * @param accessToken token
	 * @param code 卡券编号
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/consume", method = { RequestMethod.POST, RequestMethod.GET })
	public String consume(@RequestParam String accessToken,@RequestParam  String code){

		if(!StringUtil.isNotEmpty(accessToken, code)){
			return returnParamError();
		}
		
		try {
			Map<String, Object> map = weChatPublicService.consumeCardCode(accessToken, code, null);			
			return JsonUtil.toJson(map);
			
		} catch (Exception e) {			
			logger.error("error:", e);
			return returnSystemError();
		}
	}
	
	/**
	 * 微信发送模板消息-支付成功
	 * 
	 * 	{{first.DATA}}
	 *
	 *	支付金额：{{orderMoneySum.DATA}}
	 *	商品信息：{{orderProductName.DATA}}
	 *	{{Remark.DATA}}
	 * 
	 * @param userId 用户id
	 * @param mainOrderId 主支付单号
	 * @param orderId 子订单号
	 * @param isSplitOrder 是否拆单
	 * @param orderMoneySum 订单金额
	 * @param orderProductName 商品名称
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/payOk", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<TemplateMsgResult> sendTemplateMsg4PayOk(@RequestParam String userId,@RequestParam String mainOrderId, @RequestParam String orderId, @RequestParam String isSplitOrder, 
			@RequestParam String orderMoneySum, @RequestParam String orderProductName){
		
		try{
			logger.info("[发送支付成功消息] userId={},mainOrderId={},orderId={},isSplitOrder={},orderMoneySum={},orderProductName={}",
					userId,mainOrderId,orderId,isSplitOrder,orderMoneySum,orderProductName);
			//查询用户微信id
			AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
			logger.info("[发送支付成功消息]mainOrderId={},orderId={}, 数据库返回accountWeixinBind={}",mainOrderId,orderId,accountWeixinBind);
			
			ContentBuilder<TemplateMsgResult> builder = new ContentBuilder<TemplateMsgResult>();
			String openId = null;
			if(accountWeixinBind!=null){
				openId = accountWeixinBind.getWeixinId();
			}
			if(StringUtil.isBlank(openId)){
				logger.info("[发送支付成功消息]mainOrderId={},orderId={},该用户没有绑定微信userId={}",mainOrderId,orderId,userId);
				return builder.buildDefError("该用户没有绑定微信", null);
			}
			logger.info("[发送支付成功消息]mainOrderId={},orderId={}, 开始调用微信接口",mainOrderId,orderId);		
			
			TemplateMsgResult templateMsgResult = weChatPublicService.sendTemplateMsg4PayOk(userId,openId, mainOrderId, orderId, isSplitOrder, orderMoneySum, orderProductName);
			return builder.buildDefSuccess(templateMsgResult);
			
		}catch(Exception e){
			logger.error("[发送支付成功消息]mainOrderId={},orderId={}, 开始调用微信接口e={}",mainOrderId,orderId,e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 领取红包通知
	 * 
	 *	{{first.DATA}} (恭喜您获得{title}红包)
	 *	成功领取：{{keyword1.DATA}}
	 *	红包金额：{{keyword2.DATA}}
	 *	{{remark.DATA}} (有效期：expiry，红包使用条件：{limit}。)
	 *
	 * @param userId 用户Id
	 * @param title 红包名称
	 * @param expiry 有效期
	 * @param limit 使用条件
	 * @param keyword1 领取的红包名称
	 * @param keyword2 红包金额
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/receiveRedEnvelope", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<TemplateMsgResult> sendTemplateMsg4ReceiveRedEnvelope(@RequestParam String userId,@RequestParam  String title,
			@RequestParam String expiry,@RequestParam  String limit,@RequestParam  String keyword1,@RequestParam  String keyword2){
		
		//查询用户微信id
		AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
		
		String openId = null;
		if(accountWeixinBind!=null){
			openId = accountWeixinBind.getWeixinId();
		}
		ContentBuilder<TemplateMsgResult> builder = new ContentBuilder<TemplateMsgResult>();
		if(StringUtil.isBlank(openId)){
			logger.info("该用户没有绑定微信userId={}",userId);
			return builder.buildDefError("该用户没有绑定微信", null);
		}
		TemplateMsgResult templateMsgResult = weChatPublicService.sendTemplateMsg4ReceiveRedEnvelope(userId, openId, title, expiry, limit, keyword1, keyword2);
		return builder.buildDefSuccess(templateMsgResult);
	}

	
	/**
	 * 快递签收通知
	 * 
	 *  {{first.DATA}}
	 *	签收人：{{keyword1.DATA}}
	 *	签收时间：{{keyword2.DATA}}
	 *	{{remark.DATA}}
	 * 
	 * @param userId 用户id
	 * @param mainOrderId 主支付单号
	 * @param orderId 子订单号
	 * @param isSplitOrder 是否拆单
	 * @param keyword1 签收人
	 * @param keyword2 签收时间
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/signForExpress", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<TemplateMsgResult> sendTemplateMsg4SignForExpress(String userId,String mainOrderId, String orderId,
			String isSplitOrder, String keyword1, String keyword2){
		
		//查询用户微信id
		AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
		
		String openId = null;
		if(accountWeixinBind!=null){
			openId = accountWeixinBind.getWeixinId();
		}
		ContentBuilder<TemplateMsgResult> builder = new ContentBuilder<TemplateMsgResult>();
		if(StringUtil.isBlank(openId)){
			logger.info("该用户没有绑定微信userId={}",userId);
			return builder.buildDefError("该用户没有绑定微信", null);
		}
		TemplateMsgResult templateMsgResult = weChatPublicService.sendTemplateMsg4SignForExpress(userId, openId, mainOrderId, orderId, isSplitOrder, keyword1, keyword2);
		return builder.buildDefSuccess(templateMsgResult);
	}
	/**
	 * 优惠券到期提醒
	 * 
	 *  {{first.DATA}}
	 *	主题：{{theme.DATA}}
	 *	券代号：{{code.DATA}}
	 *	有效期:{{date.DATA}}
	 *	{{remark.DATA}}
	 *
	 * @param userId 用户id
	 * @param theme 主题
	 * @param code 券代号
	 * @param date 有效期
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/couponExpiryRemind", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<TemplateMsgResult> sendTemplateMsg4CouponExpiryRemind(String userId, String theme, String code, String date){
		
		//查询用户微信id
		AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
		
		String openId = null;
		if(accountWeixinBind!=null){
			openId = accountWeixinBind.getWeixinId();
		}
		ContentBuilder<TemplateMsgResult> builder = new ContentBuilder<TemplateMsgResult>();
		if(StringUtil.isBlank(openId)){
			logger.info("该用户没有绑定微信userId={}",userId);
			return builder.buildDefError("该用户没有绑定微信", null);
		}
		TemplateMsgResult templateMsgResult = weChatPublicService.sendTemplateMsg4CouponExpiryRemind(userId, openId, theme, code, date);
		return builder.buildDefSuccess(templateMsgResult);
	}
	
	/**
	 * 到期提醒
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
	@ResponseBody
	@RequestMapping(value = "/expiryRemind", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<TemplateMsgResult> sendTemplateMsg4ExpiryRemind(String userId, String first, String remark, String name,String expDate){
		
		ContentBuilder<TemplateMsgResult> builder = new ContentBuilder<TemplateMsgResult>();
//		if(!StringUtil.isNotEmpty(userId, name,expDate)){
//			return builder.buildDefError("参数不可空",null);
//		}
		//查询用户微信id
		AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
		
		String openId = null;
		if(accountWeixinBind!=null){
			openId = accountWeixinBind.getWeixinId();
		}

		if(StringUtil.isBlank(openId)){
			logger.info("该用户没有绑定微信userId={}",userId);
			return builder.buildDefError("该用户没有绑定微信", null);
		}
		TemplateMsgResult templateMsgResult = weChatPublicService.sendTemplateMsg4ExpiryRemind(openId, first, remark, name, expDate);
		return builder.buildDefSuccess(templateMsgResult);
	}
	/**
	 * 退款通知
	 * {{first.DATA}}
	 *
	 *	退款原因：{{reason.DATA}}
	 *	退款金额：{{refund.DATA}}
	 *	{{remark.DATA}}
	 * 
	 * @param userId 用户id
	 * @param mainOrderId 主支付单号
	 * @param orderId 子订单号
	 * @param isSplitOrder 是否拆单
	 * @param reason 退款原因
	 * @param refund 退款金额
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/refundNotice", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<TemplateMsgResult> sendTemplateMsg4RefundNotice(String userId,String mainOrderId, String orderId,
			String isSplitOrder, String reason, String refund){
		
		//查询用户微信id
		AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
		
		String openId = null;
		if(accountWeixinBind!=null){
			openId = accountWeixinBind.getWeixinId();
		}
		ContentBuilder<TemplateMsgResult> builder = new ContentBuilder<TemplateMsgResult>();
		if(StringUtil.isBlank(openId)){
			logger.info("该用户没有绑定微信userId={}",userId);
			return builder.buildDefError("该用户没有绑定微信", null);
		}		
		TemplateMsgResult templateMsgResult = weChatPublicService.sendTemplateMsg4RefundNotice(userId, openId, mainOrderId, orderId, isSplitOrder, reason, refund);
		return builder.buildDefSuccess(templateMsgResult);
	}

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
	 * @param userId 用户id
	 * @param mainOrderId 主支付单号
	 * @param orderId 子订单号
	 * @param isSplitOrder 是否拆单
	 * @param keyword1 订单内容
	 * @param keyword2 物流服务
	 * @param keyword3快递单号
	 * @param keyword4 收货信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderDeliver", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<TemplateMsgResult> sendTemplateMsg4OrderDeliver(String userId,String mainOrderId, String orderId,
			String isSplitOrder, String keyword1, String keyword2, String keyword3, String keyword4){
		
		//查询用户微信id
		AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
		
		String openId = null;
		if(accountWeixinBind!=null){
			openId = accountWeixinBind.getWeixinId();
		}
		ContentBuilder<TemplateMsgResult> builder = new ContentBuilder<TemplateMsgResult>();
		if(StringUtil.isBlank(openId)){
			logger.info("该用户没有绑定微信userId={}",userId);
			return builder.buildDefError("该用户没有绑定微信", null);
		}
		TemplateMsgResult templateMsgResult = weChatPublicService.sendTemplateMsg4OrderDeliver(userId, openId, mainOrderId, orderId, isSplitOrder, keyword1, keyword2, keyword3, keyword4);
		return builder.buildDefSuccess(templateMsgResult);
	}


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
	 * @param first 可不传(不传默认发送:恭喜您成功领取了优惠券。)
	 * @param remark 可不传(不传默认发送:查看优惠券)
	 * @param keyword1 优惠券名称
	 * @param keyword2 兑换码
	 * @param keyword3 失效期
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCouponSuccess", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<TemplateMsgResult>  sendTemplateMsg4GetCouponSuccess(String userId, String first,
			String remark, String keyword1, String keyword2, String keyword3){
		
		ContentBuilder<TemplateMsgResult> builder = new ContentBuilder<TemplateMsgResult>();
//		if(!StringUtil.isNotEmpty(userId, keyword1,keyword2,keyword3)){
//			return builder.buildDefError("参数不可空",null);
//		}
		//查询用户微信id
		AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
		
		String openId = null;
		if(accountWeixinBind!=null){
			openId = accountWeixinBind.getWeixinId();
		}
		if(StringUtil.isBlank(openId)){
			logger.info("该用户没有绑定微信userId={}",userId);
			return builder.buildDefError("该用户没有绑定微信", null);
		}
		TemplateMsgResult templateMsgResult = weChatPublicService.sendTemplateMsg4GetCouponSuccess(openId, first, remark, keyword1, keyword2, keyword3);
		return builder.buildDefSuccess(templateMsgResult);
	}
	
	/**
	 * 订单提交成功
	 * 
		{{first.DATA}}
		
		订单号：{{orderID.DATA}}
		待付金额：{{orderMoneySum.DATA}}
		{{backupFieldName.DATA}}{{backupFieldData.DATA}}
		{{remark.DATA}}
	 *
	 * @param userId userId
	 * @param first
	 * @param remark 
	 * @param orderID 订单号
	 * @param orderMoneySum 待付金额
	 * @param backupFieldName 扩展名称
	 * @param backupFieldData 扩展值
	 * @param jumpUrl 跳转地址
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submitOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<TemplateMsgResult> sendTemplateMsg4SubmitOrder(String userId, String first, String remark,
			String orderID, String orderMoneySum, String backupFieldName,String backupFieldData,String jumpUrl){
		
		ContentBuilder<TemplateMsgResult> builder = new ContentBuilder<TemplateMsgResult>();

		//查询用户微信id
		AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
		
		String openId = null;
		if(accountWeixinBind!=null){
			openId = accountWeixinBind.getWeixinId();
		}
		if(StringUtil.isBlank(openId)){
			logger.info("该用户没有绑定微信userId={}",userId);
			return builder.buildDefError("该用户没有绑定微信", null);
		}
		TemplateMsgResult templateMsgResult = weChatPublicService.sendTemplateMsg4SubmitOrder(openId, first, remark, orderID, orderMoneySum, backupFieldName, backupFieldData, jumpUrl);
		return builder.buildDefSuccess(templateMsgResult);
	}
	
/*	*//**
	 * 查询客户是否关注公众号的接口	
	 * @param weixinId
	 * @return true 是
	 *//*
	@ResponseBody
	@RequestMapping(value = "/queryIsSubscribeWinxin", method = { RequestMethod.POST, RequestMethod.GET })
	public boolean queryIsSubscribeWinxin(String weixinId){
		
		AccountWeixin accountWeixin = accountWeixinService.findByWeixinId(weixinId);
		
		if(accountWeixin == null){
			return false;			
		}
		if(accountWeixin.getUnsubscribeTime() != null){
			return false;
		}
		return true;		
	}*/
	
	/**
	 * 查询客户是否关注公众号的接口	
	 * @param userId
	 * @param weixinId
	 * @return true 是
	 */
	@ResponseBody
	@RequestMapping(value = "/queryIsSubscribeWinxin", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<String> queryIsSubscribeWinxin(String userId, String weixinId){
		
		ContentBuilder<String> builder = new ContentBuilder<String>();
		
		//不能同时为空
		if(StringUtil.isBlank(userId) && StringUtil.isBlank(weixinId)){
			return builder.buildDefError("参数userId和weixinId不能同时为空");
		}
		
		//微信id为空 根据绑定表查询
		if(StringUtil.isBlank(weixinId)){
			//查询用户微信id
			AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
			
			if(accountWeixinBind == null){
				return builder.buildDefError("用户微信没用绑定尚品账户", null);
			}
			
			weixinId = accountWeixinBind.getWeixinId();
		}		
		
		AccountWeixin accountWeixin = accountWeixinService.findByWeixinId(weixinId);
		
		if(accountWeixin == null){
			return builder.buildDefError("用户微信没有关注尚品公众号", null);		
		}
		if(accountWeixin.getUnsubscribeTime() != null){
			return builder.buildDefError("用户微信已取消关注尚品公众号", null);
		}
		return builder.buildDefSuccess("用户已关注尚品公众号", null);
	}

	/**
	 * 微信发送模板消息 通用
	 * @param openId 微信id
	 * @param templateId 模板id
	 * @param jumpUrl 调整详情地址
	 * @param map data数据map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendTemplateMsg", method = { RequestMethod.POST, RequestMethod.GET })
	public ContentBuilder<TemplateMsgResult> sendTemplateMsg(String userId,String templateId,
			String jumpUrl,  String json){
		
		Map<String,String> map = JsonUtil.fromJson(json, new TypeToken<Map<String,String>>(){});
		
		ContentBuilder<TemplateMsgResult> builder = new ContentBuilder<TemplateMsgResult>();

		//查询用户微信id
		AccountWeixinBind accountWeixinBind = accountWeixinBindedService.findByUserId(userId);
		
		String openId = null;
		if(accountWeixinBind!=null){
			openId = accountWeixinBind.getWeixinId();
		}
		if(StringUtil.isBlank(openId)){
			logger.info("该用户没有绑定微信userId={}",userId);
			return builder.buildDefError("该用户没有绑定微信", null);
		}
		TemplateMsgResult templateMsgResult = weChatPublicService.sendTemplateMsg(openId, templateId, jumpUrl, map);
		return builder.buildDefSuccess(templateMsgResult);
	}
	

	


}















