package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangpin.biz.bo.AppMsg;
import com.shangpin.biz.bo.AppMsgList;
import com.shangpin.biz.bo.PcMsg;
import com.shangpin.biz.bo.base.ContentBuilder;
import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.biz.bo.message.OrderMessage;
import com.shangpin.biz.bo.message.ReturnGoodsMessage;
import com.shangpin.biz.bo.message.ShowMultiMessage;
import com.shangpin.biz.service.ASPBizMsgNoticeService;
import com.shangpin.biz.service.abstraction.AbstractBizMsgNoticeService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.PicCdnHash;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.StringUtil;
@Service
public class ASPBizMsgNoticeServiceImpl extends AbstractBizMsgNoticeService implements ASPBizMsgNoticeService {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String findMsgList(String userId,String pageIndex,String pageSize,String  messageType,String readStatus, String version, String plateForm) {
		/*String resultStr = this.findMsgListByUserId(userId,pageIndex,pageSize,messageType,readStatus);
		ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.ANY);
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<PcMsg> msgList = new ArrayList<PcMsg>();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, PcMsg.class);  
		AppMsgList appMsgList = new AppMsgList();
		try {
			msgList = (List<PcMsg>)mapper.readValue(resultStr, javaType);
			appMsgList = ASPBizMsgNoticeServiceImpl.transObject(msgList);
			//resultStr = mapper.writeValueAsString(appMsgList);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return JsonUtil.toJson(ResultBaseNew.success(appMsgList));*/
		//新定义之后的主站返回的消息实体（全部json形式），线上老的消息可以不显示了
		ContentBuilder builder = new ContentBuilder();
    	Map<String, List<AppMsg>> map = new HashMap<String, List<AppMsg>>();
		List<PcMsg> pcMsgs = findMsgs(userId, pageIndex, pageSize, messageType, readStatus);
		if(null == pcMsgs || pcMsgs.size() == 0){
			map.put("list", new ArrayList<AppMsg>());
	    	return JsonUtil.toJson(builder.buildDefSuccess(map));
		}
		List<AppMsg> list = new ArrayList<AppMsg>(Integer.parseInt(pageSize));
		for(PcMsg pcMsg : pcMsgs){
			AppMsg msg = new AppMsg();
			msg.setMessage(pcMsg.getMessageName());//消息内容
			msg.setMsgId(pcMsg.getMessageId());
			msg.setReadFlag(pcMsg.getReadStatus());
			msg.setTime(pcMsg.getCreateDate());
			msg.setTitle(pcMsg.getMessageName());
			String content = pcMsg.getMessageContent();
			OrderMessage orderMessage = JsonUtil.fromJson(content, OrderMessage.class);
			msg.setOrderId(orderMessage.getOrderNo());
			msg.setMainOrderId(orderMessage.getMainOrderNo());
			msg.setIsSplitOrder(orderMessage.getIsSplit());
			msg.setMessage(orderMessage.getDesc());
			if(StringUtil.compareVersion("2.9.10", version) == 1){
				msg.setImageUrl(StringUtils.isEmpty(orderMessage.getPicNo()) ? "" : PicCdnHash.getPicUrl(orderMessage.getPicNo(), "1"));
				msg.setTypeFlag("2");
				if("IOS".equals(plateForm)){
					msg.setIcon(Constants.MESSAGE_ICON_ORDER_ISO);
				}else {
					msg.setIcon(Constants.MESSAGE_ICON_ORDER_ANDROID);
				}
			}else {
				msg.setMessageType("2");
			}
			list.add(msg);
		}
    	map.put("list", null != list ? list : new ArrayList<AppMsg>());
    	return JsonUtil.toJson(builder.buildDefSuccess(map));
	}

	@Override
	public String updateMsgStatus(String userId, String msgId, String readFlag) {
		String resultStr = this.udpateReadMsgStatus(userId, msgId, readFlag);
		//成功
		if("1".equals(resultStr)){
			return JsonUtil.toJson(ResultBaseNew.emptyContent());
		}else{//失败
			return JsonUtil.toJson(ResultBaseNew.build(resultStr, "失败",null));
		}
	}
	@SuppressWarnings("unused")
	private static AppMsgList transObject(List<PcMsg> msgList){
		AppMsgList appMsgList = new AppMsgList();
		List<AppMsg> list = new ArrayList<AppMsg>();
		appMsgList.setList(list);
		if(msgList.size()>0){
			Iterator<PcMsg> it = msgList.iterator();
			while(it.hasNext()){
				PcMsg pcMsg = (PcMsg)it.next();
				String messageType = pcMsg.getMessageType();
					AppMsg appMsg = new AppMsg();
					appMsg.setMessage(pcMsg.getMessageName());//消息内容
					appMsg.setMessageContent(pcMsg.getMessageContent());
					appMsg.setMessageType(pcMsg.getMessageType());
					appMsg.setMsgId(pcMsg.getMessageId());
					appMsg.setReadFlag(pcMsg.getReadStatus());
					appMsg.setTime(pcMsg.getCreateDate());
					//类型为订单详情
					String messageContent = pcMsg.getMessageContent();
					if("2".equals(pcMsg.getMessageType())){//订单消息通知
						if(null!=messageContent&&messageContent.indexOf(",")>-1){
							String str[] = messageContent.split(",");
							if(str.length>2){
								appMsg.setMainOrderId(str[0]);
								appMsg.setOrderId(str[1]);
								appMsg.setIsSplitOrder(str[2]);
							}
							appMsg.setTypeFlag("2");
							appMsg.setTime("订单通知的标题");
							appMsg.setIcon("http://pic6.shangpin.com/group1/M00/FA/F4/rBQKaFbo-LmANJw_AAA89eOR05c172.png");
							appMsg.setImageUrl("http://192.168.20.73/e/s/12356/10-10.jpg");
							appMsg.setRefConten("");
						}
					}
					list.add(appMsg);
			}
			
			
		}	
		return appMsgList;
	}

	@Override
	public List<ShowMultiMessage> findMessageByPage(String userId, String pageIndex, String pageSize, String messageType, String readStatus, String plateForm) {
		List<PcMsg> pcMsgs = findMsgs(userId, pageIndex, pageSize, messageType, readStatus);
		if(null == pcMsgs || pcMsgs.size() == 0){
			return null;
		}
		List<ShowMultiMessage> showMultiMessages = new ArrayList<ShowMultiMessage>(Integer.parseInt(pageSize));
		for(PcMsg pcMsg : pcMsgs){
			ShowMultiMessage message = new ShowMultiMessage();
			String content = pcMsg.getMessageContent();
			ReturnGoodsMessage returnGoodsMessage = JsonUtil.fromJson(content, ReturnGoodsMessage.class);
			message.setTitle(pcMsg.getMessageName());
			message.setContent(returnGoodsMessage.getDesc());
			message.setImageUrl(PicCdnHash.getPicUrl(returnGoodsMessage.getPicNo(), "1"));
			message.setType(Constants.NOTICE_RECONTENT_TYPE_WAP);
			message.setRefContent(Constants.WAP_URL + "returnGoods/returnProgress?userId=" + userId + "&returnId=" + returnGoodsMessage.getReturnId());
			message.setCreateTime(pcMsg.getCreateDate());
			if("IOS".equals(plateForm)){
				message.setIcon(Constants.MESSAGE_ICON_RETURN_IOS);
			}else {
				message.setIcon(Constants.MESSAGE_ICON_RETURN_ANDROID);
			}
			showMultiMessages.add(message);
		}
		return showMultiMessages;
	}

}
