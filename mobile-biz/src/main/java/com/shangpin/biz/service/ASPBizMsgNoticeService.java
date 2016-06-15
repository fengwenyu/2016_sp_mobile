package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.NoticeMenu;
import com.shangpin.biz.bo.PcMsg;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.message.MessageCount;
import com.shangpin.biz.bo.message.ShowMultiMessage;

public interface ASPBizMsgNoticeService {
	/**
	 * 根据用户id查询消息列表
	 * @param userId
	 * @return
	 */
	public String findMsgList(String userId,String pageIndex,String pageSize,String  messageType,String readStatus, String version, String plateForm);
	/**
	 * 查询消息列表
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @param messageType
	 * @param readStatus
	 * @return
	 */
	public List<PcMsg> findMsgs(String userId,String pageIndex,String pageSize,String  messageType,String readStatus);
	
	/**
	 * 查询消息列表，前端标准输入格式
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @param messageType
	 * @param readStatus
	 * @return
	 */
	public List<ShowMultiMessage> findMessageByPage(String userId,String pageIndex,String pageSize,String  messageType,String readStatus, String plateForm);
	/**
	 * 更新消息已读状态
	 * @param userId
	 * @param msgId
	 * @param readFlag
	 * @return
	 */
	public String updateMsgStatus(String userId,String msgId,String readFlag);
	
	/**
	 * 更新消息状态
	 * @param userId
	 * @param msgId
	 * @param readFlag
	 * @return
	 */
	public ResultBase udpateReadMsgStatusObj(String userId,String msgId,String readFlag);
	
	 /**
	 * 获取消息菜单
	 * @return
	 */
	public String getMenuList();
		
	/**
	 * 获取消息菜单
	 * @return
	 */
	public List<NoticeMenu> getMenuListObj();
	
	/**
	 * 批量更新消息状态
	 * @param userId 用户ID
	 * @param messageType 消息类型
	 * @param readStatus 消息状态
	 * @return
	 */
	public String updateBatchMessageStatus(String userId, String messageType, String readStatus);
	/**
	 * 批量更新消息状态
	 * @param userId
	 * @param messageType
	 * @param readStatus
	 * @return
	 */
	public ResultBase updateBatchMessageStatusObj(String userId, String messageType, String readStatus);

	/**
	 * 获取消息数量
	 * @param userId 用户ID
	 * @param messageType 
	 * @param readStatus 消息状态，0未读，1已读
	 * @return
	 */
	public String findMsgCount(String userId, String messageType, String readStatus);
	/**
	 * 获取消息数量
	 * @param userId
	 * @param messageType
	 * @param readStatus
	 * @return
	 */
	public MessageCount findMsgCountObj(String userId, String messageType, String readStatus);
}
