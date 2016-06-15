package com.shangpin.base.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.shangpin.base.service.MsgNoticeService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.base.utils.PropertiesUtil;
import com.shangpin.utils.HttpClientUtil;
@Service
public class MsgNoticeServiceImpl implements MsgNoticeService {

	private StringBuilder messageNoticeRUL = new StringBuilder(GlobalConstants.BASE_URL_MESSAGE_CENTER).append("message/findUserMessageByCondition");
	private StringBuilder messageReadRUL = new StringBuilder(GlobalConstants.BASE_URL_MESSAGE_CENTER).append("message/updateUserMessageStatus");
	private StringBuilder unreadMsgRUL = new StringBuilder(GlobalConstants.BASE_URL_MESSAGE_CENTER).append("message/findUserMessageTotalCountByCondition");
	private StringBuilder messageBatchReadRUL = new StringBuilder(GlobalConstants.BASE_URL_MESSAGE_CENTER).append("message/batch/update");
	

	@Override
	public String findMsgListByUserId(String userId,String pageIndex,String pageSize,String  messageType,String readStatus) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		params.put("messageType", messageType);
		params.put("readStatus", readStatus);
		return HttpClientUtil.doGet(messageNoticeRUL.toString(), params);
	}

	@Override
	public String udpateMsgStatus(String userId, String msgId, String readFlag) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("MessageId", msgId);
		params.put("ReadStatus", readFlag);
		return HttpClientUtil.doGet(messageReadRUL.toString(), params);
	}

	@Override
	public String findUnreadCount(String userId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		return HttpClientUtil.doGet(unreadMsgRUL.toString(), params);
	}

	@Override
	public String getMenuList() {
		PropertiesUtil propertiesUtil = PropertiesUtil.getInstance("/menu.properties");
		String value = propertiesUtil.getProperty("menu_list");
		return value;
	}
	
	@Override
	public String updateBatchMessageStatus(String userId, String messageType, String readStatus) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("messageType", messageType);
		params.put("readStatus", readStatus);
		return HttpClientUtil.doGet(messageBatchReadRUL.toString(), params);
	}
	
	@Override
	public String findMsgCount(String userId, String messageType, String readStatus) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("messageType", messageType);
		params.put("readStatus", readStatus);
		return HttpClientUtil.doGet(unreadMsgRUL.toString(), params);
	}
}
