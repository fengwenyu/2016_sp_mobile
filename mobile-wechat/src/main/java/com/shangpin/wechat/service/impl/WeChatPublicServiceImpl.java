package com.shangpin.wechat.service.impl;

import com.shangpin.utils.*;
import com.shangpin.wechat.bo.base.*;
import com.shangpin.wechat.bo.reply.CustomReply;
import com.shangpin.wechat.constants.CommonConstants;
import com.shangpin.wechat.constants.PublicPlatformConstants;
import com.shangpin.wechat.constants.enums.TemplateMsgEnum;
import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wechat.utils.common.CacheUtil;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeChatPublicServiceImpl implements WeChatPublicService {

	private static final Logger logger = LoggerFactory
			.getLogger(WeChatOpenServiceImpl.class);

	private static Map<String, String> tokenParams = null;

	private static Map<String, String> ticketParams = null;

	static {
		tokenParams = new HashMap<String, String>();
		tokenParams
				.put("grant_type", PublicPlatformConstants.PUBLIC_GRANT_TYPE);
		tokenParams.put("appid", PublicPlatformConstants.PUBLIC_APP_ID);
		tokenParams.put("secret", PublicPlatformConstants.PUBLIC_APP_SECRET);

		ticketParams = new HashMap<String, String>();
		ticketParams.put("type", PublicPlatformConstants.PUBLIC_JSAPI_TYPE);

	}

	@Override
	public String getToken() {
		String result = CacheUtil.findData(
				PublicPlatformConstants.PUBLIC_PLATFORM_CACHE, "getToken",
				tokenParams, PublicPlatformConstants.PUBLIC_TOKEN_URL, "GET");
		try {
			WeChatToken token = JSONUtils.json2pojo(result, WeChatToken.class);
			return token.getAccess_token();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getTicket() {
		ticketParams.put("access_token", getToken());
		String result = CacheUtil.findData(
				PublicPlatformConstants.PUBLIC_PLATFORM_CACHE, "getTicket",
				ticketParams, PublicPlatformConstants.PUBLIC_TICKET_URL, "GET");
		try {
			WeiXinTicket ticket = JSONUtils.json2pojo(result,
					WeiXinTicket.class);
			return ticket.getTicket();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAccessToken(String code) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", PublicPlatformConstants.PUBLIC_APP_ID);
		params.put("secret", PublicPlatformConstants.PUBLIC_APP_SECRET);
		params.put("code", code);
		params.put("grant_type", PublicPlatformConstants.GRANT_TYPE);
		String result = HttpClientUtil.doGet(
				PublicPlatformConstants.ACCESS_TOKEN, params);
		return result;
	}

	@Override
	public String userInfo(String accessToken, String openId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("openid", openId);
		params.put("lang", "zh_CN");
		String result = HttpClientUtil.doGet(PublicPlatformConstants.USER_INFO,
				params);
		return result;
	}

	@Override
	public AccessToken getAccessTokenObj(String code) {
		String json = this.getAccessToken(code);
		logger.debug("access token json:" + json);
		AccessToken token = JsonUtil.fromJson(json, AccessToken.class);
		return token;
	}

	@Override
	public UserInfo userInfoObj(String accessToken, String openId) {
		String json = this.userInfo(accessToken, openId);
		logger.debug("user info json:" + json);
		UserInfo userInfo = JsonUtil.fromJson(json, UserInfo.class);
		return userInfo;
	}

	@Override
	public String sendMsg(String access_token, String msg) {
		String url = PublicPlatformConstants.SEND_MSG + "?access_token="
				+ access_token;
		return WebContentUtil.readContentFromPost(url, msg);

	}

	@Override
	public String sendMsg(CustomReply customReply) {
		String msg = JsonUtil.toJson(customReply);
		return sendMsg(getToken(), msg);
	}

	@Override
	public String baseUser(String accessToken, String openId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("openid", openId);
		params.put("lang", "zh_CN");
		String result = HttpClientUtil.doGet(
				PublicPlatformConstants.USER_INFO_BASE, params);
		return result;
	}

	@Override
	public UserInfo baseUserObj(String accessToken, String openId) {
		String json = this.baseUser(accessToken, openId);
		logger.debug("user info json:" + json);
		UserInfo userInfo = JsonUtil.fromJson(json, UserInfo.class);
		return userInfo;
	}

	@Override
	public String qrcodeCreate(String accessToken, String content) {
		String url = PublicPlatformConstants.QRCODE_CREATE + "?access_token="
				+ accessToken;
		return WebContentUtil.readContentFromPost(url, content);
	}

	@Override
	public String showQRCode(String ticket) {
		return PublicPlatformConstants.SHOW_QRCODE + "?ticket=" + ticket;
	}

	@Override
	public String upload(String accessToken, String type, String path) {
		String url = PublicPlatformConstants.UPLOAD + "?access_token="
				+ accessToken + "&type=" + type;
		try {
			return WebContentUtil.readContentFromFile(url, path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveQRCode(String openId, String savePath, String ticket) {
		String url = PublicPlatformConstants.SHOW_QRCODE + "?ticket=" + ticket;
		try {
			HttpClientUtil.downloadFile(url, savePath);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String createCard(String accessToken, String postJson) {
		String url = PublicPlatformConstants.CREATE_CARD + "?access_token="
				+ accessToken;
		try {
			String resultJson = HttpClientUtil.doPostWithJSON(url, postJson);
			CreateCardResult createCardResult = JsonUtil.fromJson(resultJson,
					CreateCardResult.class);
			if (!"0".equals(createCardResult.getErrcode())) {
				logger.info("WeChat createCard error, errmsg={}",
						createCardResult.getErrmsg());
				return null;
			}
			return createCardResult.getCard_id();
		} catch (Exception e) {
			logger.error("WeChat createCard:e={}" + e);
		}
		return null;
	}

	@Override
	public QueryCardCodeResult queryCardCode(String accessToken, String code,
			String cardId) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("code", code);
		if (!StringUtil.isBlank(cardId)) {
			jsonMap.put("card_id", cardId);
		}

		jsonMap.put("check_consume", true);
		String url = PublicPlatformConstants.QUERY_CARD_CODE + "?access_token="
				+ accessToken;
		try {
			String resultJson = HttpClientUtil.doPostWithJSON(url,
					JsonUtil.toJson(jsonMap));
			logger.error("WeChat queryCardCode:resultJson={}", resultJson);
			QueryCardCodeResult queryCardCodeResult = JsonUtil.fromJson(
					resultJson, QueryCardCodeResult.class);

			return queryCardCodeResult;
		} catch (Exception e) {
			logger.error("WeChat queryCardCode:e={}" + e);
		}
		return null;
	}

	@Override
	public Map<String, Object> consumeCardCode(String accessToken, String code,
			String cardId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			QueryCardCodeResult queryCardCodeResult = queryCardCode(
					accessToken, code, cardId);
			// 返回码是否正确
			if (!"0".equals(queryCardCodeResult.getErrcode())) {
				logger.info("WeChat consumeCardCode error, errmsg={}",
						queryCardCodeResult.getErrmsg());
				throw new Exception(queryCardCodeResult.getErrmsg());
			}

			ConsumeCardCodeResult consumeCardCodeResult = consumeByCode(
					accessToken, code, cardId);

			if (!"0".equals(consumeCardCodeResult.getErrcode())) {
				throw new Exception(consumeCardCodeResult.getErrmsg());
			}

			map.put("code", 0);
			map.put("msg", "成功");
			map.put("content", "{}");
			return map;
		} catch (Exception e) {
			logger.error("WeChat queryCardCode:e={}" + e);
			map.put("code", 1);
			map.put("msg", "失败");
			map.put("content", "{\"cause\":\"" + e.getMessage() + "\"}");
			return map;
		}
	}

	private ConsumeCardCodeResult consumeByCode(String accessToken,
			String code, String cardId) {

		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("code", code);
		if (!StringUtil.isBlank(cardId)) {
			jsonMap.put("card_id", cardId);
		}
		String url = PublicPlatformConstants.CONSUME_CARD_CODE
				+ "?access_token=" + accessToken;
		try {
			String jsonResult = HttpClientUtil.doPostWithJSON(url,
					JsonUtil.toJson(jsonMap));
			ConsumeCardCodeResult consumeCardCodeResult = JsonUtil.fromJson(
					jsonResult, ConsumeCardCodeResult.class);
			return consumeCardCodeResult;
		} catch (Exception e) {
			logger.error("WeChat queryCardCode:e={}" + e);
		}
		return null;
	}

	@Override
	public String uploadLogo(String accessToken, File logo) {
		String url = PublicPlatformConstants.UPLOAD_LOGO + "?access_token="
				+ accessToken;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("buffer", logo);
			String resultJson = HttpClientUtil.doPostFile(url, params);
			logger.info("WeChat uploadLogo, resultJson={}", resultJson);
			UploadLogoResult uploadLogoResult = JsonUtil.fromJson(resultJson,
					UploadLogoResult.class);
			if (uploadLogoResult == null) {
				logger.info("WeChat uploadLogo error, uploadLogoResult is null");
				return null;
			}
			// 事实并没有返回errcode
			// if(!"0".equals(uploadLogoResult.getErrcode())){
			// logger.info("WeChat uploadCardLogo error, errmsg={}",uploadLogoResult.getErrmsg());
			// return null;
			// }
			return uploadLogoResult.getUrl();
		} catch (Exception e) {
			logger.error("WeChat uploadLogo:e={}" + e);
		}
		return null;
	}

	@Override
	public String queryPoiListWithJson(String accessToken, int pageNo,
			int pageSize) {
		String url = PublicPlatformConstants.QUERY_POI_LIST + "?access_token="
				+ accessToken;
		try {
			// 起始位置
			int offset = (pageNo - 1 < 0 ? 0 : pageNo - 1) * pageSize;
			// 数量限制
			int count = pageSize;

			// 入参跟文档有出入,实际按此种方式发送
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("begin", offset);
			params.put("limit", count);

			String resultJson = HttpClientUtil.doPostWithJSON(url,
					JsonUtil.toJson(params));
			logger.info("WeChat queryPoiList, resultJson={}", resultJson);
			return resultJson;
		} catch (Exception e) {
			logger.error("WeChat queryPoiList:e={}" + e);
		}
		return null;
	}

	@Override
	public QueryPoiListResult queryPoiList(String accessToken, int pageNo,
			int pageSize) {
		String resultJson = queryPoiListWithJson(accessToken, pageNo, pageSize);
		return JsonUtil.fromJson(resultJson, QueryPoiListResult.class);
	}

	@Override
	public QueryUserCardResult queryUserCard(String accessToken, String openid) {
		return queryUserCard(accessToken, openid, null);
	}

	@Override
	public QueryUserCardResult queryUserCard(String accessToken, String openid,
			String cardId) {
		String url = PublicPlatformConstants.QUEYR_USER_CARD + "?access_token="
				+ accessToken;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("openid", openid);
			if (!StringUtil.isBlank(cardId)) {
				params.put("card_id", cardId);
			}
			String resultJson = HttpClientUtil.doPostWithJSON(url,
					JsonUtil.toJson(params));
			logger.info("WeChat queryUserCard, resultJson={}", resultJson);
			QueryUserCardResult queryUserCardResult = JsonUtil.fromJson(
					resultJson, QueryUserCardResult.class);
			return queryUserCardResult;
		} catch (Exception e) {
			logger.error("WeChat queryUserCard:e={}" + e);
		}
		return null;
	}

	@Override
	public QueryBatchCardResult queryBatchCard(String accessToken, int pageNo,
			int pageSize) {
		return queryBatchCard(accessToken, pageNo, pageSize, null);
	}

	@Override
	public QueryBatchCardResult queryBatchCard(String accessToken, int pageNo,
			int pageSize, List<String> statusList) {
		String url = PublicPlatformConstants.QUEYR_BATCH_CARD
				+ "?access_token=" + accessToken;
		try {
			// 起始位置
			int offset = (pageNo - 1 < 0 ? 0 : pageNo - 1) * pageSize;
			// 数量限制
			int count = pageSize;

			// 入参跟文档有出入,实际按此种方式发送
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("offset", offset);
			params.put("count", count);
			if (statusList != null && !statusList.isEmpty()) {
				params.put("status_list ", statusList);
			}

			String resultJson = HttpClientUtil.doPostWithJSON(url,
					JsonUtil.toJson(params));
			logger.info("WeChat queryBatchCard, resultJson={}", resultJson);
			QueryBatchCardResult queryBatchCardResult = JsonUtil.fromJson(
					resultJson, QueryBatchCardResult.class);
			return queryBatchCardResult;
		} catch (Exception e) {
			logger.error("WeChat queryBatchCard:e={}" + e);
		}
		return null;
	}

	@Override
	public String queryCardDetailByCardIdWithJson(String accessToken,
			String cardId) {
		String url = PublicPlatformConstants.QUERY_CARD_DETAIL
				+ "?access_token=" + accessToken;
		try {
			// 入参跟文档有出入,实际按此种方式发送
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("card_id", cardId);

			String resultJson = HttpClientUtil.doPostWithJSON(url,
					JsonUtil.toJson(params));
			logger.info("WeChat queryCardDetailByCardId, resultJson={}",
					resultJson);

			return resultJson;
		} catch (Exception e) {
			logger.error("WeChat queryCardDetailByCardId:e={}" + e);
		}
		return null;
	}

	@Override
	public QueryCardDetailResult queryCardDetailByCardId(String accessToken,
			String cardId) {
		String resultJson = queryCardDetailByCardIdWithJson(accessToken, cardId);
		return JsonUtil.fromJson(resultJson, QueryCardDetailResult.class);
	}

	@Override
	public TemplateMsgResult sendTemplateMsg(String accessToken, String msg) {

		String url = PublicPlatformConstants.SEND_TEMPLATE_MSG
				+ "?access_token=" + accessToken;
		try {

			String resultJson = HttpClientUtil.doPostWithJSON(url, msg);
			logger.info("WeChat sendTemplateMsg,入参msg={} resultJson={}", msg,
					resultJson);

			TemplateMsgResult templateMsgResult = JsonUtil.fromJson(resultJson,
					TemplateMsgResult.class);
			return templateMsgResult;
		} catch (Exception e) {
			logger.error("WeChat sendTemplateMsg:e={}" + e);
		}
		return null;
	}

	@Override
	public TemplateMsgResult sendTemplateMsg(String openId, String templateId,
			String jumpUrl, Map<String, String> map) {

		String url = PublicPlatformConstants.SEND_TEMPLATE_MSG
				+ "?access_token=" + getToken();
		try {

			Map<String, Object> jsonMap = new HashMap<>();
			jsonMap.put("touser", openId);
			jsonMap.put("template_id", templateId);
			jsonMap.put("url", jumpUrl);

			Map<String, Object> dataMap = new HashMap<>();
			jsonMap.put("data", dataMap);

			for (Map.Entry<String, String> entry : map.entrySet()) {

				Map<String, String> valueMap = new HashMap<>();
				valueMap.put("value", entry.getValue());
				valueMap.put("color", "#173177");

				dataMap.put(entry.getKey(), valueMap);

			}

			String msg = JsonUtil.toJson(jsonMap);

			String resultJson = HttpClientUtil.doPostWithJSON(url, msg);
			logger.info("WeChat sendTemplateMsg,入参msg={} resultJson={}", msg,
					resultJson);

			TemplateMsgResult templateMsgResult = JsonUtil.fromJson(resultJson,
					TemplateMsgResult.class);
			return templateMsgResult;
		} catch (Exception e) {
			logger.error("WeChat sendTemplateMsg:e={}" + e);
		}
		return null;
	}

	private TemplateMsgResult sendTemplateMsg(String accessToken,
			TemplateMsg<?> templateMsg) {
		String msg;
		try {
			msg = JsonUtil.toJson(templateMsg);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sendTemplateMsg(accessToken, msg);

	}

	@Override
	public TemplateMsgResult sendTemplateMsg4PayOk(String userId,
			String openId, String mainOrderId, String orderId,
			String isSplitOrder, String orderMoneySum, String orderProductName) {

		String accessToken = getToken();

		if (StringUtil.isBlank(orderId)) {
			orderId = mainOrderId;
		}
	//跳转地址
		String url = PublicPlatformConstants.SHANGPIN_URL + CommonConstants.ORDER_DETAIL 
				+ "?isSplitOrder=" + isSplitOrder + "&orderId=" + orderId + "&mainOrderId=" + mainOrderId
				+ "&userId=" + userId;

		/*500元以内：50
		500-1000元：100
		1000-2000元：200
		2000以上：400*/
		//结束语
//		int orderMoney = Integer.valueOf(orderMoneySum);
		double orderMoney = Double.valueOf(orderMoneySum);
		int money = 0;
		if(orderMoney < 500){
			money = 50;
		}
		if(orderMoney >= 500 && orderMoney < 1000){
			money = 100;
		}
		if(orderMoney >= 1000 && orderMoney < 2000){
			money = 200;
		}
		if(orderMoney >= 2000){
			money = 400;
		}

		String remark = TemplateMsgEnum.PAY_OK.getRemark().replace("{money}",money+"");		TemplateMsgData4PayOk template = new TemplateMsgData4PayOk();
		template.setFirst(TemplateMsgEnum.PAY_OK.getFirst());
		template.setRemark(remark);
		template.setOrderMoneySum(orderMoneySum);
		template.setOrderProductName(orderProductName);

		TemplateMsg<TemplateMsgData4PayOk> templateMsg = new TemplateMsg<TemplateMsgData4PayOk>();
		templateMsg.setData(template);
		templateMsg.setTemplate_id(TemplateMsgEnum.PAY_OK.getId());
		templateMsg.setTouser(openId);
		templateMsg.setUrl(url);

		return sendTemplateMsg(accessToken, templateMsg);
	}

	@Override
	public TemplateMsgResult sendTemplateMsg4SignForExpress(String userId,
			String openId, String mainOrderId, String orderId,
			String isSplitOrder, String keyword1, String keyword2) {

		String accessToken = getToken();

		if (StringUtil.isBlank(orderId)) {
			orderId = mainOrderId;
		}

		// 跳转地址
		String url = PublicPlatformConstants.SHANGPIN_URL
				+ CommonConstants.ORDER_DETAIL + "?isSplitOrder="
				+ isSplitOrder + "&orderId=" + orderId + "&mainOrderId="
				+ mainOrderId + "&userId=" + userId;

		TemplateMsgData4Keywords template = new TemplateMsgData4Keywords();
		template.setFirst(TemplateMsgEnum.SIGN_FOR_EXPRESS.getFirst());
		template.setRemark(TemplateMsgEnum.SIGN_FOR_EXPRESS.getRemark());
		template.setKeyword1(keyword1);
		template.setKeyword2(keyword2);

		TemplateMsg<TemplateMsgData4Keywords> templateMsg = new TemplateMsg<TemplateMsgData4Keywords>();
		templateMsg.setData(template);
		templateMsg.setTemplate_id(TemplateMsgEnum.SIGN_FOR_EXPRESS.getId());
		templateMsg.setTouser(openId);
		templateMsg.setUrl(url);

		return sendTemplateMsg(accessToken, templateMsg);
	}

	@Override
	public TemplateMsgResult sendTemplateMsg4CouponExpiryRemind(String userId,
			String openId, String theme, String code, String date) {

		String accessToken = getToken();

		// 跳转地址
		String url = PublicPlatformConstants.SHANGPIN_URL
				+ CommonConstants.COUPON_LIST;

		TemplateMsgData4ExpiryRemind template = new TemplateMsgData4ExpiryRemind();
		template.setFirst(TemplateMsgEnum.COUPON_EXPIRY_REMIND.getFirst());
		template.setRemark(TemplateMsgEnum.COUPON_EXPIRY_REMIND.getRemark());
		template.setTheme(theme);
		template.setCode(code);
		template.setDate(date);

		TemplateMsg<TemplateMsgData4ExpiryRemind> templateMsg = new TemplateMsg<TemplateMsgData4ExpiryRemind>();
		templateMsg.setData(template);
		templateMsg
				.setTemplate_id(TemplateMsgEnum.COUPON_EXPIRY_REMIND.getId());
		templateMsg.setTouser(openId);
		templateMsg.setUrl(url);

		return sendTemplateMsg(accessToken, templateMsg);
	}

	@Override
	public TemplateMsgResult sendTemplateMsg4RefundNotice(String userId,
			String openId, String mainOrderId, String orderId,
			String isSplitOrder, String reason, String refund) {

		String accessToken = getToken();

		if (StringUtil.isBlank(orderId)) {
			orderId = mainOrderId;
		}

		// 跳转地址
		String url = PublicPlatformConstants.SHANGPIN_URL
				+ CommonConstants.ORDER_DETAIL + "?isSplitOrder="
				+ isSplitOrder + "&orderId=" + orderId + "&mainOrderId="
				+ mainOrderId + "&userId=" + userId;

		TemplateMsgData4RefundNotice template = new TemplateMsgData4RefundNotice();
		template.setFirst(TemplateMsgEnum.REFUND_NOTICE.getFirst());
		template.setRemark(TemplateMsgEnum.REFUND_NOTICE.getRemark());
		template.setReason(reason);
		template.setRefund(refund);

		TemplateMsg<TemplateMsgData4RefundNotice> templateMsg = new TemplateMsg<TemplateMsgData4RefundNotice>();
		templateMsg.setData(template);
		templateMsg.setTemplate_id(TemplateMsgEnum.REFUND_NOTICE.getId());
		templateMsg.setTouser(openId);
		templateMsg.setUrl(url);

		return sendTemplateMsg(accessToken, templateMsg);
	}

	@Override
	public TemplateMsgResult sendTemplateMsg4OrderDeliver(String userId,
			String openId, String mainOrderId, String orderId,
			String isSplitOrder, String keyword1, String keyword2,
			String keyword3, String keyword4) {

		String accessToken = getToken();

		if (StringUtil.isBlank(orderId)) {
			orderId = mainOrderId;
		}

		// 跳转地址
		String url = PublicPlatformConstants.SHANGPIN_URL
				+ CommonConstants.ORDER_DETAIL + "?isSplitOrder="
				+ isSplitOrder + "&orderId=" + orderId + "&mainOrderId="
				+ mainOrderId + "&userId=" + userId;

		TemplateMsgData4Keywords template = new TemplateMsgData4Keywords();
		template.setFirst(TemplateMsgEnum.ORDER_DELIVER.getFirst());
		template.setRemark(TemplateMsgEnum.ORDER_DELIVER.getRemark());
		template.setKeyword1(keyword1);
		template.setKeyword2(keyword2);
		template.setKeyword3(keyword3);
		template.setKeyword4(keyword4);

		TemplateMsg<TemplateMsgData4Keywords> templateMsg = new TemplateMsg<TemplateMsgData4Keywords>();
		templateMsg.setData(template);
		templateMsg.setTemplate_id(TemplateMsgEnum.ORDER_DELIVER.getId());
		templateMsg.setTouser(openId);
		templateMsg.setUrl(url);

		return sendTemplateMsg(accessToken, templateMsg);
	}

	@Override
	public TemplateMsgResult sendTemplateMsg4ReceiveRedEnvelope(String userId,
			String openId, String title, String expiry, String limit,
			String keyword1, String keyword2) {

		String accessToken = getToken();

		// 跳转地址
		String url = PublicPlatformConstants.SHANGPIN_URL
				+ CommonConstants.COUPON_LIST;

		String templateId = TemplateMsgEnum.RECEIVE_RED_ENVELOPE.getId();
		String first = TemplateMsgEnum.RECEIVE_RED_ENVELOPE.getFirst().replace(
				"{title}", title);
		String remark = TemplateMsgEnum.RECEIVE_RED_ENVELOPE.getRemark()
				.replace("{expiry}", expiry).replace("{limit}", limit);

		TemplateMsgData4Keywords template = new TemplateMsgData4Keywords();
		template.setFirst(first);
		template.setRemark(remark);
		template.setKeyword1(keyword1);
		template.setKeyword2(keyword2);

		TemplateMsg<TemplateMsgData4Keywords> templateMsg = new TemplateMsg<TemplateMsgData4Keywords>();
		templateMsg.setData(template);
		templateMsg.setTemplate_id(templateId);
		templateMsg.setTouser(openId);
		templateMsg.setUrl(url);

		return sendTemplateMsg(accessToken, templateMsg);
	}

	@Override
	public TemplateMsgResult sendTemplateMsg4ReceiveRedEnvelope(String openId,
			String first, String remark, String keyword1, String keyword2) {

		String accessToken = getToken();

		// 跳转地址
		String url = PublicPlatformConstants.SHANGPIN_URL
				+ CommonConstants.COUPON_LIST;

		String templateId = TemplateMsgEnum.RECEIVE_RED_ENVELOPE.getId();

		TemplateMsgData4Keywords template = new TemplateMsgData4Keywords();
		template.setFirst(first);
		template.setRemark(remark);
		template.setKeyword1(keyword1);
		template.setKeyword2(keyword2);

		TemplateMsg<TemplateMsgData4Keywords> templateMsg = new TemplateMsg<TemplateMsgData4Keywords>();
		templateMsg.setData(template);
		templateMsg.setTemplate_id(templateId);
		templateMsg.setTouser(openId);
		templateMsg.setUrl(url);

		return sendTemplateMsg(accessToken, templateMsg);
	}

	@Override
	public TemplateMsgResult sendTemplateMsg4GetCouponSuccess(String openId,
			String first, String remark, String keyword1, String keyword2,
			String keyword3) {

		String accessToken = getToken();

		// 跳转地址
		String url = PublicPlatformConstants.SHANGPIN_URL
				+ CommonConstants.COUPON_LIST;

		String templateId = TemplateMsgEnum.COUPON_GET_SUCCESS.getId();
		if (StringUtil.isBlank(first)) {
			first = TemplateMsgEnum.COUPON_GET_SUCCESS.getFirst();
		}
		if (StringUtil.isBlank(remark)) {
			remark = TemplateMsgEnum.COUPON_GET_SUCCESS.getRemark();
		}

		TemplateMsgData4Keywords template = new TemplateMsgData4Keywords();
		template.setFirst(first);
		template.setRemark(remark);
		template.setKeyword1(keyword1);
		template.setKeyword2(keyword2);
		template.setKeyword3(keyword3);

		TemplateMsg<TemplateMsgData4Keywords> templateMsg = new TemplateMsg<TemplateMsgData4Keywords>();
		templateMsg.setData(template);
		templateMsg.setTemplate_id(templateId);
		templateMsg.setTouser(openId);
		templateMsg.setUrl(url);

		return sendTemplateMsg(accessToken, templateMsg);
	}

	@Override
	public TemplateMsgResult sendTemplateMsg4ExpiryRemind(String openId,
			String first, String remark, String name, String expDate) {

		String accessToken = getToken();

		// 跳转地址
		String url = PublicPlatformConstants.SHANGPIN_URL
				+ CommonConstants.COUPON_LIST;

		String templateId = TemplateMsgEnum.EXPIRY_REMIND.getId();
		if (StringUtil.isBlank(first)) {
			first = TemplateMsgEnum.EXPIRY_REMIND.getFirst();
		}
		if (StringUtil.isBlank(remark)) {
			remark = TemplateMsgEnum.EXPIRY_REMIND.getRemark();
		}

		TemplateMsgData4ExpiryRemind template = new TemplateMsgData4ExpiryRemind();
		template.setFirst(first);
		template.setRemark(remark);
		template.setName(name);
		template.setExpDate(expDate);

		TemplateMsg<TemplateMsgData4ExpiryRemind> templateMsg = new TemplateMsg<TemplateMsgData4ExpiryRemind>();
		templateMsg.setData(template);
		templateMsg.setTemplate_id(templateId);
		templateMsg.setTouser(openId);
		templateMsg.setUrl(url);

		return sendTemplateMsg(accessToken, templateMsg);
	}

	@Override
	public ErrorInfo createMenu(String accessToken, String jsonMenu) {
		try {
			String json = HttpClientUtil.doPostWithJSON(
					PublicPlatformConstants.MENU_CREATE + "?access_token="
							+ accessToken, jsonMenu);
			return JsonUtil.fromJson(json, ErrorInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ErrorInfo delMenu(String accessToken) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		String json = HttpClientUtil.doGet(PublicPlatformConstants.MENU_DEL,
				params);
		return JsonUtil.fromJson(json, ErrorInfo.class);
	}

	@Override
	public String getMenu(String accessToken) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		return HttpClientUtil.doGet(PublicPlatformConstants.MENU_GET, params);
	}

	@Override
	public MenuButton getMenuObj(String accessToken) {
		String json = this.getMenu(accessToken);
		if (!StringUtils.isEmpty(json)) {
			MenuList menuList = JsonUtil.fromJson(json, MenuList.class);
			return menuList.getMenu();
		}
		return null;
	}

	public static void main(String[] args) {
		String json = "{\"menu\":{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\",\"sub_button\":[]},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\",\"sub_button\":[]},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\",\"sub_button\":[]}]}]}}";
		MenuList menuList = JsonUtil.fromJson(json, MenuList.class);
		System.out.println(menuList);
	}

	@Override
	public TemplateMsgResult sendTemplateMsg4SubmitOrder(String openId,
			String first, String remark, String orderID, String orderMoneySum,
			String backupFieldName, String backupFieldData, String jumpUrl) {
		String accessToken = getToken();

		// 跳转地址
		String url = jumpUrl;

		TemplateMsgEnum templateMsgEnum = TemplateMsgEnum.SUBMIT_ORDER;

		String templateId = templateMsgEnum.getId();
		if (StringUtil.isBlank(first)) {
			first = templateMsgEnum.getFirst();
		}
		if (StringUtil.isBlank(remark)) {
			remark = templateMsgEnum.getRemark();
		}

		TemplateMsgData4SubmitOrder template = new TemplateMsgData4SubmitOrder();
		template.setFirst(first);
		template.setRemark(remark);
		template.setOrderID(orderID);
		template.setOrderMoneySum(orderMoneySum);
		template.setBackupFieldName(backupFieldName);
		template.setBackupFieldData(backupFieldData);

		TemplateMsg<TemplateMsgData4SubmitOrder> templateMsg = new TemplateMsg<TemplateMsgData4SubmitOrder>();
		templateMsg.setData(template);
		templateMsg.setTemplate_id(templateId);
		templateMsg.setTouser(openId);
		templateMsg.setUrl(url);

		return sendTemplateMsg(accessToken, templateMsg);

	}

}
