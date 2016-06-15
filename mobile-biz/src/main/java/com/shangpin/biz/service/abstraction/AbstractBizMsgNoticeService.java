package com.shangpin.biz.service.abstraction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.MsgNoticeService;
import com.shangpin.biz.bo.NoticeMenu;
import com.shangpin.biz.bo.PcMsg;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.message.MessageCount;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;
/**
 * 消息通知接口
 * @author Administrator
 *
 */
public abstract class AbstractBizMsgNoticeService {

    @Autowired
    private MsgNoticeService msgNoticeService;
    

    public String findMsgListByUserId(String userId,String pageIndex,String pageSize,String  messageType,String readStatus) {
        String data = msgNoticeService.findMsgListByUserId(userId,pageIndex,pageSize,messageType,readStatus);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }
    
    public List<PcMsg> findMsgs(String userId,String pageIndex,String pageSize,String  messageType,String readStatus){
    	String json = findMsgListByUserId(userId, pageIndex, pageSize, messageType, readStatus);
    	if(StringUtils.isEmpty(json)){
    		return null;
    	}
    	ResultObjMapList<PcMsg> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<PcMsg>>() {});
    	if(Constants.SUCCESS.equals(result.getCode()) && null != result.getContent()){
    		return result.getContent().get("list");
    	}
    	return null;
    }
    
    public String udpateReadMsgStatus(String userId ,String msgId, String readFlag){
    	String data = msgNoticeService.udpateMsgStatus(userId, msgId, readFlag);
    	if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }
    
    public ResultBase udpateReadMsgStatusObj(String userId ,String msgId, String readFlag){
    	String json = udpateReadMsgStatus(userId, msgId, readFlag);
    	if(StringUtils.isEmpty(json)){
    		return null;
    	}
    	ResultBase resultBase = JsonUtil.fromJson(json, ResultBase.class);
    	return resultBase;
    }
    
    public String getMenuList(){
    	return msgNoticeService.getMenuList();
    }
    
    public List<NoticeMenu> getMenuListObj(){
    	String json = this.getMenuList();
    	if(StringUtils.isEmpty(json)){
    		return null;
    	}
    	List<NoticeMenu> noticeMenus = JsonUtil.fromJson(json, new TypeToken<List<NoticeMenu>>(){});
    	if(null == noticeMenus || noticeMenus.size() == 0){
    		return null;
    	}
    	return noticeMenus;
    }
    
    public String updateBatchMessageStatus(String userId, String messageType, String readStatus){
    	String data = msgNoticeService.updateBatchMessageStatus(userId, messageType, readStatus);
    	if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }
    
    public ResultBase updateBatchMessageStatusObj(String userId, String messageType, String readStatus){
    	String json = updateBatchMessageStatus(userId, messageType, readStatus);
    	if(StringUtils.isEmpty(json)){
    		return null;
    	}
    	ResultBase resultBase = JsonUtil.fromJson(json, ResultBase.class);
    	return resultBase;
    }
    
     public String findMsgCount(String userId, String messageType, String readStatus){
    	return msgNoticeService.findMsgCount(userId, messageType, readStatus);
    }
    
     public MessageCount findMsgCountObj(String userId, String messageType, String readStatus){
    	 String json = findMsgCount(userId, messageType, readStatus);
    	 if(StringUtils.isEmpty(json)){
    		 return null;
    	 }
    	 ResultObjOne<MessageCount> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<MessageCount>>(){});
    	 if(Constants.SUCCESS.equals(result.getCode()) && null != result.getContent()){
    		 return result.getContent();
    	 }
    	 return null;
     }
}
