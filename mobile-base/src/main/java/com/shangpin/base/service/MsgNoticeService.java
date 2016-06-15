package com.shangpin.base.service;

public interface MsgNoticeService {

	public String findMsgListByUserId(String userId,String pageIndex,String pageSize,String  messageType,String readStatus);
	
	public String udpateMsgStatus(String userId,String msgId,String readFlag);

	public String findUnreadCount(String userId);
	
	/**
	 * 获取消息通知的菜单列表
	 * @return
	 */
	public String getMenuList();
	
	/**
	 * 批量更新消息状态
	 * @param userId 用户ID
	 * @param messageType 消息类型
	 * @param readStatus 消息状态
	 * @return
	 */
	public String updateBatchMessageStatus(String userId, String messageType, String readStatus);

	/**
	 * 获取消息数量
	 * @param userId 用户ID
	 * @param messageType 
	 * @param readStatus 消息状态，0未读，1已读
	 * @return
	 */
	public String findMsgCount(String userId, String messageType, String readStatus);
}
