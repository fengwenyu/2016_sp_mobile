package com.shangpin.wireless.api.view.servlet;

import com.shangpin.biz.bo.AwardsInfo;
import com.shangpin.biz.bo.RedActivity;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizStarPacketService;
import com.shangpin.biz.service.SPBizWeixinPacketService;
import com.shangpin.core.entity.WeiXinPacketAccount;
import com.shangpin.core.entity.WeiXinPacketRecord;
import com.shangpin.utils.EmojiUtil;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.IpUtils;
import com.shangpin.wechat.bo.base.CashBonusResult;
import com.shangpin.wechat.bo.base.UserInfo;
import com.shangpin.wechat.service.WeChatMerchantService;
import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wireless.api.api2server.domain.GetVCodeServerResponse;
import com.shangpin.wireless.api.api2server.domain.SPGoodsDetailServerResponse;
import com.shangpin.wireless.api.domain.*;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.service.*;
import com.shangpin.wireless.api.util.*;
import com.shangpin.wireless.api.weixinbean.ConfigEntity;
import com.shangpin.wireless.api.weixinbean.LotteryConfig;
import com.shangpin.wireless.api.weixinbean.MsgReceived;
import com.shangpin.wireless.api.weixinbean.XmlArticleItem;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.nuxeo.common.xmap.XMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**

接收文本类消息 
接收地理位置类消息 
接收图片类消息 
接收链接类消息	
 
回复文本消息 
回复图文类消息 
回复音乐类消息	
 
用户关注事件推送 
用户取消关注事件推送
 
会话界面自定义菜单	设置自定义菜单（内测）	权限审核中

图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。
 * 
 * @Author:wangwenguan
 * @CreatDate: 2013-05-27
 */
public class WeXinPlatformServlet extends BaseServlet {
	
	public static final Logger logger = LoggerFactory.getLogger(WeXinPlatformServlet.class);

	public static final String CHARSET	= "UTF-8";

	private static final long serialVersionUID = 1L;
	private static final String URL_BASE	= "https://api.weixin.qq.com/cgi-bin/";
	//private static final String ONLINE_URL_BASE	= "http://113.31.17.240:94/agent/weixin";
	private static final String ONLINE_URL_BASE	= "http://wx.ntalker.com/agent/weixin";
	private static final String APP_ID	= "wx4e93df954af2f52c";
	private static final String APP_SECRET	= "2a55eede9fbd467e25e6a0eb7b17ce60";
//	#王文冠账号：oFHXijhanQsN1n6iZPyepw1fsmZ0
//	#赵晓账号：oFHXijsSnol93uI6ch_2nyhW0Aoc
	private static String[] ADMIN = null;
	static {
		final String admins = ProReader.getInstance().getProperty("weixinadmins");
		if (admins != null && admins.length() > 0) {
			ADMIN	= admins.split(",");
		}
	}
	private static final String ACCESS_PRIVATE	= "private";
	private static final String ACCESS_PROTECT	= "protect";
	private static final String ACCESS_PUBLIC	= "public";
	private static final String TYPE_MSG_TEXT	= "text";
	private static final String TYPE_MSG_IMAGE	= "image";
	private static final String TYPE_MSG_VOICE	= "voice";
	private static final String TYPE_MSG_VIDEO	= "video";
	private static final String TYPE_MSG_LOCATION	= "location";
	private static final String TYPE_MSG_LINK	= "link";
	private static final String TYPE_MSG_EVENT	= "event";
	private static final String TYPE_MSG_MUSIC	= "music";
	private static final String TYPE_MSG_NEWS	= "news";
	private static final String TYPE_EVENT_SUBSCRIBE	= "subscribe";
	private static final String TYPE_EVENT_UNSUBSCRIBE	= "unsubscribe";
	private static final String TYPE_EVENT_SCAN	= "scan";
	private static final String TYPE_EVENT_LOCATION	= "LOCATION";
	private static final String TYPE_EVENT_CLICK	= "CLICK";
	private static final String SOURCE_USER	= "user";	//	用户
	private static final String SOURCE_AUTO	= "auto";	//	自动回复
	private static final String SOURCE_CUSTOM	= "custom";	//	客服回复
	private static final String ONLINE_CUSTOM_ON= "ON";	//	客服开启
	private static final Hashtable<String, ConfigEntity> configs = new Hashtable<String, ConfigEntity>();
	private static final HashMap<String, RecordEntity> SendRecords = new HashMap<String, RecordEntity>();
	AccountWeixinService accountService;
	AccountWeixinBindedService accountBindedService;
	WeixinMessageService weixinMessageService;
	WeixinLotteryService weixinLotteryService;
	static WeChatPublicService weiXinService = null;
	SPBizWeixinPacketService bizWeixinPacketService;
	SPBizStarPacketService bizStarPacketService;
	WeChatMerchantService weChatMerchantService;
    WeXinPlatformService weXinPlatformService;

