package com.shangpin.wireless.api.weixinbean;


import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;


@XObject(value = "xml")
public class MsgReceived {
//	<xml><ToUserName><![CDATA[gh_abfc1ec3686b]]></ToUserName>
//	<FromUserName><![CDATA[oFHXijhanQsN1n6iZPyepw1fsmZ0]]></FromUserName>
//	<CreateTime>1363686314</CreateTime>
//	<MsgType><![CDATA[text]]></MsgType>
//	<Content><![CDATA[你好吗]]></Content>
//	<MsgId>5856988120732790036</MsgId>
//	</xml>
//	WeXinPlatformServlet doPost
//	len = 453
//	beans.length = 1
//	ToUserName: gh_abfc1ec3686b
//	FromUserName: oFHXijhanQsN1n6iZPyepw1fsmZ0
//	CreateTime: 1367053705
//	MsgType: image
//	MsgId: 5871450954950639285
//	Content: null
//	PicUrl: http://mmsns.qpic.cn/mmsns/sWPiaaWicMzkNxPkAx7sZfFNqQ1ltpV9104BKWDDEcXyGeR9Dcen5kHg/0
	/** 开发者微信号 */
	@XNode("ToUserName")
	private String ToUserName;
	/** 发送方帐号（一个OpenID） */
	@XNode("FromUserName")
	private String FromUserName;
	/** 消息创建时间 （整型） */
	@XNode("CreateTime")
	private long CreateTime;
	/** 消息类型 */
	@XNode("MsgType")
	private String MsgType;	//	text;image;location;link;event

	/**==========  文本消息  ==========*/
	/** 文本消息内容 */
	@XNode("Content")
	private String Content;

	/**==========  图片消息  ==========*/
	/** 图片链接 */
	@XNode("PicUrl")
	private String PicUrl;

	/**==========  多媒体消息  ==========*/
	/** 媒体ID */
	@XNode("MediaId")
	private String MediaId;
	/** 多媒体格式 */
	@XNode("Format")
	private String Format;
	/** 语音识别结果 */
	@XNode("Recognition")
	private String Recognition;
	/** 视频消息缩略图 */
	@XNode("ThumbMediaId")
	private String ThumbMediaId;

	/**==========  地理位置消息  ==========*/
	/** 地理位置纬度 */
	@XNode("Location_X")
	private String Location_X;
	/** 地理位置经度 */
	@XNode("Location_Y")
	private String Location_Y;
	/** 地图缩放大小 */
	@XNode("Scale")
	private String Scale;
	/** 地理位置信息 */
	@XNode("Label")
	private String Label;
	
	/**==========  链接消息  ==========*/
	/** 消息标题 */
	@XNode("Title")
	private String Title;
	/** 消息描述 */
	@XNode("Description")
	private String Description;
	/** 消息链接 */
	@XNode("Url")
	private String Url;
	
	/**==========  事件推送  ==========*/
	/** 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)、LOCATION(上报地理位置事件)、scan(用户已关注时的二维码扫描事件推送) */
	@XNode("Event")
	private String Event;
	/** 事件KEY值，与自定义菜单接口中KEY值对应 */
	@XNode("EventKey")
	private String EventKey;	//	CLICK的菜单值；subscribe的二维码
	/** 二维码关注的区分 */
	@XNode("Ticket")
	private String Ticket;
	/** 地理位置纬度 */
	@XNode("Latitude")
	private String Latitude;
	/** 地理位置经度 */
	@XNode("Longitude")
	private String Longitude;
	/** 地理位置精度 */
	@XNode("Precision")
	private String Precision;

	/**==========  图文消息  ==========*/
	/** 图文消息个数 */
	@XNode("ArticleCount")
	private int ArticleCount;
	/** 品牌列表 */
	@XNode(value = "Articles")
	private XmlArticles Articles;
	
	/** 消息id，64位整型 */
	@XNode("MsgId")
	private String MsgId;
	
	private Object gotoWithReply;
	
	private Object reserved;
	private String online;// 在线客服
	
	private String sendNickName;//发送二维码用户昵称
	private double packetMoney;//红包金额
	private int first;//第一次关注公共账号
	private String focusOpenId;//识别人的openId
	private String focusNickName;//识别人的昵称
	
	
	public String getFocusOpenId() {
		return focusOpenId;
	}

	public void setFocusOpenId(String focusOpenId) {
		this.focusOpenId = focusOpenId;
	}

	public String getFocusNickName() {
		return focusNickName;
	}

	public void setFocusNickName(String focusNickName) {
		this.focusNickName = focusNickName;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public String getSendNickName() {
		return sendNickName;
	}

	public void setSendNickName(String sendNickName) {
		this.sendNickName = sendNickName;
	}

	public double getPacketMoney() {
		return packetMoney;
	}

	public void setPacketMoney(double packetMoney) {
		this.packetMoney = packetMoney;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public XmlArticles getArticles() {
		return Articles;
	}

	public void setArticles(XmlArticles articles) {
		Articles = articles;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public Object getGotoWithReply() {
		return gotoWithReply;
	}

	public void setGotoWithReply(Object gotoWithReply) {
		this.gotoWithReply = gotoWithReply;
	}

	public Object getReserved() {
		return reserved;
	}

	public void setReserved(Object reserved) {
		this.reserved = reserved;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	@Override
	public String toString() {
		return "MsgReceived [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + ", Content=" + Content + ", PicUrl=" + PicUrl
				+ ", MediaId=" + MediaId + ", Format=" + Format
				+ ", Recognition=" + Recognition + ", ThumbMediaId="
				+ ThumbMediaId + ", Location_X=" + Location_X + ", Location_Y="
				+ Location_Y + ", Scale=" + Scale + ", Label=" + Label
				+ ", Title=" + Title + ", Description=" + Description
				+ ", Url=" + Url + ", Event=" + Event + ", EventKey="
				+ EventKey + ", Ticket=" + Ticket + ", Latitude=" + Latitude
				+ ", Longitude=" + Longitude + ", Precision=" + Precision
				+ ", ArticleCount=" + ArticleCount + ", Articles=" + Articles
				+ ", MsgId=" + MsgId + ", gotoWithReply=" + gotoWithReply
				+ ", reserved=" + reserved + ", online=" + online + "]";
	}
	
	
}
