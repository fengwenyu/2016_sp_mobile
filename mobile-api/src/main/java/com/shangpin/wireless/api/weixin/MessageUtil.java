package com.shangpin.wireless.api.weixin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.common.xmap.XMap;

import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.weixin.bazaar.BazaarUtil;
import com.shangpin.wireless.api.weixin.bazaar.TokenUtil;
import com.shangpin.wireless.api.weixinbean.ConfigEntity;
import com.shangpin.wireless.api.weixinbean.MsgReceived;

public class MessageUtil {
	protected final Log log = LogFactory.getLog(MessageUtil.class);
	private static final String URL_BASE = "https://api.weixin.qq.com/cgi-bin/";
	private static final String APP_ID = "wxa6e4cc8469c8be74";
	private static final String APP_SECRET = "1c6eaa2a20b37fcc8890e6acef605142";
	private static final String ADMIN	= "oFHXijhanQsN1n6iZPyepw1fsmZ0";
	private static final String ACCESS_PUBLIC	= "public";
	private static final String TYPE_MSG_VOICE	= "voice";
	public void sendMsg(String msg) {
		String accesstoken = TokenUtil.getAccessToken(APP_ID, APP_SECRET);
		if (accesstoken == null) {
			log.warn("accesstoken is null");
			return;
		}
		String url = URL_BASE + "message/custom/send?access_token="	+ accesstoken;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("grant_type", "client_credential");
		map.put("appid", APP_ID);
		map.put("secret", APP_SECRET);
		try {
			String data = WebUtil.readContentFromPost(url, msg);
			JSONObject obj = JSONObject.fromObject(data);
			final int errcode = obj.getInt("errcode");
			if (0 == errcode) { // 发送成功
			} else if (42001 == errcode) { // access_token超时
				log.debug("access_token timeout，repeat send" + accesstoken);
				sendMsg(msg);
			} else if (45015 == errcode) { // 用户24小时内没有与官微交互，不能推送消息
				log.debug("Amounted to less than 24 hours ,Don't push the message");
			} else {
				log.debug(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void parseXmlMsg (String data) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes("UTF-8"));
		parseXmlMsg(bais);
	}
	
	private static String getCommand (MsgReceived msg) {
		String command = msg.getContent();
		if (command == null && TYPE_MSG_VOICE.equals(msg.getMsgType())) {
			command = msg.getRecognition();
		}
		
		return command;
	}
	public static String processTextMsg (MsgReceived msg) {
		String textContent = getCommand(msg);
		ConfigEntity entity = BazaarUtil.get(textContent);
		if (entity == null) return null;
		String access = entity.getAccess();
		if (access != null) {
			boolean verified = false;
			if (msg.getFromUserName().equals(ADMIN)) {
				verified = true;
			}
			if (ACCESS_PUBLIC.equals(access)) {
				verified = true;
			} 
			if (!verified) return null;
		}
		String starttime = entity.getStartTime();
		String endtime = entity.getEndTime();
		String timeformat = entity.getTimeFormat();
		if (starttime != null && starttime.length() > 0
				&& endtime != null && endtime.length() > 0
				&& timeformat != null && timeformat.length() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat(timeformat);
			String now = sdf.format(new Date().getTime());
			//	时间条件不符合
			if (now.compareTo(starttime) < 0 || now.compareTo(endtime) > 0) {
				return null;
			}
		}

		final String type = entity.getType();
		if ("menuadd".equals(type)) {
			return processMenuAddMsg(msg, entity);
		}else if("menudel".equals(type)){
			return processMenuDelMsg(msg, entity);
		}
		return null;
	}
	
	
	private static String processMenuDelMsg (MsgReceived msg, ConfigEntity entity) {
		final Object replyObj = entity.getReplied();
		
		final String accesstoken = TokenUtil.getAccessToken(APP_ID, APP_SECRET);
		String url = URL_BASE + "menu/delete";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("access_token", accesstoken);
		
		try {
			String data = WebUtil.readContentFromGet(url, map);
			
			JSONObject obj = JSONObject.fromObject(data);
			final int errcode = obj.getInt("errcode");
			final String errmsg = obj.getString("errmsg");
			
			StringBuilder sbBuff = new StringBuilder();
			sbBuff.append("<xml>");
			sbBuff.append("<ToUserName><![CDATA[").append(msg.getFromUserName()).append("]]></ToUserName>");
			sbBuff.append("<FromUserName><![CDATA[").append(msg.getToUserName()).append("]]></FromUserName>");
			sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
			sbBuff.append("<MsgType><![CDATA[text]]></MsgType>");
			if (errcode == 0) {
				sbBuff.append("<Content><![CDATA[").append(replyObj.toString()).append("]]></Content>");
			} else {
				sbBuff.append("<Content><![CDATA[").append(errmsg).append("]]></Content>");
			}
			sbBuff.append("<FuncFlag>0</FuncFlag>");
			sbBuff.append("</xml>");
			
			return sbBuff.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "<xml></xml>";
	}
	
	private static String processMenuAddMsg (MsgReceived msg, ConfigEntity entity) {
		final Object replyObj = entity.getReplied();
		
		final String accesstoken = TokenUtil.getAccessToken(APP_ID, APP_SECRET);
		String url = URL_BASE + "menu/create?access_token=" + accesstoken;
		
		try {
			String postStr = PatternUtil.replaceAllPattern (replyObj.toString(), msg);
			String data = WebUtil.readContentFromPost(url, postStr);
			
			JSONObject obj = JSONObject.fromObject(data);
			final int errcode = obj.getInt("errcode");
			final String errmsg = obj.getString("errmsg");
			
			StringBuilder sbBuff = new StringBuilder();
			sbBuff.append("<xml>");
			sbBuff.append("<ToUserName><![CDATA[").append(msg.getFromUserName()).append("]]></ToUserName>");
			sbBuff.append("<FromUserName><![CDATA[").append(msg.getToUserName()).append("]]></FromUserName>");
			sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
			sbBuff.append("<MsgType><![CDATA[text]]></MsgType>");
			if (errcode == 0) {
				sbBuff.append("<Content><![CDATA[").append(replyObj.toString()).append("]]></Content>");
			} else {
				sbBuff.append("<Content><![CDATA[").append(errmsg).append("]]></Content>");
			}
			sbBuff.append("<FuncFlag>0</FuncFlag>");
			sbBuff.append("</xml>");
			
			return sbBuff.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "<xml></xml>";
	}
	
	public static MsgReceived parseXmlMsg (InputStream is) throws Exception {
		XMap xmap = new XMap();
		xmap.register(MsgReceived.class);
		Object[] beans = xmap.loadAll(is);
		if (beans != null && beans.length == 1) {
			MsgReceived msg = (MsgReceived)beans[0];
//			WeixinMessage message = new WeixinMessage();
//			message.setFromUser(msg.getFromUserName());
//			message.setToUser(msg.getToUserName());
//			message.setMsgId(msg.getMsgId());
//			message.setMsgType(msg.getMsgType());
//			message.setCreateTime(new Date());
//			if ("gh_abfc1ec3686b".equals(msg.getFromUserName())) {
//				message.setSource(SOURCE_AUTO);
//			} else {
//				message.setSource(SOURCE_USER);
//			}
//			
//			StringBuilder sbBuff = new StringBuilder();
//			sbBuff.append("ToUserName: ").append(msg.getToUserName()).append("\n");
//			sbBuff.append("FromUserName: ").append(msg.getFromUserName()).append("\n");
//			sbBuff.append("CreateTime: ").append(msg.getCreateTime()).append("\n");
//			sbBuff.append("MsgType: ").append(msg.getMsgType()).append("\n");
//			if (msg.getMsgId() != null) {
//				sbBuff.append("MsgId: ").append(msg.getMsgId()).append("\n");
//			}
//			if (TYPE_MSG_TEXT.equals(msg.getMsgType())) {
//				sbBuff.append("Content: ").append(msg.getContent()!=null?msg.getContent():"").append("\n");
//				
//				message.setContent(msg.getContent());
//			} else if (TYPE_MSG_IMAGE.equals(msg.getMsgType())) {
//				StringBuilder sb = new StringBuilder();
//				sb.append("PicUrl: ").append(msg.getPicUrl()!=null?msg.getPicUrl():"");
//				sbBuff.append(sb.toString()).append("\n");
//				sbBuff.append("MediaId: ").append(msg.getMediaId()!=null?msg.getMediaId():"").append("\n");
//				
//				message.setMediaId(msg.getMediaId());
//				message.setParams(sb.toString());
//			} else if (TYPE_MSG_VOICE.equals(msg.getMsgType())) {
//				StringBuilder sb = new StringBuilder();
//				sb.append("Format: ").append(msg.getFormat()!=null?msg.getFormat():"").append("\n");
//				sb.append("Recognition: ").append(msg.getRecognition()!=null?msg.getRecognition():"");
//				sbBuff.append(sb.toString()).append("\n");
//				sbBuff.append("MediaId: ").append(msg.getMediaId()!=null?msg.getMediaId():"").append("\n");
//
//				message.setContent(msg.getRecognition());
//				message.setMediaId(msg.getMediaId());
//				message.setParams(sb.toString());
//			} else if (TYPE_MSG_VIDEO.equals(msg.getMsgType())) {
//				StringBuilder sb = new StringBuilder();
//				sb.append("ThumbMediaId: ").append(msg.getThumbMediaId()!=null?msg.getThumbMediaId():"");
//				sbBuff.append(sb.toString()).append("\n");
//				sbBuff.append("MediaId: ").append(msg.getMediaId()!=null?msg.getMediaId():"").append("\n");
//				
//				message.setMediaId(msg.getMediaId());
//				message.setParams(sb.toString());
//			} else if (TYPE_MSG_LOCATION.equals(msg.getMsgType())) {
//				StringBuilder sb = new StringBuilder();
//				sb.append("Label: ").append(msg.getLabel()!=null?msg.getLabel():"").append("\n");
//				sb.append("Location: ").append(msg.getLocation_X()!=null?(msg.getLocation_Y() + "," + msg.getLocation_X()):"").append("\n");
//				sb.append("Scale: ").append(msg.getScale()!=null?msg.getScale():"");
//				sbBuff.append(sb.toString()).append("\n");
//				
//				message.setParams(sb.toString());
//			} else if (TYPE_MSG_LINK.equals(msg.getMsgType())) {
//				StringBuilder sb = new StringBuilder();
//				sb.append("Url: ").append(msg.getUrl()!=null?msg.getUrl():"").append("\n");
//				sb.append("Title: ").append(msg.getTitle()!=null?msg.getTitle():"").append("\n");
//				sb.append("Description: ").append(msg.getDescription()!=null?msg.getDescription():"");
//				sbBuff.append(sb.toString()).append("\n");
//				
//				message.setParams(sb.toString());
//			} else if (TYPE_MSG_EVENT.equals(msg.getMsgType())) {
//				message.setEventType(msg.getEvent());
//				
//				sbBuff.append("Event: ").append(msg.getEvent()!=null?msg.getEvent():"").append("\n");
//				StringBuilder sb = new StringBuilder();
//				if (TYPE_EVENT_SUBSCRIBE.equals(msg.getEvent()) || TYPE_EVENT_SCAN.equals(msg.getEvent())) {
//					sb.append("EventKey: ").append(msg.getEventKey()!=null?msg.getEventKey():"").append("\n");
//					sb.append("Ticket: ").append(msg.getTicket()!=null?msg.getTicket():"");
//					sbBuff.append(sb.toString()).append("\n");
//					
//					message.setParams(sb.toString());
//				} else if (TYPE_EVENT_CLICK.equals(msg.getEvent())) {
//					sb.append("EventKey: ").append(msg.getEventKey()!=null?msg.getEventKey():"");
//					sbBuff.append(sb.toString()).append("\n");
//					
//					message.setParams(sb.toString());
//				} else if (TYPE_EVENT_LOCATION.equals(msg.getEvent())) {
//					sb.append("Latitude: ").append(msg.getLatitude()!=null?msg.getLatitude():"").append("\n");
//					sb.append("Longitude: ").append(msg.getLongitude()!=null?msg.getLongitude():"").append("\n");
//					sb.append("Precision: ").append(msg.getPrecision()!=null?msg.getPrecision():"");
//					sbBuff.append(sb.toString()).append("\n");
//					
//					message.setParams(sb.toString());
//				}
//			} else if (TYPE_MSG_NEWS.equals(msg.getMsgType())) {
//				StringBuilder sb = new StringBuilder();
//				sb.append("\"articles\":[\n");
//				if (msg.getArticleCount() > 0) {
//					List<XmlArticleItem> items = msg.getArticles().getItems();
//					for (int i=0;i<items.size();i++) {
//						sb.append("{\n");
//						XmlArticleItem item = items.get(i);
//						sb.append("\"title\":\"").append(item.getTitle()).append("\",\n");
//						sb.append("\"description\":\"").append(item.getDescription()).append("\",\n");
//						sb.append("\"url\":\"").append(item.getUrl()).append("\",\n");
//						sb.append("\"picurl\":\"").append(item.getPicUrl()).append("\"\n");
//						if (i == items.size() - 1) {
//							sb.append("}\n");
//						} else {
//							sb.append("},\n");
//						}
//					}
//				}
//				sb.append("]");
//
//				sbBuff.append(sb.toString()).append("\n");
//				
//				message.setParams(sb.toString());
//			}
			return msg;
		}
		
		return null;
	}
}