	@Override
	public void init() throws ServletException {
		accountService = (AccountWeixinService)getBean(AccountWeixinService.class);
		accountBindedService = (AccountWeixinBindedService) getBean(AccountWeixinBindedService.class);
		weixinMessageService = (WeixinMessageService) getBean(WeixinMessageService.class);
		weixinLotteryService = (WeixinLotteryService) getBean(WeixinLotteryService.class);
		weiXinService = (WeChatPublicService) getBean(WeChatPublicService.class);
		bizWeixinPacketService = (SPBizWeixinPacketService)getBean(SPBizWeixinPacketService.class);
		bizStarPacketService = (SPBizStarPacketService)getBean(SPBizStarPacketService.class);		
		weChatMerchantService = (WeChatMerchantService)getBean(WeChatMerchantService.class);
        weXinPlatformService = (WeXinPlatformService)getBean(WeXinPlatformService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("[微信]weixinplatformServlet doGet=========================");
//		for (Enumeration e = request.getParameterNames();e.hasMoreElements();) {
//			String key = (String)e.nextElement();
//			System.out.println(key + "=" + request.getParameter(key));
//		}
		//logger.debug(String.valueOf(bizWeixinPacketService.existUser("249894389489843")));
		if (checkSignature(request)) {
			response.getWriter().print(request.getParameter("echostr"));
		}
//		WeXinPlatformServlet doGet
//		signature=f171b3c50473a2136582cd976a66a755ee180332
//		nonce=1363360868
//		echostr=5855590337774890979
//		timestamp=1363685346
		if (configs.isEmpty()) {
			updateConfig();
		}
	}

//	WeXinPlatformServlet doPost
//	len = 282
//	<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>
//	<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>
//	<CreateTime>1363686314</CreateTime>
//	<MsgType><![CDATA[text]]></MsgType>
//	<Content><![CDATA[你好吗]]></Content>
//	<MsgId>5856988120732790036</MsgId>
//	</xml>
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    logger.info("[微信]WeXinPlatformServlet doPost====================== " + Thread.currentThread().getId());
		long begin = System.currentTimeMillis();
		if (configs.isEmpty()) {
			updateConfig();
		}
		writeLog("\n" + FileUtil.getIpAddr(request) + "\n[receive]=");
//		if (len > 0 && is != null) {
//			StringBuilder sb = new StringBuilder();
//			byte[] bytes = new byte[2048];
//			try {
//				int read;
//				while ((read = is.read(bytes)) != -1) {
//					sb.append(new String(bytes, 0, read));
//				}
//				System.out.println(sb.toString());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		try {
			MsgReceived msg = parseXmlMsg (request.getInputStream());
//			msg.setOnline("ON");
//			msg.setMsgId(new Date().getTime()+"");
			logger.info("[微信]receive msg=========={}", msg.toString());
			String token=weiXinService.getToken();
			logger.info("[微信]weixin token========={}", token);
			if (msg != null) {
				String data = null;
				final String msgType = msg.getMsgType();
				logger.info("[微信]msg type==========={}", msgType);
				if (TYPE_MSG_TEXT.equals(msgType)) {
					data = processTextMsg(msg,token,request);
				} else if (TYPE_MSG_IMAGE.equals(msgType)) {
					data = processTextMsg(msg,token,request);
				} else if (TYPE_MSG_VOICE.equals(msgType) && msg.getRecognition() != null) {
					data = processTextMsg(msg,token,request);
				}
				else if (TYPE_MSG_EVENT.equals(msgType)) {
					final String event = msg.getEvent();
					logger.info("[微信]事件类型=========={}", event);
					if ("subscribe".equals(event)) {
						msg.setContent("**关注**");
						data = processTextMsg(msg,token,request);
						
						//	保存新用户
						new AddWeixinUser(msg.getFromUserName(), msg.getEventKey(), msg.getTicket()).start();
					} else if ("unsubscribe".equals(event)) {
						new AddWeixinUser(msg.getFromUserName(), true).start();
						data = "<xml></xml>";
					} else if ("CLICK".equals(event)) {//点击事件
						String eventKey = msg.getEventKey();//与菜单定义中的点击类型中的key值对应。000768
						logger.info("[微信]点击事件的key值=========={}", eventKey);
						msg.setContent(eventKey);
						data = processTextMsg(msg,token,request);
						logger.info("[微信]process msg data============={}", data);
						if("qrcode".equals(data)){
							return;
						}
					} else if("SCAN".equals(event)){//识别二维码已经关注尚品网公共号的用户
						String eventKey = msg.getEventKey();
						logger.info("[微信]点击事件的key值=========={}", eventKey);
						long userId = Long.parseLong(eventKey);
						logger.info("[微信]send qrcode user id============{}", userId);
						WeiXinPacketAccount account = bizWeixinPacketService.findById(userId);
						logger.info("[微信]send qrcode user info============={}", account);
						if(null != account){//表示是通过好友发送的二维码
							//获取识别二维码用户信息
							String openId = msg.getFromUserName();
							logger.info("[微信]discern user open id=============={}", openId);
							String send_openId = account.getOpenId();
							logger.info("[微信]send qrcode open id=========={}", send_openId);
							if(send_openId.equals(openId)){//表示识别的是自己的二维码，需要给用户提示不能识别自己的二维码
								msg.setContent("识别自己二维码提示");
								data = processTextMsg(msg,token,request);
							}else {
								UserInfo userInfo = weiXinService.baseUserObj(token, openId);
								logger.info("[微信]user info==================={}", userInfo.toString());
								//保存识别用户信息
								boolean isUser = bizWeixinPacketService.existUser(openId);
								WeiXinPacketAccount focusAccount;
								if(!isUser){
									WeiXinPacketAccount account1 = new WeiXinPacketAccount();
									account1.setOpenId(openId);
									account1.setNickName(EmojiUtil.filterEmoji(userInfo.getNickname()));
									account1.setHeadImgUrl(userInfo.getHeadimgurl());
									account1.setSex(userInfo.getSex());
									account1.setCountry(userInfo.getCountry());
									account1.setProvince(userInfo.getProvince());
									account1.setCity(userInfo.getCity());
									account1.setLanguage(userInfo.getLanguage());
									account1.setUnionid(userInfo.getUnionid());
									account1.setCreateTime(new Timestamp(new Date().getTime()));
									account1.setShangpinPacket(5.0);
									account1.setBalance(5.0);
									focusAccount = bizWeixinPacketService.save(account1);
								}else {
									focusAccount = bizWeixinPacketService.findByOpenId(openId);
								}
								
								//1.给发送二维码的好友进行关联
								WeiXinPacketRecord record = new WeiXinPacketRecord();
								//判断两个好友是否已经相互识别过二维码
								boolean flag = bizWeixinPacketService.isDiscern(openId, send_openId);
								logger.info("[微信]is discern================{}", flag);
								if(flag){
									msg.setContent("是否已经识别");
									data = processTextMsg(msg,token,request);
								}else {
									int packetNum = account.getReceivePacketNum();
									double packetMoney = account.getReceivePacketMoney();
									logger.info("[微信]nicke name is===============" + EmojiUtil.filterEmoji(account.getNickName()) + "=======receivePacketNmu:" + packetNum + "=====receivePacketMoney:" + packetMoney);
									double packet = redPacket(packetNum, packetMoney);
									record.setSendOpenId(openId);
									record.setSendNickName(EmojiUtil.filterEmoji(userInfo.getNickname()));
									record.setReceiveOpenId(account.getOpenId());
									record.setReceiveNickName(EmojiUtil.filterEmoji(account.getNickName()));
									record.setPacketMoney(packet);
									record.setCreateTime(new Timestamp(new Date().getTime()));
									bizWeixinPacketService.save(record);
									//2.更新发送二维码好友表，收到一个红包和红包金额
									account.setReceivePacketNum(account.getReceivePacketNum() + 1);
									double receivePacketMoney = formatDouble(account.getReceivePacketMoney() + packet);
									account.setReceivePacketMoney(receivePacketMoney);
									double lastBalance = account.getBalance();
									double balance = formatDouble(lastBalance + packet);
									account.setBalance(balance);
									bizWeixinPacketService.save(account);
									//更新识别二维码用户送出红包的个数和金额
									focusAccount.setSendPacketNum(focusAccount.getSendPacketNum() + 1);
									double sendPacketMoney = formatDouble(focusAccount.getSendPacketMoney() + packet);
									focusAccount.setSendPacketMoney(sendPacketMoney);
									bizWeixinPacketService.save(focusAccount);
									//给收到红包的用户发送消息
//									double accountReceiveMoney = account.getReceivePacketMoney();
//									double shangpinPacket = account.getShangpinPacket();
//									double d = accountReceiveMoney + shangpinPacket;
//									double receiveMoney = formatDouble(d);
									double receiveMoney = account.getBalance();
									String receiveMsg = com.shangpin.wireless.api.util.Constants.RECEIVE_MSG;
									String content = receiveMsg.replace("${user.openId}", send_openId).replace("${user.nickName}", EmojiUtil.filterEmoji(focusAccount.getNickName())).replace("${packetMoney}", String.valueOf(packet)).replace("${receive.money}", String.valueOf(receiveMoney));
									logger.info("[微信]send receive user msg================{}", content);
									sendMsg(content, token);
									//3.向识别二维码的用户推送红包信息
									logger.info("[微信]向识别二维码用户推送红包回复消息=============================");
									msg.setContent("红包回复");
									msg.setPacketMoney(packet);
									msg.setSendNickName(record.getReceiveNickName());
									data = processTextMsg(msg,token,request);
								}
							}
						}
					}
				}
				logger.info("[微信]very handler msg return data==============={}", data);
				if (data == null) {
					msg.setContent("**default**");
					data = processTextMsg(msg,token,request);
				}
				if (data != null && data.length() > 0) {
					RecordEntity entity = SendRecords.get(msg.getFromUserName());
					long now = System.currentTimeMillis();
					if (entity != null) {
//						一分钟内不向同一个用户重复推送相同内容
						if (entity.sendtime + 6000 > now
								&& data.equals(entity.msg)) {
							return;
						}
					} else {
						entity = new RecordEntity();
						entity.userid = msg.getFromUserName();
					}
					entity.msg = data;
					entity.sendtime = now;
					SendRecords.put(msg.getFromUserName(), entity);
					logger.info("[微信]response write data start=================");
					response.getWriter().print(data);
					if(1 == msg.getFirst()){
						String focusOpenId = msg.getFocusOpenId();
						String first_send_msg = com.shangpin.wireless.api.util.Constants.FIRST_FOCUS_MSG;
						logger.info("[微信]origin first send msg================={}", first_send_msg);
						String content = first_send_msg.replace("${user.openId}", focusOpenId);
						logger.info("[微信]first_send_msg======================{}", content);
						sendMsg(content, token);
					}
					logger.info("[微信]response write data end=================");
//					writeLog("[reply]=\n" + data + "\n");
					writeLog("[reply]=");
					parseXmlMsg (data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long used = System.currentTimeMillis() - begin;
		logger.info("[微信]WeXinPlatformServlet doPost " + Thread.currentThread().getId() + ": used " + used);
	}
	
	private boolean checkSignature (HttpServletRequest request) {
//		String signature = request.getParameter("signature");
//        String timestamp = request.getParameter("timestamp");
//        String nonce = request.getParameter("nonce");
        		
//		String tmpArr = array(TOKEN, timestamp, nonce);
//		sort(tmpArr);
//		String tmpStr = implode( tmpArr );
//		tmpStr = sha1( tmpStr );
//		
//		if( tmpStr == signature ){
			return true;
//		}else{
//			return false;
//		}
	}
	
	private static final String REPLY_TYPE_COMMON = "common";
	private static final String REPLY_TYPE_VCODE = "vcode";
	private static final String REPLY_QRCODE = "qrcode";
	private static final String REPLY_TYPE_SHANGPIN_PRODUCT = "spproduct";
	private static final String REPLY_TYPE_AOLAI_PRODUCT = "alproduct";
	private static final String REPLY_TYPE_GOTO = "goto";
	private static final String REPLY_TYPE_USER	= "user";
	private static final String REPLY_TYPE_BIND = "bind";	//	会检查是否绑定过
	private static final String REPLY_TYPE_UNBIND = "unbind";
	private static final String REPLY_TYPE_LOTTERY = "lottery";
	private static final String REPLY_TYPE_JOIN_TEST = "jointest";
	private static final String REPLY_TYPE_QUIT_TEST = "quittest";
	private static final String REPLY_TYPE_MENU_ADD = "menuadd";
	private static final String REPLY_TYPE_MENU_DEL = "menudel";
	private static final String REPLY_ONLINE_CUSTOM	= "online";	//	在线客服
	private static final String REPLY_TYPE_POST = "post";
	private String processTextMsg (MsgReceived msg,String token, HttpServletRequest request) {
		final String ticket = msg.getTicket();
		logger.info("[微信]receive msg ticket=================={}", ticket);
		if(null != ticket && "subscribe".equals(msg.getEvent())){//表示是通过二维码关注尚品公共账号的
			String eventKey = msg.getEventKey();
			logger.info("[微信]qrcode eventKey====================={}", eventKey);
			String[] keys = eventKey.split("_");
			long userId = Long.parseLong(keys[1]);
			logger.info("[微信]send qrcode user id============{}", userId);
			WeiXinPacketAccount account = bizWeixinPacketService.findById(userId);
			logger.info("[微信]send qrcode user info============={}", account);
			if(null != account){//表示是通过好友发送的二维码
				//获取识别二维码用户信息
				String openId = msg.getFromUserName();
				if(openId.equals(account.getOpenId())){
					msg.setContent("识别自己二维码提示");
				}else {
					UserInfo userInfo = weiXinService.baseUserObj(token, openId);
					logger.info("[微信]user info==================={}", userInfo.toString());
					//保存识别用户信息
					boolean isUser = bizWeixinPacketService.existUser(openId);
					WeiXinPacketAccount focusAccount = null;
					if(!isUser){
						WeiXinPacketAccount account1 = new WeiXinPacketAccount();
						account1.setOpenId(openId);
						account1.setNickName(EmojiUtil.filterEmoji(userInfo.getNickname()));
						account1.setHeadImgUrl(userInfo.getHeadimgurl());
						account1.setSex(userInfo.getSex());
						account1.setCountry(userInfo.getCountry());
						account1.setProvince(userInfo.getProvince());
						account1.setCity(userInfo.getCity());
						account1.setLanguage(userInfo.getLanguage());
						account1.setUnionid(userInfo.getUnionid());
						account1.setCreateTime(new Timestamp(new Date().getTime()));
						account1.setShangpinPacket(5.0);//尚品微信5元红包
						account1.setBalance(5.0);
						focusAccount = bizWeixinPacketService.save(account1);
					}else{
						focusAccount = bizWeixinPacketService.findByOpenId(openId);
					}
					//判断是否两个好友是否相互识别过，比如识别过取消关注从新关注，就不应该再互送红包
					boolean isDiscern = bizWeixinPacketService.isDiscern(openId, account.getOpenId());
					if(!isDiscern){
						//1.给发送二维码的好友进行关联
						int packetNum = account.getReceivePacketNum();
						double packetMoney = account.getReceivePacketMoney();
						logger.info("[微信]nicke name is===============" + EmojiUtil.filterEmoji(account.getNickName()) + "=======receivePacketNmu:" + packetNum + "=====receivePacketMoney:" + packetMoney);
						double packet = redPacket(packetNum, packetMoney);
						WeiXinPacketRecord record = new WeiXinPacketRecord();
						record.setSendOpenId(openId);
						record.setSendNickName(EmojiUtil.filterEmoji(userInfo.getNickname()));
						record.setReceiveOpenId(account.getOpenId());
						record.setReceiveNickName(EmojiUtil.filterEmoji(account.getNickName()));
						record.setPacketMoney(packet);
						record.setCreateTime(new Timestamp(new Date().getTime()));
						bizWeixinPacketService.save(record);
						//2.更新发送二维码好友表，收到一个红包和红包金额
						account.setReceivePacketNum(account.getReceivePacketNum() + 1);
						double receivePacketMoney = formatDouble(account.getReceivePacketMoney() + packet);
						account.setReceivePacketMoney(receivePacketMoney);
						double lastBalance = account.getBalance();
						double balance = formatDouble(lastBalance + packet);
						account.setBalance(balance);
						bizWeixinPacketService.save(account);
						//更新识别二维码用户送出红包的个数和金额
						focusAccount.setSendPacketNum(focusAccount.getSendPacketNum() + 1);
						double sendPacketMoney = formatDouble(focusAccount.getSendPacketMoney() + packet);
						focusAccount.setSendPacketMoney(sendPacketMoney);
						bizWeixinPacketService.save(focusAccount);
						//给收到红包的用户发送消息
						double receiveMoney = account.getBalance();
						String receiveMsg = com.shangpin.wireless.api.util.Constants.RECEIVE_MSG;
						String content = receiveMsg.replace("${user.openId}", account.getOpenId()).replace("${user.nickName}", EmojiUtil.filterEmoji(focusAccount.getNickName())).replace("${packetMoney}", String.valueOf(packet)).replace("${receive.money}", String.valueOf(receiveMoney));
						logger.info("[微信]send receive msg============={}", content);
						sendMsg(content, token);
						//3.给新关注的用户推送消息
						logger.info("[微信]向识别二维码用户推送红包回复消息=============================");
						msg.setContent("红包回复");
						msg.setPacketMoney(packet);
						msg.setSendNickName(record.getReceiveNickName());
						//4.第一次关注推送尚品赠送的小礼包
						msg.setFirst(1);
						msg.setFocusOpenId(userInfo.getOpenid());
						msg.setFocusNickName(EmojiUtil.filterEmoji(userInfo.getNickname()));
					}else{
						msg.setContent("是否已经识别");
					}
				}
			}
		}

		//开头标识
		String prefix = "@";
		
		//输入 '#手机号' 领取红包
		if(TYPE_MSG_TEXT.equals(msg.getMsgType()) && msg.getContent().startsWith(prefix)){
			
			if(true){
				ConfigEntity entitydef0=new ConfigEntity();
				entitydef0.setReplied("活动已结束");
				return  processPostTextMsg(msg, entitydef0,token);				
			}
			
			/*配置信息*/
			String wishing = "10月22日轻奢购物狂欢节盛大开启";//主题
			String act_name = "购物狂欢节";//活动名称
			String remark = "尚品网10月22日轻奢购物狂欢节";//备注
			String totalNum = "1";//领取人数
			
			//已领取标识
			String redStatus = "1";
			//用户微信id
			String openId = msg.getFromUserName();			
			
			
//			//微信0:00-8:00点不发送红包
//			Calendar rightNow = Calendar.getInstance();
//			int hour = rightNow.get(Calendar.HOUR_OF_DAY);
//			if(hour >=0 && hour <8){
//				ConfigEntity entitydef=new ConfigEntity();
//				entitydef.setReplied("微信红包每天00：00－8：00不能发送，请在8:00-24:00期间领取。");
//				return  processPostTextMsg(msg, entitydef,token);
//			}
			
			String content = msg.getContent();
			//校验是否是#开头的电话号码
			Pattern p = Pattern.compile("^"+prefix+"1\\d{10}$");
			Matcher m = p.matcher(content);
			if(!m.matches()){
				logger.info("[领取红包]["+content+"]用户输入您输入的手机号码格式错误。");
				ConfigEntity entitydef=new ConfigEntity();
				entitydef.setReplied("咦，手机号输入不对哦，不是说回复“@您的手机号”吗？再试一次");
				return  processPostTextMsg(msg, entitydef,token);
			}
			logger.info("[领取红包]["+content+"]用户信息 openId={}", openId);
			String phone =  content.replace(prefix, "");
			
			//查询红包内容
			ResultObjOne<RedActivity> cashPacketResult = bizStarPacketService.getCashPacket(phone);			
			
			//判断是否已有领取资格
			if(cashPacketResult == null || !"0".equals(cashPacketResult.getCode())){
				logger.info("[领取红包]["+content+"]查询现金红包接口返回空,没有领取资格。");
				ConfigEntity entitydef=new ConfigEntity();
				entitydef.setReplied("您的手机号没有参加活动哦，快去微博话题找#赵世诚任性#抢红包");
				return  processPostTextMsg(msg, entitydef,token);
			}

			RedActivity RedActivity = cashPacketResult.getContent();
			
			if(RedActivity == null ){
				logger.info("[领取红包]["+content+"]查询现金红包接口返回content为空,没有领取资格。");
				ConfigEntity entitydef=new ConfigEntity();
				entitydef.setReplied("您的手机号没有参加活动哦，快去微博话题找#赵世诚任性#抢红包");
				return  processPostTextMsg(msg, entitydef,token);
			}
			
			List<AwardsInfo> awardsInfos = RedActivity.getAwardsInfo();
			if(awardsInfos == null || awardsInfos.isEmpty()){
				logger.info("[领取红包]["+content+"]查询现金红包接口返回List为空,没有领取资格。");
				ConfigEntity entitydef=new ConfigEntity();
				entitydef.setReplied("您的手机号没有参加活动哦，快去微博话题找#赵世诚任性#抢红包");
				return  processPostTextMsg(msg, entitydef,token);
			}
			
			//激活状态
			String status = awardsInfos.get(0).getIsActive();
			String amount = awardsInfos.get(0).getAmount();
			logger.info("[领取红包]["+content+"]现金红包金额amount={},激活状态status={}",amount,status);
			
			//已领
			if(redStatus.equals(status)){
				logger.info("[领取红包]["+content+"]状态为已领,不能再领。");
				ConfigEntity entitydef=new ConfigEntity();
				entitydef.setReplied("别闹，您已经领过了，不带这么玩儿的……");
				return  processPostTextMsg(msg, entitydef,token);
			}
			
			/*调用微信模板消息*/
			String first = "您的1020元红包将在10分钟内存入"+phone+"尚品网账户中，点击详情查看";
			String foot = "账号为手机号,密码已通过短信形式下发您的手机";
			String keyword1 = "1个";
			String keyword2 ="1020元";
			
			//为0 (接口没有红包存了0)
			if(StringUtils.isBlank(amount) || "0".equals(amount.trim())){
							
				//推送优惠券信息
				weiXinService.sendTemplateMsg4ReceiveRedEnvelope(openId, first, foot, keyword1, keyword2);
				logger.info("[领取红包]["+content+"]模板消息已推送。");
								
				logger.info("[领取红包]["+content+"]没有获取红包。");				
				ConfigEntity entitydef=new ConfigEntity();
				entitydef.setReplied("您的手机号没有抽到现金，但已成功抽取到了1020元礼包,快点击＂领取红包通知＂查收吧");
				return  processPostTextMsg(msg, entitydef,token);
			}
			
			//到这里,终于可以领了
			String ip = IpUtils.getIpAddrForFirst(request);			
			
			//转化为分  (此处保证为整数,若出现小数点会报签名错误)
			BigDecimal bigDecimal = new BigDecimal(amount);
			amount = bigDecimal.multiply(BigDecimal.valueOf(100)).intValue()+"";
			
			CashBonusResult cashBonusResult = weChatMerchantService.cashBonus(openId, amount, totalNum, wishing, ip, act_name, remark);
			logger.info("[领取红包]["+content+"]红包已发送给 openId={}",openId);			
				
			//推送优惠券信息
			weiXinService.sendTemplateMsg4ReceiveRedEnvelope(openId, first, foot, keyword1, keyword2);
			logger.info("[领取红包]["+content+"]模板消息已推送。");
			
			
			//发送微信红包成功 回写缓存
			if("SUCCESS".equals(cashBonusResult.getReturn_code()) &&
					"SUCCESS".equals(cashBonusResult.getResult_code())){				
				//调用激活接口
				ResultObjOne<Map<String, Object>> activeResult = bizStarPacketService.activeCashPacket("1"/*现金*/, phone);				
				
				if(activeResult != null && "0".equals(activeResult.getCode())){
					logger.info("[领取红包]["+content+"]调用激活接口成功,用户已领取红包。");	
				}else{
					logger.info("[领取红包]["+content+"]调用激活接口失败,警告。");
				}
				
				logger.info("[领取红包]["+content+"]领取成功。");		
				return null;
			}
			return null;
		}
		
		if(msg.getOnline()!=null&&"ON".equals(msg.getOnline())){//在线客服				
				return sendOnlineMsg(msg,false);
		}

        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        //调用自动回复
        boolean isSend = weXinPlatformService.business(msg, token, weiXinService);
		if(isSend){
			logger.info("[微信] 已发送自动回复!");
			return "";
		}

        ///////////////////////////////////////////////////////////////////////////////////////////////////////

		String textContent = getCommand(msg);//获取命令
		logger.info("[微信]command==========={}", textContent);
//		System.out.println("processTextMsg: " + textContent);
		if (textContent == null || textContent.length() == 0) return null;
		
		ConfigEntity entity = configs.get(textContent);
		logger.info("[微信]configEntity=========={}", entity);
		if (entity == null) {
			for (Iterator iter = configs.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				logger.info("[微信]属性文件中配置的key值=========={}", key);
				ConfigEntity e = configs.get(key);
				logger.info("[微信]属性文件中key值对应的实体类内容================{}", e);
				String regular = e.getRegular();
				logger.info("[微信]regular========{}", regular);
				try {
				if (regular != null && regular.length() > 0) {
					Pattern pattern = Pattern.compile(regular);
					Matcher m = pattern.matcher(textContent);
					boolean matched = m.matches();
					logger.info("[微信]matched============={}", matched);
					if (matched) {
						entity = e;
						logger.info("[微信]group count============{}", m.groupCount());
						if (m.groupCount() > 0) {
							e.setRegularGroup(m.group(1));
						}
						break;
					}
				}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		if (entity == null) {
			ConfigEntity entitydef=new ConfigEntity();
			entitydef.setReplied("感谢您关注尚品网，如有问题请点击\"我\"-\"在线客服\"进行咨询。");
			return  processPostTextMsg(msg, entitydef,token);
			
		}
		String access = entity.getAccess();
		logger.info("[微信]access========={}", access);
		if (access != null) {
			boolean verified = false;
			if (ADMIN != null && ADMIN.length > 0) {
				for (int i=0;i<ADMIN.length;i++) {
					if (msg.getFromUserName().equals(ADMIN[i])) {
						verified = true;break;
					}
				}
			}
			if (ACCESS_PRIVATE.equals(access)) {
			} else if (ACCESS_PUBLIC.equals(access)) {
				verified = true;
			} else if (ACCESS_PROTECT.equals(access)) {
				if (!verified)
				if (entity.getTestUsers() != null) {
					String[] testUsers = entity.getTestUsers();
					for (int i=0;i<testUsers.length;i++) {
						if (msg.getFromUserName().equals(testUsers[i])) {
							verified = true;
							break;
						}
					}
				}
				if (!verified)
				for (int i=Testers.size()-1;i>=0;i--) {
					if (msg.getFromUserName().equals(Testers.get(i))) {
						verified = true;
						break;
					}
				}
			}
			if (!verified) return null;
		}
//		System.out.println(entity.toString());
		String starttime = entity.getStartTime();
		logger.info("[微信]start time======={}", starttime);
		String endtime = entity.getEndTime();
		logger.info("[微信]end time============{}", endtime);
		String timeformat = entity.getTimeFormat();
		logger.info("[微信]time format================={}", timeformat);
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
			logger.info("[微信]属性文件中配置的类型============{}", type);
			if (REPLY_TYPE_COMMON.equals(type)) {
				return processCommonTextMsg(msg, entity);
			} else if (REPLY_TYPE_USER.equals(type)) {
				return processUserTextMsg(msg, entity,token,request);
			} else if (REPLY_TYPE_BIND.equals(type)) {
				return processBindTextMsg(msg, entity,token, request);
			} else if (REPLY_TYPE_UNBIND.equals(type)) {
				return processUnbindTextMsg(msg, entity,token,request);
			} else if (REPLY_TYPE_VCODE.equals(type)) {
				return processVCodeTextMsg(msg, entity);
			} else if (REPLY_TYPE_SHANGPIN_PRODUCT.equals(type)) {
				return processSPProductMsg(msg, entity);
//			} else if (REPLY_TYPE_AOLAI_PRODUCT.equals(type)) {
//				return processProductMsg(msg, entity, SHOP_TYPY_AOLAI);
			} else if (REPLY_TYPE_GOTO.equals(type)) {
				return processGotoTextMsg(msg, entity,token,request);
			} else if (REPLY_TYPE_LOTTERY.equals(type)) {
				return processLotteryTextMsg(msg, entity,token,request);
			} else if (REPLY_TYPE_JOIN_TEST.equals(type) || REPLY_TYPE_QUIT_TEST.equals(type)) {
				return processTestMsg(msg, entity);
			} else if (REPLY_TYPE_MENU_ADD.equals(type)) {
				return processMenuAddMsg(msg, entity);
			} else if (REPLY_TYPE_MENU_DEL.equals(type)) {
				return processMenuDelMsg(msg, entity);
			} else if (REPLY_TYPE_POST.equals(type)) {
				return processPostTextMsg(msg, entity,token);
			}else if (REPLY_ONLINE_CUSTOM.equals(type)) {
				processPostTextMsg(msg, entity,token);
				return sendOnlineMsg(msg,true);
			}
		return null;
	}
	
	private static String processCommonImageMsg (MsgReceived msg, ConfigEntity entity){
		StringBuilder sbBuff = new StringBuilder();
		sbBuff.append("<xml>");
		sbBuff.append("<ToUserName><![CDATA[").append(msg.getFromUserName()).append("]]></ToUserName>");
		sbBuff.append("<FromUserName><![CDATA[").append(msg.getToUserName()).append("]]></FromUserName>");
		sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
		sbBuff.append("<MsgType><![CDATA[image]]></MsgType>");
		sbBuff.append("<Image>");
		sbBuff.append("<MediaId><![CDATA[").append(msg.getMediaId()).append("]]></MediaId>");
		sbBuff.append("</Image>");
		sbBuff.append("</xml>");
		
		return sbBuff.toString();
	}
	
	
//	处理普通文字消息
	private static String processCommonTextMsg (MsgReceived msg, ConfigEntity entity) {
		StringBuilder sbBuff = new StringBuilder();
		sbBuff.append("<xml>");
		sbBuff.append("<ToUserName><![CDATA[").append(msg.getFromUserName()).append("]]></ToUserName>");
		sbBuff.append("<FromUserName><![CDATA[").append(msg.getToUserName()).append("]]></FromUserName>");
		sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
		final Object replyObj = entity.getReplied();
		logger.info("[微信]reply obj==================={}", replyObj);
		String reply = null;
		if (replyObj instanceof String) {
			reply = (String)replyObj;
			sbBuff.append("<MsgType><![CDATA[text]]></MsgType>");
			sbBuff.append("<Content><![CDATA[").append(reply).append("]]></Content>");
		} else if (replyObj instanceof JSONArray) {
			JSONArray replyArray = (JSONArray)replyObj;
			reply = replyArray.getString(new Random().nextInt(replyArray.size()));
			sbBuff.append("<MsgType><![CDATA[text]]></MsgType>");
			sbBuff.append("<Content><![CDATA[").append(reply).append("]]></Content>");
		} else if (replyObj instanceof JSONObject) {
			JSONObject obj = (JSONObject)replyObj;
			String msgtype = obj.getString("msgtype");
			logger.info("[微信]msgtype========================={}", msgtype);
			if ("news".equals(msgtype)) {
//				{"msgtype":"news","list":[{"title":"李某某","desc":"李某某被于某某摸下体，挑逗未成年人","pic":"http://pic.baidu.com","url":"http://news.sina.com.cn"}]}
				sbBuff.append("<MsgType><![CDATA[news]]></MsgType>");
				JSONArray array = obj.getJSONArray("list");
				sbBuff.append("<ArticleCount><![CDATA[").append(array.size()).append("]]></ArticleCount>");
				sbBuff.append("<Articles>");
				for (int i=0;i<array.size();i++) {
					sbBuff.append("<item>");
					try {
					JSONObject item = array.getJSONObject(i);
					sbBuff.append("<Title><![CDATA[").append(item.getString("title")).append("]]></Title>");
					sbBuff.append("<Description><![CDATA[").append(item.getString("desc")).append("]]></Description>");
					sbBuff.append("<PicUrl><![CDATA[").append(item.getString("pic")).append("]]></PicUrl>");
					sbBuff.append("<Url><![CDATA[").append(item.getString("url")).append("]]></Url>");
					} catch (Exception e) {}
					sbBuff.append("</item>");
				}
				sbBuff.append("</Articles>");
			}
		}
		sbBuff.append("<FuncFlag>0</FuncFlag>");
		sbBuff.append("</xml>");
		
		String origin = sbBuff.toString();
		
		return replaceAllPattern (origin, msg);
	}
	
//	处理获取V码类消息
	private static String processVCodeTextMsg (MsgReceived msg, ConfigEntity entity) {
		String name = entity.getRegularGroup();
		if (name != null && name.length() > 0) {
//			if (name.startsWith("的")) name = name.substring(1);
//			if (name.endsWith("的")) name = name.substring(0, name.length() - 1);
			String url = Constants.SP_BASE_URL + "getVCode";
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", name);
			map.put("region", "weixin");
			try {
				String data = WebUtil.readContentFromGet(url, map);
				GetVCodeServerResponse response = new GetVCodeServerResponse();
				response.json2Obj(data);
				StringBuilder sbBuff = new StringBuilder();
				sbBuff.append("<xml>");
				sbBuff.append("<ToUserName><![CDATA[").append(msg.getFromUserName()).append("]]></ToUserName>");
				sbBuff.append("<FromUserName><![CDATA[").append(msg.getToUserName()).append("]]></FromUserName>");
				sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
				sbBuff.append("<MsgType><![CDATA[text]]></MsgType>");
				if (Constants.SUCCESS.equals(response.getCode())) {
					String reply = null;
					if (msg.getGotoWithReply() != null) {
						reply = (String)msg.getGotoWithReply();
					} else {
						reply = (String)entity.getReplied();
					}
					if (reply != null && reply.length() > 0) {
						sbBuff.append("<Content><![CDATA[").append(reply.replace("${vcode}", response.getList().getString(0))).append("]]></Content>");
					} else {
						sbBuff.append("<Content><![CDATA[").append(response.getList().get(0)).append("]]></Content>");
					}
				} else {
					sbBuff.append("<Content><![CDATA[").append(response.getMsg()).append("]]></Content>");
				}
				sbBuff.append("<FuncFlag>0</FuncFlag>");
				sbBuff.append("</xml>");
				return sbBuff.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private String processGotoTextMsg (MsgReceived msg, ConfigEntity entity,String token, HttpServletRequest request) {
		msg.setContent(entity.getGotoKeyword());
		msg.setGotoWithReply(entity.getReplied());
		return processTextMsg (msg,token,request);
	}
	
	private static String processSPProductMsg (MsgReceived msg, ConfigEntity entity) {
		String name = entity.getRegularGroup();
		if (name == null || name.length() == 0) return null;
		
		String textContent = getCommand(msg);
		if (textContent.startsWith("products:")) {
			textContent = textContent.substring("products:".length(), textContent.length() - 1);
		} else {
			return null;
		}
		StringBuilder sbBuff = new StringBuilder();
		sbBuff.append("<xml>");
		sbBuff.append("<ToUserName><![CDATA[").append(msg.getFromUserName()).append("]]></ToUserName>");
		sbBuff.append("<FromUserName><![CDATA[").append(msg.getToUserName()).append("]]></FromUserName>");
		sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
		sbBuff.append("<MsgType><![CDATA[news]]></MsgType>");
		String[] productids = textContent.split(",");
		final int len = productids.length > 10 ? 10 : productids.length;
		sbBuff.append("<ArticleCount><![CDATA[").append(len).append("]]></ArticleCount>");
		sbBuff.append("<Articles>");
		for (int i=0;i<len;i++) {
			sbBuff.append("<item>");
			try {
			// 获取商品信息
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("productid", productids[i]);
			map.put("picw", i==0?"300":"60");
			map.put("pich", i==0?"400":"80");
			String url = Constants.SP_BASE_URL + "SPProductDetail/";
			String data = WebUtil.readContentFromGet(url, map);
			SPGoodsDetailServerResponse serverResponse = new SPGoodsDetailServerResponse();
			serverResponse.json2Obj(data);
			sbBuff.append("<Title><![CDATA[").append(serverResponse.getName()).append("]]></Title>");
			String desc = serverResponse.getBuyer();
			if (desc.length() == 0) {
				desc = serverResponse.getBrand();
			}
			sbBuff.append("<Description><![CDATA[").append(desc).append("]]></Description>");
			sbBuff.append("<PicUrl><![CDATA[").append(serverResponse.getPics().get(0)).append("]]></PicUrl>");
			sbBuff.append("<Url><![CDATA[").append(serverResponse.getShareurl()).append("]]></Url>");
			} catch (Exception e) {}
			sbBuff.append("</item>");
		}
		sbBuff.append("</Articles>");
		sbBuff.append("<FuncFlag>0</FuncFlag>");
		sbBuff.append("</xml>");
		return sbBuff.toString();
		
	}
	
	private String processBindTextMsg (MsgReceived msg, ConfigEntity entity,String token, HttpServletRequest request) {
		
		String weixinid = msg.getFromUserName();
		try {
		HqlHelper hqlHelper = new HqlHelper(AccountWeixinBinded.class, "aa");
		hqlHelper.addWhereCondition("aa.weixinId=?", weixinid);
		hqlHelper.addWhereCondition("aa.bindTime is not NULL");
		hqlHelper.addWhereCondition("aa.unbindTime is NULL");
		hqlHelper.addWhereCondition("aa.markup=?", "hand");
		AccountWeixinBinded account = accountBindedService.getByCondition(hqlHelper);
		
		if (account != null) {
			final String loginName = account.getLoginName();

			msg.setContent("**alreadybinded**");
			msg.setReserved(loginName);
			return processTextMsg (msg,token,request);
		} else {
			return processCommonTextMsg (msg, entity);
		}
//		给某用户推送消息，https://api.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN
//		支持类型：文本text；图片image；语音voice；视频video；链接link；图文news
//		{
//		    "touser":"OPENID",
//		    "msgtype":"link",
//		    "link": 
//		    {
//		         "title":"title",
//		         "description":"description",
//		         "url":"http://weixin.qq.com/",
//		         "thumb_media_id":"THUMB_MEDIA_ID"
//		    }
//		}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private String processUnbindTextMsg (MsgReceived msg, ConfigEntity entity,String token, HttpServletRequest request) {
		
		String weixinid = msg.getFromUserName();
		try {
		HqlHelper hqlHelper = new HqlHelper(AccountWeixinBinded.class, "aa");
		hqlHelper.addWhereCondition("aa.weixinId=?", weixinid);
		hqlHelper.addWhereCondition("aa.bindTime is not NULL");
		hqlHelper.addWhereCondition("aa.unbindTime is NULL");
		AccountWeixinBinded account = accountBindedService.getByCondition(hqlHelper);
		
		if (account != null) {
			account.setUnbindTime(new Date());
			accountBindedService.update(account);

			return processCommonTextMsg (msg, entity);
		} else {
			msg.setContent("**notbinded**");
			return processTextMsg (msg,token, request);
		}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private String processUserTextMsg (MsgReceived msg, ConfigEntity entity,String token, HttpServletRequest request) {
		
		String weixinid = msg.getFromUserName();
		try {
		HqlHelper hqlHelper = new HqlHelper(AccountWeixinBinded.class, "aa");
		hqlHelper.addWhereCondition("aa.weixinId=?", weixinid);
		hqlHelper.addWhereCondition("aa.bindTime is not NULL");
		hqlHelper.addWhereCondition("aa.unbindTime is NULL");
		hqlHelper.addWhereCondition("aa.markup=?", "hand");
		AccountWeixinBinded account = accountBindedService.getByCondition(hqlHelper);
		
		if (account != null) {

			return processCommonTextMsg (msg, entity);
		} else {
			msg.setContent(msg.getContent() + "账户绑定");
			return processTextMsg (msg,token,request);
		}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private static Object LotteryLock = new Object();
	private String processLotteryTextMsg (MsgReceived msg, ConfigEntity entity,String token, HttpServletRequest request) {
		
		String weixinid = msg.getFromUserName();
		try {
		HqlHelper hqlHelper = new HqlHelper(AccountWeixinBinded.class, "aa");
		hqlHelper.addWhereCondition("aa.weixinId=?", weixinid);
		hqlHelper.addWhereCondition("aa.bindTime is not NULL");
		hqlHelper.addWhereCondition("aa.unbindTime is NULL");
		AccountWeixinBinded account = accountBindedService.getByCondition(hqlHelper);
		
		if (account != null) {
			LotteryConfig config = getLotteryConfig();
			Date date = new Date();
			if (config != null
					&& config.getStartTime().before(date)
					&& config.getEndTime().after(date)) {
				final String issueId = config.getId();
				
				hqlHelper = new HqlHelper(WeixinLottery.class, "ww");
				hqlHelper.addWhereCondition("ww.issueId=?", issueId);
				hqlHelper.addWhereCondition("ww.weixinId=?", weixinid);
				WeixinLottery weixinLottery = weixinLotteryService.getByCondition(hqlHelper);
				if (weixinLottery != null) {
					return BuildReplyMsg(msg, "您已经参与过抽奖");
				}
				synchronized (LotteryLock) {
				Random ran = new Random();
				int probability = ran.nextInt(10000);
				for (int i=0;i<config.getEntity().length;i++) {
					LotteryConfig.PrizeEntity prizeEntity = config.getEntity()[i];
					if (probability >= prizeEntity.getRangeStart() && probability < prizeEntity.getRangeEnd()) {
						hqlHelper = new HqlHelper(WeixinLottery.class, "ww");
						hqlHelper.addWhereCondition("ww.issueId=?", issueId);
						hqlHelper.addWhereCondition("ww.prizeLevel=?", String.valueOf((i + 1)));
						List<WeixinLottery> weixinLotteryList = weixinLotteryService.getListByCondition(hqlHelper);
						if (weixinLotteryList.size() < prizeEntity.getCount()) {
							weixinLottery = new WeixinLottery();
							weixinLottery.setIssueId(issueId);
							weixinLottery.setWeixinId(weixinid);
							weixinLottery.setUserId(account.getUserId());
							weixinLottery.setPrizeLevel(String.valueOf((i + 1)));
							weixinLottery.setCreateTime(date);
							String activateCode = null;
							if (prizeEntity.getActivatecode() instanceof String) {
								activateCode = (String)prizeEntity.getActivatecode();
							} else if (prizeEntity.getActivatecode() instanceof JSONArray) {
								final int index = weixinLotteryList.size();
								activateCode = ((JSONArray)prizeEntity.getActivatecode()).getString(index);
							}
							weixinLottery.setActivateCode(activateCode);
							logger.info("[微信]probability = " + probability + "; prizeLevel = " + (i+1) + "; weixinid = " + weixinid + "; activateCode = " + activateCode);
							weixinLotteryService.save(weixinLottery);
							final String reply = prizeEntity.getDesc().replace("${activatecode}", activateCode);
							return BuildReplyMsg(msg, reply);
						} else {
							probability = prizeEntity.getRangeEnd();
						}
					}
				}
				return BuildReplyMsg(msg, "抽奖活动已结束，奖品已全部送出，感谢您的参与！");
				}
			} else {
				return BuildReplyMsg(msg, "抽奖活动已结束");
			}
		} else {
			msg.setContent(msg.getContent() + "账户绑定");
			return processTextMsg (msg,token, request);
		}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	private static final int TESTER_MENBER_COUNT	= 5;
	private static ArrayList<String> Testers = new ArrayList<String>();
	private String processTestMsg (MsgReceived msg, ConfigEntity entity) {
		final String type = entity.getType();
		final String weixinid = msg.getFromUserName();
		if (REPLY_TYPE_JOIN_TEST.equals(type)) {
			Testers.remove(weixinid);
			if (Testers.size() >= TESTER_MENBER_COUNT) {
				Testers.remove(0);
			}
			Testers.add(weixinid);
		} else if (REPLY_TYPE_QUIT_TEST.equals(type)) {
			Testers.remove(weixinid);
		}
		return processCommonTextMsg (msg, entity);
	}
	
	private String processMenuAddMsg (MsgReceived msg, ConfigEntity entity) {
		final Object replyObj = entity.getReplied();
		
		final String accesstoken = weiXinService.getToken();
		String url = URL_BASE + "menu/create?access_token=" + accesstoken;
		
		try {
			String postStr = replaceAllPattern (replyObj.toString(), msg);
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
			BuildReplyMsg(msg, e.getMessage());
			e.printStackTrace();
		}
		return "<xml></xml>";
	}

	private String processMenuDelMsg (MsgReceived msg, ConfigEntity entity) {
		final Object replyObj = entity.getReplied();
		
		final String accesstoken = weiXinService.getToken();
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
			BuildReplyMsg(msg, e.getMessage());
			e.printStackTrace();
		}
		return "<xml></xml>";
	}

	private String processPostTextMsg (MsgReceived msg, ConfigEntity entity,String token) {
		final String weixinid = msg.getFromUserName();
		final Object replyObj = entity.getReplied();

		String postStr = replaceAllPattern (replyObj.toString(), msg);
		String sendMsgText = BuildTextMsgBody(weixinid, postStr);
		logger.info("[微信]发送的文本消息内容============={}", sendMsgText);
		sendMsg(sendMsgText,token);
		return "";
	}
	
	
	private static String getCommand (MsgReceived msg) {
		String command = msg.getContent();
		if (command == null && TYPE_MSG_VOICE.equals(msg.getMsgType())) {
			command = msg.getRecognition();
		}
		
		return command;
	}
	
	public static String BuildReplyMsg (MsgReceived msg, String reply) {
		StringBuilder sbBuff = new StringBuilder();
		sbBuff.append("<xml>");
		sbBuff.append("<ToUserName><![CDATA[").append(msg.getFromUserName()).append("]]></ToUserName>");
		sbBuff.append("<FromUserName><![CDATA[").append(msg.getToUserName()).append("]]></FromUserName>");
		sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
		sbBuff.append("<MsgType><![CDATA[text]]></MsgType>");
		sbBuff.append("<Content><![CDATA[").append(reply).append("]]></Content>");
		sbBuff.append("<FuncFlag>0</FuncFlag>");
		sbBuff.append("</xml>");
		
		return sbBuff.toString();
	}
	
	public static void updateConfig () {
		logger.info("[微信]load property replymessage=========");
		try {
		Properties props = PropertiesUtil.getInstance("/weixin/replymessage.properties");
		configs.clear();
		for (Enumeration e = props.keys(); e.hasMoreElements();) {
			String propkey = (String) e.nextElement();
			logger.debug("[微信]property key========={}", propkey);
			final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
			ConfigEntity entity = ConfigEntity.parse(propvalue);
			final String type = entity.getType();
			if (REPLY_TYPE_MENU_ADD.equals(type)
					|| REPLY_TYPE_MENU_DEL.equals(type)) {
				entity.setAccess(ACCESS_PRIVATE);
			}
			final String[] keywords = entity.getKeywords();
			if (keywords != null) {
				for (int i=0;i<keywords.length;i++) {
					configs.put(keywords[i], entity);
				}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("WeXinPlatformServlet-update:" + e);
		}
	}
	
	private static final int SHOP_TYPY_SHANGPIN	= 1;
	private static final int SHOP_TYPY_AOLAI	= 2;
	private static final String PREFIX_PARAMS	= "#(";
	private static final String SUFFIX_PARAMS	= ")#";
	private static final String URL_SHANGPIN	= Constants.WEIXIN_ACTION_BASE_URL;

	private static final String PATTERN_WX_USER_OPENID	= "${openid.user.wx}";
	private static final String PATTERN_SP_URL	= "${url.sp}";
	private static final String PATTERN_AL_URL	= "${url.al}";
	private static final String PATTERN_SP_WOMEN_NEW_URL	= "${url.spwomennew}";
	private static final String PATTERN_SP_MEN_NEW_URL	= "${url.spmennew}";
	private static final String PATTERN_SP_PRODUCT_URL	= "${url.spproduct}";
	private static final String PATTERN_SP_PRODUCT_NAME_URL	= "${name.spproduct}";
	private static final String PATTERN_SP_PRODUCT_BRAND_URL	= "${brand.spproduct}";
	private static final String PATTERN_SP_PRODUCT_IMAGE_URL	= "${url.image.spproduct}";
	private static final String PATTERN_ACCOUNT_BIND_URL	= "${url.bind}";
	private static final String PATTERN_USER_INFO_URL	= "${url.userinfo}";
	private static final String PATTERN_COUPONS_URL	= "${url.coupons}";
	private static final String PATTERN_ORDERS_URL	= "${url.orders}";
	private static final String PATTERN_LOGISTICS_URL	= "${url.logistics}";
	private static final String PATTERN_BINDED_LOGINNAME	= "${loginname.binded}";
	private static final String PATTERN_TIME_NOW	= "${now.time}";
	private static final String PATTERN_OPEN_ID = "${qrcode.touser}";
	private static final String PATTERN_NICK_NAME = "${nickName}";
	private static final String PATTERN_PACKET_MONEY = "${packetMoney}";
	private static final String PATTERN_NEXT_NICK_NAME = "${nextNickName}";
	private static final String PATTERN_NEXT_PACKET_MONEY = "${nextPacketMoney}";
	private static final String PATTERN_MEDIA_ID = "${qrcode.media_id}";
	private static final String PATTERN_LOGIN_NAME = "${loginName}";
//	定义新的pattern，一定要加到数组里面！！！切记！！
	private static final String[] PATTERNS	= {
		PATTERN_WX_USER_OPENID,
		PATTERN_SP_URL,					PATTERN_AL_URL,
		PATTERN_SP_WOMEN_NEW_URL,		PATTERN_SP_MEN_NEW_URL,
		PATTERN_SP_PRODUCT_URL,			PATTERN_SP_PRODUCT_NAME_URL,
		PATTERN_SP_PRODUCT_BRAND_URL,	PATTERN_SP_PRODUCT_IMAGE_URL,
		PATTERN_ACCOUNT_BIND_URL,		PATTERN_USER_INFO_URL,
		PATTERN_COUPONS_URL,			PATTERN_ORDERS_URL,
		PATTERN_LOGISTICS_URL,
		PATTERN_BINDED_LOGINNAME,
		PATTERN_TIME_NOW,
		PATTERN_NICK_NAME,
		PATTERN_PACKET_MONEY,
		PATTERN_NEXT_NICK_NAME,
		PATTERN_NEXT_PACKET_MONEY,
		PATTERN_MEDIA_ID,
		PATTERN_LOGIN_NAME,
	};

	public static final String replaceAllPattern (String origin, MsgReceived msg) {
		logger.info("[微信]replaceAllPattern origin=============={}", origin);
		for (int i=PATTERNS.length-1;i>=0;i--) {
			String replacement;
			do {
				replacement = replacePattern (origin, PATTERNS[i], msg);
				if (replacement != null) {
					origin = replacement;
				} else {
					break;
				}
			} while (true);
		}
		
		return origin;
	}
	
	public static final String replacePattern (String origin, String pattern, MsgReceived msg) {
		//logger.info("[微信]replacePattern pattern============={}", pattern);
		try {
		final int index = origin.indexOf(pattern);
		if (index < 0) return null;
		final int nextindex = index + pattern.length();
		final int startparams = origin.indexOf(PREFIX_PARAMS, nextindex);
		final int endparams = origin.indexOf(SUFFIX_PARAMS, nextindex);
		
		final String weixinid = msg.getFromUserName();
		StringBuilder sb = new StringBuilder();
		
		if (PATTERN_SP_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("spindex!index");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_AL_URL.equals(pattern)) {
			sb.append(origin.substring(0, index));
			sb.append(Constants.BASE_M_AOLAI_URL +"aolaiindex!index?ch=36");
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_ACCOUNT_BIND_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("weixinaction!loginbind");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_USER_INFO_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("accountaction!info");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_COUPONS_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("couponaction!couponlist");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_ORDERS_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("orderaction!orderlist");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_LOGISTICS_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("logisticeaction!logisticeInfo");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_SP_WOMEN_NEW_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("merchandiseaction!getNewlist?gender=0&categoryid=A01");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_SP_MEN_NEW_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("merchandiseaction!getNewlist?gender=1&categoryid=A02");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_SP_PRODUCT_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("merchandiseaction!spdetail?");
			if (nextindex == startparams && endparams > startparams) {
				String productid = origin.substring(startparams + PREFIX_PARAMS.length(), endparams);
				sb.append("productid=").append(productid);
				String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
				
				sb = new StringBuilder();
				sb.append(origin.substring(0, index));
				sb.append(URL_SHANGPIN).append(encrypt);
				sb.append(origin.substring(endparams + SUFFIX_PARAMS.length()));
				return sb.toString();
			}
		} else if (PATTERN_SP_PRODUCT_NAME_URL.equals(pattern)) {
			if (nextindex == startparams && endparams > startparams) {
				String productid = origin.substring(startparams + PREFIX_PARAMS.length(), endparams);
				SPGoodsDetailServerResponse product = getSPProduct (productid);
				if (product != null && product.getName() != null) {
					return origin.substring(0, index) + (product.getName()) + origin.substring(endparams + SUFFIX_PARAMS.length());
				}
			}
		} else if (PATTERN_SP_PRODUCT_BRAND_URL.equals(pattern)) {
			if (nextindex == startparams && endparams > startparams) {
				String productid = origin.substring(startparams + PREFIX_PARAMS.length(), endparams);
				SPGoodsDetailServerResponse product = getSPProduct (productid);
				if (product != null && product.getBrand() != null) {
					return origin.substring(0, index) + (product.getBrand()) + origin.substring(endparams + SUFFIX_PARAMS.length());
				}
			}
		} else if (PATTERN_SP_PRODUCT_IMAGE_URL.equals(pattern)) {
			if (nextindex == startparams && endparams > startparams) {
				String productid = origin.substring(startparams + PREFIX_PARAMS.length(), endparams);
				SPGoodsDetailServerResponse product = getSPProduct (productid);
				if (product != null && product.getPics() != null) {
					return origin.substring(0, index) + (product.getPics().getString(0)) + origin.substring(endparams + SUFFIX_PARAMS.length());
				}
			}
		} else if (PATTERN_WX_USER_OPENID.equals(pattern)) {
			sb.append(origin.substring(0, index));
			sb.append(msg.getFromUserName());
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_BINDED_LOGINNAME.equals(pattern)) {
			sb.append(origin.substring(0, index));
			sb.append((String)msg.getReserved());
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_TIME_NOW.equals(pattern)) {
			if (nextindex == startparams && endparams > startparams) {
				String timeformat = origin.substring(startparams + PREFIX_PARAMS.length(), endparams);
				SimpleDateFormat sdf = new SimpleDateFormat(timeformat);
				String now = sdf.format(new Date().getTime());
				
				sb.append(origin.substring(0, index));
				sb.append(now);
				sb.append(origin.substring(endparams + SUFFIX_PARAMS.length()));
				return sb.toString();
			}
		} else if(PATTERN_NICK_NAME.equals(pattern)){
			sb.append(origin.substring(0, index));
			sb.append(msg.getSendNickName());
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if(PATTERN_PACKET_MONEY.equals(pattern)){
			sb.append(origin.substring(0, index));
			sb.append(msg.getPacketMoney());
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if(PATTERN_NEXT_NICK_NAME.equals(pattern)){
			sb.append(origin.substring(0, index));
			sb.append(msg.getSendNickName());
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if(PATTERN_NEXT_PACKET_MONEY.equals(pattern)){
			sb.append(origin.substring(0, index));
			sb.append(msg.getPacketMoney());
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if(PATTERN_MEDIA_ID.equals(pattern)){
			sb.append(origin.substring(0, index));
			sb.append(msg.getMediaId());
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if(PATTERN_LOGIN_NAME.equals(pattern)){
			sb.append(origin.substring(0, index));
			sb.append(msg.getFromUserName());
			sb.append(origin.substring(nextindex));
			return sb.toString();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	static HashMap<String, SPGoodsDetailServerResponse> SPProductCache = new HashMap<String, SPGoodsDetailServerResponse>();
	private static SPGoodsDetailServerResponse getSPProduct (String productid) {
		SPGoodsDetailServerResponse serverResponse = SPProductCache.get(productid);
		if (serverResponse != null) {
			return serverResponse;
		}
		try {
		// 获取商品信息
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("productid", productid);
		map.put("picw", "60");
		map.put("pich", "80");
		String url = Constants.SP_BASE_URL + "SPProductDetail/";
		String data = WebUtil.readContentFromGet(url, map);
		serverResponse = new SPGoodsDetailServerResponse();
		serverResponse.json2Obj(data);
		
		if (SPProductCache.size() > 20) {
			SPProductCache.clear();
		}
		SPProductCache.put(productid, serverResponse);
		return serverResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	private static void sendMsg (String msg,String token) {
		
		String url = URL_BASE + "message/custom/send?access_token=" + token;

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("grant_type", "client_credential");
		map.put("appid", APP_ID);
		map.put("secret", APP_SECRET);

		try {
			String data = WebUtil.readContentFromPost(url, msg);
//			{"errcode":45015,"errmsg":"response out of time limit"}
//			{"errcode":42001,"errmsg":"access_token expired"}
			
			JSONObject obj = JSONObject.fromObject(data);
			final int errcode = obj.getInt("errcode");
			logger.info("[微信]send msg return code=========={}", errcode);
			if (0 == errcode) {	//	发送成功
				
			} else if (42001 == errcode) {	//	access_token超时
				logger.info("[微信]---111---access_token timeout，repeat send---111---" + token);
				sendMsg(msg,weiXinService.getToken());
				logger.info("[微信]---222---access_token timeout，repeat send---222---" + token);
			} else if (45015 == errcode) {	//	用户24小时内没有与官微交互，不能推送消息
				logger.info("[微信]Amounted to less than 24 hours ,Don't push the message");
			} else {
				logger.info("[微信]data :"+data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("unused")
	private static String sendOnlineTextMsg (MsgReceived msg,String token) {		
		StringBuilder sbBuff = new StringBuilder();
		sbBuff.append("<xml>");
		sbBuff.append("<ToUserName><![CDATA[").append(msg.getToUserName()).append("]]></ToUserName>");
		sbBuff.append("<FromUserName><![CDATA[").append(msg.getFromUserName()).append("]]></FromUserName>");
		sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
		sbBuff.append("<MsgType><![CDATA[text]]></MsgType>");	
		sbBuff.append("<Content><![CDATA[").append(msg.getContent()).append("]]></Content>");
		sbBuff.append("</xml>");
		String url = ONLINE_URL_BASE;		
		try {
			String data = WebUtil.readContentFromPost(url, sbBuff.toString());
		} catch (Exception e) {
			BuildReplyMsg(msg, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	private static String sendOnlineMsg (MsgReceived msg,boolean isFirst) {		
		StringBuilder sbBuff = new StringBuilder();
		boolean flag=true;
		if(TYPE_MSG_TEXT.equals(msg.getMsgType())){
			sbBuff.append("<xml>");
			sbBuff.append("<ToUserName><![CDATA[").append(msg.getToUserName()).append("]]></ToUserName>");
			sbBuff.append("<FromUserName><![CDATA[").append(msg.getFromUserName()).append("]]></FromUserName>");
			sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
			sbBuff.append("<MsgType><![CDATA[text]]></MsgType>");	
			sbBuff.append("<Content><![CDATA[").append(msg.getContent()).append("]]></Content>");
			sbBuff.append("<MsgId>").append(msg.getMsgId()).append("</MsgId>");
			sbBuff.append("</xml>");			
		}else if(TYPE_MSG_IMAGE.equals(msg.getMsgType())){
			sbBuff.append("<xml>");
			sbBuff.append("<ToUserName><![CDATA[").append(msg.getToUserName()).append("]]></ToUserName>");
			sbBuff.append("<FromUserName><![CDATA[").append(msg.getFromUserName()).append("]]></FromUserName>");
			sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
			sbBuff.append("<MsgType><![CDATA[image]]></MsgType>");	
			sbBuff.append("<PicUrl><![CDATA[").append(msg.getPicUrl()).append("]]></PicUrl>");
			sbBuff.append("<MediaId><![CDATA[").append(msg.getMediaId()).append("]]></MediaId>");
			sbBuff.append("<MsgId>").append(msg.getMsgId()).append("</MsgId>");
			sbBuff.append("</xml>");	
		}else if(TYPE_MSG_VOICE.equals(msg.getMsgType())){
			sbBuff.append("<xml>");
			sbBuff.append("<ToUserName><![CDATA[").append(msg.getToUserName()).append("]]></ToUserName>");
			sbBuff.append("<FromUserName><![CDATA[").append(msg.getFromUserName()).append("]]></FromUserName>");
			sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
			sbBuff.append("<MsgType><![CDATA[voice]]></MsgType>");	
			sbBuff.append("<MediaId><![CDATA[").append(msg.getMediaId()).append("]]></MediaId>");
			sbBuff.append("<Format><![CDATA[").append(msg.getFormat()).append("]]></Format>");
			sbBuff.append("<MsgId>").append(msg.getMsgId()).append("</MsgId>");
			sbBuff.append("</xml>");	
		}else{
	        flag=false;
			sbBuff.append("<xml>");
			sbBuff.append("<ToUserName><![CDATA[").append(msg.getToUserName()).append("]]></ToUserName>");
			sbBuff.append("<FromUserName><![CDATA[").append(msg.getFromUserName()).append("]]></FromUserName>");
			sbBuff.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>");
			sbBuff.append("<MsgType><![CDATA[text]]></MsgType>");	
			sbBuff.append("<Content><![CDATA[申请在线客服]]></Content>");
			sbBuff.append("<MsgId>").append(new Date().getTime()).append("</MsgId>");
			sbBuff.append("</xml>");
		}
		String url = ONLINE_URL_BASE;		
		try {
		    if(isFirst||flag){	
		    	
		    	logger.info("[微信]发送给客服接口数据 =" + sbBuff.toString());
//		        String data = WebUtil.readContentFromPost(url, sbBuff.toString());
		    	String data = HttpClientUtil.doPostWithJSON(url, sbBuff.toString());
		        logger.info("[微信]调用在线客服接口返回 data =" + data);
		    }
		} catch (Exception e) {
			BuildReplyMsg(msg, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String BuildTextMsgBody (String touser, String content) {
		JSONObject obj = new JSONObject();
		obj.put("touser", touser);
		obj.put("msgtype", "text");
		JSONObject text = new JSONObject();
		text.put("content", content);
		obj.put("text", text);
		
		return obj.toString();
	}
	
	/**
	 * 发送图片组装的json数据
	 * @param touser
	 * @param mediaId
	 * @return
	 */
	public static String buildImgMsgBody(String touser, String mediaId){
		JSONObject object = new JSONObject();
		object.put("touser", touser);
		object.put("msgtype", "image");
		JSONObject obj = new JSONObject();
		obj.put("media_id", mediaId);
		object.put("image", obj);
		return object.toString();
	}
	
	public static String buildNewMsgBody(String touser,String title, String desc, String picurl, String url){
		JSONObject newsContObj = new JSONObject();
		newsContObj.put("title", title);
		newsContObj.put("description", desc);
		newsContObj.put("picurl", picurl);
		newsContObj.put("url", url);
		JSONArray array = new JSONArray();
		array.add(newsContObj);
		JSONObject newsObj = new JSONObject();
		newsObj.put("articles", array);
		JSONObject obj = new JSONObject();
		obj.put("touser", touser);
		obj.put("msgtype", "news");
		obj.put("news", newsObj);
		return obj.toString();
	}

	private static final String KEY_WEIXIN_LOTTERY	= "weixinlottery";
	private static final int LOTTERY_KEEP_TIME	= 30 * 60 * 1000;	//	30分钟
	private static LotteryConfig LotteryCache;
	private static long LotteryTime;
	private static LotteryConfig getLotteryConfig () {

		long now = System.currentTimeMillis();
		try {
		if (now > LotteryTime + LOTTERY_KEEP_TIME) {
			Properties props = PropertiesUtil.getInstance("/coupon/config.properties");

			for (Enumeration e = props.keys(); e.hasMoreElements();) {
				String propkey = (String) e.nextElement();
				final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
				if (KEY_WEIXIN_LOTTERY.equals(propkey)) {
					LotteryCache = LotteryConfig.parse(propvalue);
				}
			}
			LotteryTime = now;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LotteryCache;
	}

	public static void writeLog(String log) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fileName = sdf.format(new Date()) + ".txt";
			String path = ProReader.getInstance().getProperty("weixinOperationLogsPath");
			File filePath = new File(path);
			if (!filePath.exists())
				filePath.mkdirs();
			RandomAccessFile randomFile = new RandomAccessFile(path + fileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.write((log + "\n").getBytes());
			randomFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseXmlMsg (String data) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes("UTF-8"));
		parseXmlMsg(bais);
	}
	private MsgReceived parseXmlMsg (InputStream is) throws Exception {
		XMap xmap = new XMap();
		xmap.register(MsgReceived.class);
		Object[] beans = xmap.loadAll(is);
		if (beans != null && beans.length == 1) {
			MsgReceived msg = (MsgReceived)beans[0];			
			WeixinMessage message = new WeixinMessage();
			message.setFromUser(msg.getFromUserName());
			message.setToUser(msg.getToUserName());
			message.setMsgId(msg.getMsgId());
			message.setMsgType(msg.getMsgType());
			message.setCreateTime(new Date());
			if ("gh_abfc1ec3686b".equals(msg.getFromUserName())) {
				message.setSource(SOURCE_AUTO);
			} else {
				message.setSource(SOURCE_USER);
			}
			
			StringBuilder sbBuff = new StringBuilder();
			sbBuff.append("ToUserName: ").append(msg.getToUserName()).append("\n");
			sbBuff.append("FromUserName: ").append(msg.getFromUserName()).append("\n");
			sbBuff.append("CreateTime: ").append(msg.getCreateTime()).append("\n");
			sbBuff.append("MsgType: ").append(msg.getMsgType()).append("\n");
			logger.info("[微信]msg type is============={}", msg.getMsgType());
			if (msg.getMsgId() != null) {
				sbBuff.append("MsgId: ").append(msg.getMsgId()).append("\n");
			}
			if (TYPE_MSG_TEXT.equals(msg.getMsgType())) {
				sbBuff.append("Content: ").append(msg.getContent()!=null?msg.getContent():"").append("\n");
				
				message.setContent(msg.getContent());
			} else if (TYPE_MSG_IMAGE.equals(msg.getMsgType())) {
				StringBuilder sb = new StringBuilder();
				sb.append("PicUrl: ").append(msg.getPicUrl()!=null?msg.getPicUrl():"");
				sbBuff.append(sb.toString()).append("\n");
				sbBuff.append("MediaId: ").append(msg.getMediaId()!=null?msg.getMediaId():"").append("\n");
				
				message.setMediaId(msg.getMediaId());
				message.setParams(sb.toString());
			} else if (TYPE_MSG_VOICE.equals(msg.getMsgType())) {
				StringBuilder sb = new StringBuilder();
				sb.append("Format: ").append(msg.getFormat()!=null?msg.getFormat():"").append("\n");
				sb.append("Recognition: ").append(msg.getRecognition()!=null?msg.getRecognition():"");
				sbBuff.append(sb.toString()).append("\n");
				sbBuff.append("MediaId: ").append(msg.getMediaId()!=null?msg.getMediaId():"").append("\n");

				message.setContent(msg.getRecognition());
				message.setMediaId(msg.getMediaId());
				message.setParams(sb.toString());
			} else if (TYPE_MSG_VIDEO.equals(msg.getMsgType())) {
				StringBuilder sb = new StringBuilder();
				sb.append("ThumbMediaId: ").append(msg.getThumbMediaId()!=null?msg.getThumbMediaId():"");
				sbBuff.append(sb.toString()).append("\n");
				sbBuff.append("MediaId: ").append(msg.getMediaId()!=null?msg.getMediaId():"").append("\n");
				
				message.setMediaId(msg.getMediaId());
				message.setParams(sb.toString());
			} else if (TYPE_MSG_LOCATION.equals(msg.getMsgType())) {
				StringBuilder sb = new StringBuilder();
				sb.append("Label: ").append(msg.getLabel()!=null?msg.getLabel():"").append("\n");
				sb.append("Location: ").append(msg.getLocation_X()!=null?(msg.getLocation_Y() + "," + msg.getLocation_X()):"").append("\n");
				sb.append("Scale: ").append(msg.getScale()!=null?msg.getScale():"");
				sbBuff.append(sb.toString()).append("\n");
				
				message.setParams(sb.toString());
			} else if (TYPE_MSG_LINK.equals(msg.getMsgType())) {
				StringBuilder sb = new StringBuilder();
				sb.append("Url: ").append(msg.getUrl()!=null?msg.getUrl():"").append("\n");
				sb.append("Title: ").append(msg.getTitle()!=null?msg.getTitle():"").append("\n");
				sb.append("Description: ").append(msg.getDescription()!=null?msg.getDescription():"");
				sbBuff.append(sb.toString()).append("\n");
				
				message.setParams(sb.toString());
			} else if (TYPE_MSG_EVENT.equals(msg.getMsgType())) {
				logger.info("[微信]msg event is================{}", msg.getEvent());
				message.setEventType(msg.getEvent());
				sbBuff.append("Event: ").append(msg.getEvent()!=null?msg.getEvent():"").append("\n");
				StringBuilder sb = new StringBuilder();
				if (TYPE_EVENT_SUBSCRIBE.equals(msg.getEvent()) || TYPE_EVENT_SCAN.equals(msg.getEvent())) {
					sb.append("EventKey: ").append(msg.getEventKey()!=null?msg.getEventKey():"").append("\n");
					sb.append("Ticket: ").append(msg.getTicket()!=null?msg.getTicket():"");
					sbBuff.append(sb.toString()).append("\n");
					
					message.setParams(sb.toString());
				} else if (TYPE_EVENT_CLICK.equals(msg.getEvent())) {//点击事件
					String eventKey = msg.getEventKey();
					logger.info("[微信]click key is=================={}", eventKey);
					sb.append("EventKey: ").append(msg.getEventKey()!=null?msg.getEventKey():"");
					sbBuff.append(sb.toString()).append("\n");
					message.setParams(sb.toString());
				} else if (TYPE_EVENT_LOCATION.equals(msg.getEvent())) {
					sb.append("Latitude: ").append(msg.getLatitude()!=null?msg.getLatitude():"").append("\n");
					sb.append("Longitude: ").append(msg.getLongitude()!=null?msg.getLongitude():"").append("\n");
					sb.append("Precision: ").append(msg.getPrecision()!=null?msg.getPrecision():"");
					sbBuff.append(sb.toString()).append("\n");
					
					message.setParams(sb.toString());
				}
			} else if (TYPE_MSG_NEWS.equals(msg.getMsgType())) {
				StringBuilder sb = new StringBuilder();
				sb.append("\"articles\":[\n");
				if (msg.getArticleCount() > 0) {
					List<XmlArticleItem> items = msg.getArticles().getItems();
					for (int i=0;i<items.size();i++) {
						sb.append("{\n");
						XmlArticleItem item = items.get(i);
						sb.append("\"title\":\"").append(item.getTitle()).append("\",\n");
						sb.append("\"description\":\"").append(item.getDescription()).append("\",\n");
						sb.append("\"url\":\"").append(item.getUrl()).append("\",\n");
						sb.append("\"picurl\":\"").append(item.getPicUrl()).append("\"\n");
						if (i == items.size() - 1) {
							sb.append("}\n");
						} else {
							sb.append("},\n");
						}
					}
				}
				sb.append("]");

				sbBuff.append(sb.toString()).append("\n");
				
				message.setParams(sb.toString());
			}
			writeLog(sbBuff.toString());			
		    HqlHelper hqlHelper = new HqlHelper(WeixinMessage.class, "aa");
            hqlHelper.addWhereCondition("aa.fromUser=?", msg.getFromUserName());
            hqlHelper.addOrderByProperty("aa.createTime", false);
		    WeixinMessage mes = weixinMessageService.getByCondition(hqlHelper); 
		    if(mes!=null&&"ON".equals(mes.getReserve0())){
                msg.setOnline("ON");
            }else{
                if("EventKey: 在线客服".equals(message.getParams())){
                    message.setReserve0(ONLINE_CUSTOM_ON);
                    weixinMessageService.save(message);
                }
            }		    		    							
			return msg;
		}
		
		return null;
	}
	public void getTest(){
		
	}
	
	/**
	 * double 类型相加格式化
	 * @param d
	 * @return
	 */
	public double formatDouble(double d){
		DecimalFormat format = new DecimalFormat("#.00");
		String str = format.format(d);
		return Double.parseDouble(str);
	}
	/**
	 * 
	 * @param totalPacketNum  总人数
	 * @param totalPackMoney  总钱数
	 * @return
	 */
	public static double redPacket(int totalPacketNum,double totalPackMoney  ){
		double tmpMoney=0;
		
		if(0<=(totalPacketNum+1)&&(totalPacketNum+1)<8){
			int	totalPeople=7; // 抢红包总人数
			double	totalMoney=9.5-totalPackMoney;// 红包总金额
			if((totalPacketNum+1)<7){
				tmpMoney=randomm(totalMoney, totalPeople, totalPacketNum);
			}else{
				double tmpMoney1 = (Math.random()*(9.99999*100-9.5*100)+9.5*100)/100;
				tmpMoney=tmpMoney1-totalPackMoney;
			}
		
		}else if(8<=(totalPacketNum+1)&&(totalPacketNum+1)<30){
			int	totalPeople=29;
			if(totalPacketNum+1==8){
				  double tmpMon = (Math.random()*(15*100-10*100)+10*100)/100;
				  tmpMoney=tmpMon-totalPackMoney;
			}
			else if((totalPacketNum+1)<29){
				double	totalMoney=29.5-totalPackMoney;
				tmpMoney=randomm(totalMoney, totalPeople, totalPacketNum);
			}else{
				double tmpMoney1 = (Math.random()*(29.99999*100-29.5*100)+29.5*100)/100;
				tmpMoney=tmpMoney1-totalPackMoney;
			}
		}else if(30<=(totalPacketNum+1)&&(totalPacketNum+1)<50){
			int	totalPeople=49;
			double minMoney = 0.01;
			if(totalPacketNum+1==30){
				  double tmpMon = (Math.random()*(40*100-30*100)+30*100)/100;
				  tmpMoney=tmpMon-totalPackMoney;
			}
			else if((totalPacketNum+1)<49){
				double	totalMoney=49.5-totalPackMoney;
				tmpMoney=randomm(totalMoney, totalPeople, totalPacketNum);
			}else{
				double tmpMoney1 = (Math.random()*(49.99999*100-49.5*100)+49.5*100)/100;
				tmpMoney=tmpMoney1-totalPackMoney;
			}
		
		}else if(totalPacketNum+1==50){
			  double tmpMon = (Math.random()*(60*100-50*100)+50*100)/100;
			  tmpMoney=tmpMon-totalPackMoney;
		}else if(50<(totalPacketNum+1)&&(totalPacketNum+1)<100){
			int	totalPeople=100;
				double	totalMoney=99.5-totalPackMoney;
				tmpMoney=randomm(totalMoney, totalPeople, totalPacketNum);
			
		}else if(100<=(totalPacketNum+1)&&(totalPacketNum+1)<300){
			int	totalPeople=300;
				double	totalMoney=149.5-totalPackMoney;
				tmpMoney=randomm(totalMoney, totalPeople, totalPacketNum);
			
		}else{
			int	totalPeople=5002;
			double	totalMoney=200-totalPackMoney;
			tmpMoney=randomm(totalMoney, totalPeople, totalPacketNum);
		}
		
		BigDecimal  b = new  BigDecimal(tmpMoney);  
		double  f1 =  b.setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue();  
		if(f1==0){
			f1=0.01;
		}
		return  f1;
	}
	
	private static double randomm(double totalMoney,int totalPeople,double totalPacketNum){
		double safeMoney=(totalMoney-(totalPeople-(totalPacketNum+1))*0.01)/(totalPeople -(totalPacketNum+1));//随机安全上限
		double tmpMoney = (Math.random()*(safeMoney*100-0.01*100)+0.01*100)/100;
		return tmpMoney ;
	}
	public static void main(String args[]) {
//		String text = "<weixin><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName><FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName></weixin>";
//		String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <web-app version=\"2.4\" xmlns=\"http://java.sun.com/xml/ns/j2ee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd\"></web-app><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName><FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>";
//		String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>\n<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>\n<CreateTime>1363686314</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[你好吗]]></Content>\n<MsgId>5856988120732790036</MsgId>";
		//	账户绑定
//		String text = "<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>\n<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>\n<CreateTime>1363686314</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[账户绑定]]></Content>\n<MsgId>5856988120732790036</MsgId>\n</xml>";
		//	女士新品
//		String text = "<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>\n<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>\n<CreateTime>1363686314</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[女士新品]]></Content>\n<MsgId>5856988120732790036</MsgId>\n</xml>";
		//	订单查询
//		String text = "<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>\n<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>\n<CreateTime>1363686314</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[订单查询]]></Content>\n<MsgId>5856988120732790036</MsgId>\n</xml>";
		//	尚品最新活动
//		String text = "<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>\n<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>\n<CreateTime>1363686314</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[尚品最新活动]]></Content>\n<MsgId>5856988120732790036</MsgId>\n</xml>";
		//	强制解除绑定
//		String text = "<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>\n<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>\n<CreateTime>1363686314</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[强制解除绑定]]></Content>\n<MsgId>5856988120732790036</MsgId>\n</xml>";
		//	菜单
	//	String text = "<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>\n<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>\n<CreateTime>1363686314</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[创建菜单]]></Content>\n<MsgId>5856988120732790036</MsgId>\n</xml>";
		//	抽奖
//		String text = "<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>\n<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>\n<CreateTime>1363686314</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[大乐透]]></Content>\n<MsgId>5856988120732790036</MsgId>\n</xml>";
		//	V码
//		String text = "<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>\n<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>\n<CreateTime>1363686314</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[IFC三夜三天]]></Content>\n<MsgId>5856988120732790036</MsgId>\n</xml>";
		//	图文

		String text = "<xml><ToUserName><![CDATA[oFHXijs8DAnA2OfSwOUH7rZtuv4U]]></ToUserName><FromUserName><![CDATA[gh_abfc1ec3686b]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>3</ArticleCount><Articles><item><Title><![CDATA[1夜1天]]></Title><Description><![CDATA[1日1天]]></Description><PicUrl><![CDATA[http://mmsns.qpic.cn/mmsns/uSsXTbwzqVcQiarcO5ym9UVeB76JiajebY49QiaqxLWB3UlJno7jZJk7A/0]]></PicUrl><Url><![CDATA[m.shangpin.com]]></Url></item><item><Title><![CDATA[2夜2天]]></Title><Description><![CDATA[2日2天]]></Description><PicUrl><![CDATA[222]]></PicUrl><Url><![CDATA[2222]]></Url></item><item><Title><![CDATA[3夜3天]]></Title><Description><![CDATA[3日3天]]></Description><PicUrl><![CDATA[333]]></PicUrl><Url><![CDATA[3333]]></Url></item></Articles></xml>";

		//String text = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>3</ArticleCount><Articles><item><Title><![CDATA[1夜1天]]></Title><Description><![CDATA[1日1天]]></Description><PicUrl><![CDATA[111]]></PicUrl><Url><![CDATA[1111]]></Url></item><item><Title><![CDATA[2夜2天]]></Title><Description><![CDATA[2日2天]]></Description><PicUrl><![CDATA[222]]></PicUrl><Url><![CDATA[2222]]></Url></item><item><Title><![CDATA[3夜3天]]></Title><Description><![CDATA[3日3天]]></Description><PicUrl><![CDATA[333]]></PicUrl><Url><![CDATA[3333]]></Url></item></Articles></xml>";

		String s="<xml><ToUserName><![CDATA[oFHXijvkFXv7ypscJ-jl3rP3O4lY]]></ToUserName><FromUserName><![CDATA[gh_abfc1ec3686b]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[发飞机啊空房间爱咖啡健身卡]]></Content></xml>";
		//s=BuildTextMsgBody("oFHXijvkFXv7ypscJ-jl3rP3O4lY","dsadfafdsafdfs");
		//sendMsg(s);
		//String a="{\"text\":{\"content\":\"你好\"},\"msgtype\":\"text\",\"touser\":\"oFHXijvkFXv7ypscJ-jl3rP3O4lY\"}";oS8YyR4MIgV0IMYiIAmI0jQAc4Rs3ISOX18R2xsM5EtYiYjEjD4L9mU2AwzfNlgi
		//String a="{\"text\":{\"content\":\"你好\"},\"msgtype\":\"text\",\"touser\":\"oS8YyR4MIgV0IMYiIAmI0jQAc4Rs3ISOX18R2xsM5EtYiYjEjD4L9mU2AwzfNlgi\"}";
//		String a="{\"touser\":\"oFHXijvkFXv7ypscJ-jl3rP3O4lY\",\"msgtype\":\"text\",\"text\":{\"content\":\"我是今天的值班客服，很高兴为您服务。请问您需要咨询哪方面的问题？为了更好的跟踪服务效果，请在对话结束后对我的服务满意度进行评价，非常感谢您的支持。\"}}";
//		WeChatPublicService weiXinService= new WeChatPublicServiceImpl();
//		String accesstoken = weiXinService.getToken();
//		if (accesstoken == null) {
//			logger.warn("accesstoken is null");
//			return;
//		}
//		String url = URL_BASE + "message/custom/send?access_token=MvpQtQoKslKjqezjtOTad7MAZRSOUzDoBCR3GIKrzenxzXPJd5ReBdJ1bV-lfvLkMe_A-lnvP-4KpjeiP5yA12QhSDNb-Xma5DD67UyeCxc";
//		
//		
//		try {
//			String data = WebUtil.readContentFromPost(url, text);
//			System.out.println("data:" + data);
////			
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("WeXinPlatformServlet ："+e);
//		}
//		MsgReceived received = new MsgReceived();
//		ConfigEntity entity = new ConfigEntity();
//		//entity.setReplied("{\"msgtype\":\"image\",\"image\":{\"media_id\":\"${qrcode.media_id}\"}}");
//		entity.setReplied("{\"msgtype\":\"news\",\"list\":[{\"title\":\"你帮元\",\"desc\":\"突然觉得有钱了，送给元红包，摆摆手，别客气…\",\"pic\":\"http://wx.qlogo.cn/mmopen/Iic9WLWEQMg2GyAfv2AbTQbMnxPwu8D7qbgr8DY2UmF9ZpvTOXt4Ogu93fyUTQSTeIGZNk1IOibTJQjCYlcg2fye63S345OciaO/0\",\"url\":\"http://m.shangpin.com\"}]");
//		processCommonTextMsg(received, entity);
		String result = buildNewMsgBody("123", "ceshi", "ceshi123", "www.pic/com", "m.shangpin.com");
		System.out.println("result:" + result);
		try {
			/*byte[] bytes = text.getBytes("UTF-8");
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			XMap xmap = new XMap();
			xmap.register(MsgReceived.class);
			Object[] beans = xmap.loadAll(bais);
//			System.out.println("beans.length = " + beans.length);
			if (beans != null && beans.length == 1) {
				MsgReceived msg = (MsgReceived)beans[0];
//				System.out.println("ToUserName: " + msg.getToUserName());
//				System.out.println("FromUserName: " + msg.getFromUserName());
//				System.out.println("MsgType: " + msg.getMsgType());
//				System.out.println("Content: " + msg.getContent());
				
				updateConfig ();
				final String msgType = msg.getMsgType();
				if (TYPE_MSG_TEXT.equals(msgType)) {
					String data = new WeXinPlatformServlet().processTextMsg(msg);
//					System.out.println(data);
				}
			}*/
//			
////			Pattern pattern = Pattern.compile("^\\s*我要(.+)活动的?[Vv微]码\\s*$");
//			Pattern pattern = Pattern.compile(".*[Ii][Ff][Cc][3三]夜[3三]天.*");
//			Matcher m = pattern.matcher("Ifc3夜三天");
//			boolean b = m.matches();
//			System.out.println(b + "; " + m.groupCount());
//			if (b)
//			for (int i=0;i<=m.groupCount();i++) {
//				System.out.println(m.group(i));
//			}
//			String reply = "V码：${vcode}（使用时字母不区分大小写）";
//			System.out.println(reply.replace("${vcode}", "DSGKSGGSJklsdg1232"));
//			String propvalue = "{keywords:[\"测试\",\"你好\"],type:\"common\",reply:\"手机尚品http://www.shangpin.com/Main/Mobile \\n手机奥莱http://www.aolai.com/Home/Mobile\"}";
////			String propvalue = "{keywords:[\"**关注**\"],type:\"common\",reply:\"呃~~这么久了才来关注我，不过还不晚哦~~5月20日是尚品网三周年生日，那一天将是数万款国际大牌最低至三折的底价出售大狂欢！为感恩回馈，我们将为您奉上一份份额外惊喜！抢V码赚大牌、一元抵百元、下单送520元礼券、邀请好友最高送6150元现金~~~更多优惠攻略请查询http://www.shangpin.com/520\\n此外，特赠送给您50元优惠券满300元订单可使用（充值码为1113336169），即刻在shangpin.com登陆或注册，并在“个人账户”中“我的优惠券”栏目下输入充值码，即可在shangpin.com或aolai.com购物使用啦！赶快开启您的尚品购物之旅吧~~~\"}";
//			ConfigEntity entity = ConfigEntity.parse(propvalue);
//			System.out.println(entity.getReplied());
//			System.out.println((int)('t'));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class AddWeixinUser extends Thread {
		
		private String weixinid;
		private String eventKey;
//		private String ticket;
		private boolean unsubscribe;
		
		public AddWeixinUser (String weixinid, String eventKey, String ticket) {
			this.weixinid = weixinid;
			this.eventKey = eventKey;
//			this.ticket = ticket;
		}
		
		public AddWeixinUser (String weixinid, boolean unsubscribe) {
			this.weixinid = weixinid;
			this.unsubscribe = unsubscribe;
		}
		
		@Override
		public void run () {
			try {
			HqlHelper hqlHelper = new HqlHelper(AccountWeixin.class, "aa");
			hqlHelper.addWhereCondition("aa.weixinId=?", weixinid);
			AccountWeixin account = accountService.getByCondition(hqlHelper);
			Date date = new Date();
			if (account == null) {
				account = new AccountWeixin();
				account.setWeixinId(weixinid);
				if (unsubscribe) {
					account.setUnsubscribeTime(date);
				} else {
					account.setSubscribeTime(date);
					if (eventKey != null && eventKey.startsWith("qrscene")) {
						account.setSceneId(eventKey.substring("qrscene".length() + 1));
					}
				}
				account.setCreateTime(date);
				
				accountService.save(account);
			} else {
				if (unsubscribe) {
					account.setUnsubscribeTime(date);
				} else {
					account.setSubscribeTime(date);
				}
				accountService.update(account);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class RecordEntity {
	String userid;
	String msg;
	long sendtime;
}
